package org.jiumao.mall.appkey;

import java.util.HashMap;

import org.jiumao.common.utils.JsonUtil;
import org.jiumao.remote.common.NettyHandler;
import org.jiumao.remote.service.RemotingCommand;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class AppkeyServerHandler extends NettyHandler {

    private static final AppkeyService SERVICE = new AppkeyService();

    @Override
    public void processMessageReceived(ChannelHandlerContext ctx, RemotingCommand msg) {
        AppkeyDetail detail = JsonUtil.decode(msg.getBody(), AppkeyDetail.class);
        
        HashMap<String, String> map = msg.getExtFields();
        String key = map.get("action");
        switch (key) {
            case "access":
                boolean isAccess = SERVICE.access(detail.get_id(), detail.getPath());
                map.put("isAccess", isAccess ? "true" : "false");
                break;

            case "insert":
                int appkey = SERVICE.insert(detail);
                map.put("appkey", appkey+"");
                break;
            default:
                break;
        }
        ByteBuf buf = msg.encode();
        ctx.writeAndFlush(buf);
        buf.release();
    }

}
