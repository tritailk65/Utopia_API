/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.Notification;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.MyBadRequestException;
import com.utopia.social_network.utopia_api.interfaces.INotificationService;
import com.utopia.social_network.utopia_api.model.NotificationModel;
import com.utopia.social_network.utopia_api.repository.NotificationRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trita
 */
@Service
public class NotificationService implements INotificationService{
    
    @Autowired
    private NotificationRepository notiRepo;
    
    @Autowired
    private UserService userService;

    @Override
    public List<NotificationModel> getAllNotiByUserId(Long id, String when) {
        try {         
            List<Notification> notiList = new ArrayList<Notification>();
            List<NotificationModel> notificationModels = new ArrayList<NotificationModel>();
            
            Calendar cal = Calendar.getInstance();  
            Date date;
            
            if (when.contains("week")){
                cal.add(Calendar.DAY_OF_YEAR, -7);  
                date = cal.getTime();  
                notiList = notiRepo.findAllNotificationByUser(id,date);
            } else if (when.contains("month")){
                cal.add(Calendar.DAY_OF_YEAR, -31);  
                date = cal.getTime();  
                notiList = notiRepo.findAllNotificationByUser(id,date);
            } else if (when.contains("earlier")){
                cal.add(Calendar.DAY_OF_YEAR, -62);  
                date = cal.getTime();  
                notiList = notiRepo.findAllNotificationByUser(id,date);
            }
            
            if (!notiList.isEmpty()){
                for (Notification x : notiList) {
                    User uTarget = userService.getUserById(x.getUserId());
                    User uSource = userService.getUserById(x.getSourceId());
                    
                    NotificationModel n = new NotificationModel();
                    n.setUserSource(uSource);
                    n.setUserTaget(uTarget);
                    n.setType(x.getType());
                    n.setContext(x.getContext());
                    n.setUpdateAt(x.getUpdateAt());
                    
                    notificationModels.add(n);
                }
                
            }
            return notificationModels;
        } catch (Exception ex) {
            throw new MyBadRequestException(ex.toString());
        }
        
    }

}
