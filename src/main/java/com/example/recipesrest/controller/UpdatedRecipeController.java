package com.example.recipesrest.controller;


import com.example.recipesrest.dto.RecipeDTO;
import com.example.recipesrest.entity.Recipe;
import com.example.recipesrest.facade.RecipeFacade;
import com.example.recipesrest.payload.response.MessageResponse;
import com.example.recipesrest.service.RecipeService;
import com.example.recipesrest.validation.ResponseErrorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/recipe")
@CrossOrigin
public class UpdatedRecipeController {

    @Autowired
    private RecipeFacade recipeFacade;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private ResponseErrorValidator responseErrorValidator;

    @PostMapping("/create")
    public ResponseEntity<Object> createRecipe(@Valid @RequestBody RecipeDTO recipeDTO,
                                               BindingResult bindingResult,
                                               Principal principal){
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Recipe recipe = recipeService.createRecipe(recipeDTO,principal);
        RecipeDTO createdRecipe = recipeFacade.recipeToRecipeDTO(recipe);

        return new ResponseEntity<>(createdRecipe, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<RecipeDTO>> getAllRecipes(){
       List<RecipeDTO> recipeDTOList =


        recipeService.getAllRecipes()
                .stream()
                .map(recipeFacade::recipeToRecipeDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(recipeDTOList,HttpStatus.OK);
    }

    @GetMapping("/user/recipes")
    public ResponseEntity<List<RecipeDTO>> getAllRecipesForUser(Principal principal){

        List<RecipeDTO> recipeDTOList = recipeService.getAllRecipesForUser(principal)
                .stream()
                .map(recipeFacade::recipeToRecipeDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(recipeDTOList,HttpStatus.OK);
    }



    @PostMapping("/{postId}/delete")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable("postId") String postId, Principal principal) {
        recipeService.deletePost(Long.parseLong(postId), principal);
        return new ResponseEntity<>(new MessageResponse("Post was deleted"), HttpStatus.OK);
    }
}
