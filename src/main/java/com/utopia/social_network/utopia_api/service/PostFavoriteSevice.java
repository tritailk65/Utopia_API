/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.entity.PostFavorite;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IPostFavoriteSevice;
import com.utopia.social_network.utopia_api.repository.PostFavoriteRepository;
import com.utopia.social_network.utopia_api.repository.PostRepository;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    private PostRepository postRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PostFavorite> getAllPostFavoriteByUserId(Long userId) {
        return repository.findAllPostFavoriteByUserId(userId);
    }

    @Override
    public PostFavorite save(Long userId, Long postId) {
        PostFavorite pf = new PostFavorite();
        
        Date date = new Date();
        
        pf.setPostId(postId);
        pf.setUserId(userId);
        pf.setDateFavorite(date);
        
        return repository.save(pf);
    }
    
    @Override
    public PostFavorite saveFavoritePost(Long userId, Long postId) {
        Optional<User> user = userRepo.findById(userId);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("Khong tim thay User! Kiem tra lai ID");
        }
        Optional<Post> post = postRepo.findById(postId);
        if(post.isEmpty()){
            throw new ResourceNotFoundException("Khong tim thay Post! Kiem tra lai ID");
        }
        Optional<PostFavorite> postFavirote = repository.findPostFavoriteByUserIdAndPostId(userId, postId);
        if(postFavirote.isEmpty()){
            PostFavorite newPostFavirote = save(userId,postId);
            long shareCount = post.get().getShareCount()+ 1;
            postRepo.updatePostSetLikeAndShareById(post.get().getLikeCount(),shareCount, postId);
            return newPostFavirote;
        }
        PostFavorite tmp = postFavirote.get();
        repository.delete(tmp);
        long shareCount = post.get().getShareCount() - 1;
        postRepo.updatePostSetLikeAndShareById(post.get().getLikeCount(),shareCount, postId);
        return tmp;
    }
    
    
}
