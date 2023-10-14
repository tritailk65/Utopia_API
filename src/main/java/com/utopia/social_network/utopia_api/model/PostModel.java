/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.model;

import java.util.Date;
import java.util.UUID;


/**
 *
 * @author trita
 */

public class PostModel {
    public long post_Id;
    public long user_Id;
    public String title;
    public String Content;
    public String Status;
    public Date DatePublished;          

    public long getPost_Id() {
        return post_Id;
    }

    public void setPost_Id(long post_Id) {
        this.post_Id = post_Id;
    }

    public long getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(long user_Id) {
        this.user_Id = user_Id;
    }

    public PostModel(long post_Id, long user_Id, String title, String Content, String Status, Date DatePublished) {
        this.post_Id = post_Id;
        this.user_Id = user_Id;
        this.title = title;
        this.Content = Content;
        this.Status = Status;
        this.DatePublished = DatePublished;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Date getDatePublished() {
        return DatePublished;
    }

    public void setDatePublished(Date DatePublished) {
        this.DatePublished = DatePublished;
    }


}
