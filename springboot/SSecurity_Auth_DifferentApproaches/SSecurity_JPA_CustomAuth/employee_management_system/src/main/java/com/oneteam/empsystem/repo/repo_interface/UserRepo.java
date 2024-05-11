package com.oneteam.empsystem.repo.repo_interface;

import com.oneteam.empsystem.entity.pojo.User;

public interface UserRepo{
    
    User saveUser(User user);
    User findByUserName(String userName);
    
}
