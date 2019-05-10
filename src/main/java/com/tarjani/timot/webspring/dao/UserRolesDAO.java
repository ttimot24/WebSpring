/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.dao;

import com.tarjani.timot.webspring.entity.UserRole;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Timot
 */
@Repository
@Transactional
public class UserRolesDAO extends AbstractHibernateDAO < UserRole >{
    
    public UserRolesDAO(){
        this.setEntityClass(UserRole.class);
    }
    
}
