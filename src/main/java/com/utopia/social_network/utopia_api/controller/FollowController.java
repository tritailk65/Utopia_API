/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;

import com.utopia.social_network.utopia_api.entity.Following;
import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.interfaces.IFollowService;
import com.utopia.social_network.utopia_api.model.CreatePostModel;
import com.utopia.social_network.utopia_api.model.FollowingModel;
import com.utopia.social_network.utopia_api.utils.APIResult;
import java.text.ParseException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
     
    @GetMapping("/FollowedUsers/{id}")
    private APIResult getFollowedUsers(@PathVariable long id) {
        List<FollowingModel> followedUsers = followService.findByUserSourceId(id);
        return new APIResult(200, "Ok", null, followedUsers);
    }
    
    @GetMapping("/followed-users/{userId}")
    public ResponseEntity<List<User>> getFollowedUsers(@PathVariable Long userId) {
        List<User> followedUsers = followService.findUsersFollowedByCurrentUser(userId);
        return ResponseEntity.ok(followedUsers);
    }
    
    @DeleteMapping("/unfollow/{usersourceId}/{usertargetId}")
    public ResponseEntity<String> unfollowUser(@PathVariable long usersourceId, @PathVariable long usertargetId) {
        boolean result = followService.unFollow(usersourceId, usertargetId);
        if (result) {
            return ResponseEntity.ok("Unfollowed user successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to unfollow user.");
        }
    }

}

