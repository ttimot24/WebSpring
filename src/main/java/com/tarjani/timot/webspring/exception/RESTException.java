/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.exception;

/**
 *
 * @author Timot
 */
public class RESTException extends RuntimeException{

    public RESTException(String message) {
        super(message);
    }
    
}
