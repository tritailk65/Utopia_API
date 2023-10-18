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
    List<Post> findPostById (Long id);
    
    List<Post> findPostByUserId (Long id);
    
    @Modifying
    @Query(value =  "update post p set p.title = ? where p.id = ?", nativeQuery = true)
    int updatePostSetTitleById(String title, Long id);

}
