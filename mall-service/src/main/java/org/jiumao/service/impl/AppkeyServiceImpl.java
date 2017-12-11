package org.jiumao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jiumao.mall.StartUp;
import org.jiumao.mall.appkey.AppkeyClientHandler;
import org.jiumao.remote.ServiceHelper;
import org.jiumao.remote.client.NettyRemotingClient;
import org.jiumao.service.AbstractService;

public class AppkeyServiceImpl extends AbstractService<NettyRemotingClient> {
    
    
    public static final AppkeyServiceImpl INSTANCE = new AppkeyServiceImpl();

    private AppkeyServiceImpl() {
        // 提供负载均衡服务
        List<String> serverAddrs = new ArrayList<String>(1);
        serverAddrs.add("127.0.0.1:"+StartUp.APPKEY_PORT);
        setServerList(serverAddrs);
        Singleton = service();
    }

    @Override
    public NettyRemotingClient service() {
        return ServiceHelper.clientStart(new AppkeyClientHandler(), serverAddrs);
    }


}
