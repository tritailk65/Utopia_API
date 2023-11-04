package com.utopia.social_network.utopia_api.service;

import com.utopia.social_network.utopia_api.entity.Notification;
import com.utopia.social_network.utopia_api.entity.Post;
import com.utopia.social_network.utopia_api.entity.PostComment;
import com.utopia.social_network.utopia_api.entity.User;
import com.utopia.social_network.utopia_api.exception.MyBadRequestException;
import com.utopia.social_network.utopia_api.exception.ResourceNotFoundException;
import com.utopia.social_network.utopia_api.interfaces.IPostCommentService;
import com.utopia.social_network.utopia_api.model.PostCommentModel;
import com.utopia.social_network.utopia_api.repository.NotificationRepository;
import com.utopia.social_network.utopia_api.repository.PostCommentRepository;
import com.utopia.social_network.utopia_api.repository.PostRepository;
import com.utopia.social_network.utopia_api.repository.UserRepository;
import com.utopia.social_network.utopia_api.viewModel.CommentVM;
import com.utopia.social_network.utopia_api.viewModel.ReplyCommentVM;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCommentService implements IPostCommentService {

    @Autowired
    private PostCommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;    
    @Autowired
    private NotificationRepository notiRepository;    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PostComment> getAllPostCommentById(Long id) {
        List<PostComment> postComments = commentRepository.findAllPostCommentById(id);
        
        if (postComments.size() == 0){
            throw new ResourceNotFoundException("Khong tim thay! Kiem tra lai ID");
        } else {
            return postComments;
        }
    }
    
    
    public List<CommentVM> getAllCommentByPostId(Long id) {
        List<CommentVM> comments = new ArrayList<CommentVM>();
        List<PostComment> data = commentRepository.findAllPostCommentByPostId(id);

        if (data.isEmpty()) {
            return comments;
        }

        Map<Long, CommentVM> commentMap = new HashMap<>(); // Sử dụng Map (Dictionary) để lưu trữ CommentVM theo id

        for (PostComment p : data) {
            if (p.getParentId() <= 0) {
                CommentVM tmp = new CommentVM(p.getId(), p.getUserId(), p.getPostId(), p.getDateComment());
                tmp.setComment(p.getComment());
                tmp.setTotals(0);
                
                if(p.getUser() != null){
                    tmp.getUser().setId(p.getUser().getId());
                    tmp.getUser().setAvatarPath(p.getUser().getAvatarPath());
                    tmp.getUser().setCreateAt(p.getUser().getCreateAt());
                    tmp.getUser().setUserName(p.getUser().getUserName());
                    tmp.getUser().setWebsite(p.getUser().getWebsite());
                }
                comments.add(tmp);

                // Lưu CommentVM cha vào từ điển
                commentMap.put(p.getId(), tmp);
            }
        }
        
        for (PostComment p : data) {
            if (p.getParentId() > 0) {
                long tmp_id = p.getParentId();
                CommentVM parentComment = commentMap.get(tmp_id);
                if (parentComment != null) {
                    ReplyCommentVM reply = new ReplyCommentVM();
                    reply.setId(p.getId());
                    reply.setComment(p.getComment());
                    reply.setDate(p.getDateComment());
                    reply.setParentId(p.getParentId());
                    reply.setPostId(p.getPostId());
                    reply.setUserId(p.getUserId());
                    if(p.getUser() != null ){
                        reply.getUser().setId(p.getUser().getId());
                        reply.getUser().setAvatarPath(p.getUser().getAvatarPath());
                        reply.getUser().setCreateAt(p.getUser().getCreateAt());
                        reply.getUser().setUserName(p.getUser().getUserName());
                        reply.getUser().setWebsite(p.getUser().getWebsite());
                    }
                    parentComment.getReplies().add(reply);
                    parentComment.setTotals(parentComment.getTotals() + 1);
                }
            }
        }

        return comments;
    }
    
    @Override
    public PostComment userCommentPost(PostCommentModel commentModel){
        try {
            PostComment p = convertToEntity(commentModel);
            List<User> tmp_user = userRepository.findAllById(p.getUserId());
            if(tmp_user.isEmpty()){
                return new PostComment();
            }
            List<Post> tmp_post = postRepository.findAllById(p.getPostId());
            if(tmp_post.isEmpty()){
                return new PostComment();
            }
            User user = tmp_user.get(0);
            Post post = tmp_post.get(0);      
            PostComment newComment = new PostComment();
            Date dateNow = new Date();
            newComment.setDateComment(dateNow);
            newComment.setUserId(user.getId());
            newComment.setPostId(post.getId());
            newComment.setComment(p.getComment());
            newComment.setParentId(-1);
            newComment.setItemId(-1);
            commentRepository.save(newComment);
            
            // User tự comment vào post của mình thì ko thông báo
            if(user.getId() != post.getUserId()){
                Notification noti = new Notification();
                noti.setContext(user.getFullName()+" just commented on your post");
                noti.setType("comment");
                noti.setUpdateAt(dateNow);
                noti.setUserId(post.getUserId());
                noti.setSourceId(user.getId());

                notiRepository.save(noti);
            }
            
            
            return p;
        } catch (ParseException ex){
            throw new MyBadRequestException(ex.toString());
        }
    }
    
    @Override
    public PostComment userReplyComment(PostCommentModel commentModel){
        try {
            PostComment p = convertToEntity(commentModel);
            List<User> tmp_user = userRepository.findAllById(p.getUserId());
            if(tmp_user.isEmpty()){
                return new PostComment();
            }
            List<Post> tmp_post = postRepository.findAllById(p.getPostId());
            if(tmp_post.isEmpty()){
                return new PostComment();
            }
            List<PostComment> tmp_comment = commentRepository.findAllPostCommentById((long)p.getParentId());
            if(tmp_comment.isEmpty()){
                return new PostComment();
            }
            User user = tmp_user.get(0);
            Post post = tmp_post.get(0);   
            PostComment parentCmt = tmp_comment.get(0);
            PostComment newComment = new PostComment();
            Date dateNow = new Date();
            newComment.setDateComment(dateNow);
            newComment.setUserId(user.getId());
            newComment.setPostId(post.getId());
            newComment.setComment(p.getComment());
            newComment.setParentId(p.getParentId());

            commentRepository.save(newComment);
            
            // User tự replied comment thì ko thông báo
            if(parentCmt.getUserId() != user.getId()){
                Notification noti = new Notification();
                noti.setContext(user.getFullName()+" just replied on your comment");
                noti.setType("comment");
                noti.setUpdateAt(dateNow);
                noti.setUserId(parentCmt.getUserId());
                noti.setSourceId(user.getId());

                notiRepository.save(noti);
            }
            
            return p;
        } catch (ParseException ex){
            throw new MyBadRequestException(ex.toString());
        }
    }
  

    private PostComment convertToEntity(PostCommentModel commentModel) throws ParseException{
        PostComment postComment = modelMapper.map(commentModel, PostComment.class);
        return postComment;
    }

    @Override
    public boolean editComment(long commentId, long userId, String comment) {
        try{
            PostComment m_comment = commentRepository.findPostCommentByIdAndUserId(commentId, userId);
            if(m_comment == null){
                return false;
            }
            commentRepository.updateComment(comment, commentId);
            return true;
        }
        catch(Exception ex){
            System.out.println("Edit comment failed -> Exception: "+ex.getMessage());
            throw new MyBadRequestException(ex.toString());
        }
    }

    @Override
    public boolean deleteComment(long commentId,long token) {
        try{
            PostComment m_comment = commentRepository.findPostCommentByIdAndUserId(commentId, token);
            if(m_comment == null){
                return false;
            }
            if(m_comment.getParentId() <= 0){
                // Nếu comment là comment cha thì xóa tất cả replies
                commentRepository.deleteComment(commentId);
                commentRepository.deleteReplies(commentId);
            }
            else{
                // Nếu comment là reply con thì chỉ xóa comment đó
                commentRepository.deleteComment(commentId);
            }
            return true;
        }
        catch(Exception ex){
            System.out.println("Delete comment failed -> Exception: "+ex.getMessage());
            throw new MyBadRequestException(ex.toString());
        }
    }

}
