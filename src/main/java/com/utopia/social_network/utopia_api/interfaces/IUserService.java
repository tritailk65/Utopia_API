/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.model.UserLoginModel;
import com.utopia.social_network.utopia_api.model.UserProfileModel;
import com.utopia.social_network.utopia_api.model.UserRegisterModel;
import java.util.List;

/**
 *
 * @author trita
 */
public interface IUserService {
    
    List<User> getAllUser();
    
    void updateUserAvatarPath(String path, Long id);  
    
    User getUserById(Long id);
    
    Boolean login(UserLoginModel uLogin);

    User signUp(UserRegisterModel userRegisterModel);
    
    User editProfile(UserProfileModel uProfile, Long id);
}
