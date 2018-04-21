package com.example.bank.controllers;

import com.example.bank.repositories.UserRepository;
import com.example.bank.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

@Autowired
    private UserRepository userRepository;



}
