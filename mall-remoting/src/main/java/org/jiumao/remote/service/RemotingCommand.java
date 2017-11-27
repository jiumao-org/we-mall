package org.jiumao.remote.service;


import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Remoting模块中，服务器与客户端通过传递RemotingCommand来交互
 */
public class RemotingCommand {
    private static AtomicInteger RequestId = new AtomicInteger(0);
    private int flag = 0;
    private static final int RPC_TYPE = 0; 
    private static final int RPC_ONEWAY = 1; 
    private int opaque = RequestId.getAndIncrement();

    private HashMap<String, String> extFields;

    private transient byte[] body;


    protected RemotingCommand() {
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
        ByteBuffer byteBuffer = ByteBuffer.wrap(array);
        return decode(byteBuffer);
    }


    public static RemotingCommand decode(ByteBuffer byteBuffer) {
        return null;
    }


    public ByteBuffer encode() {
        return encode(this);
    }


    public static ByteBuffer encode(RemotingCommand cmd) {
        return null;
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
