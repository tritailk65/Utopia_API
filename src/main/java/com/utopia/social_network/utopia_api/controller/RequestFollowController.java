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
import org.springframework.web.bind.annotation.GetMapping;
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
@CrossOrigin()
@RestController
@RequestMapping("/api/RequestFollow")
public class RequestFollowController {
    private static final Logger logger = LoggerFactory.getLogger(RequestFollowController.class);
    
    @Autowired
    private IRequestFollowService rqService;
    
    @Autowired
    private APIResult rs ;
    
    @GetMapping
    private APIResult getAllRequestFollowByUser(@RequestHeader("Token") Long token){
        return new APIResult(200, "Ok", null, rqService.getAllRequestFollow(token));
    }
    
    @PostMapping(value = {"/SendRequestFollow/UserTar={idTar}"})
    private APIResult sendRequest(@RequestHeader("Token") Long token, @PathVariable("idTar") Long idTar){
        return new APIResult(200, "Ok", null, rqService.sendRequestFollow(token,idTar));
    }
    
    @PostMapping(value = {"/AcceptRequestFollow/UserTar={idTar}"})
    private APIResult acceptRequest(@RequestHeader("Token") Long token,@PathVariable("idTar") Long idTar){
        rqService.acceptRequestFollow(idTar, token);
        return rs.MessageSuccess("Chấp nhận lời mời follow thành công !", null);
    }
    
    @PutMapping(value = {"/DeleteRequestFollow/UserTar={idTar}"})
    private APIResult deleteRequest(@RequestHeader("Token") Long token,@PathVariable("idTar") Long idTar){
        rqService.deleteRequestFollow(idTar, token);
        return rs.MessageSuccess("Xóa mời follow thành công !", null);
    }
    
    @PutMapping(value = {"/CancelRequestFollow/UserTar={idTar}"})
    private APIResult cancelRequest(@RequestHeader("Token") Long token,@PathVariable("idTar") Long idTar){
        rqService.cancelRequestFollow(token, idTar);
        return rs.MessageSuccess("Hủy bỏ lời mời follow thành công !", null);   
    }

}
