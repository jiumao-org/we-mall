package org.jiumao.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.jiumao.remote.client.NettyRemotingClient;
import org.jiumao.service.impl.AuthServiceImpl;
import org.jiumao.service.impl.OrderServiceImpl;


public abstract class RPCServices {
 
    public static NettyRemotingClient getAuthService() {
       return AuthServiceImpl.INSTANCE.getInstance();
    }
    
    public static KafkaProducer<Long, byte[]> getOrderService() {
        return OrderServiceImpl.INSTANCE.getInstance();
    }

}
