/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.config;

import com.tarjani.timot.webspring.interceptor.GlobalViewVariablesInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 *
 * @author user
 */
@Configuration
@EnableWebMvc
@EnableJms
@EnableScheduling
@ComponentScan({"com.tarjani.timot.webspring.*"})
@PropertySource(value = "application.properties", ignoreResourceNotFound = false)
public class AppConfig implements WebMvcConfigurer{
    
    @Autowired
    Environment environment;
    
    @Bean
    public UrlBasedViewResolver getViewResovler() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setViewClass(JstlView.class);
        urlBasedViewResolver.setPrefix("/WEB-INF/jsp/");
        urlBasedViewResolver.setSuffix(".jsp");
        return urlBasedViewResolver;
    }
    
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("WEB-INF/");
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addWebRequestInterceptor(new GlobalViewVariablesInterceptor(this.environment));
    }
    
    public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**");
    }
    
    @Bean
    public HandlerExceptionResolver sentryExceptionResolver() {
        return new io.sentry.spring.SentryExceptionResolver();
    }

    
}
