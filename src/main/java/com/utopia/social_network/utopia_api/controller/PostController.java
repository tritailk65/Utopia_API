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
import com.utopia.social_network.utopia_api.model.EditPostModel;
import com.utopia.social_network.utopia_api.service.FileStorageService;
import io.swagger.v3.oas.annotations.headers.Header;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    private FileStorageService fileStorageService;
    
    @Autowired
    private APIResult rs;
    
    @PostMapping
    private APIResult createdNewPost(@RequestBody CreatePostModel postModel) throws ParseException{
        Post post = convertToEntity(postModel);
        Long id = postService.CreatePostV2(post);
        return rs.MessageSuccess("Tạo mới bài viết thành công !", id);
    }
    
    @PutMapping
    private APIResult editPost(@RequestHeader("token") Long user, @RequestBody EditPostModel model) throws ParseException{
        boolean res = postService.EditPost(model.getPostId(),model.getTitle(),model.getIsHideLike(),model.getCommentStat(),user);
        return rs.MessageSuccess("Tạo mới bài viết thành công !", null);
    }
    
    
    @PostMapping(value = "/UploadImage/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    private APIResult uploadFile(@RequestPart(value = "avatar") MultipartFile files, @PathVariable("id") Long id) {
        
        //List<String> lst = new ArrayList<String>();
        //for(MultipartFile file : files){
            String fileName = fileStorageService.storeFile(files);
            System.out.println(fileName);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    //                .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();

            String rs = fileName + fileDownloadUri + files.getContentType() + files.getSize();
           // lst.add(fileName);
        //}
        postService.updateSinglePostImage(fileName, id, files.getContentType());
        return new APIResult(200, "Ok", null, null);
    }
    
    @GetMapping
    private APIResult getAllPost(){
        return new APIResult(200,"Ok",null,postService.GetAllPost(null));
    }
    
    @GetMapping(value = "/getListPostForViewer/page={page}")
    private APIResult getListPostForViewer(@RequestHeader("token") Long user,@PathVariable("page") int page){
        return new APIResult(200,"Ok",null,postService.GetListPostForViewer(user,page));
    }
    
    @GetMapping(value = "/GetListPostProfile/UserName={name}&page={page}")
    private APIResult getListPostProfile(@PathVariable("name") String name,@PathVariable("page") int page){
        return new APIResult(200,"Ok",null,postService.GetListPostProfile(name,page));
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
