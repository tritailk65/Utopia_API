/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.Following;
import com.utopia.social_network.utopia_api.entity.RequestFollow;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IFollowService;
import com.utopia.social_network.utopia_api.repository.FollowingRepository;
import com.utopia.social_network.utopia_api.repository.RequestFollowRepository;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
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
    @Autowired
    private FollowingRepository _followingRepo;
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
    public boolean acceptRequestFollow(long user_src, long user_tar){
        try{
            User user1 = _userRepo.findUserById(user_src);
            User user2 = _userRepo.findUserById(user_tar);
            if(user1 == null || user2 == null) return false;
            RequestFollow request = _requestRepo.findRequestFollowByUserSourceIdAndUserTargetId(user_src, user_tar);
            if(request == null){
                return false;
            }
            RequestFollow tmp = new RequestFollow();
            Date currentTime = new Date();
            tmp.setApproveDate(currentTime);
            tmp.setUserSourceId(user_src);
            tmp.setUserTargetId(user_tar);
            _requestRepo.save(tmp);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    @Override
    public List<Following> getAllFollow(@Nullable Long id) {
        if (id == null) {
            return _followingRepo.findAll();
        } else {
            List<Following> followById = _followingRepo.findAllById(id);
            if (followById.size() == 0) {
                throw new ResourceNotFoundException("Khong tim thay! Kiem tra lai ID");
            } else {
                return followById;
            }
        }
    }    
}
