/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

import com.utopia.social_network.utopia_api.entity.User;
import java.util.Date;

/**
 *
 * @author trita
 */
public class RequestFollowModel {

    private Long id;

    private long userSourceId;

    private long userTargetId;

    private Date requestDate;

    private Date approveDate;

    private int isPending;  //For check request in status waiting
    
    private User userRequest;

    public RequestFollowModel(Long id, long userSourceId, long userTargetId, Date requestDate, Date approveDate, int isPending, User userRequest) {
        this.id = id;
        this.userSourceId = userSourceId;
        this.userTargetId = userTargetId;
        this.requestDate = requestDate;
        this.approveDate = approveDate;
        this.isPending = isPending;
        this.userRequest = userRequest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(User userRequest) {
        this.userRequest = userRequest;
    }
    
    
}
