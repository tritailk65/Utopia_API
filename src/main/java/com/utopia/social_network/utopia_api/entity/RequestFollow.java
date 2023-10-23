/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.entity;

import java.util.Date;
import javax.annotation.Generated;
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
@Table(name = "request_follow")
public class RequestFollow {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column
    private Long id;
    
    @Column
    private long userSourceId;
    
    @Column
    private long userTargetId;
    
    @Column
    private Date requestDate;
    
    @Column
    private Date approveDate;
    
    @Column
    private Date deniedDate;
    
    @Column
    private Date terminationDate;
    
    @Column
    private int mutualFriend;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userSourceId", insertable = false, updatable = false)
    private User user;

    public Long getId() {
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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Date getDeniedDate() {
        return deniedDate;
    }

    public void setDeniedDate(Date deniedDate) {
        this.deniedDate = deniedDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public int getMutualFriend() {
        return mutualFriend;
    }

    public void setMutualFriend(int mutualFriend) {
        this.mutualFriend = mutualFriend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
