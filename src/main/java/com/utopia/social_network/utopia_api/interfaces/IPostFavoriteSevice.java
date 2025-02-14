/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

import com.utopia.social_network.utopia_api.entity.PostFavorite;
import com.utopia.social_network.utopia_api.model.PostForViewerModel;
import com.utopia.social_network.utopia_api.viewModel.SavePostFavoriteVM;
import java.util.List;

/**
 *
 * @author trita
 */
public interface IPostFavoriteSevice {
    List<PostForViewerModel> getAllPostFavoriteByUserId(Long userId);
    
    PostFavorite save(Long userId, Long postId);
    SavePostFavoriteVM saveFavoritePost(Long userId, Long postId);
    //Cancle save post here
}
