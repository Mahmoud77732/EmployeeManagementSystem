/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oneteam.empsystem.repo.repo_interface;

import com.oneteam.empsystem.entity.pojo.Role;

/**
 *
 * @author mahmoud
 */
public interface RoleRepo {
    
    Role findRoleByName(String roleName);
    
}
