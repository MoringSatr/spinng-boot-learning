package top.liubowen.learning10.protostuff;

import java.util.Map;

import com.google.common.collect.Maps;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * protostuff序列化和反序列化工具类
 * 
 * @author liubowen
 * @date 2017年6月8日 上午11:18:59
 */
public class ProtoStorageUtils {

	/** 缓存序列化数据对象类 */
	private static Map<Class<?>, Schema<?>> cachedSchemas = Maps.newHashMap();

	/**
	 * 获取缓存序列化数据对象类
	 * 
	 * @param clazz
	 * @return
	 */
	private static <T> Schema<T> getSchema(Class<T> clazz) {
		Schema<T> schema = (Schema<T>) cachedSchemas.get(clazz);
		if (schema == null) {
			throw new IllegalArgumentException("not illegal class");
		}
		return schema;
	}

	public static void registerSchema(Class<?> clazz) {
		Schema<?> schema = cachedSchemas.get(clazz);
		if (schema == null) {
			schema = RuntimeSchema.getSchema(clazz);
			if (schema != null) {
				cachedSchemas.put(clazz, schema);
			}
		}
	}

	/**
	 * 序列化
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> byte[] serializer(T obj) {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) obj.getClass();
		LinkedBuffer buffer = LinkedBuffer.allocate(4096);
		try {
			Schema<T> schema = getSchema(clazz);
			return ProtobufIOUtil.toByteArray(obj, schema, buffer);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			buffer.clear();
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param data
	 * @param clazz
	 * @return
	 */
	public static <T> T deserializer(byte[] data, Class<T> clazz) {
		try {
			Schema<T> schema = getSchema(clazz);
			T obj = schema.newMessage();
			ProtobufIOUtil.mergeFrom(data, obj, schema);
			return obj;
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

}
