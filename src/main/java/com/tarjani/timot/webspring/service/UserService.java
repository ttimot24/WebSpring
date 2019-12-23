package com.tarjani.timot.webspring.service;

import com.tarjani.timot.webspring.config.AuthConfig;
import com.tarjani.timot.webspring.dao.UserRolesDAO;
import com.tarjani.timot.webspring.dao.UsersDAO;
import com.tarjani.timot.webspring.entity.User;
import com.tarjani.timot.webspring.exception.RESTException;
import java.util.List;
import java.util.Optional;
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
    UsersDAO usersDAO;
    
    @Autowired
    UserRolesDAO rolesDAO;
    
    @Autowired
    AuthConfig auth;
      
    public List<User> getAllUsers(){
        return this.usersDAO.findAll();
    }
    
    public User getUserById(final Long id){
        
       Optional<User> user =  Optional.ofNullable((User) this.usersDAO.find(id));
       
       return user.orElseThrow(() -> new RESTException("User not found with id: "+id));
    }
    
    public void createUser(final User user){
        
        user.setPassword(this.auth.passwordEncoder().encode(user.getPassword()));
    
        if(user.getRole() == null){  
            user.setRole(this.rolesDAO.find(2));
        }
        
        this.usersDAO.save(user);
    }
    
    public User updateUserById(final Long id, final User updateUser){
        
        User existingUser = this.getUserById(id);
        
        //We don't have to check null because of optional in getUserById
        //if(existingUser!=null){
        existingUser.setName(updateUser.getName());
        existingUser.setUsername(updateUser.getUsername());
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setImage(updateUser.getImage());

        this.usersDAO.update(existingUser);
            
        //}
            
        return existingUser;
    }
    
    public void deleteUserById(final Long id){
         this.usersDAO.delete(this.getUserById(id));
    }

    public UsersDAO getUsersDAO() {
        return usersDAO;
    }

    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public UserRolesDAO getRolesDAO() {
        return rolesDAO;
    }

    public void setRolesDAO(UserRolesDAO rolesDAO) {
        this.rolesDAO = rolesDAO;
    }

    public AuthConfig getAuth() {
        return auth;
    }

    public void setAuth(AuthConfig auth) {
        this.auth = auth;
    }

}
