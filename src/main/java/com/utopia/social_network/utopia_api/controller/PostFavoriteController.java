/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;

import com.utopia.social_network.utopia_api.interfaces.IPostFavoriteSevice;
import com.utopia.social_network.utopia_api.utils.APIResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author trita
 */
@CrossOrigin
@RestController
@RequestMapping("/api/PostFavorite")
public class PostFavoriteController {

    private static final Logger logger = LoggerFactory.getLogger(PostFavoriteController.class);

    @Autowired
    private IPostFavoriteSevice favoriteSevice;

    @Autowired
    private APIResult rs;
    
    @GetMapping(value = {"/User/{id}"})
    private APIResult getAllUser(@PathVariable("id") Long userid) {
        return new APIResult(200, "Ok", null, favoriteSevice.getAllPostFavoriteByUserId(userid));
    }

    @PostMapping(value = {"/userid={userid}&postid={postid}"})
    private APIResult userSavePost(@PathVariable("userid") Long userid, @PathVariable("postid") Long postid) {
        favoriteSevice.savePost(userid, postid);
        return rs.MessageSuccess("Lưu bài viết thành công !", null);
    }
}
