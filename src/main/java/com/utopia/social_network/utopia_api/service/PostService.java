package com.utopia.social_network.utopia_api.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.utopia.social_network.utopia_api.common.FilterSort;
import com.utopia.social_network.utopia_api.common.Pagination;
import com.utopia.social_network.utopia_api.entity.Image;
import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.entity.PostFavorite;
import com.utopia.social_network.utopia_api.entity.PostLike;
import com.utopia.social_network.utopia_api.exception.MyBadRequestException;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IPostService;
import com.utopia.social_network.utopia_api.model.PostForViewerModel;
import com.utopia.social_network.utopia_api.model.UserPostForViewerModel;
import com.utopia.social_network.utopia_api.repository.ImageRepository;
import com.utopia.social_network.utopia_api.repository.PostFavoriteRepository;
import com.utopia.social_network.utopia_api.repository.PostLikeRepository;
import com.utopia.social_network.utopia_api.repository.PostRepository;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import com.utopia.social_network.utopia_api.entity.User;

import org.springframework.stereotype.Service;

/**
 *
 * @author trita
 */
@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepo;
    @Autowired
    private PostLikeRepository likeRepo;
    @Autowired
    private PostFavoriteRepository saveRepo;
    @Autowired
    private ImageRepository imgRepo;
    @Autowired
    private UserRepository userRepo;

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
        post.setCommentCount(0);
        postRepo.save(post);
    }

    @Override
    public void DeletePostById(Long id) {
        postRepo.updatePostSetIsActiveById(id);
    }

    @Override
    public List<PostForViewerModel> GetListPostProfile(String name, int page) {
        List<PostForViewerModel> data = new ArrayList<>();
        
        try {
            User u = userRepo.findUserByUserName(name);
            
            List<Post> posts = postRepo.findAllPostProfile(u.getId(),Pagination.pageSize,Pagination.getSkip(page));
            if(posts.isEmpty()){
                return data;
            }
            
            List<PostLike> likes = likeRepo.findAllPostLikeByUserId(u.getId(),FilterSort.getAsc("id"));
            List<PostFavorite> favorites = saveRepo.findAllPostFavoriteByUserId(u.getId(),FilterSort.getAsc("id"));
            
            for(Post x : posts){
                PostForViewerModel tmp = new PostForViewerModel();
            
                tmp.setId(x.getId());
                tmp.setTitle(x.getTitle());
                tmp.setContent(x.getContent());
                tmp.setDatePublished(x.getDatePublished());
                tmp.setLastUpdate(x.getLastUpdate());
                tmp.setIsHideLike(x.getIsHideLike());
                tmp.setCommentCount(x.getCommentCount());
                tmp.setCommentStat(x.getCommentStat());
                tmp.setLikeCount(x.getLikeCount());
                tmp.setShareCount(x.getShareCount());
            
                if(likes.size() > 0){
                    for(PostLike s : likes){
                        if(s.getPostId() == x.getId()){
                            tmp.setIsLiked(true);
                            break;
                        }
                    }
                }
            
                if(favorites.size() > 0){
                    for(PostFavorite s : favorites){
                        if(s.getPostId() == x.getId()){
                            tmp.setIsSaved(true);
                            break;
                        }
                    }
                }
            
                if(x.getUserId() == u.getId()){
                    tmp.setIsOwner(true);
                }

                if(x.getPostImages().size() > 0){
                    for(Image img : x.getPostImages()){
                        tmp.getImages().add(img);
                    }
                }
            
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
        } catch (Exception ex){
            throw new MyBadRequestException(ex.toString());
        }   
    }

    @Override
    public List<PostForViewerModel> GetListPostForViewer(Long id,int page) {
        List<PostForViewerModel> data = new ArrayList<>();
        List<Post> posts = postRepo.findAllPostForViewer(Pagination.pageSize,Pagination.getSkip(page));
        if(posts.isEmpty()){
            return data;
        }
        
        List<PostLike> likes = likeRepo.findAllPostLikeByUserId(id,FilterSort.getAsc("id"));
        List<PostFavorite> favorites = saveRepo.findAllPostFavoriteByUserId(id,FilterSort.getAsc("id"));

        for(Post x : posts){
            PostForViewerModel tmp = new PostForViewerModel();
            
            tmp.setId(x.getId());
            tmp.setTitle(x.getTitle());
            tmp.setContent(x.getContent());
            tmp.setDatePublished(x.getDatePublished());
            tmp.setLastUpdate(x.getLastUpdate());
            tmp.setIsHideLike(x.getIsHideLike());
            tmp.setCommentCount(x.getCommentCount());
            tmp.setCommentStat(x.getCommentStat());
            tmp.setLikeCount(x.getLikeCount());
            tmp.setShareCount(x.getShareCount());
            
            long time = tmp.calcDuration(x.getLastUpdate());
            tmp.setTime(time);
            
            if(likes.size() > 0){
                for(PostLike s : likes){
                    if(s.getPostId() == x.getId()){
                        tmp.setIsLiked(true);
                        break;
                    }
                }
            }
            
            if(favorites.size() > 0){
                for(PostFavorite s : favorites){
                    if(s.getPostId() == x.getId()){
                        tmp.setIsSaved(true);
                        break;
                    }
                }
            }
            
            if(x.getUserId() == id){
                tmp.setIsOwner(true);
            }
            
            if(x.getPostImages().size() > 0){
                for(Image img : x.getPostImages()){
                    tmp.getImages().add(img);
                }
            }
            
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

    @Override
    public void updatePostImage(List<String> path, Long id) {
        try{
            Post post = postRepo.findPostById(id);
            if(post == null){
                throw new ResourceNotFoundException("Khong tim thay Post! Kiem tra lai ID");
            }
            Date dateNow = new Date();
            for(String x : path){
                Image img = new Image();
                img.setName(x);
                img.setSize(10);
                img.setType("post");
                img.setDateUpdate(dateNow);
                imgRepo.save(img);
                post.getPostImages().add(img);
                
            }
            postRepo.save(post);
        }
        catch(Exception ex){
            throw new MyBadRequestException("Đã có lỗi xảy ra");
        }
    }

    @Override
    public Long CreatePostV2(Post post) {
        Date dateNow = new Date();
        post.setDatePublished(dateNow);
        post.setLastUpdate(dateNow);
        post.setIsActive(1);
        post.setLikeCount(0);
        post.setShareCount(0);
        post.setCommentCount(0);
        postRepo.save(post);
        return post.getId();
    }

    @Override
    public void updateSinglePostImage(String path, Long id, String type) {
        try{
            Post post = postRepo.findPostById(id);
            if(post == null){
                throw new ResourceNotFoundException("Khong tim thay Post! Kiem tra lai ID");
            }
            Date dateNow = new Date();
            
            Image img = new Image();
                img.setName(path);
                img.setSize(10);
                img.setType(type);
                img.setDateUpdate(dateNow);
                imgRepo.save(img);
                post.getPostImages().add(img);
            postRepo.save(post);
        }
        catch(Exception ex){
            throw new MyBadRequestException("Đã có lỗi xảy ra");
        }
    }

    @Override
    public boolean EditPost(Long postId, String title, int isHideLike, int commentStat, Long userId) {
        try{
            Post post = postRepo.findPostById(postId);
            if(post == null){
                throw new ResourceNotFoundException("Khong tim thay Post! Kiem tra lai ID");
            }
            if(post.getUserId() != userId){
                throw new ResourceNotFoundException("Khong tim thay Post! Kiem tra lai ID");
            }
            postRepo.editPost(title, isHideLike, commentStat, postId);
            return true;
        }
        catch(Exception ex){
            throw new MyBadRequestException("Đã có lỗi xảy ra");
        }
    }

}
