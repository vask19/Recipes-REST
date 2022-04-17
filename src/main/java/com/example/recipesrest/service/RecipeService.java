package com.example.recipesrest.service;

import com.example.recipesrest.entity.RecipeEntity;
import com.example.recipesrest.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    public Optional<RecipeEntity> getRecipeById(Long id){
        return recipeRepository.findById(id);

    }

    public Long addNewRecipe(RecipeEntity savedRecipe){
        Integer id = 0;
        recipeRepository.save(savedRecipe);
        recipeRepository.findAll()
                .forEach(recipeEntity -> {
                    if (savedRecipe.equals(recipeEntity))
                        savedRecipe.setId(recipeEntity.getId());

                });

         return savedRecipe.getId();

    }

    public void deleteRecipeById(Long id){
        recipeRepository.deleteById(id);
    }

}
