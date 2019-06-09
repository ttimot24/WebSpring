/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.controller;

import com.tarjani.timot.webspring.dao.UsersDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    
    private static final Logger log = LogManager.getLogger(LoginController.class);    
    
    @RequestMapping({"login"})
    public String index(Model model) {
     
        log.debug("Login controller is running");
        
        return "login";
    }
    
}
