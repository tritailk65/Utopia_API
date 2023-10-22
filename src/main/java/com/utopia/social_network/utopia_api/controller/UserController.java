/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.controller;
import com.utopia.social_network.utopia_api.utils.APIResult;
import com.utopia.social_network.utopia_api.interfaces.IUserService;
import com.utopia.social_network.utopia_api.model.PostModel;
import com.utopia.social_network.utopia_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author hieu
 */
public class UserController {
    private IUserService userService;
    @Autowired
    private APIResult rs;
    @PostMapping 
    private APIResult createdNewUser(@RequestBody PostModel postMpdel){
        
    }
}
