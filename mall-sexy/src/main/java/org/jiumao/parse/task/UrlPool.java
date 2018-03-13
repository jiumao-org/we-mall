package org.jiumao.parse.task;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jiumao.db.redis.BaseRedis;
import org.jiumao.db.redis.JedisPoolUtil;
import org.jiumao.db.redis.SetRedis;

import redis.clients.jedis.JedisPool;

/**
 * url 存储队列：host->set(url)
 * 
 * @author Bevis-Pei<ppf@jiumao.org>
 * @date 2018/02/02
 */
public class UrlPool {

    /** 所有的任务节点 */
    private static final SetRedis TASK_HOST, MQ;
    /** 按照host分类，URL去重 */
    private static final Map<String, SetRedis> HOST_SET;
    private static final JedisPool JEDIS_POOL;
    static {
        String host = "127.0.0.1";
        int port = 6379;
        JEDIS_POOL = new JedisPool(host, port);
        TASK_HOST = new SetRedis(JEDIS_POOL, "host");
        MQ = new SetRedis(JEDIS_POOL, "urlQueue");
        HOST_SET = new HashMap<>();
    }

    /**
     * 提取URL（绝对路径）中host
     * 
     * @param url
     * @throws MalformedURLException
     */
    public static String hostOfUrl(String url) {
        try {
            URL u = new URL(url);
            return u.getHost();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println(url);
        }
        return null;
    }

    /**
     * 获取所有抓取站点
     */
    public static synchronized Set<byte[]> getHosts() {
        return TASK_HOST.getSAll();
    }

    /**
     * 添加要解析的url
     * 
     * @param url 绝对路径
     * @throws MalformedURLException
     */
    public static synchronized boolean addUrl(String url) {
        String host = hostOfUrl(url);
        if (StringUtils.isNotEmpty(host)) {
            SetRedis set = HOST_SET.get(host);
            if (set == null) {
                set = new SetRedis(JEDIS_POOL, host);
                HOST_SET.put(host, set);
            }
            if (set.existSet(url.getBytes())) {
                return false;
            } else {
                MQ.addSet(url.getBytes());
                return true;
            }
        }

        return false;
    }

    public static synchronized void addSource(String url) {
        MQ.addSet(url.getBytes());
    }


    public static synchronized void addDoneUrl(String url) {
        String host = hostOfUrl(url);

        if (StringUtils.isNotEmpty(host)) {
            SetRedis set = HOST_SET.get(host);
            if (set == null) {
                set = new SetRedis(JEDIS_POOL, host);
                HOST_SET.put(host, set);
            }
            set.addSet(url.getBytes());
        }
    }

    /**
     * 随机提取一条任务
     */
    public static synchronized String popUrl() {
        byte[] bs = MQ.pop();
        if (bs == null) {
            return null;
        }
        return new String(bs);
    }
}
