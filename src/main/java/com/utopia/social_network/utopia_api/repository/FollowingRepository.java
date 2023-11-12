/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.repository;

import com.utopia.social_network.utopia_api.entity.Following;
import com.utopia.social_network.utopia_api.model.FollowingModel;
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
    List<FollowingModel> findByUserSourceId(long userSourceId);
    List<FollowingModel> findByUserSourceIdAndUserTargetIdIn(long userSourceId, List<Long> userTargetIds);
    boolean deleteByUserSourceIdAndUserTargetId(long userSourceId, long userTargetId);
    FollowingModel findByUserSourceIdAndUserTargetId(long userSourceId, long userTargetId);
}
