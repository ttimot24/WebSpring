/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.config;

import com.tarjani.timot.webspring.exception.RESTException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Timot
 */
@RestControllerAdvice
public class RestExceptionHandler {
 
    @ExceptionHandler(value = { RESTException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RESTException handleCustomException(RESTException ex) {
        return ex;
    }
    
}