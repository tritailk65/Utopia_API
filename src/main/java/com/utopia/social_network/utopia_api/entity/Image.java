/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utopia.social_network.utopia_api.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author trita
 */
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long Id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private long size;

    @Column
    private Date dateUpdate;
    
    @ManyToMany(mappedBy = "postImages")
    private Set<Post> posts;

}
