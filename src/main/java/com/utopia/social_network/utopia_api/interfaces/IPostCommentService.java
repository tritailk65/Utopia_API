/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

import com.utopia.social_network.utopia_api.entity.PostComment;
import com.utopia.social_network.utopia_api.model.PostCommentModel;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author trita
 */
public interface IPostCommentService {
    List<PostComment> getAllPostCommentById(Long id);
    
    PostComment userCommentPost(PostCommentModel commentModel);
}
