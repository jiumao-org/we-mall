package org.jiumao.common.utils;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.jiumao.common.constants.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 生成唯一编号
 * <p>
 * 分布式id生成器
 * 
 * @author ppf@jiumao.org
 * @date 2017年10月25日
 */
public final class IdUtil {
    protected static final Logger log = LoggerFactory.getLogger(LoggerName.Server);

    private static volatile long EndOfUserId = 0;
    private static volatile long EndOfCardId = 0;
    private static volatile long[] EndOfOrderIds = new long[10000];
    private static final DistributedAtomicLong DisAutoUserId;
    private static final DistributedAtomicLong DisAutoCardId;
    private static final DistributedAtomicLong[] DisAutoOrderIds = new DistributedAtomicLong[10000];
    private static final String PATH_USERID = "/counter/userid";
    private static final String PATH_CARDID = "/counter/cardid";
    private static final String PATH_ORDERID = "/counter/orderid/";
    private static final AtomicLong UserId = new AtomicLong(19910315L);
    private static final AtomicLong CardId = new AtomicLong(1000 * 1000 * 1000L);
    private static final AtomicLong[] OrderIds = new AtomicLong[10000];

    private static Long delta = (long) (2 << 14);
    private static final CuratorFramework CURATOR;
    static {
        CURATOR = CuratorFrameworkFactory.builder()//
                .connectString(SysConfig.ZK_SERVER)//
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))//
                .build();
        CURATOR.start();
        final long BEGIN_ORDERID = 1010101010L;
        DisAutoUserId = new DistributedAtomicLong(CURATOR, PATH_USERID, new RetryNTimes(3, 1000));
        DisAutoCardId = new DistributedAtomicLong(CURATOR, PATH_CARDID, new RetryNTimes(3, 1000));
        try {
            EndOfUserId = DisAutoUserId.add(UserId.get() + delta).postValue();
            EndOfCardId = DisAutoCardId.add(CardId.get() + delta).postValue();
            for (int i = 0; i < DisAutoOrderIds.length; i++) {
                DistributedAtomicLong oid = DisAutoOrderIds[i]//
                        = new DistributedAtomicLong(CURATOR, PATH_ORDERID + i, new RetryNTimes(3, 1000));
                EndOfOrderIds[i] = oid.add(BEGIN_ORDERID + delta).postValue();
                OrderIds[i] = new AtomicLong(BEGIN_ORDERID);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ID 生成失败", e.getMessage());
        }
    }


    /** 生成HTTP访问ID，用于日志 */
    public final static String requsetId(String path, int seed) {
        int random = (int) (Math.random() * (seed));
        long now = System.currentTimeMillis();
        now = now * seed + random;
        return path + ":" + now;
    }



    private final static long preSixBit = 0b111111;

    /**
     * 订单号 bit位 ：10进制 用户id后四位 + 6bit随机自增 + 9bit 当年的第几天 + 随机码后续
     * 
     * @param userId
     * @throws Exception
     */
    public final static long getOrderId(long userId) throws Exception {
        if (0 == userId) return 0;
        int lastFourUserId = (int) (userId % 10000);
        long oId = OrderIds[lastFourUserId].incrementAndGet();
        if (oId > EndOfOrderIds[lastFourUserId]) {
            EndOfOrderIds[lastFourUserId] = DisAutoOrderIds[lastFourUserId].add(delta).postValue();
        }
        
        long sixBit = oId & preSixBit;
        long day = DateUtil.getDayOfYear() << 6;
        long remainBit = (oId ^ preSixBit) << 15;
        return lastFourUserId + (sixBit + day + remainBit) * 10000;
    }

    public static long getUserId() throws Exception {
        long uId = UserId.incrementAndGet();
        if (uId > EndOfUserId) {
            EndOfUserId = DisAutoUserId.add(delta).postValue();
        }
        return uId;
    }

    public static long getCardId() throws Exception {
        long cId = CardId.incrementAndGet();
        if (cId > EndOfCardId) {
            EndOfCardId = DisAutoCardId.add(delta).postValue();
        }
        return cId;
    }



}
