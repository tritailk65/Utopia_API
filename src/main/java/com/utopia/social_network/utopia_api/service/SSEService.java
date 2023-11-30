/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.google.gson.Gson;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.model.HttpMessage;
import com.utopia.social_network.utopia_api.model.HttpNotification;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SSEService {
    
    @Autowired
    public UserRepository userRepo;
    private final List<HttpNotification> httpNotifications = new ArrayList<>();
    
    public void addNotification(HttpNotification httpNotification) {
        httpNotifications.add(httpNotification);
    }
    
    public void addMessageForClient(String token, String content, String type , boolean isAlert) {
        for (HttpNotification notification : httpNotifications) {
            if (notification.getToken().equals(token)) {
                HttpMessage msg = new HttpMessage(content,type,isAlert);
                Gson gson = new Gson();
                String json = gson.toJson(msg);
                notification.getMessagers().add(json);
            }
        }
    }

    public void removeNotification(HttpNotification httpNotification) {
        httpNotifications.remove(httpNotification);
    }

    public List<HttpNotification> getHttpNotifications() {
        return httpNotifications;
    }

    public void removeByToken(String token) {
        Iterator<HttpNotification> iterator = httpNotifications.iterator();
        while (iterator.hasNext()) {
            HttpNotification notification = iterator.next();
            if (notification.getToken().equals(token)) {
                
                iterator.remove();
            }
        }
    }
    
    public void removeById(String id) {
        Iterator<HttpNotification> iterator = httpNotifications.iterator();
        while (iterator.hasNext()) {
            HttpNotification notification = iterator.next();
            if (notification.getId().equals(id)) {
                iterator.remove();
            }
        }
    }
    
    public void sendUnseenNotification(String token) {
        boolean flag = false;
        User user = userRepo.findUserById(Long.valueOf(token));
        if(user.getAlert() != null && !user.getAlert().isEmpty()){
            Gson gson = new Gson();
            java.lang.reflect.Type listType = new TypeToken<List<HttpMessage>>(){}.getType();
            List<HttpMessage> messages = gson.fromJson(user.getAlert(), listType);
            for (HttpNotification notification : httpNotifications) {
                if (notification.getToken().equals(token)) {
                    flag = true;
                    for(HttpMessage msg : messages){
                        String json = gson.toJson(msg);
                        notification.getMessagers().add(json);
                    }
                }
            }
        }
        userRepo.updateUserSetAlertById("",Long.valueOf(token));
    }
    
    public boolean checkUserOffline(String token,String content, String type) {
        boolean flag = false;
        for (HttpNotification notification : httpNotifications) {
            if (notification.getToken().equals(token)) {
                flag = true;
            }
        }
        if(flag == false){
            HttpMessage msg = new HttpMessage(content,type,false);
            Gson gson = new Gson();
            User user = userRepo.findUserById(Long.valueOf(token));
            List<HttpMessage> messages = new ArrayList<HttpMessage>();
            String alert = "";
            if(user.getAlert() == null || user.getAlert().isEmpty()){
                messages.add(msg);
                alert = gson.toJson(messages);
            }
            else{
                java.lang.reflect.Type listType = new TypeToken<List<HttpMessage>>(){}.getType();
                messages = gson.fromJson(user.getAlert(), listType);
                messages.add(msg);
                alert = gson.toJson(messages);
            }
            userRepo.updateUserSetAlertById(alert, Long.valueOf(token));
        }
        return flag;
    }

}
