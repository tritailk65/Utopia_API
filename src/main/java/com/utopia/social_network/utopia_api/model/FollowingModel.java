/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

import com.utopia.social_network.utopia_api.entity.Following;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class FollowingModel {
    private long id;
    private long userSourceId;
    private long userTargetId;
    private Date dateFollow;
    
    public FollowingModel(){
        
    }
    
    public FollowingModel(Following following) {
        this.id = following.getId();
        this.userSourceId = following.getUserSourceId();
        this.userTargetId = following.getUserTargetId();
        this.dateFollow = following.getDateFollow();
    }
    
    public FollowingModel(long id, long userSourceId, long userTargetId, Date dateFollow) {
        this.id = id;
        this.userSourceId = userSourceId;
        this.userTargetId = userTargetId;
        this.dateFollow = dateFollow;
    }

    public long getId() {
        return id;
    }

    public long getUserSourceId() {
        return userSourceId;
    }

    public long getUserTargetId() {
        return userTargetId;
    }

    public Date getDateFollow() {
        return dateFollow;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserSourceId(long userSourceId) {
        this.userSourceId = userSourceId;
    }

    public void setUserTargetId(long userTargetId) {
        this.userTargetId = userTargetId;
    }

    public void setDateFollow(Date dateFollow) {
        this.dateFollow = dateFollow;
    }
    
}
