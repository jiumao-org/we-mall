package org.jiumao.common.utils;


import org.apache.commons.lang3.ArrayUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.NullNode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import javax.annotation.Nullable;


public final class JsonUtil {

    // ============================json-javaBean==================================

    /**
     * 输入和输出都有可能为null， 注意null检查
     */
    public static <E> E json2Bean(ObjectMapper m, String json, Class<E> type) {

        try {
            return m.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    /**
     * 输入和输出都有可能为null， 注意null检查
     */
    @Nullable
    public static <E> String bean2Json(ObjectMapper m, E e) {
        try {
            return m.writeValueAsString(e);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
    
    final static ObjectMapper m = new ObjectMapper();
    @Nullable
    public static <E> String bean2Json(E e) {
        try {
            return m.writeValueAsString(e);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    // ============================json-field==================================

    private static final JsonNode NULL_NODE = NullNode.getInstance();
    private static JsonFactory factory = new JsonFactory();

    public static JsonNode jsonTree(ObjectMapper m, String json) {
        try {
            JsonParser jp = factory.createJsonParser(new StringReader(json));
            m.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            return m.readTree(jp);
        } catch (Exception e) {
            System.out.println(e);
        }
        return NULL_NODE;
    }
    
    public static JsonWraper toJsonWraper(String json){
		ObjectMapper m = new ObjectMapper();// 复用
		JsonNode tree = JsonUtil.jsonTree(m, json);
		return JsonWraper.of(tree);
    }

    /**
     * 当this为{@link NullNode}值或者键值不存在，会返回null
     */
    public static String getValue(JsonNode node, String name) {
        JsonNode nodeThis = getJsonNode(node, name);
        if (isNull(nodeThis)) {
            return null;
        }
        return nodeThis.toString();
    }

    public static JsonNode getJsonNode(JsonNode node, String name) {
        if (name == null || isNull(node)) return NULL_NODE;

        StringTokenizer st = new StringTokenizer(name, ".");
        while (st.hasMoreTokens()) {
            String key = st.nextToken().trim();
            if (key.isEmpty() || null == (node = node.findValue(key))) {
                return NULL_NODE;
            }
        }
        return node;
    }

    /**
     * null或者当{@code JsonNode}是{@link NullNode}
     */
    public static boolean isNull(JsonNode node) {
        return null == node || NULL_NODE == node;
    }
    
    //===========================================fastJson-Serializer===============================================

    public static final SerializerFeature[] features = //
    { SerializerFeature.WriteMapNullValue, // 输出空置字段
     SerializerFeature.WriteNullListAsEmpty,// list字段如果为null，输出为[]，而不是null
     SerializerFeature.WriteNullNumberAsZero,// 数值字段如果为null，输出为0，而不是null
     SerializerFeature.WriteNullBooleanAsFalse,// Boolean字段如果为null，输出为false，而不是null
     SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };

    public static String toJson(final Object obj, boolean prettyFormat) {
        return JSON.toJSONString(obj, prettyFormat);
    }


    public static <T> T fromJson(String json, Class<T> classOfT) {
        return JSON.parseObject(json, classOfT);
    }


    public static byte[] toBytes(final Object obj) {
        return JSON.toJSONBytes(obj, features);
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

