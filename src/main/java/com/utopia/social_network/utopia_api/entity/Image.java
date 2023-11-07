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

    public Image(long Id, String name, String type, long size, Date dateUpdate, Set<Post> posts) {
        this.Id = Id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.dateUpdate = dateUpdate;
        this.posts = posts;
    }

    public Image() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
    
    
    
}
