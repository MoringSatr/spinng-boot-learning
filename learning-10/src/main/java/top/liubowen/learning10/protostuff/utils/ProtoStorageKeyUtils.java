package top.liubowen.learning10.protostuff.utils;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import top.liubowen.learning10.protostuff.ProtoStorage;

import java.util.Map;
import java.util.Set;

/**
 * @author: liubowen
 * @date: 2018-12-25 13:26
 * @description:
 */
public class ProtoStorageKeyUtils {

	private static Map<Class<?>, String> keys = Maps.newHashMap();

	private static Set<String> allKeys = Sets.newHashSet();

	public static void registerKey(Class<?> clazz) {
		if (keys.containsKey(clazz)) {
			return;
		}
		ProtoStorage annotation = clazz.getAnnotation(ProtoStorage.class);
		if (annotation == null) {
			throw new IllegalArgumentException("class is illegal class, has not ProtoStorage annotation");
		}
		if (allKeys.contains(annotation.value())) {
			throw new IllegalArgumentException("class is illegal class, has same key");
		}
		keys.put(clazz, annotation.value());
	}

	public static byte[] getKey(Class<?> clazz) {
		String s = keys.get(clazz);
		return s.getBytes();
	}

	public static byte[] getKey(Class<?> clazz, long id) {
		String s = keys.get(clazz);
		String s1 = s + id;
		return s1.getBytes();
	}

	public static byte[] getKey(String flag, long id) {
		String s1 = flag + id;
		return s1.getBytes();
	}
}
