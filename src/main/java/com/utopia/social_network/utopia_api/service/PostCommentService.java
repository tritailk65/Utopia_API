/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.PostComment;
import com.utopia.social_network.utopia_api.exception.MyBadRequestException;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IPostCommentService;
import com.utopia.social_network.utopia_api.model.PostCommentModel;
import com.utopia.social_network.utopia_api.repository.PostCommentRepository;

import java.text.ParseException;
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
            commentRepository.save(p);
            return p;
        } catch (ParseException ex){
            throw new MyBadRequestException(ex.toString());
        }
    }

    private PostComment convertToEntity(PostCommentModel commentModel) throws ParseException{
        PostComment postComment = modelMapper.map(commentModel, PostComment.class);
        return postComment;
    }
}
