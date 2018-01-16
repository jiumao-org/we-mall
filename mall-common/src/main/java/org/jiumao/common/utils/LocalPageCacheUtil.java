package org.jiumao.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.jiumao.common.constants.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端可以写本地文件利用pagecache 缓存
 * 
 * @author ppf@jiumao.org
 * @date 2017/12/23
 */
public class LocalPageCacheUtil {
    protected static final Logger log = LoggerFactory.getLogger(LoggerName.Store);
    private static final String ProjectPath = System.getProperty("user.dir");
    private static FileChannel channel;
    private static MappedByteBuffer buff;
    private static RandomAccessFile raf;
    private static final long FILE_SIZE = 32L;


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
        } catch (Exception e) {
            throw new Error(e);
        }

    }

    private static void init(File abort) throws FileNotFoundException, IOException {
        raf = new RandomAccessFile(abort, "rw");
        raf.setLength(FILE_SIZE);
        channel = raf.getChannel();
        buff = channel.map(FileChannel.MapMode.READ_WRITE, 0, FILE_SIZE);
    }

    /**
     * 备份到abort文件，现在分配id位置，不用加锁，最后一次写完就好
     * 
     * @throws IOException
     */
    public static void backup() {
        buff.force();
    }



    /**
     * 读取abort文件从上次分配的id开始使用
     * 
     * @throws IOException
     */
    public static void recovery() {}

    @Override
    protected void finalize() throws Throwable {
        try {
            channel.close();
            raf.close();
        } catch (Throwable e) {
            log.error("Abort file close failure", e.getMessage());
        }
    }

}
