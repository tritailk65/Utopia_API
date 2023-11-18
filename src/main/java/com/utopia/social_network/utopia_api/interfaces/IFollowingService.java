/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

import com.utopia.social_network.utopia_api.entity.Following;
import java.util.List;

/**
 *
 * @author toica
 */
public interface IFollowingService {
    
    List<Following> getAllFollowingByUser(Long id);
    
    void cancelFollow(Long userSrc, Long userTar);
}
