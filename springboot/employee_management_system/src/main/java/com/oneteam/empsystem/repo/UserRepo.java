package com.oneteam.empsystem.repo;

import com.oneteam.empsystem.entity.pojo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,Long> {
    
    UserEntity findByUsername(String username);
    
}
