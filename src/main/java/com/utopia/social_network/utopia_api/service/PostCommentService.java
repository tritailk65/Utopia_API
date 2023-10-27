/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.entity.PostComment;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.MyBadRequestException;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IPostCommentService;
import com.utopia.social_network.utopia_api.model.PostCommentModel;
import com.utopia.social_network.utopia_api.repository.PostCommentRepository;
import com.utopia.social_network.utopia_api.repository.PostRepository;
import com.utopia.social_network.utopia_api.repository.UserRepository;

import java.text.ParseException;
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
public class PostCommentService implements IPostCommentService {

    @Autowired
    private PostCommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PostComment> getAllPostCommentById(Long id) {
        List<PostComment> postComments = commentRepository.findAllPostCommentById(id);
        
        if (postComments.size() == 0){
            throw new ResourceNotFoundException("Khong tim thay! Kiem tra lai ID");
        } else {
            return postComments;
        }

    }

    @Override
    public PostComment userCommentPost(PostCommentModel commentModel){
        try {
            PostComment p = convertToEntity(commentModel);
            List<User> tmp_user = userRepository.findAllById(p.getUserId());
            if(tmp_user.isEmpty()){
                return new PostComment();
            }
            List<Post> tmp_post = postRepository.findAllById(p.getPostId());
            if(tmp_post.isEmpty()){
                return new PostComment();
            }
            User user = tmp_user.get(0);
            Post post = tmp_post.get(0);      
            PostComment newComment = new PostComment();
            Date dateNow = new Date();
            newComment.setDateComment(dateNow);
            newComment.setUserId(user.getId());
            newComment.setPostId(post.getId());
            newComment.setComment(p.getComment());
            // Chưa biết cách set null cho 2 trường này :((
//            newComment.setParentId(null);
//            newComment.setItemId(null);
            commentRepository.save(newComment);
            
            return p;
        } catch (ParseException ex){
            throw new MyBadRequestException(ex.toString());
        }
    }
    
    @Override
    public PostComment userReplyComment(PostCommentModel commentModel){
        try {
            PostComment p = convertToEntity(commentModel);
            List<User> tmp_user = userRepository.findAllById(p.getUserId());
            if(tmp_user.isEmpty()){
                return new PostComment();
            }
            List<Post> tmp_post = postRepository.findAllById(p.getPostId());
            if(tmp_post.isEmpty()){
                return new PostComment();
            }
            List<PostComment> tmp_comment = commentRepository.findAllPostCommentById((long)p.getParentId());
            if(tmp_comment.isEmpty()){
                return new PostComment();
            }
            User user = tmp_user.get(0);
            Post post = tmp_post.get(0);      
            PostComment newComment = new PostComment();
            Date dateNow = new Date();
            newComment.setDateComment(dateNow);
            newComment.setUserId(user.getId());
            newComment.setPostId(post.getId());
            newComment.setComment(p.getComment());
            newComment.setParentId(p.getParentId());

            commentRepository.save(newComment);
            
            return p;
        } catch (ParseException ex){
            throw new MyBadRequestException(ex.toString());
        }
    }
    
    
    
    public boolean createNewComment(Post post){
        try{
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    private PostComment convertToEntity(PostCommentModel commentModel) throws ParseException{
        PostComment postComment = modelMapper.map(commentModel, PostComment.class);
        return postComment;
    }
}
