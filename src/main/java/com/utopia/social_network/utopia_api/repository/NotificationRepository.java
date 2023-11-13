/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.repository;

import com.utopia.social_network.utopia_api.entity.Notification;
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
public interface NotificationRepository extends JpaRepository<Notification, Long>{

    @Modifying
    @Query(value =  "select * from notification n where n.user_id = ? and n.update_at >= ? and n.update_at <= ? order by 5 desc", nativeQuery = true)
    List<Notification> findAllNotificationByAmount(Long id, Date dateAfter, Date dateBefore);
    
    @Modifying
    @Query(value =  "select * from notification n where n.user_id = ? and month(n.update_at) = ? and n.update_at < ? order by 5 desc", nativeQuery = true)
    List<Notification> findAllNotificationByMonthAndDate(Long id, int month, Date dateBefore);
    
    @Modifying
    @Query(value =  "select * from notification n where n.user_id = ? and month(n.update_at) = ? order by 5 desc", nativeQuery = true)
    List<Notification> findAllNotificationByMonth(Long id, int month);
    
    void deleteByUserIdAndSourceIdAndType(Long UserId, Long SourceId, String type);

}
