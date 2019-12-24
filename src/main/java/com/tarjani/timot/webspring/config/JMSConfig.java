/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.config;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.QueueConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;

/**
 *
 * @author Timot
 */
@Configuration
public class JMSConfig {

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory defaultConnectionFactory;
    
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(this.defaultConnectionFactory);
       
        return factory;
    }
    
}
