package com.example.recipesrest.service;

import com.example.recipesrest.model.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class RecipeService {
    private ConcurrentHashMap<Integer, Recipe> database;
    private static int counter = 0;
    {
        database = new ConcurrentHashMap<>();
    }

    public Recipe getRecipe(int id){
        if (database.containsKey(id-1))
           return database.get(id-1);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Integer addNewRecipe(Recipe recipe){
        database.put(counter++,recipe);
        return counter;
    }
}
