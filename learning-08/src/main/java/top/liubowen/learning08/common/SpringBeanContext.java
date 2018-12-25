package top.liubowen.learning08.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author liubowen
 * @date 2018/5/8 17:11
 * @description bean管理
 */
@Slf4j
@Component
public class SpringBeanContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanContext.applicationContext == null) {
            SpringBeanContext.applicationContext = applicationContext;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T fetchBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fetchBean(String name) {
        return (T) applicationContext.getBean(name);
    }

}
