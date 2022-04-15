package com.example.recipesrest.service;

import com.example.recipesrest.model.Recipe;
import com.example.recipesrest.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    public Optional<Recipe> getRecipeById(Long id){
        return recipeRepository.findById(id);

    }

    public Integer addNewRecipe(Recipe recipe){
        return null;

    }
}
