/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.controller;

import com.tarjani.timot.webspring.dao.UsersDAO;
import com.tarjani.timot.webspring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
public class LoginController {
    
    @Autowired
    UsersDAO users;
    
    @RequestMapping({"login"})
    public String index(Model model) {
                
    /*    User user = users.find(1);
        
        System.out.println(user.getName());
    */    
        
        return "login";
    }
    
}
