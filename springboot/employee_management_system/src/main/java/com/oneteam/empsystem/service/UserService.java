package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.dto.UserEntityDTO;
import com.oneteam.empsystem.entity.mapper.UserMapper;
import com.oneteam.empsystem.entity.pojo.Authority;
import com.oneteam.empsystem.entity.pojo.UserEntity;
import com.oneteam.empsystem.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Service
@Transactional
public class UserService implements ApplicationRunner {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;
    
    // initialize _ insertion auth data
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create sample users and authorities
        UserEntity user1 = new UserEntity("ali", "{noop}12345", 1);
        UserEntity user2 = new UserEntity("osama", "{noop}12345", 1);
        Authority authority1 = new Authority("Employee");
        Authority authority2 = new Authority("Manager");
        authority1.setUser(user1);
        authority2.setUser(user2);
        System.out.println("-----> 1");
        user1.getAuthorities().add(authority1);
        System.out.println("-----> 2");
        user2.getAuthorities().add(authority1);
        System.out.println("-----> 3");
        user2.getAuthorities().add(authority2);
        System.out.println("-----> 4");

        // Save users and authorities to the database
        if(userRepo.findByUsername(user1.getUsername()) == null){
            userRepo.save(user1);
        }
        System.out.println("-----> 5");
        if(userRepo.findByUsername(user2.getUsername()) == null){
            userRepo.save(user2);
        }
        System.out.println("-----> 6");
    }


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