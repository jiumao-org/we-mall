package org.jiumao.service.order;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serdes.LongSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Aggregator;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Serialized;
import org.apache.kafka.streams.kstream.SessionWindows;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.internals.KStreamAggregate;
import org.apache.kafka.streams.kstream.internals.TimeWindow;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.jiumao.common.utils.JsonSerializable;
import org.jiumao.common.utils.MallConstants;
import org.jiumao.db.kafka.AbstractTable;
import org.jiumao.db.kafka.stream.serdes.SerdesFactory;
import org.jiumao.wechatMall.entity.Order;

/**
 * 提供订单k-v查询
 * 
 * @author ppf@jiumao.org
 * @date 2017/12/10
 */
public class OrderTable extends AbstractTable<Long, byte[]> {

    private static final OrderTable ORDER_TABLE = new OrderTable();

    public static OrderTable getInstance() {
        return ORDER_TABLE;
    }

    public OrderTable() {
        worker();
    }

    private String queryableStoreName = MallConstants.ORDER_COMMITED_TOPIC + QUERY_TABLE_POSTFIX;

    @Override
    public ReadOnlyKeyValueStore<Long, byte[]> worker() {
        Properties config = super.configBuilder()//
                .put(StreamsConfig.APPLICATION_ID_CONFIG, MallConstants.ORDER_COMMITED_TOPIC)//
                .put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap)//
                .put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Long().getClass())//
                .put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass())//
                .build();

        StreamsBuilder builder = new StreamsBuilder();
        KafkaStreams streams = new KafkaStreams(builder.build(), new StreamsConfig(config));
        streams.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            // TODO Auto-generated method stub
            log.error(e.getMessage());
        });
        streams.start();

        return this.worker = // k-v query
                streams.store(queryableStoreName, QueryableStoreTypes.<Long, byte[]>keyValueStore());
    }

    @Override
    public byte[] get(Long k) {
        return worker.get(k);
    }

    
}


