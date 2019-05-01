/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.entity;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Timot
 */
public class UserTest {
    
    @Test
    public void testConstructorAndGetters() throws Exception {
       
        long id = 1;
        String name = "TestUser";
        String userName = "test.user";
        String slug = "test-user";
        String password = "password";
        String email = "email";
        String image = "profile.png";
        
        User user = new User(id, name, userName, slug, password, email, new UserRole(), 0, image, null);
    
        assertEquals(name,user.getName());
        assertEquals(userName,user.getUsername());
        assertEquals(slug,user.getSlug());
        assertEquals(password,user.getPassword());
        assertEquals(email,user.getEmail());
        assertEquals(image, user.getImage());
    }
    
}
