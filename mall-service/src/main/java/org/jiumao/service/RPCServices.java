package org.jiumao.service;

import org.jiumao.remote.client.NettyRemotingClient;
import org.jiumao.service.impl.AppkeyServiceImpl;
import org.jiumao.service.impl.AuthServiceImpl;
import org.jiumao.service.impl.OrderServiceImpl;
import org.jiumao.service.order.OrderProducer;


public abstract class RPCServices {
 
    public static NettyRemotingClient getAuthService() {
       return AuthServiceImpl.INSTANCE.getInstance();
    }
    
    public static OrderProducer getOrderService() {
        return OrderServiceImpl.INSTANCE.getInstance();
    }
    
    public static NettyRemotingClient getAppkeyService() {
        return AppkeyServiceImpl.INSTANCE.getInstance();
    }

}
