package com.tarjani.timot.webspring.mdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarjani.timot.webspring.entity.User;
import com.tarjani.timot.webspring.service.UserService;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
/**
 *
 * @author Timot
 */
@Service
public class MDBUserService{
    
    private static final Logger log = LogManager.getLogger(MDBReaderService.class);    
 
    @Autowired
    private UserService userService;
    
    @Autowired
    private ObjectMapper mapper;
    
    @JmsListener(destination = "WS_USER_Q")
    public void SampleJmsListenerMethod(Message<String> userString) throws IOException {
        log.info(userString.getPayload());
        this.userService.createUser(this.mapper.readValue(userString.getPayload(),User.class));
    }

}
