package com.tarjani.timot.webspring.webservice;

import com.tarjani.timot.webspring.entity.User;
import com.tarjani.timot.webspring.service.UserService;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author user
 */
@Service
@WebService(serviceName = "UserWS")
public class UserWS extends SpringBeanAutowiringSupport {

    @Autowired
    UserService userService;
    
    @WebMethod(operationName = "register")
    public String register(@WebParam(name = "name") String name, @WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "email") String email) {
        
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setSlug(username);
        user.setPassword(password);
        user.setEmail(email);
        
        this.userService.createUser(user);
        
        return "User " + user.getName() + " succesfully registered!";
    }
}