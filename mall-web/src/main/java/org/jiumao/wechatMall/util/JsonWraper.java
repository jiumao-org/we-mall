package org.jiumao.wechatMall.util;

import org.codehaus.jackson.JsonNode;


/**
 * {@link JsonNode} 包装
 * 
 * @author ppf@jiumao.org
 */
public final class JsonWraper {
    private JsonNode nodeThis;
    private JsonNode root;
    private JsonNode marked;


    private JsonWraper() {
    }


    public static JsonWraper toJsonWraper(JsonNode node) {
        JsonWraper wraper = new JsonWraper();
        wraper.nodeThis = node;
        wraper.root = node;
        return wraper;
    }
    
    public void mark(){
    	marked = nodeThis;
    }
    
    public void toMark(){
    	nodeThis = marked;
    }
    
    public void reset(){
    	nodeThis = root;
    }


    public JsonWraper getJsonNode(String key) {
        nodeThis = JsonUtil.getJsonNode(nodeThis, key);
        return this;
    }
    
    public JsonNode getNodeThis() {
		return nodeThis;
	}


	public void setNodeThis(JsonNode nodeThis) {
		this.nodeThis = nodeThis;
	}


	@SuppressWarnings("unchecked")
    public <T> T getValue(String key, T defaultValue) {
        String v = JsonUtil.getValue(nodeThis, key);
        return null == v ? defaultValue : (T) v;
    }

	@SuppressWarnings("unchecked")
    public <T> T getValue(T defaultValue) {
        String v = nodeThis.asText();
        return null == v ? defaultValue : (T) v;
    }

    public static JsonNode asJsonNode(JsonWraper wraper) {
        return wraper.nodeThis;
    }


    @Override
    public String toString() {
        return nodeThis.toString();
    }
}
