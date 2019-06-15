/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.mdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author Timot
 */
@MessageDriven(name = "webspring-mdb", mappedName = "WS-MDB",
activationConfig={
        @ActivationConfigProperty(propertyName  = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName  = "destination", propertyValue = "WS_QUEUE"),
})
public class MDBReaderService implements MessageListener {
    
    private static final Logger log = LogManager.getLogger(MDBReaderService.class);    
    
    @Autowired
    private ObjectMapper mapper;
    
    @Override
    public void onMessage(Message message) {
        
        try{
        
            if(message instanceof TextMessage){
            
                TextMessage textMessage = (TextMessage) message;
                
                log.info(textMessage.getText());
                
            }
            
        }catch(Exception e){
            log.error("Error while consuming message: ",e);
        }
        
    }
    
    @PostConstruct
    public void ejbCreate(){
        log.info("Starting MDB...");
        log.info("Version:" +this.getVersion());
    }
    
    public String getVersion(){
        return this.getClass().getPackage().getImplementationVersion();
    }
}
