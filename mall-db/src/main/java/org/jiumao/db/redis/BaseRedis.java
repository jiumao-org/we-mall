package org.jiumao.db.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.BinaryJedis;
import redis.clients.util.Pool;


public abstract class BaseRedis<T extends BinaryJedis> {

    public static final int Default_DB = 0;
    public static final int Configure_DB = 1;
    public static final int Cache_DB = 2;
    public static final int Common_DB = 3;
    public static final int Relative_DB = 4;
    public static final int Import_DB = 5;
    // public static final int Default_DB = 6;
    // public static final int Default_DB = 7;
    // public static final int Default_DB = 8;
    // public static final int Default_DB = 9;

    private String setName;
    private byte[] setNameBs;
    private String hmName;
    private byte[] hmNameBs;
    protected T jedis;
    protected Pool<T> jedisPool;


    /**
     * 获取jedis的连接
     * 
     * @return
     */
    public BinaryJedis getCon() {
        try {
            if (null == jedis) {
                System.out.println("jedis 重新获取链接");
                this.jedis = jedisPool.getResource();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            try {
                Thread.sleep(10 * 1000);
            }
            catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if (null == jedis) {
                this.jedis = jedisPool.getResource();
            }
        }
        jedis.select(Default_DB);
        return jedis;
    }


    public BaseRedis(String setName, String hmName, Pool<T> jedisPool) {
        super();
        this.setName = setName;
        this.setNameBs = setName.getBytes();
        this.hmName = hmName;
        this.hmNameBs = hmName.getBytes();
        this.jedisPool = jedisPool;
    }


    /**
     * 创建一个Set集合并赋值, 集合中元素唯一
     * 
     * @param setName 集合名称
     * @param members 集合元素
     */
    public void addSet(byte[]... members) {
        getCon().sadd(setNameBs, members);
    }


    /**
     * 添加一条数据往set集合中
     * 
     * @param setName 集合名称
     * @param members 集合元素
     */
    public void remvSet(byte[]... members) {
        getCon().srem(setNameBs, members);
    }


    /**
     * 获取set集合中所有元素
     */
    public Set<byte[]> getSAll() {
        return getCon().smembers(setNameBs);
    }



    /**
     * 添加一个hash键值对
     */
    public void addHM(byte[] key, byte[] value) {
        Map<byte[], byte[]> hm = new HashMap<>(1);
        hm.put(key, value);
        getCon().hmset(hmNameBs, hm);
    }


    public void addHM(Map<byte[], byte[]> hash) {
        getCon().hmset(hmNameBs, hash);
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


    /**
     * 把集合从一个集合移动到另外一个集合 并删除集合的数据
     * 
     * @param old
     * @param newSet
     */
    public void move2NewSet(byte[] old, byte[] newSet) {
        Set<byte[]> ss = getCon().smembers(old);

        for (byte[] s : ss) {
            getCon().srem(old, s);
            getCon().sadd(newSet, s);
        }
    }


    /**
     * 获取set集合的成员个数
     */
    public Long getSetCount() {
        return getCon().scard(setNameBs);
    }


    /**
     * 获取哈希集合的成员个数
     */
    public Long getHmCount() {
        return getCon().hlen(hmNameBs);
    }


    /**
     * 随机的移除并返回Set中的某一成员。 <br>
     * 由于Set中元素的布局不受外部控制， <br>
     * 因此无法像List那样确定哪个元素位于Set的头部或者尾部 <br>
     */
    public byte[] pop() {
        return getCon().spop(setNameBs);
    }


    public void close() {
        jedis.close();
    }


    /**
     * set中是否存在此数据
     */
    public boolean existSet(byte[] value) {
        return getCon().sismember(setNameBs, value);
    }


    public static int getDefaultDb() {
        return Default_DB;
    }


    public static int getConfigureDb() {
        return Configure_DB;
    }


    public static int getCacheDb() {
        return Cache_DB;
    }


    public static int getCommonDb() {
        return Common_DB;
    }


    public static int getRelativeDb() {
        return Relative_DB;
    }


    public static int getImportDb() {
        return Import_DB;
    }


    public String getSetName() {
        return setName;
    }


    public String getHmName() {
        return hmName;
    }


    public T getJedis() {
        return jedis;
    }


    public Pool<T> getJedisPool() {
        return jedisPool;
    }

}
