/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.services;

import com.utopia.social_network.utopia_api.common.FilterSort;
import com.utopia.social_network.utopia_api.entity.Image;
import com.utopia.social_network.utopia_api.entity.Notification;
import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.entity.PostLike;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IPostLikeService;
import com.utopia.social_network.utopia_api.model.PostForViewerModel;
import com.utopia.social_network.utopia_api.repository.NotificationRepository;
import com.utopia.social_network.utopia_api.repository.PostLikeRepository;
import com.utopia.social_network.utopia_api.repository.PostRepository;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import com.utopia.social_network.utopia_api.viewModel.SavePostLikeVM;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    private NotificationRepository notiRepo;
    
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
        List<Post> m_post = postRepo.findAllByIdAndIsActive(postId,1);
        if(m_post.isEmpty()){
            throw new ResourceNotFoundException("Khong tim thay Post! Kiem tra lai ID");
        }
        Optional<PostLike> postLike = repository.findPostLikeByPostIdAndUserId(postId, userId);
        Post post = m_post.get(0);
        if(postLike.isEmpty()){
            PostLike newPostLike = savePost(userId,postId);
            long likeCount = post.getLikeCount() + 1;
            postRepo.updatePostSetLikeAndShareById(likeCount,post.getShareCount(), postId);
            model.setId(newPostLike.getId());
            model.setPostId(newPostLike.getPostId());
            model.setUserId(newPostLike.getUserId());
            model.setDateLike(newPostLike.getDateLike());
            
            if(user.get().getId() != post.getUserId()){
                Date dateNow = new Date();
                Notification noti = new Notification();
                noti.setContext(user.get().getUserName()+" just liked your post");
                noti.setType("like");
                noti.setUpdateAt(dateNow);
                noti.setUserId(post.getUserId());
                noti.setSourceId(user.get().getId());

                notiRepo.save(noti);
            }
            
            return model;
        }
        PostLike tmp = postLike.get();
        repository.delete(tmp);
        
        //Delete notification if unlike
        notiRepo.deleteByUserIdAndSourceIdAndType(post.getUserId(), user.get().getId(), "like");
        
        long likeCount = post.getLikeCount() - 1;
        postRepo.updatePostSetLikeAndShareById(likeCount,post.getShareCount(), postId);
        model.setId(tmp.getId());
        model.setPostId(tmp.getPostId());
        model.setUserId(tmp.getUserId());
        model.setDateLike(tmp.getDateLike());
        model.setAction("unliked");
        return model;
    }

    @Override
    public List<PostForViewerModel> getAllPostLikeByUser(Long userId) {
        List<PostLike> likes = repository.findAllPostLikeByUserId(userId,FilterSort.getDesc("dateLike"));
        List<PostForViewerModel> list = new ArrayList<PostForViewerModel>();
        for(PostLike x : likes){   
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
                
                if(x.getPost().getPostImages().size() > 0){
                    for(Image img : x.getPost().getPostImages()){
                        tmp.getImages().add(img);
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
    
    
}
