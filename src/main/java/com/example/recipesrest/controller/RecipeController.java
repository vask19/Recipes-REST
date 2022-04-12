package com.example.recipesrest.controller;



import com.example.recipesrest.model.Recipe;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RecipeController {
    private Recipe recipe = new Recipe();

    @GetMapping("/api/recipe")
    public Recipe getRecipe(){
        return recipe;

    }

    @PostMapping("/api/recipe")
    public Recipe postRecipe(@RequestBody Recipe recipe){
        System.out.println(recipe);
        this.recipe = recipe;

        return null;
    }
}
