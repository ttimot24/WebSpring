/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Component
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
 
    @Autowired
    Environment env;
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(this.dataSource());
        sessionFactory.setPackagesToScan("com.tarjani.timot.webspring.entity");
        sessionFactory.setHibernateProperties(this.hibernateProperties());
 
        return sessionFactory;
    }
 
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url")+"/"+env.getProperty("db.schemas"));
        dataSource.setUsername(env.getProperty("db.user"));
        dataSource.setPassword(env.getProperty("db.password"));
        
        Properties props = new Properties();
        props.setProperty("serverTimezone",env.getProperty("db.timezone"));
        dataSource.setConnectionProperties(props);
        
        return dataSource;
    }
 
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(this.sessionFactory().getObject());
        transactionManager.setAutodetectDataSource(true);
        
        return transactionManager;
    }
 
    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        /*hibernateProperties.setProperty(
          "hibernate.hbm2ddl.auto", "create-drop");*/
        hibernateProperties.setProperty(
          "hibernate.dialect", env.getProperty("db.dialect"));
        hibernateProperties.setProperty(
          "hibernate.show_sql", env.getProperty("db.show_sql"));
 
        return hibernateProperties;
    }
}