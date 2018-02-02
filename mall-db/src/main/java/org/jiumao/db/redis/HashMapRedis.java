package org.jiumao.db.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.BinaryJedis;
import redis.clients.util.Pool;

public class HashMapRedis extends BaseRedis<BinaryJedis> {

    private String hmName;
    private byte[] hmNameBs;

    /**
     * 添加一个hash键值对
     */
    public void addHM(byte[] key, byte[] value) {
        Map<byte[], byte[]> hm = new HashMap<>(1);
        hm.put(key, value);
        getCon().hmset(hmNameBs, hm);
    }


    public HashMapRedis(Pool<BinaryJedis> jedisPool, String hmName) {
        super(jedisPool);
        this.hmName = hmName;
        this.hmNameBs = hmName.getBytes();
    }


    public void addHM(Map<byte[], byte[]> hash) {
        getCon().hmset(hmNameBs, hash);
    }

    /**
     * 获取哈希集合的成员个数
     */
    public Long getHmCount() {
        return getCon().hlen(hmNameBs);
    }


    /**
     * 获取哈希数据表的一个只
     */
    public byte[] getHM(byte[] key) {
        return getCon().hmget(hmNameBs, key).get(0);
    }

    /**
     * 获取哈希数据表的一个只
     */
    public List<byte[]> getHM(byte[]... keys) {
        return getCon().hmget(hmNameBs, keys);
    }


    /**
     * 删除一条哈希表数据
     * 
     * @param key
     */
    public void remvHM(byte[]... key) {
        getCon().hdel(hmNameBs, key);
    }


    public String getHmName() {
        return hmName;
    }


    public void setHmName(String hmName) {
        this.hmName = hmName;
    }
}
