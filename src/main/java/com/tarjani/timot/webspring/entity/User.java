/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarjani.timot.webspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.persistence.*;

/**
 *
 * @author user
 */
@Entity
@Table(name = "users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "username")
    private String username;
 
    @Column(name = "slug")
    private String slug;
    
    @JsonIgnore
    @Column(name = "password")
    private String password;
    
    @Column(name = "email")
    private String email;

    @ManyToOne(optional = true)
    @JoinColumn(name="role_id")
    private UserRole role;
    
    @Column(name = "visits")
    private int visits;
    
    @Column(name = "image")
    private String image;
    
    @Column(name = "remember_token")
    private String rememberToken;
    
    public User(){
    
    }
    
    public User(Long id, String name, String username, String slug, String password, String email, UserRole role, int visits, String image, String remember_token) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.slug = slug;
        this.password = password;
        this.email = email;
      //  this.roleId = role_id;
        this.role = role;
        this.visits = visits;
        this.image = image;
        this.rememberToken = remember_token;
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

    @JsonProperty("slug")
    public String getSlug() {
       
        if(this.slug==null){
            this.slugify(this.getUsername());
        }
        
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
    
    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public boolean hasPermission(String permission) throws IOException{
        return this.getRole().getRights().contains(permission.toLowerCase());
    }
    
    private String slugify(String input){
        
        Pattern NONLATIN = Pattern.compile("[^\\w-]");
        Pattern WHITESPACE = Pattern.compile("[\\s]");
        Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");


        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = EDGESDHASHES.matcher(slug).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
        
    }


}
