package com.smarthouse.controller;

import com.smarthouse.dto.RecipeIngredientDTO;
import com.smarthouse.model.Recipe;
import com.smarthouse.model.RecipeIngredient;
import com.smarthouse.model.Ingredient;
import com.smarthouse.service.RecipeIngredientService;
import com.smarthouse.service.RecipeService;
import com.smarthouse.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe-ingredients")
public class RecipeIngredientController {

    @Autowired
    private final RecipeIngredientService recipeIngredientService;

    public RecipeIngredientController(RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }

    // Get all ingredients for a specific recipe as DTOs
    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<List<RecipeIngredientDTO>> getIngredientDTOsForRecipe(@PathVariable Long recipeId) {
        List<RecipeIngredientDTO> ingredients = recipeIngredientService.getIngredientsForRecipeAsDTO(recipeId);
        return ResponseEntity.ok(ingredients);
    }

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    // Link an ingredient to a recipe
    @PostMapping("/recipe/{recipeId}")
    public ResponseEntity<RecipeIngredient> linkIngredientToRecipe(
            @PathVariable Long recipeId,
            @RequestBody RecipeIngredient recipeIngredient) {
        if (recipeIngredient.getIngredient() == null || recipeIngredient.getQuantityRequired() <= 0) {
            return ResponseEntity.badRequest().body(null);
        }

        // Fetch the Recipe entity by ID
        Recipe recipe = recipeService.findRecipeEntityById(recipeId);

        // Link the ingredient to the recipe
        recipeIngredient.setRecipe(recipe);
        RecipeIngredient savedRecipeIngredient = recipeService.addRecipeIngredient(recipeIngredient);

        return ResponseEntity.ok(savedRecipeIngredient);
    }

    /*// Get all ingredients for a specific recipe
    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<List<RecipeIngredient>> getIngredientsForRecipe(@PathVariable Long recipeId) {
        Recipe recipe = recipeService.findRecipeById(recipeId);
        List<RecipeIngredient> ingredients = recipeIngredientService.getIngredientsForRecipe(recipe);
        return ResponseEntity.ok(ingredients);
    }*/

}
