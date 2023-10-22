/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.interfaces.IUserService;
import com.utopia.social_network.utopia_api.model.UserLoginModel;
import com.utopia.social_network.utopia_api.model.UserProfileModel;
import com.utopia.social_network.utopia_api.model.UserRegisterModel;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author trita
 */
@Service
public class UserService implements IUserService {

    @Autowired
    public UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findUserById(id);
    }

    @Override
    public Boolean login(UserLoginModel uLogin) {
        User u = new  User();
        
        if (uLogin.getPhone() != null){
            u = userRepo.findUserByPhoneAndPassword(uLogin.getUserName(), uLogin.getPassword());
        } else if (uLogin.getUserName() != null){
            u = userRepo.findUserByUserNameAndPassword(uLogin.getUserName(), uLogin.getPassword());
        } else if (uLogin.getUserName() != null) {
            u = userRepo.findUserByUserNameAndPassword(uLogin.getUserName(), uLogin.getPassword());
        }
       
        if (u != null){
            return true;
        }
        return false;
    }

    @Override
    public void signUp(UserRegisterModel userRegisterModel){
        User user = convertToEntity(userRegisterModel);
        Date date = new Date();
        user.setUpdateAt(date);
        userRepo.save(user);
    }

    @Override
    public void updateUserAvatarPath(String path, Long id) {
        userRepo.updateUserSetAvatarPathById(path, id);
    }

    private User convertToEntity(UserRegisterModel u) {
        User user = modelMapper.map(u, User.class);
        return user;
    }

    @Override
    public User editProfile(UserProfileModel u, Long id) {
        userRepo.updateUserSetAvatarPathById(u.getFullName(), u.getWebsite(), u.getBio(), u.getGender(), id);
        return userRepo.findUserById(id);
    }

}
