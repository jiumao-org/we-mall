package org.jiumao.remote.service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.ArrayUtils;
import org.jiumao.common.utils.JsonUtil;


/**
 * 服务器与客户端交互
 * <ol>
 * <li>{@link #opaque} 用于跟踪请求的id
 * <li>{@link #body} fastJson 序列化的数据正文
 * <li>{@link #extFields} k-v 额外的提示信息
 */
public class RemotingCommand {
    private static AtomicInteger RequestId = new AtomicInteger(0);
    private int flag = 0;
    private static final int RPC_TYPE = 0;
    private static final int RPC_ONEWAY = 1;
    private int opaque;

    private HashMap<String, String> extFields = new HashMap<>(4);

    private transient byte[] body;


    public RemotingCommand() {
        opaque = RequestId.getAndIncrement();
    }


    /**
     * 用于应答，同一个 opaqueId
     */
    public RemotingCommand(int opaqueId) {
        opaque = opaqueId;
    }


    public static RemotingCommand createRequestCommand() {
        RemotingCommand cmd = new RemotingCommand();
        return cmd;
    }


    public static RemotingCommand createResponseCommand() {
        RemotingCommand cmd = new RemotingCommand();
        return cmd;
    }


    public boolean isResponseType() {
        int bits = 1 << RPC_TYPE;
        return (this.flag & bits) == bits;
    }


    public static RemotingCommand decode(final byte[] array) {
        assert array.length > 4 + 4 + 4;
        ByteBuffer byteBuffer = ByteBuffer.wrap(array);
        return decode(byteBuffer);
    }


    public boolean isEmptyBody() {
        return ArrayUtils.isEmpty(getBody());
    }


    public static RemotingCommand decode(ByteBuffer buffer) {
        // total size
        assert buffer.limit() == buffer.getInt();
        // opaque
        RemotingCommand cmd = new RemotingCommand(buffer.getInt());

        // body
        int bodyLen = buffer.getInt();
        byte[] body = new byte[bodyLen];
        buffer.get(body);
        cmd.setBody(body);

        // exts
        int extLen = buffer.getInt();
        byte[] ext = new byte[extLen];
        buffer.get(ext);
        @SuppressWarnings("unchecked")
        HashMap<String, String> extFields = JsonUtil.decode(ext, HashMap.class);
        cmd.setExtFields(extFields);

        return cmd;
    }


    /**
     * <b>一定要 {@link ByteBuf#release()}
     */
    public ByteBuf encode() {
        return encode(this);
    }


    /**
     * <b>一定要 {@link ByteBuf#release()}
     */
    public static ByteBuf encode(RemotingCommand cmd) {
        int reqId = cmd.getOpaque();
        byte[] body = cmd.getBody();
        HashMap<String, String> msgs = cmd.getExtFields();
        byte[] append = JsonUtil.toBytes(msgs);
        int initialCapacity = 4 + 4 // total size+reqId
                + 4 + body.length // body
                + 4 + append.length;// apend
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer(initialCapacity);
        buf.writeInt(initialCapacity);
        buf.writeInt(reqId);
        buf.writeInt(body.length);
        buf.writeBytes(body);
        buf.writeInt(append.length);
        buf.writeBytes(append);

        return buf;
    }


    public void markOnewayRPC() {
        int bits = 1 << RPC_ONEWAY;
        this.flag |= bits;
    }


    public byte[] getBody() {
        return body;
    }


    public void setBody(byte[] body) {
        this.body = body;
    }


    public HashMap<String, String> getExtFields() {
        return extFields;
    }


    public void setExtFields(HashMap<String, String> extFields) {
        this.extFields = extFields;
    }


    @Override
    public String toString() {
        return "RemotingCommand [opaque=" + opaque + ", flag(B)=" + Integer.toBinaryString(0)
                + ", extFields=" + extFields + "]";
    }


    public int getOpaque() {
        return opaque;
    }


    public void setOpaque(int opaque) {
        this.opaque = opaque;
    }
}
