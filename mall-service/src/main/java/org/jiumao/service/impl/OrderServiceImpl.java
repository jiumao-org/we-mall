package org.jiumao.service.impl;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.jiumao.common.constants.LoggerName;
import org.jiumao.service.AbstractService;
import org.jiumao.service.order.OrderProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderServiceImpl extends AbstractService<KafkaProducer<Long, byte[]>>{
    
    protected static final Logger log = LoggerFactory
            .getLogger(LoggerName.Store);
    
    public static final OrderServiceImpl INSTANCE = new OrderServiceImpl();
    private OrderServiceImpl() {
        Singleton = service();
    }

    @Override
    public KafkaProducer<Long, byte[]> service() {
        return new OrderProducer().getWorker() ;
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        try {
            Singleton.close();
            Singleton.initTransactions();
            Singleton.beginTransaction();
        }catch (Exception e) {
            log.error("topic close",e.getCause());
        }
    }

}
