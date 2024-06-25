package com.VladIltekov.JobVacancyTestTask.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@PropertySource("classpath:application.properties")
@Configuration
@Component
@EnableTransactionManagement
@EnableJpaRepositories("com.VladIltekov.JobVacancyTestTask.repositories")
public class HibernateUtil {

    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("${spring.datasource.driverClassName}");
        dataSource.setUrl("${spring.datasource.url}");
        dataSource.setUsername("${spring.datasource.username}");
        dataSource.setPassword("${spring.datasource.password}");

        return dataSource;
    }


    private Properties hibernateProperties(){
        Properties properties = new Properties();
            properties.put("hibernate.dialect", "${spring.jpa.properties.hibernate.dialect}");
            properties.put("hibernate.show-sql", "${spring.jpa.show-sql}");

            return properties;
    }

    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        final LocalContainerEntityManagerFactoryBean mng = new LocalContainerEntityManagerFactoryBean();
        mng.setDataSource(dataSource());
        mng.setPackagesToScan("com.VladIltekov.JobVacancyTestTask.models");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        mng.setJpaVendorAdapter(vendorAdapter);
        mng.setJpaProperties(hibernateProperties());

        return mng;
    }

    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return transactionManager;
    }
}
