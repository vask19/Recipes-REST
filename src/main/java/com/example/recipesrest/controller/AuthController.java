package com.example.recipesrest.controller;

import com.example.recipesrest.payload.request.LoginRequest;
import com.example.recipesrest.payload.request.SignupRequest;
import com.example.recipesrest.payload.response.JWTTokenSuccessResponse;
import com.example.recipesrest.payload.response.MessageResponse;
import com.example.recipesrest.security.JWTTokenProvider;
import com.example.recipesrest.security.SecurityConstants;
import com.example.recipesrest.service.UserService;
import com.example.recipesrest.validation.ResponseErrorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private ResponseErrorValidator responseErrorValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;



    @PostMapping("/signin")
    public ResponseEntity<Object> authenticationUser(@Valid @RequestBody LoginRequest loginRequest,BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(bindingResult);
        if (ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }


    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(bindingResult);
        if (ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User registration successfully"));
    }


}
