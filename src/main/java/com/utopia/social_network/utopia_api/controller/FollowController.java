/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;

import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.interfaces.IFollowService;
import com.utopia.social_network.utopia_api.model.CreatePostModel;
import com.utopia.social_network.utopia_api.utils.APIResult;
import java.text.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/Follow")
public class FollowController {
    private static final Logger logger = LoggerFactory.getLogger(FollowController.class);
    @Autowired
    private APIResult rs;
    @Autowired
    private IFollowService followService;
    @PostMapping(value = {"/SendRequest/{idTarget}"}, produces = "application/json")
    private APIResult createdNewPost(@PathVariable("idTarget") Long id,@RequestHeader("token") Long token){

        return rs.MessageSuccess("Tạo mới bài viết thành công !", followService.addRequestFollow(token,id));
    }
    @PutMapping(value = {"/AcceptRequest/{idTarget}"}, produces = "application/json")
     private APIResult acceptRequest(@PathVariable("idTarget") Long id,@RequestHeader("token") Long token){

        return rs.MessageSuccess("Tạo mới bài viết thành công !", followService.addRequestFollow(token,id));
    }
}
}
