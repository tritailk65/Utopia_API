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
    private int isPending;  //For check request in status waiting
    
    //EAGER for transaction one more time
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userSourceId", insertable = false, updatable = false)
    private User userSrc;
    
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

    public int getIsPending() {
        return isPending;
    }

    public void setIsPending(int isPending) {
        this.isPending = isPending;
    }

    public User getUserSrc() {
        return userSrc;
    }

    public void setUserSrc(User userSrc) {
        this.userSrc = userSrc;
    }

//    public User getUserTar() {
//        return userTar;
//    }
//
//    public void setUserTar(User userTar) {
//        this.userTar = userTar;
//    }
}
