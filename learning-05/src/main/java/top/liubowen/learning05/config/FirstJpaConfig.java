package top.liubowen.learning05.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * @author liubowen
 * @date 2018/4/19 19:47
 * @description
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "firstEntityManagerFactory", transactionManagerRef = "firstTransactionManager", basePackages = {
        "top.liubowen.learning05.repository.first" })
public class FirstJpaConfig {

    @Autowired
    @Qualifier("firstDataSource")
    private DataSource dataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean("firstEntityManager")
    public EntityManager firstEntityManager(EntityManagerFactoryBuilder builder) {
        return firstEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean("firstEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource).properties(jpaProperties.getHibernateProperties(new HibernateSettings()))
                .packages("top.liubowen.learning05.entity") // 设置实体类所在位置
                .persistenceUnit("firstPersistenceUnit").build();
    }

    @Primary
    @Bean("firstTransactionManager")
    public PlatformTransactionManager firstTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(firstEntityManagerFactory(builder).getObject());
    }

}
