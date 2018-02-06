package org.jiumao.db.redis;

import java.util.List;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;


public final class JedisPoolUtil {
    private static int maxCount = 2000;
    private static long MaxWait = 2000l;
    private static int maxIdle = 500;
    private static JedisPoolConfig config = defaultPoolConfig();


    public static JedisPool DefaultPool(String host, int port, String pwd) {
        return new JedisPool(config, host, port, maxIdle, pwd, 0, false);
    }


    public static ShardedJedisPool DefaultShardedPool(List<JedisShardInfo> infoList) {
        return new ShardedJedisPool(config, infoList);
    }


    public static JedisPoolConfig defaultPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxCount);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(MaxWait);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        return config;
    }

}
