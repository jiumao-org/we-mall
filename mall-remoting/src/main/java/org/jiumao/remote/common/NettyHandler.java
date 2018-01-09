package org.jiumao.remote.common;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.jiumao.remote.service.RemotingCommand;


public abstract class NettyHandler extends SimpleChannelInboundHandler<RemotingCommand> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RemotingCommand msg) throws Exception {
         processMessageReceived(ctx, msg);

    }

    public abstract void processMessageReceived(ChannelHandlerContext ctx, RemotingCommand msg);
}