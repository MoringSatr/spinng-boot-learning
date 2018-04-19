package top.liubowen.learning05.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(entityManagerFactoryRef = "secondEntityManagerFactory", transactionManagerRef = "secondTransactionManager", basePackages = {
        "top.liubowen.learning05.repository.second" })
public class SecondJpaConfig {

    @Autowired
    @Qualifier("secondDataSource")
    private DataSource dataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Bean("secondEntityManager")
    public EntityManager secondEntityManager(EntityManagerFactoryBuilder builder) {
        return secondEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean("secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource).properties(jpaProperties.getHibernateProperties(new HibernateSettings()))
                .packages("top.liubowen.learning05.entity.second") // 设置实体类所在位置
                .persistenceUnit("secondPersistenceUnit").build();
    }

    @Bean("secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(secondEntityManagerFactory(builder).getObject());
    }

}
