package top.liubowen.learning07.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author liubowen
 * @date 2018/5/4 11:23
 * @description 该类会在程序最开始启动，在加载spring bean 之前
 */
@Slf4j
public class Bootstrap implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("Bootstrap initialize ...");
        Constants.load();
    }

}
