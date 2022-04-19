package com.example.recipesrest.service;

import com.example.recipesrest.entity.RecipeEntity;
import com.example.recipesrest.model.Recipe;
import com.example.recipesrest.repository.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
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


    //TODO
    public void updateRecipe(RecipeEntity recipe,Long id){

        Optional<RecipeEntity> updatedRecipe = recipeRepository.findById(id);
        updatedRecipe
                .ifPresentOrElse(
                        (recipeEntity) -> {
                            recipeEntity.setId(id);
                            recipeEntity.setName(recipe.getName());
                            recipeEntity.setCategory(recipe.getCategory());
                            recipeEntity.setDate(LocalDateTime.now());
                            recipeEntity.setDescription(recipe.getDescription());
                            recipeEntity.setIngredients(recipe.getIngredients());
                            recipeEntity.setDirections(recipe.getDirections());
                            recipeRepository.save(recipeEntity);
                        },
                        ()->{
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);});
    }

    public List<RecipeEntity> getRecipesByName(Optional<String> name) {

        return recipeRepository.findByNameIgnoreCaseOrderByDateDesc(name.get());


    }

    public List<RecipeEntity> getRecipesByCategory(Optional<String> category) {

        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category.get());
    }

    public void emptyQuery() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}