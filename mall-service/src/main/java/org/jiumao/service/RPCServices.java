package org.jiumao.service;

import org.jiumao.remote.client.NettyRemotingClient;
import org.jiumao.service.impl.AuthServiceImpl;


public abstract class RPCServices {
 
    public static NettyRemotingClient getAuthService() {
       return AuthServiceImpl.INSTANCE.getInstance();
    }

}
