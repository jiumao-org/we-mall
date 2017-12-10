package org.jiumao.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.RandomUtils;
import org.jiumao.common.constants.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 生成唯一编号
 * <p>
 * 非分布式生成器，多机器会冲突。后续可以扩展为高可用RPC服务
 * 
 * @author ppf@jiumao.org
 * @date 2017年10月25日
 */
public final class IdUtil {

    private static final Logger log = LoggerFactory.getLogger(LoggerName.Store);

    private static final long BEGIN_USERID = 19910315L;
    private static final long BEGIN_CARDID = 1000 * 1000 * 1000L;
    private static final long FILE_SIZE = 32L;
    private static final AtomicLong UserId = new AtomicLong(BEGIN_USERID);
    private static final AtomicLong CardId = new AtomicLong(BEGIN_CARDID);

    private static final int BEGIN_ORDERID = 1010101010;
    private static final AtomicLong OrderId = new AtomicLong(BEGIN_ORDERID);

    private static final String ProjectPath = System.getProperty("user.dir");
    private static FileChannel channel;
    private static MappedByteBuffer buff;
    private static RandomAccessFile raf;

    static {
        // 协议32字节(可扩展)：
        // 8byte 起始userID + 8byte 起始cardID + 8byte 当前userID + 8byte 当前cardID
        File abort = new File(ProjectPath + File.separator + "abort");
        try {
            if (!abort.exists()) {
                abort.createNewFile();
                init(abort);
                backup();
            } else {
                init(abort);
                recovery();
            }
        } catch (IOException e) {
            throw new Error(e);
        }

    }

    private static void init(File abort) throws FileNotFoundException, IOException {
        raf = new RandomAccessFile(abort, "rw");
        raf.setLength(FILE_SIZE);
        channel = raf.getChannel();
        buff = channel.map(FileChannel.MapMode.READ_WRITE, 0, FILE_SIZE);
    }

    private final static long randomId(int seed) {
        int random = (int) (Math.random() * (seed));
        long now = System.currentTimeMillis();
        return now * seed + random;
    }

    public static void setUserId(long fromWhere) {
        UserId.set(fromWhere);
    }

    public static void setCardId(long fromWhere) {
        CardId.set(fromWhere);
    }

    public final static long orderId() {
        return randomId(1000);
    }

    public static long getUserId() {
        return UserId.incrementAndGet();
    }

    public static long getCardId() {
        return CardId.incrementAndGet();
    }

    /**
     * 备份到abort文件，现在分配id位置，不用加锁，最后一次写完就好
     * 
     * @throws IOException
     */
    public static void backup() {
        buff.putLong(0, BEGIN_USERID);
        buff.putLong(8, BEGIN_CARDID);
        buff.putLong(16, UserId.get());
        buff.putLong(24, CardId.get());
        buff.force();
    }

    /**
     * 读取abort文件从上次分配的id开始使用
     * 
     * @throws IOException
     */
    public static void recovery() {
        UserId.set(buff.getLong(16));
        CardId.set(buff.getLong(24));
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            channel.close();
            raf.close();
        } catch (Throwable e) {
            log.error("Abort file close failure", e.getMessage());
        }
    }

    // 预先生成一堆 ID
    private static final int INIT_CAPACITY = 1024;
    private static final ArrayList<Long> ORDER_IDS = new ArrayList<>(INIT_CAPACITY);

    public static long getOrderId() {
        if (ORDER_IDS.size() < 1) initOrderIds();
        return ORDER_IDS.remove(RandomUtils.nextInt() % INIT_CAPACITY);
    }

    private static void initOrderIds() {
        for (int i = 0; i < ORDER_IDS.size(); i++) {
            ORDER_IDS.add(OrderId.getAndIncrement());
        }
    }


}
