package org.jiumao.db.kafka.stream.serdes;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.jiumao.common.utils.JsonSerializable;


/**
 * @author ppf@jiumao.org
 * @date 2017年11月30日
 * @param <T> 反序列化对象 fastjson
 */
public class GenericDeserializer<T> implements Deserializer<T> {

	private Class<T> type;

	public GenericDeserializer() {}
	
	public GenericDeserializer(Class<T> type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		if(type != null) {
			return;
		}
		
		String typeProp = isKey ? "key.deserializer.type" : "value.deserializer.type";
		String typeName = (String)configs.get(typeProp);
		try {
			type = (Class<T>)Class.forName(typeName);
		} catch (Exception ex) {
			throw new SerializationException("Failed to initialize GenericDeserializer for " + typeName, ex);
		}
	}

	@Override
	public T deserialize(String topic, byte[] data) {
		if (data == null) {
			return null;
		}
		try {
			return JsonSerializable.decode(data, type);
		} catch (Exception ex) {
			throw new SerializationException(ex);
		}
	}

	@Override
	public void close() {
	}

}