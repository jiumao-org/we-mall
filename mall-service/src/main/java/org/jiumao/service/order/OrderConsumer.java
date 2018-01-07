package org.jiumao.service.order;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.bson.Document;
import org.jiumao.api.pay.OrderStatus;
import org.jiumao.api.pay.PayProvider;
import org.jiumao.common.utils.MallConstants;
import org.jiumao.db.MongoUtil;
import org.jiumao.db.kafka.AbstractConsumer;
import org.jiumao.db.kafka.AbstractKafkaClient;
import org.jiumao.db.mongo.MongoCRUD;
import org.jiumao.db.mongo.MongoOperators;
import org.jiumao.wechatMall.entity.Order;
import org.jiumao.wechatMall.entity.OrderGoods;


public class OrderConsumer extends AbstractConsumer<Long, byte[]> {

    public static void main(String[] args) {
        new OrderConsumer().work();
    }

    public OrderConsumer() {
        topics = Arrays.asList(MallConstants.ORDER_COMMITED_TOPIC);
    }

    @Override
    public KafkaConsumer<Long, byte[]> worker() {

        Properties props = AbstractKafkaClient.configBuilder()//
                .put(BOOTSTRAP_SERVERS_CONFIG, bootstrap)//
                .put(GROUP_ID_CONFIG, MallConstants.ORDER_GROUP)//
                .put(ENABLE_AUTO_COMMIT_CONFIG, true)//
                .put(MAX_POLL_RECORDS_CONFIG, "100")//
                .put(SESSION_TIMEOUT_MS_CONFIG, "30000")//
                .put(FETCH_MIN_BYTES_CONFIG, 1)//
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
        for (ConsumerRecord<Long, byte[]> record : records) {
            orderHandle(record);
        }
    }


    private void orderHandle(ConsumerRecord<Long, byte[]> record) {
        Long orderId = record.key();
        byte[] jsonBytes = record.value();
        Order order = Order.decode(jsonBytes, Order.class);
        if (order != null && order.getId() == orderId) {
            if (order.getCreatTime() + order.getDeadLine() > System.currentTimeMillis()) {

                // 1. 调用支付API和订单状态
                Document doc = MongoCRUD.findById(MongoUtil.Tables.ORDER_STATE_COL, order.getId());
                if (doc != null) return;// 避免重复处理订单
                boolean isPay = PayProvider.pay(order.asOrderStatus());

                // 2.更新商品数量
                if (isPay) {
                    order.setStatus(OrderStatus.Status.PAID);
                    List<OrderGoods> goods = order.getGoods();
                    for (OrderGoods good : goods) {
                        Document newDoc = new Document(MongoOperators.INC, new Document().append("saleNum", good.getNum()));
                        MongoCRUD.updateById(MongoUtil.Tables.GOOD_COL, good.getId(), newDoc, false);
                    }
                } else {
                    order.setStatus(OrderStatus.Status.UNPAID);
                }

                // 3. 更新用户订单状态
                Document newDoc = new Document(MongoOperators.PUSH_ARRAY, new Document("orders", order.asOrderStatus().asDocument()));
                MongoCRUD.updateById(MongoUtil.Tables.USER_ORDER_COL, order.getUserId(), newDoc, false);
            }
        }
    }
}
