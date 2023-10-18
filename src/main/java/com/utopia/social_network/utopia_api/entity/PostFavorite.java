/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author trita
 */
@Entity
@Table(name = "post_favorite")
public class PostFavorite {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column
    private long id;
    
    @Column
    private long postId;
    
    @Column
    private long userId;
    
    @Column
    private Date DateFavorite;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", insertable = false, updatable = false)
    private Post post;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    public PostFavorite(long id, long postId, long userId, Date DateFavorite, Post post, User user) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.DateFavorite = DateFavorite;
        this.post = post;
        this.user = user;
    }

    public long getId() {
        return id;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
