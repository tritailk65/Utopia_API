/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.viewModel;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class SavePostFavoriteVM {
    private long id;
    private long postId;
    private long userId;
    private Date DateFavorite;
    private String action = "saved";

    public SavePostFavoriteVM() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDateFavorite() {
        return DateFavorite;
    }

    public void setDateFavorite(Date DateFavorite) {
        this.DateFavorite = DateFavorite;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    
}
