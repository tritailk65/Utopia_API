/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.model.PostForViewerModel;
import java.util.List;
import org.springframework.lang.Nullable;

/**
 *
 * @author trita
 */

public interface IPostService{
    
    List<Post> GetAllPost(@Nullable Long id);
    
    List<PostForViewerModel> GetListPostForViewer(@Nullable Long id);
    
    List<Post> GetAllPostByUser (Long id);
    
    void CreatePost(Post post);
    
    void DeletePostById(Long id);
    
}