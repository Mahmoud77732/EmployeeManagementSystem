/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.empsystem.entity.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author mahmoud
 */
@Entity
@Table(name = "authorities") // ==> authorities: is the default name s_security deal with, if you changet it, u must provide queries in Config_class to tell spring how to fetch the data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Authority {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "authority", length = 50)
    private String authority;
    
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private UserEntity user;

    public Authority(String authority, UserEntity user) {
        this.authority = authority;
        this.user = user;
    }

    
}
