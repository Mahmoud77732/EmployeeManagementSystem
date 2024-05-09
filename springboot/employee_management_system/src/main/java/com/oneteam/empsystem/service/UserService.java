package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.dto.UserEntityDTO;
import com.oneteam.empsystem.entity.mapper.UserMapper;
import com.oneteam.empsystem.entity.pojo.Authority;
import com.oneteam.empsystem.entity.pojo.UserEntity;
import com.oneteam.empsystem.repo.UserRepo;
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
        // spring security uses "ROLE_" as a prefix for roles
        // "{noop}" tells spring security that the password will be a plain text
        // Create sample users and authorities
        UserEntity user1 = new UserEntity("ali", "{noop}12345", true);
        UserEntity user2 = new UserEntity("osama", "{noop}12345", true);
        Authority authority1 = new Authority("ROLE_EMPLOYEE", user1);
        Authority authority2 = new Authority("ROLE_EMPLOYEE", user2);
        Authority authority3 = new Authority("ROLE_MANAGER", user2);
        user1.getAuthorities().add(authority1);
        user2.getAuthorities().add(authority2);
        user2.getAuthorities().add(authority3);
        
        // Save users and authorities to the database
        if(getUserByUsername(user1.getUsername()) == null)
        {
            userRepo.save(user1);    
        }
        if(getUserByUsername(user2.getUsername()) == null){
            userRepo.save(user2);
        }
    }

    //TODO using DTO
    public List<UserEntityDTO> getAllUsers() {
        List<UserEntity> users = userRepo.findAll();
        return users.stream()
                .map(userMapper)
                .collect(Collectors.toList());
    }

    public UserEntityDTO saveUser(UserEntity user) {
        UserEntity savedUser = userRepo.save(user);
        return userMapper.apply(savedUser);
    }

    public void deleteUser(String username) {
        UserEntity user = getUserByUsername(username);
        userRepo.delete(user);
    }
    
    public UserEntity getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

}
