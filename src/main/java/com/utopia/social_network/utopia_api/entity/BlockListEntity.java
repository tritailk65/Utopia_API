/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.entity;


import java.util.Date;

import java.util.UUID;
import javax.persistence.*;

/**
 *
 * @author trita
 */
@Entity
@Table(name = "blocklist")
public class BlockListEntity {
    
    @Id
    @Column(name = "User_Id")
    @GeneratedValue
    private UUID UserId;
    
    @Column(name = "DateBlocked")
    private Date DateBlocked;

    public UUID getUserId() {
        return UserId;
    }

    public Date getDateBlocked() {
        return DateBlocked;
    }

    public void setDateBlocked(Date DateBlocked) {
        this.DateBlocked = DateBlocked;
    }
}
