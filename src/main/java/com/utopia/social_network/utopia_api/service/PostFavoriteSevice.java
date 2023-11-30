/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.common.FilterSort;
import com.utopia.social_network.utopia_api.entity.Image;
import com.utopia.social_network.utopia_api.entity.Notification;
import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.entity.PostFavorite;
import com.utopia.social_network.utopia_api.entity.PostLike;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IPostFavoriteSevice;
import com.utopia.social_network.utopia_api.model.PostForViewerModel;
import com.utopia.social_network.utopia_api.repository.NotificationRepository;
import com.utopia.social_network.utopia_api.repository.PostFavoriteRepository;
import com.utopia.social_network.utopia_api.repository.PostLikeRepository;
import com.utopia.social_network.utopia_api.repository.PostRepository;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import com.utopia.social_network.utopia_api.viewModel.SavePostFavoriteVM;
import java.util.ArrayList;
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
    private PostLikeRepository likeRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private NotificationRepository notiRepo;
    @Autowired
    private SSEService sseService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PostForViewerModel> getAllPostFavoriteByUserId(Long userId) {
        List<PostForViewerModel> list = new ArrayList<PostForViewerModel>();
        
        List<PostLike> likes = likeRepo.findAllPostLikeByUserId(userId,FilterSort.getAsc("id"));
        List<PostFavorite> favorites = repository.findAllPostFavoriteByUserId(userId,FilterSort.getDesc("id"));
        for(PostFavorite x : favorites){   
            if(x.getPost().getIsActive() == 1){
                PostForViewerModel tmp = new PostForViewerModel();
            
                tmp.setId(x.getPost().getId());
                tmp.setContent(x.getPost().getContent());
                tmp.setCommentStat(x.getPost().getCommentStat());
                tmp.setDatePublished(x.getPost().getDatePublished());
                tmp.setIsHideLike(x.getPost().getIsHideLike());
                tmp.setLastUpdate(x.getPost().getLastUpdate());
                tmp.setLikeCount(x.getPost().getLikeCount());
                tmp.setCommentCount(x.getPost().getCommentCount());
                tmp.setShareCount(x.getPost().getShareCount());
                tmp.setTitle(x.getPost().getTitle());
                tmp.setIsSaved(true);
                
                if(x.getPost().getPostImages().size() > 0){
                    for(Image img : x.getPost().getPostImages()){
                        tmp.getImages().add(img);
                    }
                }
                
                if(likes.size() > 0){
                    for(PostLike s : likes){
                        if(s.getPostId() == x.getPost().getId()){
                            tmp.setIsLiked(true);
                            break;
                        }
                    }
                }       
                
                tmp.getUser().setId(x.getPost().getUser().getId());
                tmp.getUser().setUserName(x.getPost().getUser().getUserName());
                tmp.getUser().setCreateAt(x.getUser().getCreateAt());
                tmp.getUser().setUpdateAt(x.getUser().getUpdateAt());
                tmp.getUser().setAvatarPath(x.getUser().getAvatarPath());
                tmp.getUser().setWebsite(x.getUser().getWebsite());

                list.add(tmp);
            }
        }
        return list;
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
    public SavePostFavoriteVM saveFavoritePost(Long userId, Long postId) {
        Optional<User> user = userRepo.findById(userId);
        SavePostFavoriteVM model = new SavePostFavoriteVM();
        if(user.isEmpty()){
            throw new ResourceNotFoundException("Khong tim thay User! Kiem tra lai ID");
        }
        List<Post> list_post = postRepo.findAllByIdAndIsActive(postId,1);
        if(list_post.isEmpty()){
            throw new ResourceNotFoundException("Khong tim thay Post! Kiem tra lai ID");
        }
        Optional<PostFavorite> postFavirote = repository.findPostFavoriteByUserIdAndPostId(userId, postId);
        Post post = list_post.get(0);
        if(postFavirote.isEmpty()){
            PostFavorite newPostFavirote = save(userId,postId);
            long shareCount = post.getShareCount()+ 1;
            postRepo.updatePostSetLikeAndShareById(post.getLikeCount(),shareCount, postId);
            model.setId(newPostFavirote.getId());
            model.setPostId(newPostFavirote.getPostId());
            model.setUserId(newPostFavirote.getUserId());
            model.setDateFavorite(newPostFavirote.getDateFavorite());
            
            if(user.get().getId() != post.getUserId()){
                Date dateNow = new Date();
                Notification noti = new Notification();
                noti.setContext(user.get().getFullName()+" just saved your post");
                noti.setType("save");
                noti.setUpdateAt(dateNow);
                noti.setUserId(post.getUserId());
                noti.setSourceId(user.get().getId());
                
                if(post.isAlert()){
                    String content = user.get().getUserName()+" just saved your post";
                    String msgId = String.valueOf(post.getUserId());
                    boolean online = sseService.checkUserOffline(msgId , content , "noti");  
                    if(online){
                        sseService.addMessageForClient(msgId , content , "noti" , true);
                    }
                }

                notiRepo.save(noti);
            }
            
            return model;
        }
        PostFavorite tmp = postFavirote.get();
        repository.delete(tmp);
        long shareCount = post.getShareCount() - 1;
        postRepo.updatePostSetLikeAndShareById(post.getLikeCount(),shareCount, postId);
        model.setId(tmp.getId());
        model.setPostId(tmp.getPostId());
        model.setUserId(tmp.getUserId());
        model.setDateFavorite(tmp.getDateFavorite());
        model.setAction("unsaved");
        return model;
    }
    
    
}
