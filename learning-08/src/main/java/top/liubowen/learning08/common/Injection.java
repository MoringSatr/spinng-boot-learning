package top.liubowen.learning08.common;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author liubowen
 * @date 2018/5/8 20:52
 * @description
 */
@Slf4j
public class Injection {

	private static Map<Class<?>, Injector> injectors = Maps.newHashMap();

	public static Object inject(Object bean) {
		Class<?> clazz = bean.getClass();
		Injector injector = injectors.get(clazz);
		if (injector == null) {
			injector = Injector.build(clazz);
			injectors.put(clazz, injector);
		}
		return inject(bean, injector);
	}

	public static Object inject(Object bean, Injector injector) {
		injector.inject(bean);
		return bean;
	}

}
