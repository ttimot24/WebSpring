/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarjani.timot.webspring.config.AuthConfig;
import com.tarjani.timot.webspring.dao.UsersDAO;
import com.tarjani.timot.webspring.entity.User;
import com.tarjani.timot.webspring.entity.UserRole;
import com.tarjani.timot.webspring.service.AuthUserDetailsService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@RestController
@RequestMapping({"api/users"})
public class UserRestController {
    
    private static final Logger log = Logger.getLogger(UserRestController.class.getName());
    
    @Autowired
    UsersDAO users;
        
    private ObjectMapper mapper = new ObjectMapper();
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> users(Model model) {
                
        return this.users.findAll();
    }
    
    
    @RequestMapping(value = {"/{id}","/view/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public User read(@PathVariable("id") Long id) throws Exception {
        
       User user =  this.users.find(id);
        
       if(user==null){
           throw new Exception("User not found with id: "+id);
       }
       
       return user;
    }
    
    
    @RequestMapping(value = "/register", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public User create(HttpEntity<String> httpEntity, AuthConfig auth) throws IOException {
        String json = httpEntity.getBody();
        
        log.fine(json);
        
        
        User newUser = this.mapper.readValue(json,User.class);
 
        newUser.setPassword(auth.passwordEncoder().encode(newUser.getPassword()));
      //  newUser.setRoleId(new UserRole(2));
        
        this.users.save(newUser);
            
        return newUser;
    }
    
    @RequestMapping(value = "/delete/{id}", method = DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id, AuthConfig auth) throws IOException {
        this.users.delete(this.users.find(id));
    } 
    
    @ControllerAdvice
    public class ExceptionHandlerController {

        public static final String DEFAULT_ERROR_VIEW = "error";

        @ExceptionHandler(value = {Exception.class, RuntimeException.class})
        public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
            
            ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);

            mav.addObject("datetime", new Date());
            mav.addObject("exception", e);
            mav.addObject("url", request.getRequestURL());
            return mav;
        }
    }
    
}
