package org.jiumao.service.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.TopologyBuilder;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.jiumao.db.kafka.stream.serdes.SerdesFactory;
import org.jiumao.wechatMall.entity.OrderGoods;

/**
 * 订单分析，平台角度
 * 
 * @author ppf@jiumao.org
 * @date 2018/01/07
 */
public class OrderAnalysis {


    public static void main(String[] args) {
        Topology builder = new Topology();
        String processorNames = "";
        builder.addSource("SOURCE", "src-topic")//
                .addStateStore(//
                        Stores.keyValueStoreBuilder(//
                                Stores.persistentKeyValueStore(OrderConstants.GOOD_ORDER_TOPIC), //
                                new Serdes.LongSerde(), //
                                new Serdes.ByteArraySerde()), //
                        processorNames)//
                //
                .addProcessor("PROCESS1", GoodOrderProcessor::new, "SOURCE")//
                .addProcessor("PROCESS2", GoodOrderProcessor::new, "PROCESS1")//
                .addProcessor("PROCESS3", GoodOrderProcessor::new, "PROCESS1")//
                // connect the state store "COUNTS" with processor "PROCESS2"  
                .connectProcessorAndStateStores("PROCESS2", OrderConstants.GOOD_ORDER_TOPIC)  
                //
                .addSink("SINK1", "sink-topic1", "PROCESS1")//
                .addSink("SINK2", "sink-topic2", "PROCESS2")//
                .addSink("SINK3", "sink-topic3", "PROCESS3");
    }
}
