/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.entity.PostLike;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IPostLikeService;
import com.utopia.social_network.utopia_api.repository.PostLikeRepository;
import com.utopia.social_network.utopia_api.repository.PostRepository;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import com.utopia.social_network.utopia_api.viewModel.SavePostLikeVM;
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
public class PostLikeService implements IPostLikeService{
    
    @Autowired
    private PostLikeRepository repository;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private UserRepository userRepo;
    
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

    @Override
    public SavePostLikeVM LikePost(Long userId, Long postId) {
        Optional<User> user = userRepo.findById(userId);
        SavePostLikeVM model = new SavePostLikeVM();
        if(user.isEmpty()){
            throw new ResourceNotFoundException("Khong tim thay User! Kiem tra lai ID");
        }
        Optional<Post> post = postRepo.findById(postId);
        if(post.isEmpty()){
            throw new ResourceNotFoundException("Khong tim thay Post! Kiem tra lai ID");
        }
        Optional<PostLike> postLike = repository.findPostLikeByPostIdAndUserId(postId, userId);
        if(postLike.isEmpty()){
            PostLike newPostLike = savePost(userId,postId);
            long likeCount = post.get().getLikeCount() + 1;
            postRepo.updatePostSetLikeAndShareById(likeCount,post.get().getShareCount(), postId);
            model.setId(newPostLike.getId());
            model.setPostId(newPostLike.getPostId());
            model.setUserId(newPostLike.getUserId());
            model.setDateLike(newPostLike.getDateLike());
            return model;
        }
        PostLike tmp = postLike.get();
        repository.delete(tmp);
        long likeCount = post.get().getLikeCount() - 1;
        postRepo.updatePostSetLikeAndShareById(likeCount,post.get().getShareCount(), postId);
        model.setId(tmp.getId());
        model.setPostId(tmp.getPostId());
        model.setUserId(tmp.getUserId());
        model.setDateLike(tmp.getDateLike());
        model.setAction("unliked");
        return model;
    }

    @Override
    public List<PostLike> getAllPostLikeByUser(Long userId) {
        List<PostLike> list = repository.findAllPostLikeByUserId(userId);
        return list;
    }
    
    
}
