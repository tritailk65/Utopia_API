/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.Following;
import com.utopia.social_network.utopia_api.entity.RequestFollow;
import com.utopia.social_network.utopia_api.exception.MyBadRequestException;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IRequestFollowService;
import com.utopia.social_network.utopia_api.repository.FollowingRepository;
import com.utopia.social_network.utopia_api.repository.RequestFollowRepository;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trita
 */
@Service
public class RequestFollowService implements IRequestFollowService{
    
    @Autowired
    private RequestFollowRepository rqRepository;
    
    @Autowired
    private FollowingRepository flRepo;
    
    @Autowired
    private UserRepository uRepo;

    @Override
    public RequestFollow sendRequestFollow(Long userSrc, Long userTar) {
        
        RequestFollow rq = new RequestFollow();
        
        try{
            
//          #region: Check data
            if(uRepo.findUserById(userSrc) == null){
                throw new ResourceNotFoundException("ID User source sai, kiểm tra lại");
            }
            if(uRepo.findUserById(userTar) == null){
                throw new ResourceNotFoundException("ID User target sai, kiểm tra lại");
            }
            if(rqRepository.findRequestFollowByUserSourceIdAndUserTargetId(userSrc,userTar) != null){
                throw new ResourceNotFoundException("Lời mời follow đã được gửi");
            }

//          #endregion

            Date currentTime = new Date();
            rq.setRequestDate(currentTime);       
            rq.setUserSourceId(userSrc);
            rq.setUserTargetId(userTar);
            rqRepository.save(rq);
            return rq;
        }catch(Exception ex)
        {
            throw new MyBadRequestException(ex.toString());
        }
    }

    @Override
    public RequestFollow acceptRequestFollow(Long userSrc, Long userTar) {
        RequestFollow rq = new RequestFollow();
        Following fl = new Following();
        
        try{
//          #region: Check data
            if(rqRepository.findRequestFollowByUserSourceId(userSrc) == null){
                throw new ResourceNotFoundException("ID User source sai, kiểm tra lại");
            }
            if(rqRepository.findRequestFollowByUserTargetId(userTar) == null){
                throw new ResourceNotFoundException("ID User target sai, kiểm tra lại");
            }
//          #endregion

            Date currentTime = new Date();
            rq.setApproveDate(currentTime);       

            rqRepository.save(rq);
            
            fl.setUserSourceId(userSrc);
            fl.setUserTargetId(userTar);
            fl.setDateFollow(currentTime);
            
            flRepo.save(fl);
            
            return rq;
        }catch(Exception ex)
        {
            throw new MyBadRequestException(ex.toString());
        }
    }

    @Override
    public Boolean deleteRequestFollow(Long userSrc, Long userTar) { 
        try {
//          #region: Check data
            if(rqRepository.findRequestFollowByUserSourceId(userSrc) == null){
                throw new ResourceNotFoundException("ID User source sai, kiểm tra lại");
            }
            if(rqRepository.findRequestFollowByUserTargetId(userTar) == null){
                throw new ResourceNotFoundException("ID User target sai, kiểm tra lại");
            }
//          #endregion

            rqRepository.deleteByUserSourceIdAndUserTargetId(userSrc,userTar);
            
            return true;
            
        } catch (Exception ex){
            throw new MyBadRequestException(ex.toString());
        }
    }


}
