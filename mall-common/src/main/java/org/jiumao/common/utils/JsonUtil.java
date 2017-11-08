package org.jiumao.common.utils;


import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.NullNode;


import java.io.IOException;
import java.io.StringReader;
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
    
    @Nullable
    public static <E> String bean2Json(E e) {
    	ObjectMapper m = new ObjectMapper();
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
    
    public static JsonWraper jsonWraper(String json){
		ObjectMapper m = new ObjectMapper();// 复用
		JsonNode tree = JsonUtil.jsonTree(m, json);
		return JsonWraper.toJsonWraper(tree);
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

}

