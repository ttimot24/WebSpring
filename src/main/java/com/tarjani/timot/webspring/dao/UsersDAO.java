/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.dao;

import com.tarjani.timot.webspring.entity.User;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Repository
@Transactional
public class UsersDAO extends AbstractHibernateDAO < User > {
           
    public UsersDAO(){
        this.setEntityClass(this.getClass());
    }
    
    public User getByUsername(String userName) {
		
            List<User> userList = getCurrentSession().createQuery( "FROM " + this.getEntityClass().getSimpleName() + " WHERE username = :uname" )
                    .setParameter("uname", userName).list();
            

		if(!userList.isEmpty()) {
			return (User)userList.get(0);
		}
            return null;
    }
    
}
