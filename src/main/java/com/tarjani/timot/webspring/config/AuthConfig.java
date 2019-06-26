package com.tarjani.timot.webspring.config;

import com.tarjani.timot.webspring.service.AuthUserDetailsService;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author user
 */
@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter{
    
    private static final Logger log = Logger.getLogger(AuthConfig.class.getName());
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private AuthUserDetailsService authUserDetailsService;
    
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {       
        auth.userDetailsService(this.authUserDetailsService)
                .passwordEncoder(this.passwordEncoder());
    }
 
    @Override
    protected void configure(final HttpSecurity http) throws Exception {        
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/index","/api/**","/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("uname").passwordParameter("pwd")
                .permitAll()
                .and()
            .logout()
                .clearAuthentication(true)
                .logoutSuccessUrl("/")
                .permitAll();
    }
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
