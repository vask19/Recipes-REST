package com.example.recipesrest.repository;

import com.example.recipesrest.entity.UserForRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserForRegistration,Long> {
    UserForRegistration findUserForRegistrationByUsername(String username);
}
