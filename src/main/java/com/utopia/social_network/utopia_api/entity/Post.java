/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author trita
 */

@Entity
@Table(name = "post")
public class Post{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long id;
    
    @Column
    private Long userId;
    
    @Column
    private String title;
    
    @Column
    private String content;
    
    @Column
    private int isActive;
    
    @Column
    private Date datePublished;
    
    @Column
    private long likeCount;
    
    @Column
    private long shareCount;
    
    @Column
    private Date lastUpdate;
    
    @Column
    private int isHideLike;
    
    @Column
    private int commentStat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserId", insertable = false, updatable = false)
    private User user;
    
    @ManyToMany
    @JoinTable(
            name = "post_image",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> postImages;
    
    @OneToMany(mappedBy = "post")
    private Set<PostLike> postLikes;
    
    @OneToMany(mappedBy = "post")
    private Set<PostFavorite> postFavorites;
    
    @OneToMany(mappedBy = "post")
    private Set<PostComment> postComments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
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

    
    
}
