/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.empsystem.entity.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author mahmoud
 */
@Entity
@Table(name = "role") // ==> authorities: is the default name s_security deal with, if you changet it, u must provide queries in Config_class to tell spring how to fetch the data
@Getter
@Setter
@ToString
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name", length = 50)
    private String name;
    
    @ManyToMany(mappedBy = "roles", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    public Role() {
    }
    

    public Role(String name) {
        this.name = name;
    }

    
}
