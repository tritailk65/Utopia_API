/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.model.HttpNotification;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SSEService {
    private final List<HttpNotification> httpNotifications = new ArrayList<>();

    public void addNotification(HttpNotification httpNotification) {
        httpNotifications.add(httpNotification);
    }
    
    public void addMessageForClient(String token, String message) {
        for (HttpNotification notification : httpNotifications) {
            if (notification.getToken().equals(token)) {
                System.out.println("add msg");
                notification.getMessagers().add(message);
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
        System.out.println("remove noti");
        while (iterator.hasNext()) {
            HttpNotification notification = iterator.next();
            if (notification.getToken().equals(token)) {
                
                iterator.remove();
            }
        }
    }
    
    public void removeById(String id) {
        Iterator<HttpNotification> iterator = httpNotifications.iterator();
        System.out.println("remove notification");
        while (iterator.hasNext()) {
            HttpNotification notification = iterator.next();
            if (notification.getId().equals(id)) {
                iterator.remove();
            }
        }
    }
}
