package com.example.recipesrest.dto;


import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO {
    private Long id;
    private String name;
    private String category;
    private String description;
    private List<String > ingredients;
    private List<String > directions;
    private String username;


}
