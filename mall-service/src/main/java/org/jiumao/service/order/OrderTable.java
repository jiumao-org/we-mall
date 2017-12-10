package org.jiumao.service.order;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.jiumao.db.kafka.ATable;

/**
 * 提供订单k-v查询
 * 
 * @author ppf@jiumao.org
 * @date 2017/12/10
 */
public class OrderTable extends ATable<Long, byte[]> {

    private String queryableStoreName = OrderConstants.ORDER_COMMITED_TOPIC + QUERY_TABLE_POSTFIX;

    @Override
    public ReadOnlyKeyValueStore<Long, byte[]> worker() {
        Properties config = super.configBuilder()//
                .put(StreamsConfig.APPLICATION_ID_CONFIG, OrderConstants.ORDER_COMMITED_TOPIC)//
                .put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap)//
                .put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Long().getClass())//
                .put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass())//
                .build();

        StreamsBuilder builder = new StreamsBuilder();
        builder.stream(OrderConstants.ORDER_HANDLED_TOPIC);

        KafkaStreams streams = new KafkaStreams(builder.build(), new StreamsConfig(config));
        streams.start();
        return this.worker = // k-v query
                streams.store(queryableStoreName, QueryableStoreTypes.<Long, byte[]>keyValueStore());
    }

    @Override
    public byte[] get(Long k) {
        return worker.get(k);
    }
}
