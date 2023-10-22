/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.PostFavorite;
import com.utopia.social_network.utopia_api.interfaces.IPostFavoriteSevice;
import com.utopia.social_network.utopia_api.repository.PostFavoriteRepository;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author trita
 */
@Service
public class PostFavoriteSevice implements IPostFavoriteSevice{
    
    @Autowired
    private PostFavoriteRepository repository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PostFavorite> getAllPostFavoriteByUserId(Long userId) {
        return repository.findAllPostFavoriteByUserId(userId);
    }

    @Override
    public PostFavorite savePost(Long userId, Long postId) {
        PostFavorite pf = new PostFavorite();
        
        Date date = new Date();
        
        pf.setPostId(postId);
        pf.setUserId(userId);
        pf.setDateFavorite(date);
        
        return repository.save(pf);
    }
    
    
}
