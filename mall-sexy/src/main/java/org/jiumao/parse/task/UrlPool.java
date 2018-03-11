package org.jiumao.parse.task;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
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

    private static final SetRedis HOST;
    private static final Map<String, SetRedis> HOST_SET;
    private static final JedisPool JEDIS_POOL;
    static {
        String host = "127.0.0.1";
        int port = 6379;
        String pwd = null;
        JEDIS_POOL = JedisPoolUtil.DefaultPool(host, port, pwd);
        HOST = new SetRedis(JEDIS_POOL, "host");
        HOST_SET = new HashMap<>();
    }

    /**
     * 提取URL（绝对路径）中host
     * 
     * @param url
     * @return
     */
    public static String hostOfUrl(String url) {
        try {
            URL u = new URL(url);
            return u.getHost();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取所有抓取站点
     */
    public static Set<byte[]> getHosts() {
        return HOST.getSAll();
    }

    /**
     * 添加要解析的url
     * 
     * @param url 绝对路径
     */
    public static boolean addUrl(String url) {
        String host = hostOfUrl(url);
        if (StringUtils.isNotEmpty(host)) {
            SetRedis set = HOST_SET.get(host);
            if (set == null) {
                set = new SetRedis(JEDIS_POOL, host);
                HOST_SET.put(host, set);
            }
            set.addSet(url.getBytes());
            return true;
        }

        return false;
    }
}
