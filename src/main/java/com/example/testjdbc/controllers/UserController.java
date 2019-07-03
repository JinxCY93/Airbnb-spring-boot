package com.example.testjdbc.controllers;

import java.util.List;
import com.example.testjdbc.entities.User;
import com.example.testjdbc.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value="/users", produces="application/json")
    public List<User> displayUsers() {
        return userRepository.getAllUsers();
    }

    //Get user by email
    @GetMapping(value="/users/{email}", produces="application/json")
    public List<User> getUsersByEmail(@PathVariable("email") String email) {
        return userRepository.getUsersByEmail(email);
    }

    @PostMapping(value="/users", produces="application/json")
    public User createUser(@RequestBody User user) {
        userRepository.createUser(user);
        return user;
    }

    // update user
    @PostMapping(value="/users/{id}", produces="application/json")
    public User updateUser(@RequestBody User user,  @PathVariable("id") int id) {
        userRepository.updateUserById(user, id);
        return user;
    }
}