/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

import com.utopia.social_network.utopia_api.entity.PostFavorite;
import java.util.List;

/**
 *
 * @author trita
 */
public interface IPostFavoriteSevice {
    List<PostFavorite> getAllPostFavoriteByUserId(Long userId);
    
    PostFavorite savePost(Long userId, Long postId);
    
    //Cancle save post here
}
