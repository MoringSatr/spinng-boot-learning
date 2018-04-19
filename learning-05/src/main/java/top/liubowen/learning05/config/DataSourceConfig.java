package top.liubowen.learning05.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author liubowen
 * @date 2018/4/19 15:56
 * @description 数据源配置
 */
@Configuration
public class DataSourceConfig {

    @Bean("firstDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.first")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

}
