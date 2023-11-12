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
@Table(name = "following")
public class Following {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    
    @Column
    private long userSourceId;
    
    @Column
    private long userTargetId;
    
    @Column
    private Date dateFollow;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userSourceId", insertable = false, updatable = false)
    private User user;

    public Following() {
        
    }
    
    public Following(long id, long userSourceId, long userTargetId, Date dateFollow, User user) {
        this.id = id;
        this.userSourceId = userSourceId;
        this.userTargetId = userTargetId;
        this.dateFollow = dateFollow;
        this.user = user;
    }
    
    public long getId() {
        return id;
    }

    public long getUserSourceId() {
        return userSourceId;
    }

    public void setUserSourceId(long userSourceId) {
        this.userSourceId = userSourceId;
    }

    public long getUserTargetId() {
        return userTargetId;
    }

    public void setUserTargetId(long userTargetId) {
        this.userTargetId = userTargetId;
    }

    public Date getDateFollow() {
        return dateFollow;
    }

    public void setDateFollow(Date dateFollow) {
        this.dateFollow = dateFollow;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}
