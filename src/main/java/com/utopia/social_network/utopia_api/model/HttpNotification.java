package com.utopia.social_network.utopia_api.model;

import java.util.ArrayList;
import java.util.List;


public class HttpNotification {
    public String id ;
    public String token  = "";
    public List<String> messagers = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getMessagers() {
        return messagers;
    }

    public void setMessagers(List<String> messagers) {
        this.messagers = messagers;
    }
    
    
}
