/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

/**
 *
 * @author DELL
 */
public class EditPostModel {
    private Long postId;
    private String title = "";
    private int isHideLike = 0;
    private int commentStat = 0;
    private boolean alert = true;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsHideLike() {
        return isHideLike;
    }

    public void setIsHideLike(int isHideLike) {
        this.isHideLike = isHideLike;
    }

    public int getCommentStat() {
        return commentStat;
    }

    public void setCommentStat(int commentStat) {
        this.commentStat = commentStat;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    
    
}
