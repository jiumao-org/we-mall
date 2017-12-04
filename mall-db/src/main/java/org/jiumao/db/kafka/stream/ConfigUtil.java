package org.jiumao.db.kafka.stream;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

/**
 * kafka 默认配置信息
 * @author ppf@jiumao.org
 * @date 2017年11月30日
 */
public abstract class ConfigUtil {

    /**
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
    
}
