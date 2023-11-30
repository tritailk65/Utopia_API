/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.model.ProfileInfoModel;
import com.utopia.social_network.utopia_api.model.Email;
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
    
    UserProfileModel getUserByUserName(String name);
    
    void updateUserAvatarPath(String path, Long id);  
    
    User getUserById(Long id);
    
    User login(UserLoginModel uLogin);

    User signUp(UserRegisterModel userRegisterModel);
    
    User editProfile(UserProfileModel uProfile, Long id);
    
    List<User> getSuggestByUser (Long id);
    
    ProfileInfoModel getProfileInfo (Long id);
    String sendMail(Email email);
    
    String resetPassword(String token, String password);
}
