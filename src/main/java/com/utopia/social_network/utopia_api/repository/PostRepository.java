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

    List<Post> findAllById(Long id);
    
    List<Post> findAllByIsActive(int i);

    List<Post> findAllByUserIdAndIsActive(Long id, int i);

    List<Post> findAllByIdAndIsActive(Long id, int i);
    
    @Query("SELECT p FROM Post p JOIN p.user u WHERE p.isActive = 1 ORDER BY p.datePublished DESC")
    List<Post> findAllPostForViewer();
    
    Post findPostById(Long id);


}
