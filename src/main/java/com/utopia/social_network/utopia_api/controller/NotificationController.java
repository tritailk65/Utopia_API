/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;

import com.utopia.social_network.utopia_api.interfaces.INotificationService;
import com.utopia.social_network.utopia_api.utils.APIResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author trita
 */

@CrossOrigin
@RestController
@RequestMapping("/api/Notification")
public class NotificationController {
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
    
    @Autowired
    private INotificationService notificationService;
    
    @Autowired
    private APIResult rs;
    
    @GetMapping(value = {"/ThisWeek/User/{id}"})
    private APIResult getAllNotiThisWeek(@PathVariable("id") Long id) {
        return new APIResult(200, "Ok", null, notificationService.getAllNotiByUserId(id,"week"));
    }
    
    @GetMapping(value = {"/ThisMonth/User/{id}"})
    private APIResult getAllNotiThisMonth(@PathVariable("id") Long id) {
        return new APIResult(200, "Ok", null, notificationService.getAllNotiByUserId(id,"month"));
    }
    
    @GetMapping(value = {"/Earlier/User/{id}"})
    private APIResult getAllNotiEarlier(@PathVariable("id") Long id) {
        return new APIResult(200, "Ok", null, notificationService.getAllNotiByUserId(id, "earlier"));
    }
}
