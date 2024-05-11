package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.pojo.Role;
import com.oneteam.empsystem.entity.pojo.User;
import com.oneteam.empsystem.repo.repo_interface.RoleRepo;
import com.oneteam.empsystem.repo.repo_interface.UserRepo;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@Transactional
public class UserServiceImpl implements UserService, ApplicationRunner {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    // ==> initialize _ insertion auth data
    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        // ==> spring security uses "ROLE_" as a prefix for roles
        // ==> plain-text-pass: "{noop}" tells spring security that the password will be a plain text
        // ==> Bcrypt-pass: to Generate BCrypt Passwords:- https://www.bcryptcalculator.com/
        // ==> Bcrypt-pass: passowrd must be >= 68 char-length "({bcrypt} = 8 char) + (encodedPassword = 60 chars)"
        // ==> Create sample users and authorities
        // UserEntity user1 = new UserEntity("ali", "{noop}12345", true);
        // User user1 = new User("ali", "{bcrypt}$2a$10$cNzlM1MDnHRlA36iOo/88e035sbb.bQP4JWGKZ44GaAR9ahJx7CH.", true);
        User user1 = new User("ali", "$2a$10$xb1ZNnq9ruWuLgaQb9KYFuuCppjyRG9dCWUpbTFJkqItHMBe6Z4gO", true);
        User user2 = new User("osama", "$2a$10$xb1ZNnq9ruWuLgaQb9KYFuuCppjyRG9dCWUpbTFJkqItHMBe6Z4gO", true);
        Role role1 = new Role("ROLE_EMPLOYEE");
        Role role2 = new Role("ROLE_EMPLOYEE");
        Role role3 = new Role("ROLE_MANAGER");
        user1.getRoles().add(role1);
        user2.getRoles().add(role2);
        user2.getRoles().add(role3);
        
        // ==> Save users and authorities to the database
        if(findByUserName(user1.getUserName()) == null)
        {
            userRepo.saveUser(user1);    
        }
        if(findByUserName(user2.getUserName()) == null){
            userRepo.saveUser(user2);
        }
    }

    @Override
    public User findByUserName(String userName) {
        // check the database if the user already exists
        return userRepo.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
