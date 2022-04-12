package com.example.recipesrest.controller;



import com.example.recipesrest.model.Recipe;
import com.example.recipesrest.service.RecipeService;
import org.springframework.web.bind.annotation.*;


@RestController
public class RecipeController {


    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id){
        return recipeService.getRecipe(id);

    }

    @PostMapping("/api/recipe")
    public Integer postRecipe(@RequestBody Recipe recipe){


        return recipeService.addNewRecipe(recipe);
    }
}
