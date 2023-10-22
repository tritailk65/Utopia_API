/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

/**
 *
 * @author hieu
 */
public class UserModel {
    public long user_id;
    public String email;
    public String username;   
    public String password;
    public String phone;
    public UserModel(long user_id, String email, String password, String username,String phone){
        this.user_id = user_id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone; 
    }

    public long getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    
}
