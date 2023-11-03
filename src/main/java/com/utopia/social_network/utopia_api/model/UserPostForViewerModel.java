/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class UserPostForViewerModel{
        private long id;
        private String userName;
        private Date createAt;
        private Date updateAt;
        private String avatarPath;
        private String website;

        public UserPostForViewerModel() {
        }
        
        public long getId() {
            return id;
        }
        public void setId(long id) {
            this.id = id;
        }
        public String getUserName() {
            return userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public Date getCreateAt() {
            return createAt;
        }
        public void setCreateAt(Date createAt) {
            this.createAt = createAt;
        }
        public Date getUpdateAt() {
            return updateAt;
        }
        public void setUpdateAt(Date updateAt) {
            this.updateAt = updateAt;
        }
        public String getAvatarPath() {
            return avatarPath;
        }
        public void setAvatarPath(String avatarPath) {
            this.avatarPath = avatarPath;
        }
        public String getWebsite() {
            return website;
        }
        public void setWebsite(String website) {
            this.website = website;
        }
        
    }
