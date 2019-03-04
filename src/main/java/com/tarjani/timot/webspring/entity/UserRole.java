/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "user_roles")
public class UserRole implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="name")
    private String name;

    @Column(name="rights")
    private String rights;

    public UserRole() {
    }

    public UserRole(int id) {
        this.id = id;
    }

    public UserRole(int id, String name, String rights) {
        this.id = id;
        this.name = name;
        this.rights = rights;
    }
        
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRawRights() {
        return rights;
    }

    public void setRawRights(String rights) {
        this.rights = rights;
    }

    public ArrayList<String> getRights() throws IOException {
        
        if(this.rights==null){
            return null;
        }
        
        final ObjectMapper mapper = new ObjectMapper();

        ArrayList<String> parsedRights = mapper.readValue(this.getRawRights(),new TypeReference<ArrayList<String>>(){});
        
        return parsedRights;
    }

    public void setRights(final ArrayList<String> rights) throws IOException {
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(out, rights);

        final byte[] data = out.toByteArray();
        
        this.rights = new String(data);
    }
    
    
    
}
