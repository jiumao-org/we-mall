package org.jiumao.parse.task;

import java.util.HashMap;
import java.util.Map;

import org.jiumao.db.redis.BaseRedis;
import org.jiumao.db.redis.JedisPoolUtil;
import org.jiumao.db.redis.SetRedis;

import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.JedisPool;

/**
 * url 存储队列：host->set(url)
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/02/02
 */
public class UrlPool {

    private static final BaseRedis<BinaryJedis> HOST;
    private static final Map<String, SetRedis> HOST_SET;
    static {
        String host = null;
        int port = 0;
        String pwd = null;
        JedisPool jedisPool = JedisPoolUtil.DefaultPool(host, port, pwd);
        HOST = new SetRedis(jedisPool, "host");
        HOST_SET = new HashMap<>();
    }

    public static String hostOfUrl(String url) {
        return null;// TODO 这里还没做
    }

    public static void getHosts() {

    }

    /**
     * 添加要解析的url
     * 
     * @param url 绝对路径
     */
    public static void addUrl(String url) {}
}
