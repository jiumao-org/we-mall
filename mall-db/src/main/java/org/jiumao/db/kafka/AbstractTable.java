package org.jiumao.db.kafka;

import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;

/**
 * kafka table
 * @author ppf@jiumao.org 
 * @date 2017/12/10
 */
public abstract class AbstractTable<K,V> extends AbstractKafkaClient<ReadOnlyKeyValueStore<K,V>> {
    /** 状态topic存储后缀 */
    protected static final String STATE_TABLE_POSTFIX = "-state-store";
    /** global-table存储后缀 */
    protected static final String GLOBAL_TABLE_POSTFIX = "-global-store";
    /** query-table存储后缀,k-v查询 */
    protected static final String QUERY_TABLE_POSTFIX = "-query-store";

    /**
     * k-v store
     * @param k
     */
    public abstract V get(K k);
    
    
}
