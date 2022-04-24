package com.example.recipesrest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Extending the adapter and adding the annotation
@EnableWebSecurity
public class WebSecurityConfigurerImpl extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfigurerImpl(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Acquiring the builder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // storing users in memory
        auth.
                userDetailsService(userDetailsService)
                .passwordEncoder(getEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/api/recipe/").hasRole("ADMIN")
                .anyRequest()
                .permitAll()
                .and()
                .csrf().disable()
                .httpBasic();

    }


    // creating a PasswordEncoder that is needed in two places
    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }




}