/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;
import com.utopia.social_network.utopia_api.utils.APIResult;
import com.utopia.social_network.utopia_api.interfaces.IPostService;
import com.utopia.social_network.utopia_api.model.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author trita
 */
@CrossOrigin
@RestController
@RequestMapping("/api/Post")
public class PostController {
    
    @Autowired
    private IPostService postService;
    
    @Autowired
    private APIResult rs;
    
    @PostMapping
    private APIResult createdNewPost(@RequestBody PostModel postModel){
        postService.CreatePost(postModel);
        return rs.MessageSuccess("Tạo mới bài viết thành công !", null);
    }
    
    @GetMapping
    private APIResult getAllPost(){
        return new APIResult(200,"Ok",null,postService.GetAllPost(null));
    }
    
    @GetMapping(value = "/{id}")
    private APIResult getPostByPost(@PathVariable("id") Long id){
        return new APIResult(200,"Ok",null,postService.GetAllPost(id));
    }
    
    @GetMapping(value = "/user={id}")
    private APIResult getPostByUser(@PathVariable("id") Long id){
        return new APIResult(200,"Ok",null,postService.GetAllPostByUser(id));
    }
    
    @PutMapping(value = "/{id}")
    private APIResult updatePostById(@PathVariable("id") Long id, @RequestBody PostModel pd){
        postService.UpdatePost(id,pd);
        return rs.MessageSuccess("Cập nhật bài viết thành công", null);
    }
    
    @DeleteMapping(value = "/{id}")
    private APIResult deletePostById(@PathVariable("id") Long id){
        return rs.MessageSuccess("Xóa bài viết thành công", null);
    }
}
