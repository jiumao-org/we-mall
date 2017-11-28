package org.jiumao.common.utils;

import java.nio.charset.Charset;

import org.apache.commons.lang3.ArrayUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * 对象序列化，json实现
 */
public abstract class JsonSerializable {
    public String toJson() {
        return toJson(false);
    }


    public String toJson(final boolean prettyFormat) {
        return toJson(this, prettyFormat);
    }


    public static String toJson(final Object obj, boolean prettyFormat) {
        return JSON.toJSONString(obj, prettyFormat);
    }


    public static <T> T fromJson(String json, Class<T> classOfT) {
        return JSON.parseObject(json, classOfT);
    }

    public static final SerializerFeature[] features = //
            { SerializerFeature.WriteMapNullValue, // 输出空置字段
             SerializerFeature.WriteNullListAsEmpty,// list字段如果为null，输出为[]，而不是null
             SerializerFeature.WriteNullNumberAsZero,// 数值字段如果为null，输出为0，而不是null
             SerializerFeature.WriteNullBooleanAsFalse,// Boolean字段如果为null，输出为false，而不是null
             SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
            };


    public static byte[] toBytes(final Object obj) {
        return JSON.toJSONBytes(obj, features);
    }


    public byte[] encode() {
        final String json = this.toJson();
        if (json != null) {
            return json.getBytes();
        }
        return null;
    }


    public static byte[] encode(final Object obj) {
        final String json = toJson(obj, false);
        if (json != null) {
            return json.getBytes(Charset.forName("UTF-8"));
        }
        return null;
    }


    public static <T> T decode(final byte[] data, Class<T> classOfT) {
        final String json = toJson(data);
        return fromJson(json, classOfT);
    }


    /**
     * 直接解析json
     */
    public static String toJson(final byte[] data) {
        if (ArrayUtils.isNotEmpty(data)) {
            return new String(data, Charset.forName("UTF-8"));
        }
        return "";
    }

}
