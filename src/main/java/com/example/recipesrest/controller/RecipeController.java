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
    public Recipe getRecipe(@PathVariable int id){
        return recipeService.getRecipe(id);

    }

    @PostMapping("/api/recipe/new")
    public RecipeWithOnlyId postRecipe(@RequestBody Recipe recipe){

        return new RecipeWithOnlyId(recipeService.addNewRecipe(recipe));



    }
}
