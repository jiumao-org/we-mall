package org.jiumao.db.redis;


import redis.clients.jedis.BinaryJedis;
import redis.clients.util.Pool;


public abstract class BaseRedis<T extends BinaryJedis> {

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
        return jedis;
    }


    public BaseRedis(Pool<T> jedisPool) {
        super();
        this.jedisPool = jedisPool;
    }



    public void close() {
        jedis.close();
    }

    public T getJedis() {
        return jedis;
    }


    public Pool<T> getJedisPool() {
        return jedisPool;
    }

}
