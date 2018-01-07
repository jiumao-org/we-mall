package org.jiumao.db.kafka;

import java.util.Collection;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public abstract class AbstractConsumer<K, V> extends AbstractKafkaClient<KafkaConsumer<K, V>> {

    protected Collection<String> topics;

    /**
     * set topics
     */
    public AbstractConsumer() {}

    public abstract void work();

}
