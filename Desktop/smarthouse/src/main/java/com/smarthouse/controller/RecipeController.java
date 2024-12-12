package com.smarthouse.controller;

import com.smarthouse.dto.RecipeDTO;
import com.smarthouse.model.Recipe;
import com.smarthouse.model.RecipeIngredient;
import com.smarthouse.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable Long recipeId) {
        return ResponseEntity.ok(recipeService.findRecipeById(recipeId));
    }

    // Fetch all recipes with pagination
    @GetMapping
    public Page<RecipeDTO> getAllRecipes(Pageable pageable) {
        return recipeService.getAllRecipes(pageable);
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @PostMapping("/{recipeId}/ingredients")
    public ResponseEntity<RecipeIngredient> linkIngredientToRecipe(
            @PathVariable Long recipeId,
            @RequestBody RecipeIngredient recipeIngredient) {
        if (recipeIngredient.getIngredient() == null || recipeIngredient.getQuantityRequired() <= 0) {
            return ResponseEntity.badRequest().body(null);
        }

        // Fetch the Recipe entity by ID
        Recipe recipe = recipeService.findRecipeEntityById(recipeId);
        recipeIngredient.setRecipe(recipe);
        RecipeIngredient savedRecipeIngredient = recipeService.addRecipeIngredient(recipeIngredient);

        // Return the saved RecipeIngredient
        return ResponseEntity.ok(savedRecipeIngredient);
    }
}
