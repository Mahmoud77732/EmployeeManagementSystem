/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.empsystem.repo.repoimpl;

import com.oneteam.empsystem.entity.pojo.User;
import com.oneteam.empsystem.repo.repo_interface.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mahmoud
 */
@Repository
public class UserRepoImpl implements UserRepo {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> query = null;
        if(!userName.isEmpty()){
            query = entityManager.createQuery("from User where userName=:uName and enabled=true", User.class);
            query.setParameter("uName", userName);
        }
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception ex) {
            user = null;
            System.err.println(ex.getMessage());
        }
        return user;
    }

    @Override
    public User saveUser(User user) {
        if (user.getId() == null) {
            entityManager.persist(user); // Persist/Save new entity
        } else {
            entityManager.merge(user); // Update existing entity
        }
        return user;
    }

}
