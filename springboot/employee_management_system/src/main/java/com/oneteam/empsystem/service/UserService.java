package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.UserEntity;
import com.oneteam.empsystem.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}