package org.jiumao.db.kafka;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.jiumao.common.constants.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * consumer or producer
 * @author ppf@jiumao.org
 * @date 2017年11月30日
 */
public abstract class AbstractKafkaClient<T> {
    protected static final Logger log = LoggerFactory
            .getLogger(LoggerName.Store);
    protected static final String AUTO_COMMIT_INTERVAL_MS = "1000";
    protected  static String bootstrap = "121.201.109.100:9092";
    
    protected T worker;

    /**kafka 默认配置信息
     * <p>
     * config key use from 
     * {@link ConsumerConfig}
     * {@link ProducerConfig}
     * @return
     */
    public static ConfigBuilder configBuilder() {
        return new ConfigBuilder();
    }

    public static class ConfigBuilder {
        protected Properties props = new Properties();
        public ConfigBuilder put(Object key, Object value) {
            props.put(key, value);
            return ConfigBuilder.this;
        }
        
        public Properties build() {
            return props;
        }
    }
    
    public abstract T worker();

    public T getWorker() {
        return worker;
    }
    
}
