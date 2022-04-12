package com.example.recipesrest;

import com.example.recipesrest.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipesRestApplication {



    public static void main(String[] args) {
        SpringApplication.run(RecipesRestApplication.class, args);
    }

}
