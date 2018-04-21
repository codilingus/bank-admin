package com.example.bank.controllers;

import com.example.bank.repositories.UserRepository;
import com.example.bank.user.User;
import com.example.bank.user.UserBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserBasic> getAllUserBasic() {

        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(user -> user.getUserBasic())
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public User getUserDetails(@PathVariable int id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@RequestBody User user, @PathVariable int id) {
        user.setUserId(id);
        userRepository.save(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
