package top.liubowen.learning10.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import top.liubowen.learning10.test.TestModel;
import top.liubowen.learning10.test.TestProperty;

@Slf4j
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("ApplicationStartup onApplicationEvent ...");
        // BeanContext.print();
        // List<Class<?>> clazzs = ClassScanner.scan("top.liubowen.*", "*.jar");
        // BeanContext.registerBeans(clazzs);
        TestProperty testProperty = new TestProperty();
        testProperty.println();

        new TestModel().print();
    }

}