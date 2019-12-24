package com.tarjani.timot.webspring.mdb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import com.tarjani.timot.webspring.entity.User;
import com.tarjani.timot.webspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author Timot
 */
@Service
public class MDBUserService {
    
    private static final Logger log = LogManager.getLogger(MDBReaderService.class); 
    
    @Autowired
    private UserService userService;
    
    @JmsListener(destination = "WS_USER_Q")
    public void mdbUserQueue(Message<User> user) { 
        this.userService.createUser(user.getPayload());
    }
}
