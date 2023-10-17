/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 * @author trita
 */
@Entity
@Table(name = "user")
public class User {
    
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long Id;
    
    @Column
    private String UserName;
    
    @Column
    private String PassWord;
    
    @Column
    private String Phone;
    
    @Column
    private String Email;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Post> posts;
    
    @OneToMany(mappedBy = "user")
    private Set<PostLike> postLikes;
    
    @OneToMany(mappedBy = "user")
    private Set<PostFavorite> postFavorites;
    
    @OneToMany(mappedBy = "user")
    private Set<PostComment> postComments;
    
    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;
    
    @OneToMany(mappedBy = "user")
    private Set<Following> followings;
    
    @OneToMany(mappedBy = "user")
    private Set<RequestFollow> requestFollows;
    
    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    public User(long Id, String UserName, String PassWord, String Phone, String Email, Set<Post> posts, Set<PostLike> postLikes, Set<PostFavorite> postFavorites, Set<PostComment> postComments, Set<Notification> notifications, Set<Following> followings, Set<RequestFollow> requestFollows, UserProfile userProfile) {
        this.Id = Id;
        this.UserName = UserName;
        this.PassWord = PassWord;
        this.Phone = Phone;
        this.Email = Email;
        this.posts = posts;
        this.postLikes = postLikes;
        this.postFavorites = postFavorites;
        this.postComments = postComments;
        this.notifications = notifications;
        this.followings = followings;
        this.requestFollows = requestFollows;
        this.userProfile = userProfile;
    }

    public long getId() {
        return Id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<PostLike> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(Set<PostLike> postLikes) {
        this.postLikes = postLikes;
    }

    public Set<PostFavorite> getPostFavorites() {
        return postFavorites;
    }

    public void setPostFavorites(Set<PostFavorite> postFavorites) {
        this.postFavorites = postFavorites;
    }

    public Set<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(Set<PostComment> postComments) {
        this.postComments = postComments;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<Following> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<Following> followings) {
        this.followings = followings;
    }

    public Set<RequestFollow> getRequestFollows() {
        return requestFollows;
    }

    public void setRequestFollows(Set<RequestFollow> requestFollows) {
        this.requestFollows = requestFollows;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    
    
}
