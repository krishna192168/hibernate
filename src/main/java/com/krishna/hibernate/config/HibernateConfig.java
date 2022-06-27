package com.krishna.hibernate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

@Component
@Configuration
public class HibernateConfig {
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value(("${spring.jpa.hibernate.ddl-auto}"))
    private String ddlAuto;

    private DataSource getDataSource() {
        DriverManagerDataSource driverMgrDataSource = new DriverManagerDataSource();
        driverMgrDataSource.setDriverClassName(driverClassName);
        driverMgrDataSource.setUrl(url);
        driverMgrDataSource.setUsername(userName);
        driverMgrDataSource.setPassword(password);
        return driverMgrDataSource;
    }

    private Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.hbm2ddl.auto",ddlAuto);
        return hibernateProperties;
    }
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        sessionFactoryBean.setPackagesToScan("com.krishna.hibernate.model");
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        return sessionFactoryBean;
    }
}
