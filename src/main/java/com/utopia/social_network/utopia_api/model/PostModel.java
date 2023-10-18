/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

import java.util.Date;
import java.util.UUID;


/**
 *
 * @author trita
 */

public class PostModel {
    public long post_Id;
    public long user_Id;
    public String title;
    public String content;
    public int isActive;
    public String status;
    public Date datePublished;
    public int isHideLike;
    public int commentStat;
    public long shareCount;
    public long likeCount;
    public Date lastUpdate;

    public PostModel(long post_Id, long user_Id, String title, String content, int isActive, String status, Date datePublished, int isHideLike, int commentStat, long shareCount, long likeCount, Date lastUpdate) {
        this.post_Id = post_Id;
        this.user_Id = user_Id;
        this.title = title;
        this.content = content;
        this.isActive = isActive;
        this.status = status;
        this.datePublished = datePublished;
        this.isHideLike = isHideLike;
        this.commentStat = commentStat;
        this.shareCount = shareCount;
        this.likeCount = likeCount;
        this.lastUpdate = lastUpdate;
    }

    public long getPost_Id() {
        return post_Id;
    }

    public long getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(long user_Id) {
        this.user_Id = user_Id;
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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
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

    public long getShareCount() {
        return shareCount;
    }

    public void setShareCount(long shareCount) {
        this.shareCount = shareCount;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    
}
