package org.jiumao.mall;

import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.lang3.StringUtils;
import org.jiumao.common.AsynHttp.AsynHttpClient;
import org.jiumao.common.AsynHttp.AsynHttps;
import org.jiumao.common.utils.JsonSerializable;
import org.jiumao.common.utils.JsonUtil;
import org.jiumao.mall.OAuth2.OAuth2ClientHandler;
import org.jiumao.mall.OAuth2.OAuth2Provider;
import org.jiumao.mall.OAuth2.code.ReturnCode;
import org.jiumao.mall.OAuth2.domain.Authorize;
import org.jiumao.mall.OAuth2.domain.ResponseType;
import org.jiumao.mall.OAuth2.implicit.ImplicitToken;
import org.jiumao.remote.ServiceHelper;
import org.jiumao.remote.common.NettyHandler;
import org.jiumao.remote.service.RemotingCommand;


/**
 * 启动auth服务
 * 
 * @author ppf@jiumao.org
 * @date 2017年11月28日
 */
public class StartUp {

    public static final int OAUTH2_PORT = 9103;
    public static final int APPKEY_PORT = 9104;


    public static void main(String[] args) {
        startAppkey();
        startOAuth2();
    }


    public static void startOAuth2() {
           
        ServiceHelper.serverStart(new OAuth2ClientHandler(), OAUTH2_PORT);
    }


    public static void startAppkey() {
        NettyHandler handler = null;
        ServiceHelper.serverStart(handler, APPKEY_PORT);
    }

}
