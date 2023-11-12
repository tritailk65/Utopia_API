/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

import com.utopia.social_network.utopia_api.entity.Following;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.model.FollowingModel;
import java.util.List;

/**
 *
 * @author toica
 */
public interface IFollowService {
    boolean addRequestFollow(long user_src, long user_tar);
    boolean acceptRequestFollow(long user_src, long user_tar);
    
    List<FollowingModel> findByUserSourceId(long userSourceId);
    List<User> findUsersFollowedByCurrentUser(long currentUserId);
    boolean unFollow(long usersourceId, long usertargetId);
}
