package top.liubowen.learning07;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.liubowen.learning07.common.Bootstrap;
import top.liubowen.learning07.common.Constants;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.addInitializers(new Bootstrap());
        ConfigurableApplicationContext context = application.run(args);

        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        for (String profile : activeProfiles) {
            log.info("Spring Boot 使用profile为: {}", profile);
        }

        log.info("Constants.RESOURCE_URL: {}", Constants.RESOURCE_URL);
        log.info("Constants.SERVER_URL: {}", Constants.SERVER_URL);
        log.info("Constants.SERVER_SOCKET_URL: {}", Constants.SERVER_SOCKET_URL);
        log.info("Constants.SERVER_ID: {}", Constants.SERVER_ID);
        log.info("Constants.SERVER_NAME: {}", Constants.SERVER_NAME);

    }

}
