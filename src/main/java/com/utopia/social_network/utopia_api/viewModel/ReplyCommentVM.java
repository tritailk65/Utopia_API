package com.utopia.social_network.utopia_api.viewModel;

import com.utopia.social_network.utopia_api.model.UserPostForViewerModel;
import java.util.Date;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class ReplyCommentVM {
    private long id;
    @NonNull
    private long userId;
    @NonNull
    private long postId;
    @NonNull
    private String comment = "";
    @NonNull
    private int parentId;
    @Nullable
    private Date date;
    @Nullable
    private UserPostForViewerModel user = new UserPostForViewerModel();

    public ReplyCommentVM(long id, long userId, long postId, int parentId, Date date) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.parentId = parentId;
        this.date = date;
    }

    public ReplyCommentVM() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserPostForViewerModel getUser() {
        return user;
    }

    public void setUser(UserPostForViewerModel user) {
        this.user = user;
    }

    
}
