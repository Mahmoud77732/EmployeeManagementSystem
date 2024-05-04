package com.oneteam.empsystem.repo.reposimpl;

import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.entity.User;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import com.oneteam.empsystem.repo.repos.UserRepo;

public class UserRepoImpl extends GenericRepoImpl<User, Long> implements UserRepo {

    public UserRepoImpl() {
        super(User.class);
    }

}
