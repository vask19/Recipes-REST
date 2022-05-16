package com.example.recipesrest.facade;

import com.example.recipesrest.dto.RecipeDTO;
import com.example.recipesrest.entity.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeFacade {

    public RecipeDTO recipeToRecipeDTO(Recipe recipe){
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setCategory(recipe.getCategory());
        recipeDTO.setName(recipe.getUser().getUsername());
        recipeDTO.setDescription(recipe.getDescription());
        recipeDTO.setId(recipe.getId());
        recipeDTO.setDirections(recipe.getDirections());
        recipeDTO.setIngredients(recipe.getIngredients());
        recipeDTO.setUsername(recipe.getUser().getUsername());

        return  recipeDTO;
    }
}
