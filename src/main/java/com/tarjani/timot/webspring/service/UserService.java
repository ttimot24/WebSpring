package com.tarjani.timot.webspring.service;

import com.tarjani.timot.webspring.config.AuthConfig;
import com.tarjani.timot.webspring.dao.UserRolesDAO;
import com.tarjani.timot.webspring.dao.UsersDAO;
import com.tarjani.timot.webspring.entity.User;
import com.tarjani.timot.webspring.exception.RESTException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Timot
 */
@Service
public class UserService {
    
    private static final Logger log = LogManager.getLogger(UserService.class);    
        
    @Autowired
    UsersDAO users;
    
    @Autowired
    UserRolesDAO roles;
    
    @Autowired
    AuthConfig auth;
      
    public List<User> getAllUsers(){
        return this.users.findAll();
    }
    
    public User getUserById(Long id){
        
       User user =  this.users.find(id);
        
       if(user==null){
           throw new RESTException("User not found with id: "+id);
       }
       
       return user;
    }
    
    public void createUser(User user){
        
        user.setPassword(this.auth.passwordEncoder().encode(user.getPassword()));
    
        if(user.getRole() == null){  
            user.setRole(this.roles.find(2));
        }
        
        this.users.save(user);
    }
    
    public User updateUserById(Long id, User updateUser){
        
        User existingUser = this.users.find(id);
        
        if(existingUser!=null){
            
            existingUser.setName(updateUser.getName());
            existingUser.setUsername(updateUser.getUsername());
            existingUser.setEmail(updateUser.getEmail());
            existingUser.setImage(updateUser.getImage());

            this.users.update(existingUser);
            
        }
            
        return existingUser;
    }
    
    public void deleteUserById(Long id){
         this.users.delete(this.users.find(id));
    }
    
}
