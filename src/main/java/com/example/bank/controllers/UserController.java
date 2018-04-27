package com.example.bank.controllers;

import com.example.bank.repositories.UserRepository;
import com.example.bank.user.User;
import com.example.bank.user.UserBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bank.validation.UserValidator;

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
    public ResponseEntity addUser(@RequestBody User user) {
        if (UserValidator.areValuesEmpty(user) || UserValidator.peselCounts11Numbers(user)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable int id) {
        if (UserValidator.areValuesEmpty(user)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            user.setUserId(id);
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
