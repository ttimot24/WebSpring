package com.tarjani.timot.webspring.rest.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tarjani.timot.webspring.entity.User;
import com.tarjani.timot.webspring.service.UserService;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    
    private static final Logger log = LogManager.getLogger(UserRestController.class);    
   
    @Autowired
    UserService userService;
    
    @Autowired
    private ObjectMapper mapper;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> users(Model model) {
                
        return this.userService.getAllUsers();
    }
    
    
    @RequestMapping(value = {"/{id}","/view/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public User read(@PathVariable("id") Long id) throws Exception {
       return this.userService.getUserById(id);
    }
    
    
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public User create(HttpEntity<String> httpEntity) throws IOException {
        String json = httpEntity.getBody();
        
        log.info(json);
        
        User newUser = this.mapper.readValue(json,User.class);
 
        this.userService.createUser(newUser);
            
        return newUser;
    }
    
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public User update(HttpEntity<String> httpEntity, @PathVariable("id") Long id) throws IOException {
        
        String json = httpEntity.getBody();
        
        log.info(json);
            
        User updateUser = this.mapper.readValue(json,User.class);
        
        return this.userService.updateUserById(id, updateUser);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Long id) throws IOException {
       
        this.userService.deleteUserById(id);
        
        ObjectNode responseJson = this.mapper.createObjectNode();
        responseJson.put("code", 200);
        responseJson.put("message", "User deleted");
        
        return this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseJson);
    } 
    
}
