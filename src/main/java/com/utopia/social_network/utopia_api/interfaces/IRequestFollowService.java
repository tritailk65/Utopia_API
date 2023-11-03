/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

import com.utopia.social_network.utopia_api.entity.RequestFollow;

/**
 *
 * @author trita
 */
public interface IRequestFollowService {
    
    //Gửi lời mời follow
    RequestFollow sendRequestFollow(Long userSrc, Long userTar);
    
    //Chấp nhập lời mời follow
    RequestFollow acceptRequestFollow(Long userSrc, Long urerTar);
    
    //Hủy bỏ gửi lời mời follow
    Boolean deleteRequestFollow(Long userSrc, Long urerTar);
   
}
