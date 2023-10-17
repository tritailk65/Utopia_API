/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.repository;

import com.utopia.social_network.utopia_api.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author trita
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findPostById (Long id);
    
//    @Modifying
//    @Query("update post p set p.title = ?1, p.content = ?2, p.status = ?3 where p.post_id = ?4")
//    void updatePostById(String title, String content, String status, long postId);

}
