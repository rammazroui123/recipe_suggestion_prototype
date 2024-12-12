package com.smarthouse.service;

import com.smarthouse.dto.RecipeIngredientDTO;
import com.smarthouse.model.Ingredient;
import com.smarthouse.model.Recipe;
import com.smarthouse.model.RecipeIngredient;
import com.smarthouse.repository.RecipeIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeIngredientService {

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    // Add a new recipe-ingredient relationship
    public RecipeIngredient addRecipeIngredient(Recipe recipe, Ingredient ingredient, Integer quantityRequired) {
        if (quantityRequired <= 0) {
            throw new IllegalArgumentException("Quantity required must be greater than 0");
        }
        RecipeIngredient recipeIngredient = new RecipeIngredient(recipe, ingredient, quantityRequired);
        return recipeIngredientRepository.save(recipeIngredient);
    }

    // Fetch all ingredients for a specific recipe
    public List<RecipeIngredient> getIngredientsForRecipe(Recipe recipe) {
        return recipeIngredientRepository.findByRecipe_RecipeId(recipe.getRecipeId());
    }

    private RecipeIngredientDTO mapToDTO(RecipeIngredient recipeIngredient) {
        return new RecipeIngredientDTO(
                recipeIngredient.getId(),
                recipeIngredient.getRecipe().getRecipeId(),
                recipeIngredient.getRecipe().getTitle(),
                recipeIngredient.getIngredient().getIngredientId(),
                recipeIngredient.getIngredient().getName(),
                recipeIngredient.getQuantityRequired()
        );
    }

    // Fetch all ingredients for a recipe as DTOs
    public List<RecipeIngredientDTO> getIngredientsForRecipeAsDTO(Long recipeId) {
        return recipeIngredientRepository.findByRecipe_RecipeId(recipeId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
