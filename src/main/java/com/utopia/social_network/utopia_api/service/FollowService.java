/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.RequestFollow;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.interfaces.IFollowService;
import com.utopia.social_network.utopia_api.repository.RequestFollowRepository;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toica
 */
@Service
public class FollowService implements IFollowService{
    
    @Autowired
    private RequestFollowRepository _requestRepo;
    @Autowired
    private UserRepository _userRepo;
    
    @Override
    public boolean addRequestFollow(long user_src, long user_tar) {
        try{
            User user1 = _userRepo.findUserById(user_src);
            User user2 = _userRepo.findUserById(user_tar);
            if(user1 == null || user2 == null) return false;
            RequestFollow request = _requestRepo.findRequestFollowByUserSourceIdAndUserTargetId(user_src, user_tar);
            if (request != null ) {
                return false;
            }
            RequestFollow tmp = new RequestFollow();
            Date currentTime = new Date();
            tmp.setRequestDate(currentTime);
            tmp.setUserSourceId(user_src);
            tmp.setUserTargetId(user_tar);
            _requestRepo.save(tmp);
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean acceptRequestFollow(long user_src, long user_tar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
