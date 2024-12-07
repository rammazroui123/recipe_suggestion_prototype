package com.smarthouse.controller;

import com.smarthouse.model.Recipe;
import com.smarthouse.model.RecipeIngredient;
import com.smarthouse.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @PostMapping("/{recipeId}/ingredients")
    public RecipeIngredient linkIngredientToRecipe(@PathVariable Long recipeId, @RequestBody RecipeIngredient recipeIngredient) {
        Recipe recipe = recipeService.findRecipeById(recipeId);
        recipeIngredient.setRecipe(recipe);
        return recipeService.addRecipeIngredient(recipeIngredient);
    }
}
