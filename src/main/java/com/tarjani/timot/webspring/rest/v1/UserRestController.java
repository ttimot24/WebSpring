/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.rest.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tarjani.timot.webspring.config.AuthConfig;
import com.tarjani.timot.webspring.dao.UserRolesDAO;
import com.tarjani.timot.webspring.dao.UsersDAO;
import com.tarjani.timot.webspring.entity.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping({"api/v1/users"})
public class UserRestController {
    
    private static final Logger log = Logger.getLogger(UserRestController.class.getName());
    
    @Autowired
    UsersDAO users;
    
    @Autowired
    UserRolesDAO roles;
      
    @Autowired
    private ObjectMapper mapper;
    
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
    
    
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public User create(HttpEntity<String> httpEntity, AuthConfig auth) throws IOException {
        String json = httpEntity.getBody();
        
        log.fine(json);
        
        User newUser = this.mapper.readValue(json,User.class);
 
        newUser.setPassword(auth.passwordEncoder().encode(newUser.getPassword()));
    
        if(newUser.getRole() == null){  
            newUser.setRole(this.roles.find(2));
        }
        
        this.users.save(newUser);
            
        return newUser;
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public User update(HttpEntity<String> httpEntity, @PathVariable("id") Long id, AuthConfig auth) throws IOException {
        
        User existingUser = this.users.find(id);
        
        if(existingUser!=null){
            
            String json = httpEntity.getBody();
        
            log.fine(json);
            
            User updateUser = this.mapper.readValue(json,User.class);

            existingUser.setName(updateUser.getName());
            existingUser.setUsername(updateUser.getUsername());
            existingUser.setEmail(updateUser.getEmail());
            existingUser.setImage(updateUser.getImage());


            this.users.update(existingUser);
            
        }
            
        return existingUser;
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Long id, AuthConfig auth) throws IOException {
        this.users.delete(this.users.find(id));
        
        ObjectNode responseJson = this.mapper.createObjectNode();
        responseJson.put("code", 200);
        responseJson.put("message", "User deleted");
        
        return this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseJson);
    } 
    
}
