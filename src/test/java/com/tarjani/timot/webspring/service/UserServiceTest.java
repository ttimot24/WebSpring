/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.service;

import com.tarjani.timot.webspring.dao.UsersDAO;
import com.tarjani.timot.webspring.entity.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author Timot
 */
public class UserServiceTest {
    
    public UserServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUserById method, of class UserService.
     */
    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
        
        User expResult = new User(1L,null,null,null,null,null,null,0,null,null);
        
        UsersDAO usersDAOMock = Mockito.mock(UsersDAO.class);
        Mockito.when(usersDAOMock.find(Mockito.anyLong())).thenReturn(expResult);
        
        Long id = 1L;
        UserService service = new UserService();
        service.setUsersDAO(usersDAOMock);
                
        User result = service.getUserById(id);
        assertEquals(expResult, result);

    }

}
