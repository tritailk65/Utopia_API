/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;

import com.utopia.social_network.utopia_api.interfaces.IPostLikeService;
import com.utopia.social_network.utopia_api.utils.APIResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author trita
 */
@RestController
@RequestMapping("/api/PostLike")
public class PostLikeController {

    private static final Logger logger = LoggerFactory.getLogger(PostLikeController.class);

    @Autowired
    private IPostLikeService postLikeService;

    @Autowired
    private APIResult rs;
    
//    @GetMapping(value = {"/User/{id}"})
//    private APIResult getAllUser(@PathVariable("id") Long userid) {
//        return new APIResult(200, "Ok", null, favoriteSevice.getAllPostFavoriteByUserId(userid));
//    }

    @PostMapping(value = {"/UserId={UserId}&PostId={PostId}"})
    private APIResult userSavePost(@PathVariable("userid") Long userid, @PathVariable("postid") Long postid) {
        postLikeService.savePost(userid, postid);
        return rs.MessageSuccess("Thích bài viết thành công !", null);
    }
}
