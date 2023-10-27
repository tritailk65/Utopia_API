/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

import javax.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

/**
 *
 * @author trita
 */
public class PostCommentModel {
    @NonNull
    private long userId;
    @NonNull
    private long postId;
    @NonNull
    private String comment;
    @NonNull
    private int parentId;

    public PostCommentModel(long userId, long postId, String comment, int parentId) {
        this.userId = userId;
        this.postId = postId;
        this.comment = comment;
        this.parentId = parentId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}

