/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.repository;
 
import com.utopia.social_network.utopia_api.entity.PostComment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author trita
 */
@Repository
@Transactional
public interface PostCommentRepository extends JpaRepository<PostComment, Long>{

    List<PostComment> findAllPostCommentById(Long id);
    
    List<PostComment> findAllPostCommentByPostId(Long id);
    
    PostComment findPostCommentByIdAndUserId(Long commentId,Long userId);
    
    @Modifying
    @Query(value =  "update post_comment cmt set cmt.comment = ? where cmt.id = ?", nativeQuery = true)
    void updateComment(String comment, Long id);
    
    @Modifying
    @Query(value =  "delete from post_comment cmt where cmt.id = ?", nativeQuery = true)
    void deleteComment(Long id);
    
    @Modifying
    @Query(value =  "delete from post_comment cmt where cmt.parent_id = ?", nativeQuery = true)
    void deleteReplies(Long id);
}
