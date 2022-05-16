package com.example.recipesrest.repository;

import com.example.recipesrest.entity.Recipe;
import com.example.recipesrest.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);
    List<Recipe> findByNameIgnoreCaseContainsOrderByDateDesc(String name);
    List<Recipe> findAllByUser(User user);
    Optional<Recipe> findRecipeEntitiesByIdAndUser(Long id, User user);







}
