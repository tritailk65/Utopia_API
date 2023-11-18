/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.repository;

import com.utopia.social_network.utopia_api.entity.Following;
import com.utopia.social_network.utopia_api.entity.RequestFollow;
import java.util.Date;
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
public interface RequestFollowRepository extends JpaRepository<RequestFollow, Long>{
    RequestFollow findRequestFollowByUserSourceIdAndUserTargetIdAndIsPending(Long userSrc, Long userTar, int isPending);
    
    @Modifying
    @Query(value =  "update request_follow r "
                    + "set r.approve_date = ? , r.is_pending = ? "
                    + "where r.user_source_id = ? and r.user_target_id= ? and r.id = ?", nativeQuery = true)
    void updateFollowRequestSetAcceptFollow(Date approve_date, int isPending, long userSrc, long userTar, long id);

    RequestFollow findByUserTargetId(Long userTar);
    
    RequestFollow findByUserSourceId(Long userSrc);

    int deleteByUserSourceIdAndUserTargetId(Long userSrc, Long userTar);

    List<RequestFollow> findByUserTargetIdAndIsPending(Long userTar, int isPending);
}
