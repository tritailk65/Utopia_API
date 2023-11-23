/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

import com.utopia.social_network.utopia_api.entity.Image;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostForViewerModel{
        private Long id;
        private String title = "";
        private String content = "";
        private long likeCount = 0;
        private long shareCount = 0;
        private long commentCount = 0;
        private int isHideLike = 0;
        private int commentStat = 0;
        private boolean isLiked = false;
        private boolean isSaved = false;
        private boolean isOwner = false;
        private Date datePublished;
        private Date lastUpdate;
        private long time;
        private List<Image> images = new ArrayList<Image>();
        private UserPostForViewerModel User = new UserPostForViewerModel();
        
        public long calcDuration (Date lastUpdate) {
            Date currentTime = new Date();
            long diff = currentTime.getTime() - lastUpdate.getTime();
            long hours = diff/(1000 * 60 * 60);
            return hours;
        }
        
        public PostForViewerModel() {
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
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

        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }

        public boolean isIsLiked() {
            return isLiked;
        }

        public void setIsLiked(boolean isLiked) {
            this.isLiked = isLiked;
        }

        public boolean isIsSaved() {
            return isSaved;
        }

        public void setIsSaved(boolean isSaved) {
            this.isSaved = isSaved;
        }

        public boolean isIsOwner() {
            return isOwner;
        }

        public void setIsOwner(boolean isOwner) {
            this.isOwner = isOwner;
        }

        public long getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(long commentCount) {
            this.commentCount = commentCount;
        }
        
    }
    
 