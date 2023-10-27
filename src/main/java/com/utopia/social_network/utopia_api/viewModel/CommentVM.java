package com.utopia.social_network.utopia_api.viewModel;

import com.utopia.social_network.utopia_api.model.UserPostForViewerModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.lang.Nullable;

public class CommentVM {
    private long id;
    @Nullable
    private long userId;
    @Nullable
    private long postId;
    @Nullable
    private String comment = "";
    @Nullable
    private Date date;
    @Nullable
    private int totals = 0;
    @Nullable
    private UserPostForViewerModel user = new UserPostForViewerModel();
    @Nullable
    private List<ReplyCommentVM> replies = new ArrayList<ReplyCommentVM>();

    public CommentVM(long id, long userId, long postId, Date date) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.date = date;
    }

    public CommentVM() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotals() {
        return totals;
    }

    public void setTotals(int totals) {
        this.totals = totals;
    }

    public UserPostForViewerModel getUser() {
        return user;
    }

    public void setUser(UserPostForViewerModel user) {
        this.user = user;
    }

    public List<ReplyCommentVM> getReplies() {
        return replies;
    }

    public void setReplies(List<ReplyCommentVM> replies) {
        this.replies = replies;
    }

    
}
