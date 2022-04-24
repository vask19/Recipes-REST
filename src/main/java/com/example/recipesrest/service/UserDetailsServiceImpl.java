package com.example.recipesrest.service;

import com.example.recipesrest.entity.UserForRegistration;
import com.example.recipesrest.model.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserForRegistration user  = userService.finUserByUserName(username);

        if (user == null)
            throw new UsernameNotFoundException("Not found: " + username);

        return new UserDetailsImpl(user);
    }
}
