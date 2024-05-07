package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.dto.UserEntityDTO;
import com.oneteam.empsystem.entity.mapper.UserMapper;
import com.oneteam.empsystem.entity.pojo.UserEntity;
import com.oneteam.empsystem.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;


    //TODO using DTO
    public List<UserEntityDTO> getAllUsers() {
        List<UserEntity> users = userRepo.findAll();
        return users.stream()
                .map(userMapper)
                .collect(Collectors.toList());
    }

    public UserEntityDTO getUserById(Long id) {
        UserEntity userEntity = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return userMapper.apply(userEntity);
    }

    public UserEntityDTO saveUser(UserEntity user) {
        UserEntity savedUser = userRepo.save(user);
        return userMapper.apply(savedUser);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

}