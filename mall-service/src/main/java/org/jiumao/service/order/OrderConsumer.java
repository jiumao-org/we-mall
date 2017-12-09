package org.jiumao.service.order;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.jiumao.db.kafka.AConsumer;
import org.jiumao.db.kafka.AbstractKafkaClient;

public class OrderConsumer extends AConsumer<Long, byte[]> {

    private static final String ORDER_COMMIT_TOPIC = "OrderCommit";

    public static void main(String[] args) {}


    @Override
    public KafkaConsumer<Long, byte[]> worker() {

        Properties props = AbstractKafkaClient.configBuilder()//
                .put(BOOTSTRAP_SERVERS_CONFIG, bootstrap)//
                .put(GROUP_ID_CONFIG, ORDER_COMMIT_TOPIC)//
                .put(ENABLE_AUTO_COMMIT_CONFIG, true)//
                .put(AUTO_COMMIT_INTERVAL_MS_CONFIG, AUTO_COMMIT_INTERVAL_MS)//
                .put(KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName())//
                .put(VALUE_DESERIALIZER_CLASS_CONFIG, BytesDeserializer.class.getName())//
                .build();

        this.worker = new KafkaConsumer<>(props);
        this.worker.subscribe(Arrays.asList(ORDER_COMMIT_TOPIC));
        System.out.printf("started");
        return this.worker;
    }

    @Override
    public void work() {
        System.out.println("pull");
        ConsumerRecords<Long, byte[]> records = getWorker().poll(100);
        System.out.printf("pulled count = %d %n", records.count());
        log.info("pulled count = %d %n", records.count());
        for (ConsumerRecord<Long, byte[]> record : records) {
            Long orderId = record.key();
            byte[] jsonBytes = record.value();
        }
    }
}
