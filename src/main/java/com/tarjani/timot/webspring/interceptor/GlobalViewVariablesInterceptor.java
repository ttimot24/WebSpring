package com.tarjani.timot.webspring.interceptor;

import com.tarjani.timot.webspring.config.AppConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class GlobalViewVariablesInterceptor implements WebRequestInterceptor {

    Environment env;

    public GlobalViewVariablesInterceptor(Environment env) {
        this.env = env;
    }
        
    @Override
    public void preHandle(WebRequest wr) throws Exception {

    }

    @Override
    public void postHandle(WebRequest wr, ModelMap mm) throws Exception {
        mm.addAttribute("appName", this.env.getProperty("spring.app.name"));
    }

    @Override
    public void afterCompletion(WebRequest wr, Exception excptn) throws Exception {
        
    }

}
