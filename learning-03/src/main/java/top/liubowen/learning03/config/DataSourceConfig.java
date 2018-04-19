package top.liubowen.learning03.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author liubowen
 * @date 2018/4/19 15:56
 * @description 数据源配置
 */
@Configuration
public class DataSourceConfig {

    @Bean("defaultDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.default")
    public DataSource defaultDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("defaultJdbcTemplate")
    public JdbcTemplate defaultJdbcTemplate() {
        return new JdbcTemplate(defaultDataSource());
    }

    @Bean("secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("secondJdbcTemplate")
    public JdbcTemplate secondJdbcTemplate() {
        return new JdbcTemplate(secondaryDataSource());
    }

}
