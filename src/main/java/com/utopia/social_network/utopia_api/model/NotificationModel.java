/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

import com.utopia.social_network.utopia_api.entity.User;
import java.util.Date;

/**
 *
 * @author trita
 */
public class NotificationModel {

    private User userSource;
    private User userTaget;
    private String type;
    private String context;
    private Date updateAt;

    public NotificationModel(User userSource, User userTaget, String type, String context, Date updateAt) {
        this.userSource = userSource;
        this.userTaget = userTaget;
        this.type = type;
        this.context = context;
        this.updateAt = updateAt;
    }
    
    public NotificationModel() {}

    public User getUserSource() {
        return userSource;
    }

    public void setUserSource(User userSource) {
        this.userSource = userSource;
    }

    public User getUserTaget() {
        return userTaget;
    }

    public void setUserTaget(User userTaget) {
        this.userTaget = userTaget;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    
    
}
