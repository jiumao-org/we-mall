package org.jiumao.service.order;

import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.jiumao.common.utils.MallConstants;
import org.jiumao.db.kafka.AbstractProducer;
import org.jiumao.db.kafka.AbstractKafkaClient;

public class OrderProducer extends AbstractProducer<Long, byte[]> {

    public OrderProducer() {
        super(MallConstants.ORDER_COMMITED_TOPIC);
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


    Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>(1);
    @Override
    public void send(Long k, byte[] v) {
        KafkaProducer<Long, byte[]> p = getWorker();
        p.initTransactions();
        p.beginTransaction();
        Future<RecordMetadata> res = worker.send(new ProducerRecord<Long, byte[]>(topic, k, v));
        RecordMetadata record;
        try {
            record = res.get();
            offsets.clear();
            offsets.put(new TopicPartition(topic, record.partition()), new OffsetAndMetadata(record.offset()));
            p.sendOffsetsToTransaction(offsets, MallConstants.ORDER_GROUP);
            p.commitTransaction();
        } catch (InterruptedException | ExecutionException e) {
            p.abortTransaction();
        }
    }
}
