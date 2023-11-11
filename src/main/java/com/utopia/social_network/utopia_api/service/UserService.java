/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.MyBadRequestException;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IUserService;
import com.utopia.social_network.utopia_api.model.UserLoginModel;
import com.utopia.social_network.utopia_api.model.UserProfileModel;
import com.utopia.social_network.utopia_api.model.UserRegisterModel;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
        User u = userRepo.findUserById(id);
        
        if(u == null){
            throw new ResourceNotFoundException("Khong tim thay User! Kiem tra lai ID");
        } else {
            return u;
        }
    }

    @Override
    public User login(UserLoginModel uLogin) {
        if(uLogin.getUserName() != null){
        return userRepo.findUserByUserNameAndPassword(uLogin.getUserName(), uLogin.getPassword());
        }else if(uLogin.getEmail() != null) return userRepo.findUserByEmailAndPassword(uLogin.getEmail(), uLogin.getPassword());
        else if(uLogin.getPhone() != null) return userRepo.findUserByPhoneAndPassword(uLogin.getPhone(), uLogin.getPassword());
        return null;
    }
    


    @Override
    public User findUserByUsernameOrEmailOrPhoneNumber(String u){
    if(userRepo.findUserByEmail(u)!=null){
    return userRepo.findUserByEmail(u);
    }
    if(userRepo.findUserByPhone(u)!=null){
    return userRepo.findUserByPhone(u);
    }
    if(userRepo.findUserByUserName(u)!=null){
    return userRepo.findUserByUserName(u);
    }
    return null;
    }

    @Override
    public User signUp(UserRegisterModel userRegisterModel) {
        
        //Check điều kiện đăng ký tài khoản mới
        if (userRepo.findUserByUserName(userRegisterModel.getUserName()) != null){
            throw new MyBadRequestException("UserName đã tồn tại");      
        } else {
            if ((userRegisterModel.getEmail() != null && userRegisterModel.getEmail() != "") ? (userRepo.findUserByEmail(userRegisterModel.getEmail()) != null) : false ){
                throw new MyBadRequestException("Email đã tồn tại");
            } else if ((userRegisterModel.getPhone()!= null && userRegisterModel.getPhone() != "") ? (userRepo.findUserByPhone(userRegisterModel.getPhone()) != null) : false ){
                throw new MyBadRequestException("Số điện thoại đã tồn tại");
            }
        }
        
        //Update time
        User user = convertToEntity(userRegisterModel);
        Date date = new Date();
        user.setCreateAt(date);
        user.setUpdateAt(date);
   
        return userRepo.save(user);
    }

    @Override
    public void updateUserAvatarPath(String fileName, Long id) {
        User u = userRepo.findUserById(id);
        
        if(u == null){
            throw new ResourceNotFoundException("Khong tim thay User! Kiem tra lai ID");
        }
        //Khong can try-cath o day
        userRepo.updateUserSetAvatarPathById(fileName, id);
    }
    
    @Override
    public User editProfile(UserProfileModel u, Long id) {
        User uCheck = userRepo.findUserById(id);
        
        if(uCheck == null){
            throw new ResourceNotFoundException("Khong tim thay User! Kiem tra lai ID");
        }
        userRepo.updateUserSetAvatarPathById(u.getFullName(), u.getWebsite(), u.getBio(), u.getGender(), id);
        userRepo.flush();
        return userRepo.findUserById(id);
    }

    private User convertToEntity(UserRegisterModel u) {
        User user = modelMapper.map(u, User.class);
        return user;
    }

    @Override
    public List<User> getSuggestByUser(Long id) {
        return null;
    }
    
    
}
