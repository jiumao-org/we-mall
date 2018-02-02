package org.jiumao.db.redis;

import java.util.Set;

import redis.clients.jedis.BinaryJedis;
import redis.clients.util.Pool;

public class SetRedis extends BaseRedis<BinaryJedis>{
    private String setName;
    private byte[] setNameBs;

    /**
     * 创建一个Set集合并赋值, 集合中元素唯一
     * 
     * @param setName 集合名称
     * @param members 集合元素
     */
    public void addSet(byte[]... members) {
        getCon().sadd(setNameBs, members);
    }


    public SetRedis(Pool jedisPool, String setName) {
        super(jedisPool);
        this.setName = setName;
        this.setNameBs = setName.getBytes();
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
     * set中是否存在此数据
     */
    public boolean existSet(byte[] value) {
        return getCon().sismember(setNameBs, value);
    }



    /**
     * 随机的移除并返回Set中的某一成员。 <br>
     * 由于Set中元素的布局不受外部控制， <br>
     * 因此无法像List那样确定哪个元素位于Set的头部或者尾部 <br>
     */
    public byte[] pop() {
        return getCon().spop(setNameBs);
    }


    public String getSetName() {
        return setName;
    }


    public void setSetName(String setName) {
        this.setName = setName;
    }
}
