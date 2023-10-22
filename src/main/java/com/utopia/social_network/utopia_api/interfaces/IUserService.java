/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.model.UserModel;
import java.util.List;
import org.springframework.lang.Nullable;
/**
 *
 * @author hieu
 */
public interface IUserService {
    List<User> GetAllUser (@Nullable Long id);
    List<User> GetAllUserById (Long id);
    void CreateUser(UserModel user);
    void DeleteUserById (Long id);
    void UpdateUser(Long id, UserModel user);
}
