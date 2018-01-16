package org.jiumao.db.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;

public abstract class AbstractProducer<K, V> extends AbstractKafkaClient<KafkaProducer<K, V>> {
    
    protected String topic;
    public AbstractProducer(String topic) {
        this.topic = topic;
    }
    public abstract void send(K k,V v);
}
