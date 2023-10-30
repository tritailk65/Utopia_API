/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;

import com.utopia.social_network.utopia_api.entity.PostComment;
import com.utopia.social_network.utopia_api.interfaces.IPostCommentService;
import com.utopia.social_network.utopia_api.model.HttpEditComment;
import com.utopia.social_network.utopia_api.model.PostCommentModel;
import com.utopia.social_network.utopia_api.utils.APIResult;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 *
 * @author trita
 */

@CrossOrigin
@RestController
@RequestMapping("/api/PostComment")
public class PostCommentController {
    
    @Autowired
    private IPostCommentService commentService;
    
    @Autowired
    private APIResult rs;
    
    @GetMapping(value = {"/Post/{id}"}, produces = "application/json")
    private APIResult getAllUser(@PathVariable("id") Long id) {        
        return new APIResult(200, "Ok", null, commentService.getAllCommentByPostId(id));
    }
    
    @PostMapping(value = {"/UserComment"}, produces = "application/json")
    private APIResult userCommentPost(@RequestBody  PostCommentModel commentModel){
        PostComment pC = commentService.userCommentPost(commentModel);      
        return new APIResult(200, "Ok", null, pC);
    }
    
    @PostMapping(value = {"/ReplyComment"}, produces = "application/json")
    private APIResult ReplyComment(@RequestBody  PostCommentModel commentModel){
        PostComment pC = commentService.userReplyComment(commentModel);      
        return new APIResult(200, "Ok", null, pC);
    }
    
    @PutMapping(value = {"/{id}/EditComment"})
    private APIResult EditComment(@PathVariable("id") Long id,@RequestBody HttpEditComment model) {     
        long userId = model.getIdUser();
        String comment = model.getComment();
        return new APIResult(200, "Ok", null, commentService.editComment(id,userId,comment));
    }
    
    @DeleteMapping(value = "/{id}")
    private APIResult deleteCommentById(@PathVariable("id") Long id,@RequestHeader("token") Long token){
        return new APIResult(200, "Ok", null, commentService.deleteComment(id,token));
    }
    
}
