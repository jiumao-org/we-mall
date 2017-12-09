package org.jiumao.service.impl;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.jiumao.service.AbstractService;
import org.jiumao.service.order.OrderProducer;

public class OrderServiceImpl extends AbstractService<KafkaProducer<Long, byte[]>>{
    
    public static final OrderServiceImpl INSTANCE = new OrderServiceImpl();
    private OrderServiceImpl() {
        Singleton = service();
    }

    @Override
    public KafkaProducer<Long, byte[]> service() {
        return new OrderProducer().getWorker() ;
    }

}
