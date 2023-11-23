/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.repository;

import com.utopia.social_network.utopia_api.entity.Following;
import com.utopia.social_network.utopia_api.entity.Following;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author trita
 */

@Repository
@Transactional
public interface FollowingRepository extends JpaRepository<Following, Long>{

    boolean findFollowingByUserTargetId(Long userTar);

    boolean findFollowingByUserSourceId(Long userSrc);

    int deleteByUserSourceIdAndUserTargetId(Long userSrc, Long userTar);


    Following findFollowingByUserSourceIdAndUserTargetId(Long userSrc, Long userTar);

    List<Following> findAllByUserTargetId(Long id);
    
    List<Following> findAllByUserSourceId(Long id);

}
