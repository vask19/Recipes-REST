package com.example.recipesrest.service;

import com.example.recipesrest.entity.UserForRegistration;
import com.example.recipesrest.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserForRegistration user){
        userRepository.save(user);
    }

    public UserForRegistration finUserByUserName(String username) {
        return userRepository.findUserForRegistrationByUsername(username);
    }
}
