package com.utopia.social_network.utopia_api.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IPostService;
import com.utopia.social_network.utopia_api.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import org.springframework.stereotype.Service;


/**
 *
 * @author trita
 */
@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepo;

    @Override
    public List<Post> GetAllPost(@Nullable Long id){

        if (id == null) {
            return postRepo.findAll();

        } else if (id != null) {
            List<Post> pInfo = postRepo.findPostById(id);
            if (pInfo.size() != 0) {
                return postRepo.findPostById(id);
            } 
        }
        return null;
    }

    @Override
    public List<Post> GetAllPostByUser(Long id) {
        return postRepo.findPostByUserId(id);
    }

    @Override
    public void CreatePost(Post post) {
            postRepo.save(post);
    }

    @Override
    public void DeletePostById(Long id) {
        //check if not exist
        postRepo.deleteById(id);
    }

}
