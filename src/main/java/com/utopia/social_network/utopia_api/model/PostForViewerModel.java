/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

import java.util.Date;

public class PostForViewerModel{
        private Long id;
        private String title = "";
        private String content = "";
        private long likeCount = 0;
        private long shareCount = 0;
        private int isHideLike = 0;
        private int commentStat = 0;
        private Date datePublished;
        private Date lastUpdate;
        private UserPostForViewerModel User = new UserPostForViewerModel();
        
        public PostForViewerModel() {
        }

        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
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
        public long getLikeCount() {
            return likeCount;
        }
        public void setLikeCount(long likeCount) {
            this.likeCount = likeCount;
        }
        public long getShareCount() {
            return shareCount;
        }
        public void setShareCount(long shareCount) {
            this.shareCount = shareCount;
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
        public Date getDatePublished() {
            return datePublished;
        }
        public void setDatePublished(Date datePublished) {
            this.datePublished = datePublished;
        }
        public Date getLastUpdate() {
            return lastUpdate;
        }
        public void setLastUpdate(Date lastUpdate) {
            this.lastUpdate = lastUpdate;
        }
        public UserPostForViewerModel getUser() {
            return User;
        }
        public void setUser(UserPostForViewerModel User) {
            this.User = User;
        }
        
    }
    
 