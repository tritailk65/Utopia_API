package com.utopia.social_network.utopia_api.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IPostService;
import com.utopia.social_network.utopia_api.model.PostForViewerModel;
import com.utopia.social_network.utopia_api.model.UserPostForViewerModel;
import com.utopia.social_network.utopia_api.repository.PostRepository;
import java.util.ArrayList;
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
        post.setId(new Date().getTime());
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

    @Override
    public List<PostForViewerModel> GetListPostForViewer(Long id) {
        List<PostForViewerModel> data = new ArrayList<>();
        List<Post> posts = postRepo.findAllPostForViewer();
        if(posts.isEmpty()){
            return data;
        }
        for(Post x : posts){
            PostForViewerModel tmp = new PostForViewerModel();
            
            tmp.setId(x.getId());
            tmp.setTitle(x.getTitle());
            tmp.setContent(x.getContent());
            tmp.setDatePublished(x.getDatePublished());
            tmp.setLastUpdate(x.getLastUpdate());
            tmp.setIsHideLike(tmp.getIsHideLike());
            tmp.setCommentStat(x.getCommentStat());
            tmp.setLikeCount(x.getLikeCount());
            tmp.setShareCount(x.getShareCount());
            
            if(x.getUser() != null){
                UserPostForViewerModel tmp_user = new UserPostForViewerModel();
                
                tmp_user.setId(x.getUser().getId());
                tmp_user.setUserName(x.getUser().getUserName());
                tmp_user.setCreateAt(x.getUser().getCreateAt());
                tmp_user.setUpdateAt(x.getUser().getUpdateAt());
                tmp_user.setWebsite(x.getUser().getWebsite());
                tmp_user.setAvatarPath(x.getUser().getAvatarPath());
                
                tmp.setUser(tmp_user);
            }
            else{
                continue;
            }
            data.add(tmp);
        }
        return data;
    }

}
