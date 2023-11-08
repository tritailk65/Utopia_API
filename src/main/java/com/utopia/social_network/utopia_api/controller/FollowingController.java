/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;

import com.utopia.social_network.utopia_api.utils.APIResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.utopia.social_network.utopia_api.interfaces.IFollowingService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin
@RestController
@RequestMapping("/api/Following")
public class FollowingController {
    
    private static final Logger logger = LoggerFactory.getLogger(FollowingController.class);
    
    @Autowired
    private APIResult rs;
    
    @Autowired
    private IFollowingService followService;
    
    @GetMapping(value = {"/{id}"})
    private APIResult getAllFollowerByUser(@PathVariable("id") Long id){
        return new APIResult(200,"Ok",null,followService.getAllFollowingByUser(id));
    }
    
    @PutMapping(value = {"/CancelFollow/UserSrc={idSrc}&UserTar={idTar}"})
    private APIResult deleteRequest(@PathVariable("idSrc") Long idSrc,@PathVariable("idTar") Long UserTar){
        followService.cancelFollow(idSrc, UserTar);
        return rs.MessageSuccess("Hủy follow thành công !", null); 
    }
}

