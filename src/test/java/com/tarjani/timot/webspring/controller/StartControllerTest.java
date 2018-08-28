/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author user
 */
@WebAppConfiguration
@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
public class StartControllerTest {
    
    public StartControllerTest() {
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
     * Test of index method, of class StartController.
     */
    @org.junit.Test
    public void testIndex() {
        System.out.println("index");
        Model model = null;
        StartController instance = new StartController();
        String expResult = "index";
        String result = instance.index(model);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
