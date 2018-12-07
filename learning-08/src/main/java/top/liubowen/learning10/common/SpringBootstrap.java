package top.liubowen.learning10.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author liubowen
 * @date 2017/11/8 20:26
 * @description
 */
@Component
@Slf4j
public class SpringBootstrap {

    @PostConstruct
    public void onStart() {
        log.info("server start on load beans after...");
        // List<Class<?>> clazzs = ClassScanner.scan("top.liubowen.*", "*.jar");
        // BeanContext.registerBeans(clazzs);
        // TestProperty testProperty = new TestProperty();
        // testProperty.println();
    }

    @PreDestroy
    public void onDestroy() {
        log.info("server destory...");
    }
}
