/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;
import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.utils.APIResult;
import com.utopia.social_network.utopia_api.interfaces.IPostService;
import com.utopia.social_network.utopia_api.model.CreatePostModel;
import java.text.ParseException;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    
    @Autowired
    private IPostService postService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private APIResult rs;
    
    @PostMapping
    private APIResult createdNewPost(@RequestBody CreatePostModel postModel) throws ParseException{
        Post post = convertToEntity(postModel);
        postService.CreatePost(post);
        return rs.MessageSuccess("Tạo mới bài viết thành công !", null);
    }
    
    @GetMapping
    private APIResult getAllPost(){
        return new APIResult(200,"Ok",null,postService.GetAllPost(null));
    }
    
    @GetMapping(value = "/getListPostForViewer")
    private APIResult getListPostForViewer(){
        return new APIResult(200,"Ok",null,postService.GetListPostForViewer(null));
    }
    
    @GetMapping(value = "/{id}")
    private APIResult getPostByPost(@PathVariable("id") Long id){
        return new APIResult(200,"Ok",null,postService.GetAllPost(id));
    }
    
    @GetMapping(value = "/User/{id}")
    private APIResult getPostByUser(@PathVariable("id") Long id){
        return new APIResult(200,"Ok",null,postService.GetAllPostByUser(id));
    }
    
    @DeleteMapping(value = "/{id}")
    private APIResult deletePostById(@PathVariable("id") Long id){
        //Trick for low code
        List<Post> checkIfNull = postService.GetAllPost(id);
        if (checkIfNull == null){
            throw new ResourceNotFoundException();
        } else{
            postService.DeletePostById(id);
        }
        return rs.MessageSuccess("Xóa bài viết thành công", null);
    }
    
    private Post convertToEntity (CreatePostModel pModel) throws ParseException {
        Post post = modelMapper.map(pModel, Post.class);
        return post;
    }
}
