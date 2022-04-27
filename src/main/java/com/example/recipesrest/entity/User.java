package com.example.recipesrest.entity;


import com.example.recipesrest.entity.enums.ERole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data

@Entity

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> roles = new HashSet<>();

    @Column
    private String role;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user",orphanRemoval = true)
    private List<RecipeEntity> recipeEntities = new ArrayList<>();

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public User(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User() {

    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
