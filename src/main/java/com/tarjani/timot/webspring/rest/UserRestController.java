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
import java.util.List;
import java.util.logging.Logger;
import javax.transaction.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> users(Model model) {
                
        return this.users.findAll();
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User findOne(@PathVariable("id") Long id) {
       return this.users.find(id);
    }
    
    
    @RequestMapping(value = "/register", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public User greetingJson(HttpEntity<String> httpEntity, AuthConfig auth) throws IOException {
        String json = httpEntity.getBody();
        
        log.fine(json);
        
        ObjectMapper mapper = new ObjectMapper();
        User newUser = mapper.readValue(json,User.class);
 
        newUser.setPassword(auth.passwordEncoder().encode(newUser.getPassword()));
      //  newUser.setRoleId(new UserRole(2));
        
        this.users.save(newUser);
            
        return newUser;
    }
    
}
