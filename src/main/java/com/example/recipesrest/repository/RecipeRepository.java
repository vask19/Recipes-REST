package com.example.recipesrest.repository;

import com.example.recipesrest.entity.RecipeEntity;
import com.example.recipesrest.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity,Long> {
    List<RecipeEntity> findByCategoryIgnoreCaseOrderByDateDesc(String category);
    List<RecipeEntity> findByNameIgnoreCaseContainsOrderByDateDesc(String name);





}
