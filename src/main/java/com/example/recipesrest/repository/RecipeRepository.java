package com.example.recipesrest.repository;

import com.example.recipesrest.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Long> {



}
