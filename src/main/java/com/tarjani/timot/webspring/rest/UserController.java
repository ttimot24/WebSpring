/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.rest;

import com.tarjani.timot.webspring.dao.UsersDAO;
import com.tarjani.timot.webspring.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author user
 */
@Controller
@RequestMapping({"users"})
public class UserController {
    
    @Autowired
    UsersDAO users;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> users(Model model) {
                
        return users.findAll();
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User findOne(@PathVariable("id") Long id) {
       return users.find(id);
    }
    
}
