package org.jiumao.service.order;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serdes.LongSerde;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Serialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.jiumao.common.utils.JsonSerializable;
import org.jiumao.common.utils.MallConstants;
import org.jiumao.wechatMall.entity.Order;
/**
 * 平台角度，用户订单统计
 * @author ppf@jiumao.org 
 * @date 2018/01/07
 */
public class UserOrderStream {
    
    public static void main(String[] args) {
        StreamsBuilder builder = new StreamsBuilder();
        KStream<Long, byte[]> order = builder.stream(MallConstants.ORDER_COMMITED_TOPIC);
        KeyValueMapper<Long, byte[], Long> userSelector = new KeyValueMapper<Long, byte[], Long>() {

            @Override
            public Long apply(Long key, byte[] value) {
                Order o = JsonSerializable.decode(value, Order.class);
                return null == o ? null : o.getUserId();
            }

        };
        long sizeMs = 30 * 24 * 60 * 60 * 1000;// 30 day

        order.groupBy(userSelector, Serialized.with(new LongSerde(), new Serdes.ByteArraySerde()))//
                .windowedBy(TimeWindows.of(sizeMs))//
                .aggregate(() -> 0L, (aggKey, value, aggregate) -> aggregate + 1L)//
                .toStream()//
                .to(OrderConstants.USER_ORDER_COUNT_TOPIC);
    }
}
