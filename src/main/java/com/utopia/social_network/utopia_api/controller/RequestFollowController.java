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
@CrossOrigin
@RestController
@RequestMapping("/api/RequestFollow")
public class RequestFollowController {
    private static final Logger logger = LoggerFactory.getLogger(RequestFollowController.class);
    
    @Autowired
    private IRequestFollowService rqService;
    
    @Autowired
    private APIResult rs ;
    
    @GetMapping(value = {"/{id}"})
    private APIResult getAllRequestFollowByUser(@PathVariable("id") Long id){
        return new APIResult(200, "Ok", null, rqService.getAllRequestFollow(id));
    }
    
    @PostMapping(value = {"/SendRequestFollow/UserSrc={idSrc}&UserTar={idTar}"})
    private APIResult sendRequest(@PathVariable("idSrc") Long idSrc, @PathVariable("idTar") Long idTar){
        return new APIResult(200, "Ok", null, rqService.sendRequestFollow(idSrc,idTar));
    }
    
    @PostMapping(value = {"/AcceptRequestFollow/UserSrc={idSrc}&UserTar={idTar}"})
    private APIResult acceptRequest(@PathVariable("idSrc") Long idSrc,@PathVariable("idTar") Long idTar){
        rqService.acceptRequestFollow(idTar, idSrc);
        return rs.MessageSuccess("Chấp nhận lời mời follow thành công !", null);
    }
    
    @PutMapping(value = {"/DeleteRequestFollow/UserSrc={idSrc}&UserTar={idTar}"})
    private APIResult deleteRequest(@PathVariable("idSrc") Long idSrc,@PathVariable("idTar") Long idTar){
        rqService.deleteRequestFollow(idTar, idSrc);
        return rs.MessageSuccess("Xóa mời follow thành công !", null);
    }
    
    @PutMapping(value = {"/CancelRequestFollow/UserSrc={idSrc}&UserTar={idTar}"})
    private APIResult cancelRequest(@PathVariable("idSrc") Long idSrc,@PathVariable("idTar") Long idTar){
        rqService.deleteRequestFollow(idSrc, idTar);
        return rs.MessageSuccess("Hủy bỏ lời mời follow thành công !", null);   
    }

}
