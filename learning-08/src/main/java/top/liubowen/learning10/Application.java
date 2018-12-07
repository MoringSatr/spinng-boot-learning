package top.liubowen.learning10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import top.liubowen.learning10.common.Bootstrap;
import top.liubowen.learning10.test.TestBean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.addInitializers(new Bootstrap());
        application.run(args);
    }

    @Bean
    public TestBean testBean1() {
        return new TestBean("testBean1");
    }

    @Bean
    public TestBean testBean2() {
        return new TestBean("testBean2");
    }
}
