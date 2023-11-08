/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.Following;
import com.utopia.social_network.utopia_api.exception.MyBadRequestException;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.repository.FollowingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import com.utopia.social_network.utopia_api.interfaces.IFollowingService;
import com.utopia.social_network.utopia_api.repository.UserRepository;

/**
 *
 * @author toica
 */
@Service
public class FollowingService implements IFollowingService{
    
    @Autowired
    private FollowingRepository _followingRepo;
    
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Following> getAllFollowingByUser(@Nullable Long id) {

        List<Following> followById = _followingRepo.findAllByUserTargetId(id);
        
        if (followById.isEmpty()) {
            throw new ResourceNotFoundException("Khong tim thay! Kiem tra lai ID");
        } else {
            return followById;
        }

    }    

    @Override
    public void cancelFollow(Long userSrc, Long userTar) {
        try {
//          #region: Check data
            if(userRepo.findUserById(userSrc) == null){
                throw new ResourceNotFoundException("ID User source sai, kiểm tra lại");
            }
            
            if(userRepo.findUserById(userSrc) == null){
                throw new ResourceNotFoundException("ID User target sai, kiểm tra lại");
            }
//          #endregion
            
            _followingRepo.deleteByUserSourceIdAndUserTargetId(userSrc,userTar);
            _followingRepo.deleteByUserSourceIdAndUserTargetId(userTar,userSrc);
           

        } catch (Exception ex){
            throw new MyBadRequestException(ex.toString());
        }
    }
    
}
