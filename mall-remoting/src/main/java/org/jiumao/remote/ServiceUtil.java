package org.jiumao.remote;

import io.netty.channel.Channel;

import org.jiumao.remote.client.NettyClientConfig;
import org.jiumao.remote.client.NettyRemotingClient;
import org.jiumao.remote.common.NettyDecoder;
import org.jiumao.remote.common.NettyEncoder;
import org.jiumao.remote.common.NettyHandler;
import org.jiumao.remote.server.NettyRemotingServer;
import org.jiumao.remote.server.NettyServerConfig;


public class ServiceUtil {
    private static NettyEncoder encoder = new NettyEncoder();
    private static NettyDecoder decoder = new NettyDecoder();

    private static NettyClientConfig nettyClientConfig = new NettyClientConfig();
    private static NettyServerConfig nettyServerConfig = new NettyServerConfig();


    public static NettyRemotingClient clientStart(NettyHandler handler) {
        NettyRemotingClient client = new NettyRemotingClient(nettyClientConfig, encoder, decoder, handler);
        client.start();
        return client;
    }


    public static NettyRemotingServer serverStart(NettyHandler handler) {
        NettyRemotingServer server = new NettyRemotingServer(nettyServerConfig, encoder, decoder, handler);
        server.start();
        return server;
    }

}
