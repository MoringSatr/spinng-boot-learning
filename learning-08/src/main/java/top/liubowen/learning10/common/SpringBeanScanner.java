package top.liubowen.learning10.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author liubowen
 * @date 2018/5/8 17:23
 * @description spring bean 扫描器
 */
@Slf4j
@Component
public class SpringBeanScanner implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("beanName : {}", beanName);
        BeanContext.registerBean(beanName, bean);
        return bean;
    }
}
