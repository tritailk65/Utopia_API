/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.BadRequestException;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IUserService;
import com.utopia.social_network.utopia_api.model.UserModel;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import org.springframework.stereotype.Service;
/**
 *
 * @author hieu
 */
@Service
public class UserService implements IUserService {
    
    @Autowired
    private UserRepository userRepo;
    
    @Override
    public List<User> GetAllUser(Long id) {
        List<User> rs = new ArrayList<User>();
        
        if (id == null){
            rs = userRepo.findAll();
            
        } else if (id == null){
            
        }
    }
    @Override
    public List<User> GetAllUserById(Long id) {
        return userRepo.findUserById(id);
    }

    @Override
    public void CreateUser(UserModel user) {
         
    }

    @Override
    public void DeleteUserById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void UpdateUser(Long id, UserModel user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
