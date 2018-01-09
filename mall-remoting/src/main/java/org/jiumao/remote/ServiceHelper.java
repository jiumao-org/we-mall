package org.jiumao.remote;

import java.util.List;

import org.jiumao.remote.client.NettyClientConfig;
import org.jiumao.remote.client.NettyRemotingClient;
import org.jiumao.remote.common.NettyDecoder;
import org.jiumao.remote.common.NettyEncoder;
import org.jiumao.remote.common.NettyHandler;
import org.jiumao.remote.common.RemotingHelper;
import org.jiumao.remote.exception.RemotingConnectException;
import org.jiumao.remote.exception.RemotingSendRequestException;
import org.jiumao.remote.exception.RemotingTimeoutException;
import org.jiumao.remote.exception.RemotingTooMuchRequestException;
import org.jiumao.remote.server.NettyRemotingServer;
import org.jiumao.remote.server.NettyServerConfig;
import org.jiumao.remote.service.InvokeCallback;
import org.jiumao.remote.service.RPCHook;
import org.jiumao.remote.service.RemotingCommand;


public class ServiceHelper {
    private static NettyEncoder encoder = new NettyEncoder();
    private static NettyDecoder decoder = new NettyDecoder();

    private static NettyClientConfig nettyClientConfig = new NettyClientConfig();
    private static NettyServerConfig nettyServerConfig = new NettyServerConfig();
    private static RPCHook rpcHook = new RPCHook() {
        
        @Override
        public void doBeforeRequest(String remoteAddr, RemotingCommand request) {
        }
        
        
        @Override
        public void doAfterResponse(RemotingCommand request, RemotingCommand response) {
        }
    };
    
    public static RemotingCommand shortSocket(RemotingCommand request, String addr) throws RemotingTimeoutException, RemotingSendRequestException, RemotingConnectException, InterruptedException {
       return RemotingHelper.invokeSync(addr, request, 500);
    }

    public static NettyRemotingClient clientStart(NettyHandler handler,List<String> serverAddrs) {
        NettyRemotingClient client = new NettyRemotingClient(nettyClientConfig, encoder, decoder, handler);
        client.updateServerAddressList(serverAddrs);
        client.registerRPCHook(rpcHook);
        client.start();
        return client;
    }


    public static NettyRemotingServer serverStart(NettyHandler handler, int listenPort) {
        nettyServerConfig.setListenPort(listenPort);
        NettyRemotingServer server = new NettyRemotingServer(nettyServerConfig, encoder, decoder, handler);
        server.registerRPCHook(rpcHook);
        server.start();
        return server;
    }
    
    //=====================================Example=======================================
    public static void client(String[] args) throws RemotingConnectException, RemotingTooMuchRequestException, RemotingTimeoutException, RemotingSendRequestException, InterruptedException {
        NettyHandler handler = null;
        List<String> serverAddrs = null;
        NettyRemotingClient authRPC =  clientStart(handler, serverAddrs);
        RemotingCommand request = RemotingCommand.createRequestCommand();
        InvokeCallback invokeCallback = null;
        authRPC.invokeAsync(request, invokeCallback);
        authRPC.invokeOneway(request);
        RemotingCommand responce = authRPC.invokeSync(request);
        
    }
    
    public static void server(String[] args) {
        
    }
    
    

}
