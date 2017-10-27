package org.jiumao.wechatMall.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.jiumao.wechatMall.common.constant.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.primitives.Longs;

/**
 * 生成唯一编号
 * 
 * @author ppf@jiumao.org
 * @date 2017年10月25日
 */
public final class IdGenerator {
	
	private static final Logger log = LoggerFactory.getLogger(LoggerName.Store);

	private static final AtomicLong UserId = new AtomicLong(19910315);
	private static final AtomicLong CardId = new AtomicLong(1000 * 1000 * 1000);
	private static final String ProjectPath = System.getProperty("user.dir");
	private static final FileInputStream in;
	private static final FileOutputStream out;

	static {
		// 协议 8byte userID + 8byte cardID + ...
		File abort = new File(ProjectPath + File.separator + "abort");
		try {
			if (!abort.exists()) {
				abort.createNewFile();
				out = new FileOutputStream(abort);
				backup();
				in = new FileInputStream(abort);
			}else {
				in = new FileInputStream(abort);
				recovery();
				out = new FileOutputStream(abort);
			}
			backup();
		} catch (IOException e) {
			throw new Error(e);
		}
		
	}

	private final static long randomId(int seed) {
		int random = (int) (Math.random() * (seed));
		long now = System.currentTimeMillis();
		return now * seed + random;
	}

	public static void main(String[] args) {
		System.out.println(orderId());
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
		try {
			out.write(Longs.toByteArray(UserId.get()));
			out.write(Longs.toByteArray(CardId.get()));
			out.flush();
		} catch (IOException e) {
			log.error("UserId:"+UserId.get()+"|CardId:"+CardId.get(),e.getMessage());
		}
	}

	/**
	 * 读取abort文件从上次分配的id开始使用
	 * 
	 * @throws IOException
	 */
	public static void recovery() throws IOException {
		byte[] longId = new byte[8];
		if (in.available() > 15) {
			long newValue = 0L;
			in.read(longId);
			newValue = Longs.fromByteArray(longId);
			UserId.set(newValue);
			in.read(longId);
			newValue = Longs.fromByteArray(longId);
			CardId.set(newValue);
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		try{
			in.close();
			out.close();
		}catch(Throwable e){
			log.error("Abort file close failure",e.getMessage());
		}
	}

}
