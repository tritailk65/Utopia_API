/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.model.CreatePostModel;
import com.utopia.social_network.utopia_api.model.PostForViewerModel;
import java.util.List;
import org.springframework.lang.Nullable;

/**
 *
 * @author trita
 */

public interface IPostService{
    
    List<Post> GetAllPost(@Nullable Long id);
    
    List<PostForViewerModel> GetListPostForViewer(Long id,int page);
    
    List<PostForViewerModel> GetListPostProfile(String name, int page);
    
    PostForViewerModel GetPostById(Long postId,Long userId);
    
    List<Post> GetAllPostByUser (Long id);
    
    void CreatePost(Post post);
    
    Long CreatePostV2(Post post);
    
    boolean EditPost(Long postId , String title , int isHideLike , int commentStat , boolean alert , Long userId);
    
    void DeletePostById(Long id);
    
    void updatePostImage( List<String> path, Long id);  
    
    void updateSinglePostImage( String path, Long id, String type);  
}