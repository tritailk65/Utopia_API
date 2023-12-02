/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.services;

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
    private UserRepository userRepo;

    @Override
    public RequestFollow sendRequestFollow(Long userSrc, Long userTar) {
        
        RequestFollow rq = new RequestFollow();
        Following fl = new Following();
        
        try{          
//          #region: Check data
            if(userRepo.findUserById(userSrc) == null){
                throw new ResourceNotFoundException("ID User source sai, kiểm tra lại");
            }
            if(userRepo.findUserById(userTar) == null){
                throw new ResourceNotFoundException("ID User target sai, kiểm tra lại");
            }
            if(flRepo.findFollowingByUserSourceIdAndUserTargetId(userSrc,userTar) != null){
                throw new MyBadRequestException("Bạn đã theo dõi người này rồi");
            }
            if(rqRepository.findRequestFollowByUserSourceIdAndUserTargetIdAndIsPending(userSrc,userTar,1) != null){
                throw new MyBadRequestException("Đã có request follow gửi đi");
            }
//          #endregion

            Date currentTime = new Date();
            
            rq.setRequestDate(currentTime);       
            rq.setUserSourceId(userSrc);
            rq.setUserTargetId(userTar);
            rq.setIsPending(1);
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
    public void acceptRequestFollow(Long userSrc, Long userTar) {
        Following fl = new Following();
        RequestFollow rq = new RequestFollow();
        try{
//          #region: Check data
            if(userRepo.findUserById(userSrc) == null){
                throw new ResourceNotFoundException("ID User source sai, kiểm tra lại");
            }
            if(userRepo.findUserById(userSrc) == null){
                throw new ResourceNotFoundException("ID User target sai, kiểm tra lại");
            }

            rq = rqRepository.findRequestFollowByUserSourceIdAndUserTargetIdAndIsPending(userSrc,userTar, 1);
                   
//          #endregion

            Date currentTime = new Date();
            rqRepository.updateFollowRequestSetAcceptFollow(currentTime, 0, userSrc, userTar, rq.getId());
            
            fl.setUserSourceId(userTar);
            fl.setUserTargetId(userSrc);
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
            if(userRepo.findUserById(userSrc )== null){
                throw new ResourceNotFoundException("ID User source sai, kiểm tra lại");
            }
            if(userRepo.findUserById(userTar) == null){
                throw new ResourceNotFoundException("ID User target sai, kiểm tra lại");
            }
//          #endregion

            int i = rqRepository.deleteByUserSourceIdAndUserTargetId(userSrc,userTar);
            
        } catch (Exception ex){
            throw new MyBadRequestException(ex.toString());
        }
    }

    @Override
    public void cancelRequestFollow(Long userSrc, Long userTar) {
        try {
//          #region: Check data
            if(userRepo.findUserById(userSrc )== null){
                throw new ResourceNotFoundException("ID User source sai, kiểm tra lại");
            }
            if(userRepo.findUserById(userTar) == null){
                throw new ResourceNotFoundException("ID User target sai, kiểm tra lại");
            }
//          #endregion

            int i = rqRepository.deleteByUserSourceIdAndUserTargetId(userSrc,userTar);
            int j = flRepo.deleteByUserSourceIdAndUserTargetId(userSrc, userTar);
            
        } catch (Exception ex){
            throw new MyBadRequestException(ex.toString());
        }
    }
    
    

    @Override
    public List<RequestFollow> getAllRequestFollow(Long userTar) {
        List<RequestFollow> rqs = rqRepository.findByUserTargetIdAndIsPending(userTar,1);
        
        try {        
            
            if(userRepo.findUserById(userTar) == null ){
                throw new ResourceNotFoundException("Id user sai, kiem tra lai");
            }
            
            return rqs;
        } catch (Exception ex){
            throw new MyBadRequestException(ex.toString());
        }
    }

}
