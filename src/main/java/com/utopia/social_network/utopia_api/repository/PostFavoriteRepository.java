/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.repository;

import com.utopia.social_network.utopia_api.entity.PostFavorite;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author trita
 */
@Repository
@Transactional
public interface PostFavoriteRepository extends JpaRepository<PostFavorite, Long>{

    List<PostFavorite> findAllPostFavoriteByUserId(Long userId);
    Optional<PostFavorite> findPostFavoriteByUserIdAndPostId(Long userId,Long postId);
}
