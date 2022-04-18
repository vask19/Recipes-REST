package com.example.recipesrest.service;

import com.example.recipesrest.entity.RecipeEntity;
import com.example.recipesrest.model.Recipe;
import com.example.recipesrest.repository.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    public Optional<RecipeEntity> getRecipeById(Long id){
        Optional<RecipeEntity> entity =  recipeRepository.findById(id);
        if (entity.isPresent())
            return entity;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    public Long addNewRecipe(RecipeEntity savedRecipe){
        recipeRepository.save(savedRecipe);
        recipeRepository.findAll()
                .forEach(recipeEntity -> {
                    if (savedRecipe.equals(recipeEntity))
                        savedRecipe.setId(recipeEntity.getId());

                });

        return savedRecipe.getId();

    }


    public void deleteRecipeById(Long id){
        if (recipeRepository.findById(id).isPresent())
            recipeRepository.deleteById(id);

        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void updateRecipe(RecipeEntity recipe,Long id){
        recipeRepository.findById(id)
                .ifPresent(recipeEntity -> {
                    recipeEntity.setId(id);
                    recipeEntity.setCategory(recipe.getCategory());
                    recipeEntity.setDate(LocalDateTime.now());
                    recipeEntity.setDescription(recipe.getDescription());
                    recipeEntity.setIngredients(recipe.getIngredients());
                    recipeEntity.setDirections(recipe.getDirections());
                    recipeRepository.save(recipeEntity);
                });



    }

}
