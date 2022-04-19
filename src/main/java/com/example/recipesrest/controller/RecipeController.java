package com.example.recipesrest.controller;



import com.example.recipesrest.entity.RecipeEntity;
import com.example.recipesrest.model.Recipe;
import com.example.recipesrest.model.RecipeWithOnlyId;
import com.example.recipesrest.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.List;
import java.util.Optional;



@RestController
@ResponseStatus(HttpStatus.OK)
@Validated
public class RecipeController {


    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/api/recipe/{id}")
    public RecipeEntity getRecipe(@PathVariable Long id){
        return recipeService.getRecipeById(id).get();

    }


    @GetMapping("/api/recipe/search")
    public List<RecipeEntity> searchRecipe(@RequestParam(required = false) Optional<String> name,
                                           @RequestParam(required = false) Optional<String> category){
        if (name.isPresent())
            return recipeService.getRecipesByName(name);
        else if (category.isPresent())
            return recipeService.getRecipesByCategory(category);

        recipeService.emptyQuery();
        return Collections.emptyList();


    }


    @PostMapping("/api/recipe/new")
    public RecipeWithOnlyId postRecipe(@RequestBody @Valid RecipeEntity recipe){
        System.out.println(recipe);
        return new RecipeWithOnlyId(recipeService.addNewRecipe(recipe));

    }

    @DeleteMapping("/api/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipeById(id);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/api/recipe/{id}")
    public void updateRecipe(@PathVariable Long id,
                             @RequestBody @Valid RecipeEntity recipe){

        recipeService.updateRecipe(recipe,id);

    }
}
