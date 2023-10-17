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
@Table (name = "notification")
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long Id;
    
    @Column
    private long userId;
    
    @Column
    private int isLikeNoti;
    
    @Column
    private int isCmtNoti;
    
    @Column
    private Date updateAt;
    
    @Column
    private int isActive;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    public Notification(long Id, long userId, int isLikeNoti, int isCmtNoti, Date updateAt, int isActive, User user) {
        this.Id = Id;
        this.userId = userId;
        this.isLikeNoti = isLikeNoti;
        this.isCmtNoti = isCmtNoti;
        this.updateAt = updateAt;
        this.isActive = isActive;
        this.user = user;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getIsLikeNoti() {
        return isLikeNoti;
    }

    public void setIsLikeNoti(int isLikeNoti) {
        this.isLikeNoti = isLikeNoti;
    }

    public int getIsCmtNoti() {
        return isCmtNoti;
    }

    public void setIsCmtNoti(int isCmtNoti) {
        this.isCmtNoti = isCmtNoti;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
