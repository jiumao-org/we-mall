package org.jiumao.common.utils;

/**
 * 对象序列化，json实现
 */
public abstract class JsonSerializable {
    public String toJson() {
        return toJson(false);
    }


    public String toJson(final boolean prettyFormat) {
        return JsonUtil.toJson(this, prettyFormat);
    }


    public byte[] encode() {
        final String json = this.toJson();
        if (json != null) {
            return json.getBytes();
        }
        return null;
    }
    
    public static <T> T decode(final byte[] data,Class<T> classOfT) {
        return JsonUtil.decode(data, classOfT);
    }

}
