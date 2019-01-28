/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.service;

import com.tarjani.timot.webspring.dao.UsersDAO;
import com.tarjani.timot.webspring.entity.User;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @Autowired
    private UsersDAO userDAO;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       /* User activeUser = userDAO.getByUsername(userName);
	GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
	UserDetails userDetails = (UserDetails)new User(activeUser.getUsername(),
	activeUser.getPassword(), Arrays.asList(authority));
	return userDetails;*/
        return null;
    }
    
}
