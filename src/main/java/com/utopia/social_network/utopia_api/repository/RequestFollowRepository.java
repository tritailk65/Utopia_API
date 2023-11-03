/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.repository;

import com.utopia.social_network.utopia_api.entity.Following;
import com.utopia.social_network.utopia_api.entity.RequestFollow;
import java.util.Date;
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
public interface RequestFollowRepository extends JpaRepository<RequestFollow, Long>{
    RequestFollow findRequestFollowByUserSourceIdAndUserTargetId(Long usersrcid, Long usertarid);
    
    @Modifying
    @Query(value =  "update request_follow p set p.approve_date = ? where p.id = ?", nativeQuery = true)
    int updateFollowRequestSetApprovedateById(Date approve_date, long id);

    RequestFollow findRequestFollowByUserTargetId(Long userSrc);
    
    RequestFollow findRequestFollowByUserSourceId(Long userSrc);

    void deleteByUserSourceIdAndUserTargetId(Long userSrc, Long userTar);
}
