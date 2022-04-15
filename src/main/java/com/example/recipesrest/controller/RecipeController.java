package com.example.recipesrest.controller;



import com.example.recipesrest.model.Recipe;
import com.example.recipesrest.model.RecipeWithOnlyId;
import com.example.recipesrest.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@ResponseStatus(HttpStatus.OK)
public class RecipeController {


    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable Long id){
        return recipeService.getRecipeById(id).get();

    }

    @PostMapping("/api/recipe/new")
    public RecipeWithOnlyId postRecipe(@RequestBody Recipe recipe){

        return new RecipeWithOnlyId(recipeService.addNewRecipe(recipe));

    }

    @DeleteMapping("/api/recipe/{id}")
    public void deleteRecipe(@PathVariable int id){

    }
}
