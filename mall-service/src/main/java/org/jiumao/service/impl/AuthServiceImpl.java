package org.jiumao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jiumao.mall.StartUp;
import org.jiumao.mall.OAuth2.OAuth2ClientHandler;
import org.jiumao.remote.ServiceHelper;
import org.jiumao.remote.client.NettyRemotingClient;
import org.jiumao.service.AbstractService;


public class AuthServiceImpl extends AbstractService<NettyRemotingClient> {
    
    public static final AuthServiceImpl INSTANCE = new AuthServiceImpl();

    public AuthServiceImpl() {
    }

    @Override
    public NettyRemotingClient service() {
        return ServiceHelper.clientStart(new OAuth2ClientHandler(), serverAddrs);
    }

    @Override
    public NettyRemotingClient getInstance() {
        // 提供负载均衡服务
        List<String> serverAddrs = new ArrayList<String>(1);
        serverAddrs.add("127.0.0.1:" + StartUp.OAUTH2_PORT);
        setServerList(serverAddrs);
        return service();
    }

}
