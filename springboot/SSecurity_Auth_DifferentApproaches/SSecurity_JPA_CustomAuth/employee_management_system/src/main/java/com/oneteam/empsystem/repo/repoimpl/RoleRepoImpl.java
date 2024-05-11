/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.empsystem.repo.repoimpl;

import com.oneteam.empsystem.entity.pojo.Role;
import com.oneteam.empsystem.repo.repo_interface.RoleRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mahmoud
 */
@Repository
public class RoleRepoImpl implements RoleRepo {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role findRoleByName(String theRoleName) {

        TypedQuery<Role> theQuery = entityManager.createQuery("from Role where name=:roleName", Role.class);
        theQuery.setParameter("roleName", theRoleName);

        Role theRole = null;

        try {
            theRole = theQuery.getSingleResult();
        } catch (Exception ex) {
            theRole = null;
            System.err.println(ex.getMessage());
        }

        return theRole;
    }
}
