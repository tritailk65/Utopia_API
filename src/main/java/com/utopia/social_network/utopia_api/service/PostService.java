package com.utopia.social_network.utopia_api.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.sun.istack.Nullable;
import com.utopia.social_network.utopia_api.entity.PostEntity;
import com.utopia.social_network.utopia_api.interfaces.IPostService;
import com.utopia.social_network.utopia_api.model.PostModel;
import com.utopia.social_network.utopia_api.repository.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author trita
 */
@Service
public class PostService implements IPostService{
    
    @Autowired
    private PostRepository postRepo;

    @Override
    public List<PostEntity> GetAllPost(@Nullable Long id) {
        if (id==null){
            return postRepo.findAll();
        } else if(id!=null) {
            return postRepo.findPostByUserId(id);
        }
        return null;
    }
    
    @Override
    public void CreatePost(PostModel post) {
        PostEntity pEntity = new PostEntity();
        pEntity.setContent(post.getContent());
        pEntity.setStatus(post.getStatus());
        pEntity.setTitle(post.getTitle());
        pEntity.setDatePublished(post.getDatePublished());
        postRepo.save(pEntity);
    }

    @Override
    public void DeletePostById(Long id) {      
        postRepo.deleteById(Long.parseLong(id.toString()));
    }

    @Override
    public void UpdatePost(Long id,PostModel post) {
//        //Check repository if Post null
//        postRepo.updatePostById(post.getTitle(), post.getContent(), post.getStatus(), id);
    }
    
}
