package org.jiumao.remote.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;

import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.jiumao.common.constants.LoggerName;
import org.jiumao.remote.RemotingHelper;
import org.jiumao.remote.common.NettyDecoder;
import org.jiumao.remote.common.NettyEncoder;
import org.jiumao.remote.common.NettyHandler;
import org.jiumao.remote.common.NettyRemotingAbstract;
import org.jiumao.remote.common.NettySystemConfig;
import org.jiumao.remote.common.RemotingUtil;
import org.jiumao.remote.exception.RemotingConnectException;
import org.jiumao.remote.exception.RemotingSendRequestException;
import org.jiumao.remote.exception.RemotingTimeoutException;
import org.jiumao.remote.exception.RemotingTooMuchRequestException;
import org.jiumao.remote.service.InvokeCallback;
import org.jiumao.remote.service.RPCHook;
import org.jiumao.remote.service.RemotingCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 客户端实现
 */
public class NettyRemotingClient extends NettyRemotingAbstract implements RemotingClient {
    private static final Logger log = LoggerFactory.getLogger(LoggerName.Server);

    // 服务相关
    private RPCHook rpcHook;
    private final NettyClientConfig nettyClientConfig;
    private final Bootstrap bootstrap = new Bootstrap();
    private final EventLoopGroup eventLoopGroupWorker;
    private DefaultEventExecutorGroup defaultEventExecutorGroup;
    private final Timer timer = new Timer("ClientHouseKeepingService", true);

    // 业务相关
    private final NettyEncoder encoder;
    private final NettyDecoder decoder;
    private NettyHandler handler;

    // 处理相关
    private static final long LockTimeoutMillis = 3000;
    private final Lock lockChannelTables = new ReentrantLock();
    private final Lock lockServerChannel = new ReentrantLock();
    private final AtomicInteger serverIndex = new AtomicInteger(initValueIndex());
    private final AtomicReference<String> serverChoosed = new AtomicReference<String>();
    private final AtomicReference<List<String>> serverList = new AtomicReference<List<String>>();
    private final ConcurrentHashMap<String /* addr */, ChannelWrapper> channelTables =
            new ConcurrentHashMap<String, ChannelWrapper>();


    public NettyRemotingClient(NettyClientConfig nettyClientConfig, NettyEncoder encoder,
            NettyDecoder decoder, NettyHandler handler) {
        super(nettyClientConfig.getClientOnewaySemaphoreValue(), nettyClientConfig
            .getClientAsyncSemaphoreValue());
        this.nettyClientConfig = nettyClientConfig;
        this.encoder = encoder;
        this.decoder = decoder;
        this.handler = handler;

        // 初始化线程
        int publicThreadNums = nettyClientConfig.getClientCallbackExecutorThreads();
        if (publicThreadNums <= 0) {
            publicThreadNums = 4;
        }

        this.eventLoopGroupWorker = new NioEventLoopGroup(1, new ThreadFactory() {
            private AtomicInteger threadIndex = new AtomicInteger(0);


            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, String.format("NettyClientSelector_%d",
                    this.threadIndex.incrementAndGet()));
            }
        });
    }


    @Override
    public void start() {
        this.defaultEventExecutorGroup = new DefaultEventExecutorGroup(//
            nettyClientConfig.getClientWorkerThreads(), //
            new ThreadFactory() {

                private AtomicInteger threadIndex = new AtomicInteger(0);


                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "NettyClientWorkerThread_" + this.threadIndex.incrementAndGet());
                }
            });

        this.bootstrap.group(this.eventLoopGroupWorker).channel(NioSocketChannel.class)
            .option(ChannelOption.TCP_NODELAY, true)
            .option(ChannelOption.SO_SNDBUF, NettySystemConfig.SocketSndbufSize)
            .option(ChannelOption.SO_RCVBUF, NettySystemConfig.SocketRcvbufSize)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(defaultEventExecutorGroup, //
                        encoder, //
                        decoder, //
                        new IdleStateHandler(0, 0, nettyClientConfig.getClientChannelMaxIdleTimeSeconds()),//
                        new NettyConnetManageHandler(), //
                        handler);
                }
            });
        // 每隔1秒扫描下异步调用超时情况
        this.timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                try {
                    NettyRemotingClient.this.scanResponseTable();
                }
                catch (Exception e) {
                    log.error("scanResponseTable exception", e);
                }
            }
        }, 1000 * 3, 1000);

    }


    @Override
    public void shutdown() {
        try {
            this.timer.cancel();

            for (ChannelWrapper cw : this.channelTables.values()) {
                this.closeChannel(null, cw.getChannel());
            }

            this.channelTables.clear();

            this.eventLoopGroupWorker.shutdownGracefully();

            if (this.defaultEventExecutorGroup != null) {
                this.defaultEventExecutorGroup.shutdownGracefully();
            }
        }
        catch (Exception e) {
            log.error("NettyRemotingClient shutdown exception, ", e);
        }

    }


    public void updateServerAddressList(List<String> addrs) {
        List<String> old = this.serverList.get();
        boolean update = false;

        if (!addrs.isEmpty()) {
            if (null == old) {
                update = true;
            }
            else if (addrs.size() != old.size()) {
                update = true;
            }
            else {
                for (int i = 0; i < addrs.size() && !update; i++) {
                    if (!old.contains(addrs.get(i))) {
                        update = true;
                    }
                }
            }

            if (update) {
                Collections.shuffle(addrs);
                this.serverList.set(addrs);
            }
        }
    }


    public List<String> getServerAddrList() {
        return serverList.get();
    }


    public Channel getAndCreateChannel(final String addr) throws InterruptedException {
        if (null == addr)
            return getAndCreateServerChannel();

        ChannelWrapper cw = this.channelTables.get(addr);
        if (cw != null && cw.isOK()) {
            return cw.getChannel();
        }

        return this.createChannel(addr);
    }


    private Channel getAndCreateServerChannel() throws InterruptedException {
        String addr = this.serverChoosed.get();
        if (addr != null) {
            ChannelWrapper cw = this.channelTables.get(addr);
            if (cw != null && cw.isOK()) {
                return cw.getChannel();
            }
        }

        final List<String> addrList = this.serverList.get();
        // 加锁，尝试创建连接
        if (this.lockServerChannel.tryLock(LockTimeoutMillis, TimeUnit.MILLISECONDS)) {
            try {
                addr = this.serverChoosed.get();
                if (addr != null) {
                    ChannelWrapper cw = this.channelTables.get(addr);
                    if (cw != null && cw.isOK()) {
                        return cw.getChannel();
                    }
                }

                if (addrList != null && !addrList.isEmpty()) {
                    for (int i = 0; i < addrList.size(); i++) {
                        int index = this.serverIndex.incrementAndGet();
                        index = Math.abs(index);
                        index = index % addrList.size();
                        String newAddr = addrList.get(index);

                        this.serverChoosed.set(newAddr);
                        Channel channelNew = this.createChannel(newAddr);
                        if (channelNew != null)
                            return channelNew;
                    }
                }
            }
            catch (Exception e) {
                log.error("getAndCreateServerChannel: create name server channel exception", e);
            }
            finally {
                this.lockServerChannel.unlock();
            }
        }
        else {
            log.warn("getAndCreateServerChannel: try to lock name server, but timeout, {}ms",
                LockTimeoutMillis);
        }

        return null;
    }


    /**
     * 添加服务端地址
     */
    public void addServer(String addr) throws InterruptedException {
        Objects.requireNonNull(addr, "添加的服务地址不能为空");
        List<String> servers = serverList.get();
        if (!servers.contains(addr)) {
            serverList.set(servers);
            if (this.lockChannelTables.tryLock(LockTimeoutMillis, TimeUnit.MILLISECONDS)) {
                servers.add(addr);
                ChannelFuture channelFuture =
                        this.bootstrap.connect(RemotingHelper.string2SocketAddress(addr));
                log.info("createChannel: begin to connect remote host[{}] asynchronously", addr);
                this.channelTables.put(addr, new ChannelWrapper(channelFuture));
            }
            serverChoosed.set(addr);
        }
    }


    private Channel createChannel(final String addr) throws InterruptedException {
        ChannelWrapper cw = this.channelTables.get(addr);
        if (cw != null && cw.isOK()) {
            return cw.getChannel();
        }

        // 进入临界区后，不能有阻塞操作，网络连接采用异步方式
        if (this.lockChannelTables.tryLock(LockTimeoutMillis, TimeUnit.MILLISECONDS)) {
            try {
                boolean createNewConnection = false;
                cw = this.channelTables.get(addr);
                if (cw != null) {
                    // channel正常
                    if (cw.isOK()) {
                        return cw.getChannel();
                    }
                    // 正在连接，退出锁等待
                    else if (!cw.getChannelFuture().isDone()) {
                        createNewConnection = false;
                    }
                    // 说明连接不成功
                    else {
                        this.channelTables.remove(addr);
                        createNewConnection = true;
                    }
                }
                // ChannelWrapper不存在
                else {
                    createNewConnection = true;
                }

                if (createNewConnection) {
                    ChannelFuture channelFuture =
                            this.bootstrap.connect(RemotingHelper.string2SocketAddress(addr));
                    log.info("createChannel: begin to connect remote host[{}] asynchronously", addr);
                    cw = new ChannelWrapper(channelFuture);
                    this.channelTables.put(addr, cw);
                }
            }
            catch (Exception e) {
                log.error("createChannel: create channel exception", e);
            }
            finally {
                this.lockChannelTables.unlock();
            }
        }
        else {
            log.warn("createChannel: try to lock channel table, but timeout, {}ms", LockTimeoutMillis);
        }

        if (cw != null) {
            ChannelFuture channelFuture = cw.getChannelFuture();
            if (channelFuture.awaitUninterruptibly(this.nettyClientConfig.getConnectTimeoutMillis())) {
                if (cw.isOK()) {
                    log.info("createChannel: connect remote host[{}] success, {}", addr,
                        channelFuture.toString());
                    return cw.getChannel();
                }
                else {
                    log.warn(
                        "createChannel: connect remote host[" + addr + "] failed, "
                                + channelFuture.toString(), channelFuture.cause());
                }
            }
            else {
                log.warn("createChannel: connect remote host[{}] timeout {}ms, {}", addr,
                    this.nettyClientConfig.getConnectTimeoutMillis(), channelFuture.toString());
            }
        }

        return null;
    }


    public void closeChannel(final String addr, final Channel channel) {
        if (null == channel)
            return;

        final String addrRemote = null == addr ? RemotingHelper.parseChannelRemoteAddr(channel) : addr;

        try {
            if (this.lockChannelTables.tryLock(LockTimeoutMillis, TimeUnit.MILLISECONDS)) {
                try {
                    boolean removeItemFromTable = true;
                    final ChannelWrapper prevCW = this.channelTables.get(addrRemote);

                    log.info("closeChannel: begin close the channel[{}] Found: {}", addrRemote,
                        (prevCW != null));

                    if (null == prevCW) {
                        log.info(
                            "closeChannel: the channel[{}] has been removed from the channel table before",
                            addrRemote);
                        removeItemFromTable = false;
                    }
                    else if (prevCW.getChannel() != channel) {
                        log.info(
                            "closeChannel: the channel[{}] has been closed before, and has been created again, nothing to do.",
                            addrRemote);
                        removeItemFromTable = false;
                    }

                    if (removeItemFromTable) {
                        this.channelTables.remove(addrRemote);
                        log.info("closeChannel: the channel[{}] was removed from channel table", addrRemote);
                    }

                    RemotingUtil.closeChannel(channel);
                }
                catch (Exception e) {
                    log.error("closeChannel: close the channel exception", e);
                }
                finally {
                    this.lockChannelTables.unlock();
                }
            }
            else {
                log.warn("closeChannel: try to lock channel table, but timeout, {}ms", LockTimeoutMillis);
            }
        }
        catch (InterruptedException e) {
            log.error("closeChannel exception", e);
        }
    }


    public void closeChannel(final Channel channel) {
        if (null == channel)
            return;

        try {
            if (this.lockChannelTables.tryLock(LockTimeoutMillis, TimeUnit.MILLISECONDS)) {
                try {
                    boolean removeItemFromTable = true;
                    ChannelWrapper prevCW = null;
                    String addrRemote = null;
                    for (String key : channelTables.keySet()) {
                        ChannelWrapper prev = this.channelTables.get(key);
                        if (prev.getChannel() != null) {
                            if (prev.getChannel() == channel) {
                                prevCW = prev;
                                addrRemote = key;
                                break;
                            }
                        }
                    }

                    if (null == prevCW) {
                        log.info(
                            "eventCloseChannel: the channel[{}] has been removed from the channel table before",
                            addrRemote);
                        removeItemFromTable = false;
                    }

                    if (removeItemFromTable) {
                        this.channelTables.remove(addrRemote);
                        log.info("closeChannel: the channel[{}] was removed from channel table", addrRemote);
                        RemotingUtil.closeChannel(channel);
                    }
                }
                catch (Exception e) {
                    log.error("closeChannel: close the channel exception", e);
                }
                finally {
                    this.lockChannelTables.unlock();
                }
            }
            else {
                log.warn("closeChannel: try to lock channel table, but timeout, {}ms", LockTimeoutMillis);
            }
        }
        catch (InterruptedException e) {
            log.error("closeChannel exception", e);
        }
    }


    @Override
    public RemotingCommand invokeSync(String addr, final RemotingCommand request, long timeoutMillis)
            throws InterruptedException, RemotingConnectException, RemotingSendRequestException,
            RemotingTimeoutException {
        final Channel channel = this.getAndCreateChannel(addr);
        if (channel != null && channel.isActive()) {
            if (this.rpcHook != null) {
                this.rpcHook.doBeforeRequest(addr, request);
            }
            RemotingCommand response = this.invokeSyncImpl(channel, request, timeoutMillis);
            if (this.rpcHook != null) {
                this.rpcHook.doAfterResponse(request, response);
            }
            return response;
        }
        else {
            this.closeChannel(addr, channel);
            throw new RemotingConnectException(addr);
        }
    }


    @Override
    public void invokeAsync(String addr, RemotingCommand request, long timeoutMillis,
            InvokeCallback invokeCallback) throws InterruptedException, RemotingConnectException,
            RemotingTooMuchRequestException, RemotingTimeoutException, RemotingSendRequestException {
        final Channel channel = this.getAndCreateChannel(addr);
        if (channel != null && channel.isActive()) {
            if (this.rpcHook != null) {
                this.rpcHook.doBeforeRequest(addr, request);
            }
            this.invokeAsyncImpl(channel, request, timeoutMillis, invokeCallback);
        }
        else {
            this.closeChannel(addr, channel);
            throw new RemotingConnectException(addr);
        }
    }


    @Override
    public void invokeOneway(String addr, RemotingCommand request, long timeoutMillis)
            throws InterruptedException, RemotingConnectException, RemotingTooMuchRequestException,
            RemotingTimeoutException, RemotingSendRequestException {
        final Channel channel = this.getAndCreateChannel(addr);
        if (channel != null && channel.isActive()) {
            try {
                if (this.rpcHook != null) {
                    this.rpcHook.doBeforeRequest(addr, request);
                }
                this.invokeOnewayImpl(channel, request, timeoutMillis);
            }
            catch (RemotingSendRequestException e) {
                log.warn("invokeOneway: send request exception, so close the channel[{}]", addr);
                this.closeChannel(addr, channel);
                throw e;
            }
        }
        else {
            this.closeChannel(addr, channel);
            throw new RemotingConnectException(addr);
        }
    }


    private static int initValueIndex() {
        Random r = new Random();

        return Math.abs(r.nextInt() % 999) % 999;
    }


    @Override
    public void registerRPCHook(RPCHook rpcHook) {
        this.rpcHook = rpcHook;
    }


    @Override
    public RPCHook getRPCHook() {
        return this.rpcHook;
    }

    class ChannelWrapper {
        private final ChannelFuture channelFuture;


        public ChannelWrapper(ChannelFuture channelFuture) {
            this.channelFuture = channelFuture;
        }


        public boolean isOK() {
            return (this.channelFuture.channel() != null && this.channelFuture.channel().isActive());
        }


        private Channel getChannel() {
            return this.channelFuture.channel();
        }


        public ChannelFuture getChannelFuture() {
            return channelFuture;
        }
    }

    class NettyConnetManageHandler extends ChannelDuplexHandler {
        @Override
        public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress,
                SocketAddress localAddress, ChannelPromise promise) throws Exception {
            final String local = localAddress == null ? "UNKNOW" : localAddress.toString();
            final String remote = remoteAddress == null ? "UNKNOW" : remoteAddress.toString();
            log.info("NETTY CLIENT PIPELINE: CONNECT  {} => {}", local, remote);
            super.connect(ctx, remoteAddress, localAddress, promise);
        }


        @Override
        public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
            final String remoteAddress = RemotingHelper.parseChannelRemoteAddr(ctx.channel());
            log.info("NETTY CLIENT PIPELINE: DISCONNECT {}", remoteAddress);
            closeChannel(ctx.channel());
            super.disconnect(ctx, promise);
        }


        @Override
        public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
            final String remoteAddress = RemotingHelper.parseChannelRemoteAddr(ctx.channel());
            log.info("NETTY CLIENT PIPELINE: CLOSE {}", remoteAddress);
            closeChannel(ctx.channel());
            super.close(ctx, promise);
        }


        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            final String remoteAddress = RemotingHelper.parseChannelRemoteAddr(ctx.channel());
            log.warn("NETTY CLIENT PIPELINE: exceptionCaught {}", remoteAddress);
            log.warn("NETTY CLIENT PIPELINE: exceptionCaught exception.", cause);
            closeChannel(ctx.channel());
        }


        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof IdleStateEvent) {
                IdleStateEvent evnet = (IdleStateEvent) evt;
                if (evnet.state().equals(IdleState.ALL_IDLE)) {
                    final String remoteAddress = RemotingHelper.parseChannelRemoteAddr(ctx.channel());
                    log.warn("NETTY CLIENT PIPELINE: IDLE exception [{}]", remoteAddress);
                    closeChannel(ctx.channel());
                }
            }

            ctx.fireUserEventTriggered(evt);
        }
    }

    @Override
    public RemotingCommand invokeSync(RemotingCommand request) throws InterruptedException,
            RemotingConnectException, RemotingSendRequestException, RemotingTimeoutException {
        return this.invokeSync(this.serverChoosed.get(), request, TimeoutMillis);
    }


    @Override
    public void invokeAsync(RemotingCommand request, InvokeCallback invokeCallback)
            throws InterruptedException, RemotingConnectException, RemotingTooMuchRequestException,
            RemotingTimeoutException, RemotingSendRequestException {
        this.invokeAsync(this.serverChoosed.get(), request, TimeoutMillis, invokeCallback);
    }


    @Override
    public void invokeOneway(RemotingCommand request) throws InterruptedException, RemotingConnectException,
            RemotingTooMuchRequestException, RemotingTimeoutException, RemotingSendRequestException {
        this.invokeOneway(this.serverChoosed.get(), request, TimeoutMillis);
    }

}
