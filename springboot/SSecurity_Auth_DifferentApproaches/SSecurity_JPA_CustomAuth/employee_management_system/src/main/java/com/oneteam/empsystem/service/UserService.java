/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author mahmoud
 */
public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

}
