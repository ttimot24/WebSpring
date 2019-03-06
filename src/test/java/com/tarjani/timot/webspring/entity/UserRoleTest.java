/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.entity;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Timot
 */
public class UserRoleTest {
    
    public UserRoleTest() {
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
     * Test of getId method, of class UserRole.
     */
   /* @Test
    public void testGetId() {
        System.out.println("getId");
        UserRole instance = new UserRole();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */

    /**
     * Test of setId method, of class UserRole.
     */
   /* @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        UserRole instance = new UserRole();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class UserRole.
     */
    /*@Test
    public void testGetName() {
        System.out.println("getName");
        UserRole instance = new UserRole();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class UserRole.
     */
    /*@Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        UserRole instance = new UserRole();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRawRights method, of class UserRole.
     */
    /*@Test
    public void testGetRawRights() {
        System.out.println("getRawRights");
        UserRole instance = new UserRole();
        String expResult = "";
        String result = instance.getRawRights();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRawRights method, of class UserRole.
     */
    /*@Test
    public void testSetRawRights() {
        System.out.println("setRawRights");
        String rights = "";
        UserRole instance = new UserRole();
        instance.setRawRights(rights);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRights method, of class UserRole.
     */
    /*@Test
    public void testGetRights() throws Exception {
        System.out.println("getRights");
        UserRole instance = new UserRole();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getRights();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRights method, of class UserRole.
     */
    @Test
    public void testSetRights() throws Exception {
        System.out.println("setRights");
        ArrayList<String> rights = new ArrayList<String>(){{
            add("test8");
        }};
        
        UserRole instance = new UserRole();
        instance.setRights(rights);
        
        if(instance.getRawRights() == null){
            fail("RAW rights did not setted up correctly!");
        }
        
        if(!instance.getRights().contains(rights.get(0))){
            fail("Couldn't parse rights from JSON");
        }
        
    }
    
}
