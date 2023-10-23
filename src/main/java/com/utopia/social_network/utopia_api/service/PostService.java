package com.utopia.social_network.utopia_api.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IPostService;
import com.utopia.social_network.utopia_api.repository.PostRepository;
import java.util.Date;
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
    public List<Post> GetAllPost(@Nullable Long id) {

        if (id == null) {
            return postRepo.findAllByIsActive(1);
        } else {
            List<Post> postsById = postRepo.findAllByIdAndIsActive(id, 1);
            if (postsById.size() == 0) {
                throw new ResourceNotFoundException("Khong tim thay! Kiem tra lai ID");
            } else {
                return postsById;
            }
        }
    }

    @Override
    public List<Post> GetAllPostByUser(Long id) {
        List<Post> postsByUser = postRepo.findAllByUserIdAndIsActive(id, 1);
        if (postsByUser.size() == 0){
            throw new ResourceNotFoundException("Khong tim thay! Kiem tra lai ID");
        } else {
            return postsByUser;
        }
    }

    @Override
    public void CreatePost(Post post) {
        Date dateNow = new Date();
        post.setDatePublished(dateNow);
        post.setLastUpdate(dateNow);
        post.setIsActive(1);
        post.setLikeCount(0);
        post.setShareCount(0);
        postRepo.save(post);
    }

    @Override
    public void DeletePostById(Long id) {
        postRepo.updatePostSetIsActiveById(id);
    }

}
