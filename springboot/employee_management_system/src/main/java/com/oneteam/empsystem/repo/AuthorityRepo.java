/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oneteam.empsystem.repo;

import com.oneteam.empsystem.entity.pojo.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mahmoud
 */
public interface AuthorityRepo extends JpaRepository<Authority, Long> {
    
}
