package org.jiumao.service.order;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.internals.MaterializedInternal;
import org.apache.kafka.streams.state.KeyValueStore;
import org.jiumao.db.kafka.AbstractKafkaClient;

public class OrderTable extends AbstractKafkaClient<GlobalKTable<Long, byte[]>> {

    @Override
    public GlobalKTable<Long, byte[]> worker() {
        Properties config = super.configBuilder()//
                .put(StreamsConfig.APPLICATION_ID_CONFIG, "order")//
                .put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap)//
                .put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Long().getClass())//
                .put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass())//
                .build();

        StreamsBuilder builder = new StreamsBuilder();
        Materialized<Long, byte[], KeyValueStore<Bytes, byte[]>> materialized =
                Materialized.as(OrderConsumer.ORDER_COMMIT_TOPIC + "-global-store");
         this.worker = builder.globalTable(OrderConsumer.ORDER_COMMIT_TOPIC, materialized);
         return worker;
    }



}
