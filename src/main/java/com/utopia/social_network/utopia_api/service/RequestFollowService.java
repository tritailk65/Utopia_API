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
import java.util.List;
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
            if(rqRepository.findRequestFollowByUserSourceIdAndUserTargetId(userSrc,userTar) != null || 
                    rqRepository.findRequestFollowByUserSourceIdAndUserTargetId(userTar,userSrc) != null){
                throw new ResourceNotFoundException("Lời mời follow đã được gửi");
            }

//          #endregion

            Date currentTime = new Date();
            rq.setRequestDate(currentTime);       
            rq.setUserSourceId(userSrc);
            rq.setUserTargetId(userTar);
            rq.setIsPending(1);
            rqRepository.save(rq);
            return rq;
        }catch(Exception ex)
        {
            throw new MyBadRequestException(ex.toString());
        }
    }

    @Override
    public void acceptRequestFollow(Long userSrc, Long userTar) {
        Following fl = new Following();
        RequestFollow rq = new RequestFollow();
        try{
//          #region: Check data
            if(rqRepository.findByUserSourceId(userSrc) == null){
                throw new ResourceNotFoundException("ID User source sai, kiểm tra lại");
            }
            if(rqRepository.findByUserTargetId(userTar) == null){
                throw new ResourceNotFoundException("ID User target sai, kiểm tra lại");
            }
            
            //Check request_follow have been accepted
            rq = rqRepository.findRequestFollowByUserSourceIdAndUserTargetId(userSrc, userTar);
            if(rq.getIsPending() == 0){
                throw new MyBadRequestException("Lời mời follow đã được chấp nhận");
            }
//          #endregion

            Date currentTime = new Date();
            rqRepository.updateFollowRequestSetAcceptFollow(currentTime, 0, userSrc, userTar);
            
            fl.setUserSourceId(userSrc);
            fl.setUserTargetId(userTar);
            fl.setDateFollow(currentTime);
            
            flRepo.save(fl);

        }catch(Exception ex)
        {
            throw new MyBadRequestException(ex.toString());
        }
    }

    @Override
    public void deleteRequestFollow(Long userSrc, Long userTar) { 
        try {
//          #region: Check data
            if(rqRepository.findByUserSourceId(userSrc) == null){
                throw new ResourceNotFoundException("ID User source sai, kiểm tra lại");
            }
            if(rqRepository.findByUserTargetId(userTar) == null){
                throw new ResourceNotFoundException("ID User target sai, kiểm tra lại");
            }
//          #endregion

            int i = rqRepository.deleteByUserSourceIdAndUserTargetId(userSrc,userTar);
            
        } catch (Exception ex){
            throw new MyBadRequestException(ex.toString());
        }
    }

    @Override
    public List<RequestFollow> getAllRequestFollow(Long userTar) {       
        try {
            List<RequestFollow> rqs = rqRepository.findAllByUserTargetIdAndIsPending(userTar,1);
           
            if(uRepo.findUserById(userTar) == null){
                throw new ResourceNotFoundException("Id user sai, kiểm tra lại");
            }
            
            return rqs;
        } catch (Exception ex){
            throw new MyBadRequestException(ex.toString());
        }
    }

}
