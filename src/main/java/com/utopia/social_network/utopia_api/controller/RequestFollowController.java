/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;

import com.utopia.social_network.utopia_api.interfaces.IRequestFollowService;
import com.utopia.social_network.utopia_api.utils.APIResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author trita
 */
@CrossOrigin
@RestController
@RequestMapping("/api/RequestFollow")
public class RequestFollowController {
    private static final Logger logger = LoggerFactory.getLogger(RequestFollowController.class);
    
    @Autowired
    private IRequestFollowService rqService;
    
    @Autowired
    private APIResult rs;
    
    @PostMapping(value = {"/SendRequestFollow/{idTarget}"})
    private APIResult sendRequest(@PathVariable("idTarget") Long id, @RequestHeader("token") Long token){
        return rs.MessageSuccess("Gửi lời mời follow thành công !", rqService.sendRequestFollow(token,id));
    }
    
    @PostMapping(value = {"/AcceptRequestFollow/{idTarget}"})
    private APIResult acceptRequest(@PathVariable("idTarget") Long id,@RequestHeader("token") Long token){
        return rs.MessageSuccess("Chấp nhận lời mời follow thành công !", rqService.acceptRequestFollow(token,id));
    }
    
    @PutMapping(value = {"/CancelRequestFollow/{idTarget}"})
    private APIResult cancelRequest(@PathVariable("idTarget") Long id,@RequestHeader("token") Long token){
        if (rqService.deleteRequestFollow(id, token)){
            rs.MessageSuccess("Hủy lời mời follow thành công !", null);
        } 
        return rs;       
    }
    
    @PutMapping(value = {"/DeleteRequestFollow/{idTarget}"})
    private APIResult deleteRequest(@PathVariable("idTarget") Long id,@RequestHeader("token") Long token){
        if (rqService.deleteRequestFollow(id, token)){
            rs.MessageSuccess("Xóa lời mời follow thành công !", null);
        } 
        return rs;
        
    }

}
