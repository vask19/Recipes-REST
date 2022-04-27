package com.example.recipesrest.repository;

import com.example.recipesrest.entity.RecipeEntity;
import com.example.recipesrest.entity.User;
import com.example.recipesrest.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity,Long> {
    List<RecipeEntity> findByCategoryIgnoreCaseOrderByDateDesc(String category);
    List<RecipeEntity> findByNameIgnoreCaseContainsOrderByDateDesc(String name);
    List<RecipeEntity> findAllByUser(User user);
    Optional<RecipeEntity> findRecipeEntitiesByIdAndUser(Long id,User user);





}
