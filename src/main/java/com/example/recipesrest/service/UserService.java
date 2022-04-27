package com.example.recipesrest.service;

import com.example.recipesrest.entity.User;
import com.example.recipesrest.entity.enums.ERole;
import com.example.recipesrest.exception.UserExistException;
import com.example.recipesrest.payload.request.SignupRequest;
import com.example.recipesrest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;





    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User createUser(SignupRequest userIn){
        User user = new User();
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.ROLE_USER);

        try {
            LOG.info("Saving User {}",userIn.getUsername());
            return userRepository.save(user);
        }catch (Exception e){
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }



}
