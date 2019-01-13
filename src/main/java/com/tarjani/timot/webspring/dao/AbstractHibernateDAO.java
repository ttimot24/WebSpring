/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.dao;

import com.tarjani.timot.webspring.entity.User;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public abstract class AbstractHibernateDAO< T extends Serializable >{
 
   @Autowired
   private SessionFactory sessionFactory;
 
   Class entityClass = null;
   
   public void setEntityClass(Class entity){
       this.entityClass = entity;
   }
   
   public Class getEntityClass(){
      //System.out.println((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
      return this.entityClass;
   }
 
   public T find( final long id ){
      return (T) this.getCurrentSession().get( this.getEntityClass(), id );
   }
   public List< T > findAll(){
      return getCurrentSession()
       .createQuery( "FROM " + this.getEntityClass().getName() ).list();
   }
 
   public void save( final T entity ){
      this.getCurrentSession().persist( entity );
   }
 
   public T update( final T entity ){
      return (T) this.getCurrentSession().merge( entity );
   }
 
   public void delete( final T entity ){
      this.getCurrentSession().delete( entity );
   }
   public void deleteById( final long id ){
      final T entity = this.find( id);
      this.delete( entity );
   }
 
   protected final Session getCurrentSession(){
      return this.sessionFactory.getCurrentSession();
   }
}
