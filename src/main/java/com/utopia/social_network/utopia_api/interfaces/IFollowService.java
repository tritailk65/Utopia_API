/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.utopia.social_network.utopia_api.interfaces;

/**
 *
 * @author toica
 */
public interface IFollowService {
    boolean addRequestFollow(long user_src, long user_tar);
    boolean acceptRequestFollow(long user_src, long user_tar);
    
}
