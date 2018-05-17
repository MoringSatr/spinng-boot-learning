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
        return inject(bean, Injector.build(bean.getClass()));
    }

    public static Object inject(Object bean, Injector injector) {
        injector.inject(bean);
        // load(bean);
        return bean;
    }

    // public static void load(Object bean) {
    // if(bean instanceof Loadable) {
    // long start = System.currentTimeMillis();
    // ((Loadable) bean).load();
    // long used = System.currentTimeMillis() - start;
    // logger.info("Load completed {} used {}ms", bean.getClass().getName(), used);
    // }
    // }

}
