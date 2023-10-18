/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.entity;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author trita
 */

@Entity
@Table(name = "photo")
public class Photo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private long Id;
    
    @Column
    private String imgBase64;
    
    @Column
    private Date createdAt;
    
    @Column
    private long postId;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postId", referencedColumnName = "id", insertable = false, updatable = false)
    private Post post;

    public Photo(long Id, String imgBase64, Date createdAt, long postId, Post post) {
        this.Id = Id;
        this.imgBase64 = imgBase64;
        this.createdAt = createdAt;
        this.postId = postId;
        this.post = post;
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
    
}
