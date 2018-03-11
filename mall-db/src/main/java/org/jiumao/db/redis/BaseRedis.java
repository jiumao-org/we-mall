package org.jiumao.db.redis;


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

        } catch (Exception e) {
            e.printStackTrace();
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if (null == jedis) {
                this.jedis = jedisPool.getResource();
            }
        }
        jedis.select(Default_DB);
        return jedis;
    }


    public BaseRedis(Pool<T> jedisPool) {
        super();
        this.jedisPool = jedisPool;
    }



    public void close() {
        jedis.close();
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


    public T getJedis() {
        return jedis;
    }


    public Pool<T> getJedisPool() {
        return jedisPool;
    }

}
