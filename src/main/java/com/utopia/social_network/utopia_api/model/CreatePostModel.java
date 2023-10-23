/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

import java.util.Date;

/**
 *
 * @author trita
 */
public class CreatePostModel {

    private Long userId;
    private String title;
    private String content;
    private int isHideLike;
    private int commentStat;

    public CreatePostModel(Long userId, String title, String content, int isHideLike, int commentStat) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.isHideLike = isHideLike;
        this.commentStat = commentStat;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    
    
}
