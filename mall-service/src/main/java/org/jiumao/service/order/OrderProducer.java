package org.jiumao.service.order;

import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.*;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.jiumao.db.kafka.AProducer;
import org.jiumao.db.kafka.AbstractKafkaClient;

public class OrderProducer extends AProducer<Long, byte[]> {

    public OrderProducer() {
        super(OrderConstants.ORDER_COMMITED_TOPIC);
    }
    
    public OrderProducer(String topic) {
        super(topic);
    }
    

    @Override
    public KafkaProducer<Long, byte[]> worker() {

        Properties props = AbstractKafkaClient.configBuilder()//
                .put(BOOTSTRAP_SERVERS_CONFIG, bootstrap)//
                .put(ACKS_CONFIG, "all").put(RETRIES_CONFIG, 3)//
                .put(BATCH_SIZE_CONFIG, 16384)//
                .put(LINGER_MS_CONFIG, 1)//
                .put(BUFFER_MEMORY_CONFIG, 33554432)//
                .put(KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName())//
                .put(VALUE_DESERIALIZER_CLASS_CONFIG, BytesDeserializer.class.getName())//
                .build();

        return this.worker = new KafkaProducer<>(props);
    }


    @Override
    public void send(Long k, byte[] v) {
        worker.send(new ProducerRecord<Long, byte[]>(topic,k,v ));
    }
}
