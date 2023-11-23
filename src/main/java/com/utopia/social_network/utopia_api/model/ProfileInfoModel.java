/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

/**
 *
 * @author trita
 */
public class ProfileInfoModel {
    private int post;
    private int follower;
    private int following;
    private String bio;
    private String website;

    public ProfileInfoModel(int post, int follower, int following, String bio, String website) {
        this.post = post;
        this.follower = follower;
        this.following = following;
        this.bio = bio;
        this.website = website;
    }
    
    public ProfileInfoModel(){}
    
    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
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
    
    
}
