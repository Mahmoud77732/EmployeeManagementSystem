package com.oneteam.empsystem.controller;

import com.oneteam.empsystem.entity.dto.UserEntityDTO;
import com.oneteam.empsystem.entity.pojo.UserEntity;
import com.oneteam.empsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntityDTO>> getAllUsers() {
        List<UserEntityDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable String username) {
        UserEntity user = userService.getUserByUsername(username);
        return user != null ?
                new ResponseEntity<>(user, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<UserEntityDTO> createUser(@RequestBody UserEntity user) {
        UserEntityDTO savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserEntityDTO> updateUser(@PathVariable String username, @RequestBody UserEntity user) {
        if (userService.getUserByUsername(username) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setUsername(username);
        UserEntityDTO updatedUser = userService.saveUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
