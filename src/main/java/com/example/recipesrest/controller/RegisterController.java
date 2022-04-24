package com.example.recipesrest.controller;

import com.example.recipesrest.entity.UserForRegistration;
import com.example.recipesrest.service.RecipeService;
import com.example.recipesrest.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public void register(@RequestBody UserForRegistration user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);

    }
}
