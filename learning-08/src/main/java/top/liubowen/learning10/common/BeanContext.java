package top.liubowen.learning10.common;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author liubowen
 * @date 2018/5/8 17:11
 * @description bean管理
 */
@Slf4j
public class BeanContext {

    private static Map<Class<?>, Object> beansByClass = Maps.newHashMap();
    private static Map<String, Object> beansByName = Maps.newHashMap();

    public static void registerBean(String name, Object bean) {
        if (beansByName.containsKey(name)) {
            log.error("have same bean be register, bean name : {} ", name);
            System.exit(1);
        }
        beansByName.put(name, bean);
        Class<?> clazz = bean.getClass();
        beansByClass.putIfAbsent(clazz, bean);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fetchBean(Class<T> clazz) {
        return (T) beansByClass.get(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fetchBean(String name) {
        return (T) beansByName.get(name);
    }

    public static void registerBeans(List<String> clazzNames) {
        loadPrototypeBeans(clazzNames);
    }

    private static void loadPrototypeBeans(List<String> clazzNames) {
        GenMP.makePatch(clazzNames);
    }

}
