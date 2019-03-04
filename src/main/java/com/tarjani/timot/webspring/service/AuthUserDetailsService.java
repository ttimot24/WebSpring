/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.service;

import com.tarjani.timot.webspring.dao.UsersDAO;
import java.util.Arrays;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class AuthUserDetailsService implements UserDetailsService{

    private static final Logger log = Logger.getLogger(AuthUserDetailsService.class.getName());
    
    @Autowired
    private UsersDAO userDAO;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.tarjani.timot.webspring.entity.User activeUser = userDAO.getByUsername(userName);
        
	GrantedAuthority authority = new SimpleGrantedAuthority(activeUser.getRole().getName());
	UserDetails userDetails = (UserDetails)new User(activeUser.getUsername(),
	activeUser.getPassword(), Arrays.asList(authority));
	return userDetails;
    }
    
}
