/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author Timot
 */
@Component
public class ScheduledJob {
    
    private static final Logger log = LogManager.getLogger(ScheduledJob.class);
    
    @Scheduled(cron ="0 */15 * * * ?")
    public void run(){
        System.out.println("Scheduled job run");
        log.info("Scheduled job run");
    }
    
    
}
