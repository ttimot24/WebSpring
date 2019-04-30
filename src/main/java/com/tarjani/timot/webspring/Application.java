package com.tarjani.timot.webspring;


import com.tarjani.timot.webspring.config.AppConfig;
import com.tarjani.timot.webspring.config.AuthConfig;
import com.tarjani.timot.webspring.config.ObjectMapperConfig;
import com.tarjani.timot.webspring.config.PersistenceConfig;
import com.tarjani.timot.webspring.config.SecurityInitializer;
import com.tarjani.timot.webspring.config.SwaggerConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Application implements WebApplicationInitializer {

    @Autowired
    ConfigurableEnvironment environment;
    
    @Override
    public void onStartup(ServletContext container) {
                
        AnnotationConfigWebApplicationContext application = new AnnotationConfigWebApplicationContext();
        application.register(AppConfig.class);
        application.register(PersistenceConfig.class);
        application.register(SecurityInitializer.class);
        application.register(AuthConfig.class);
        application.register(ObjectMapperConfig.class);
      //  application.register(SwaggerConfig.class);
        
        container.addListener(new ContextLoaderListener(application));
        
         ServletRegistration.Dynamic dispatcher = container
          .addServlet("dispatcher", new DispatcherServlet(application));
         
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        
    }
    

}
