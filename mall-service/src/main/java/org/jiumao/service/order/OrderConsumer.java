package org.jiumao.service.order;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.jiumao.db.kafka.AConsumer;
import org.jiumao.db.kafka.AbstractKafkaClient;
import org.jiumao.wechatMall.entity.Order;

public class OrderConsumer extends AConsumer<Long, byte[]> {

    public static void main(String[] args) {
        new OrderConsumer().work();
    }

    public OrderConsumer() {
        topics = Arrays.asList(OrderConstants.ORDER_COMMITED_TOPIC);
    }

    @Override
    public KafkaConsumer<Long, byte[]> worker() {

        Properties props = AbstractKafkaClient.configBuilder()//
                .put(BOOTSTRAP_SERVERS_CONFIG, bootstrap)//
                .put(GROUP_ID_CONFIG, OrderConstants.ORDER_GROUP)//
                .put(ENABLE_AUTO_COMMIT_CONFIG, true)//
                .put(AUTO_COMMIT_INTERVAL_MS_CONFIG, AUTO_COMMIT_INTERVAL_MS)//
                .put(KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName())//
                .put(VALUE_DESERIALIZER_CLASS_CONFIG, BytesDeserializer.class.getName())//
                .build();

        this.worker = new KafkaConsumer<>(props);
        this.worker.subscribe(topics);
        System.out.printf("started");
        return this.worker;
    }

    @Override
    public void work() {
        System.out.println("pull");
        ConsumerRecords<Long, byte[]> records = getWorker().poll(100);
        System.out.printf("pulled count = %d %n", records.count());
        log.info("pulled count = %d %n", records.count());
        OrderProducer handledProducer = new OrderProducer(OrderConstants.ORDER_HANDLED_TOPIC);
        KafkaProducer<Long, byte[]> p = handledProducer.getWorker();
        Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>(1);
        for (ConsumerRecord<Long, byte[]> record : records) {

            try {
                p.initTransactions();
                p.beginTransaction();

                commitOrder(record);

                offsets.clear();
                offsets.put(new TopicPartition(OrderConstants.ORDER_COMMITED_TOPIC, record.partition()),
                        new OffsetAndMetadata(record.offset()));
                p.sendOffsetsToTransaction(offsets, OrderConstants.ORDER_GROUP);
                p.commitTransaction();
            } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
                // 数据发送或者Offset发送出现异常时，终止事务
                p.abortTransaction();
            }
        }
    }

    private void commitOrder(ConsumerRecord<Long, byte[]> record) {
        Long orderId = record.key();
        byte[] jsonBytes = record.value();
        Order order = Order.decode(jsonBytes, Order.class);
        if (order != null && order.getId() == orderId) {
            // FIXME 订单事务处理
            
        }
    }
}
