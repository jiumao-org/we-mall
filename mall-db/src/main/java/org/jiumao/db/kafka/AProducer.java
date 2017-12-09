package org.jiumao.db.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;

public abstract class AProducer<K, V> extends AbstractKafkaClient<KafkaProducer<K, V>> {
    
    protected String topic;
    public AProducer(String topic) {
        this.topic = topic;
    }
    public abstract void send(K k,V v);
}
