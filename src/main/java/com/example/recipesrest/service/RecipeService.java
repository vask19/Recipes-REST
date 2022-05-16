package com.example.recipesrest.service;

import com.example.recipesrest.dto.RecipeDTO;
import com.example.recipesrest.entity.Recipe;
import com.example.recipesrest.entity.User;
import com.example.recipesrest.repository.RecipeRepository;
import com.example.recipesrest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);


    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }





    public Optional<Recipe> getRecipeById(Long id){
        Optional<Recipe> entity =  recipeRepository.findById(id);
        if (entity.isPresent())
            return entity;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    public Recipe createRecipe(RecipeDTO recipeDTO, Principal principal){
        User user = getUserByPrincipal(principal);
        Recipe recipe = new Recipe();
        recipe.setUser(user);
        recipe.setName(recipeDTO.getName());
        recipe.setCategory(recipeDTO.getCategory());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setDirections(recipeDTO.getDirections());
        recipe.setIngredients(recipeDTO.getIngredients());

        LOG.info("Saving Recipe for User: {}",user.getUsername());

        return recipeRepository.save(recipe);
    }

    public Long addNewRecipe(Recipe savedRecipe){
        recipeRepository.save(savedRecipe);
        recipeRepository.findAll()
                .forEach(recipeEntity -> {
                    if (savedRecipe.equals(recipeEntity))
                        savedRecipe.setId(recipeEntity.getId());

                });

        return savedRecipe.getId();

    }


    public void deleteRecipeById(Long id){
        if (recipeRepository.findById(id).isPresent())
            recipeRepository.deleteById(id);

        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    //TODO
    public void updateRecipe(Recipe recipe, Long id){

        Optional<Recipe> updatedRecipe = recipeRepository.findById(id);
        updatedRecipe
                .ifPresentOrElse(
                        (recipeEntity) -> {
                            recipeEntity.setId(id);
                            recipeEntity.setName(recipe.getName());
                            recipeEntity.setCategory(recipe.getCategory());
                            recipeEntity.setDate(LocalDateTime.now());
                            recipeEntity.setDescription(recipe.getDescription());
                            recipeEntity.setIngredients(recipe.getIngredients());
                            recipeEntity.setDirections(recipe.getDirections());
                            recipeRepository.save(recipeEntity);
                        },
                        ()->{
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);});
    }

    public List<Recipe> getRecipesByName(Optional<String> name) {

        return recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name.get());


    }

    public List<Recipe> getRecipesByCategory(Optional<String> category) {

        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category.get());
    }

    public void emptyQuery() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }


    private User getUserByPrincipal(Principal principal){
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username"));
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> getAllRecipesForUser(Principal principal) {
        User user = getUserByPrincipal(principal);
        return recipeRepository.findAllByUser(user);
    }
}