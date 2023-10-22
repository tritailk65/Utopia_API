/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.PostLike;
import com.utopia.social_network.utopia_api.interfaces.IPostLikeService;
import com.utopia.social_network.utopia_api.repository.PostLikeRepository;
import java.util.Date;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trita
 */
@Service
public class PostLikeService implements IPostLikeService{
    
    @Autowired
    private PostLikeRepository repository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostLike savePost(Long userId, Long postId) {
        PostLike postLike = new PostLike();
        
        Date dateLike = new Date();
        postLike.setUserId(userId);
        postLike.setPostId(postId);
        postLike.setDateLike(dateLike);
        
        return repository.save(postLike);
    }
    
    
}
