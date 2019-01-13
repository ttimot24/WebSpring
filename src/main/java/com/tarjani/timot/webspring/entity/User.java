/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author user
 */
@Entity
@Table(name = "users")
public class User implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "username")
    private String username;
 
    @Column(name = "slug")
    private String slug;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "email")
    private String email;

    public User(){
    
    }
    
    public User(Long id, String name, String username, String slug, String password, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.slug = slug;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
}
