/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author trita
 */

@Entity
@Table(name="user_profile") 
public class UserProfile {
    
    @Column
    private long userId;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private long id;
    
    @Column
    private String fullName;
    
    @Column
    private String gender;
    
    @Column
    private Date createAt;
    
    @Column
    private Date updateAt;
    
    @Column
    private String bio;
    
    @Column
    private String website;
    
    @Column
    private String imgBase64;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id" , insertable = false, updatable = false)
    private User user;

    public UserProfile(long userId, long id, String fullName, String gender, Date createAt, Date updateAt, String bio, String website, String imgBase64, User user) {
        this.userId = userId;
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.bio = bio;
        this.website = website;
        this.imgBase64 = imgBase64;
        this.user = user;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
