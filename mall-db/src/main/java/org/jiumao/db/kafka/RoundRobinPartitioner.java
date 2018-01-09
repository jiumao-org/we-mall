package org.jiumao.db.kafka;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

/**
 * 轮询实现负载均衡
 * @author ppf@jiumao.org 
 * @date 2017/12/09
 */
public class RoundRobinPartitioner implements Partitioner {

    private static final AtomicInteger Next = new AtomicInteger();


    @Override
    public void configure(Map<String, ?> configs) {

    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> infos = cluster.partitionsForTopic(topic);
        return Next.getAndIncrement() % infos.size();
    }

    @Override
    public void close() {}
}


