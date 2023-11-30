/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.repository;

import com.utopia.social_network.utopia_api.entity.Post;
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
public interface PostRepository extends JpaRepository<Post, Long>{

    @Modifying
    @Query(value =  "update post p set p.is_active = 0 where p.id = ?", nativeQuery = true)
    int updatePostSetIsActiveById(Long id);
    
    @Modifying
    @Query(value =  "update post p set p.like_count = ? , p.share_count = ? where p.id = ?", nativeQuery = true)
    int updatePostSetLikeAndShareById(long likeCount,long shareCount , long id);
    
    @Modifying
    @Query(value =  "update post p set p.title = ? , p.is_hide_like = ? , p.comment_stat = ? , p.alert = ? where p.id = ?", nativeQuery = true)
    int editPost(String title ,int isHideLike,int commentStat, boolean alert , long id);

    List<Post> findAllById(Long id);
        
    List<Post> findAllByIsActive(int i);
    
    List<Post> findAllByUserId(Long id);

    List<Post> findAllByUserIdAndIsActive(Long id, int i);

    List<Post> findAllByIdAndIsActive(Long id, int i);
    
    @Query(value =
       "SELECT * FROM Post "
     + "WHERE is_active = 1 ORDER BY date_published DESC "
     + " LIMIT ? OFFSET ?"
    ,nativeQuery = true)
    List<Post> findAllPostForViewer(int take,int skip);
    
    @Query(value =
       "SELECT * FROM Post "
     + "WHERE is_active = 1 AND user_id = ? ORDER BY date_published DESC "
     + " LIMIT ? OFFSET ?"
    ,nativeQuery = true)
    List<Post> findAllPostProfile(Long id,int take,int skip);
    
    Post findPostById(Long id);

    Post findPostByIdAndIsActive(Long id,int active);
}
