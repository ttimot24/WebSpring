/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.webservice;

import com.tarjani.timot.webspring.dao.UsersDAO;
import com.tarjani.timot.webspring.entity.User;
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
    UsersDAO users;
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "register")
    public String register(@WebParam(name = "name") String name, @WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "email") String email) {
        
        
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setSlug(username);
        user.setPassword(password);
        user.setEmail(email);
        
        users.save(user);
        
        return "User " + user.getName() + " succesfully registered!";
    }
}
