/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author trita
 */
@Entity
@Table(name = "post")
public class PostEntity{
    
    @Id
    @Column(name="Post_Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long postId;
    
    @Column(name="User_Id")
    private Long userId;
    
    @Column(name="Title")
    private String title;
    
    @Column(name="Content")
    private String content;
    
    @Column(name="Status")
    private String status;
    
    @Column(name="DatePublished")
    private Date datePublished;
    
    @Column(name="Photo_Id")
    private long photoId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
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
    
}
