package org.jiumao.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import org.jiumao.mall.StartUp;
import org.jiumao.mall.OAuth2.OAuth2ClientHandler;
import org.jiumao.remote.ServiceHelper;
import org.jiumao.remote.client.NettyRemotingClient;
import org.jiumao.service.AbstractService;

@Singleton
public class AuthServiceImpl extends AbstractService<NettyRemotingClient> {

    public static final AuthServiceImpl INSTANCE = new AuthServiceImpl();

    private AuthServiceImpl() {
        // 提供负载均衡服务
        List<String> serverAddrs = new ArrayList<String>(1);
        serverAddrs.add("127.0.0.1:" + StartUp.OAUTH2_PORT);
        setServerList(serverAddrs);
        Singleton = service();
    }

    @Override
    public NettyRemotingClient service() {
        return ServiceHelper.clientStart(new OAuth2ClientHandler(), serverAddrs);
    }

}
