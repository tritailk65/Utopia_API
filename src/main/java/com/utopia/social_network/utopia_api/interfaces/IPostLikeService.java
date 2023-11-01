/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

import com.utopia.social_network.utopia_api.entity.PostLike;
import com.utopia.social_network.utopia_api.viewModel.SavePostLikeVM;
import java.util.List;

/**
 *
 * @author trita
 */

public interface IPostLikeService {
    List<PostLike> getAllPostLikeByUser(Long userId);
    PostLike savePost(Long userId, Long postId); 
    SavePostLikeVM LikePost(Long userId, Long postId); 
}
