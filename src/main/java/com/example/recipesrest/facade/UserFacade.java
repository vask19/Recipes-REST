package com.example.recipesrest.facade;

import com.example.recipesrest.dto.UserDTO;
import com.example.recipesrest.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public UserDTO userToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());

        return userDTO;

    }
}
