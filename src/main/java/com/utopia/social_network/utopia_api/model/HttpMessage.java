/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author DELL
 */
public class HttpMessage {
    public String content = "";
    public String type = "";
    public String date = "";
    public boolean isAlert = false ;
    
    public HttpMessage(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        this.date = formattedTime;
    }
    
    public HttpMessage(String content,String type,boolean isAlert){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        this.date = formattedTime;
        this.content = content;
        this.type = type;
        this.isAlert = isAlert;
    }
    
    
    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public boolean isIsAlert() {
        return isAlert;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIsAlert(boolean isAlert) {
        this.isAlert = isAlert;
    }
    
    
}
