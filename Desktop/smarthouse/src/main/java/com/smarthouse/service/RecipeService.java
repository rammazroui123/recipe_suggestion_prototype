package com.smarthouse.service;

import com.smarthouse.dto.RecipeDTO;
import com.smarthouse.model.Ingredient;
import com.smarthouse.model.Recipe;
import com.smarthouse.model.RecipeIngredient;
import com.smarthouse.repository.IngredientRepository;
import com.smarthouse.repository.RecipeIngredientRepository;
import com.smarthouse.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    // Fetch paginated recipes and map to RecipeDTO
    public Page<RecipeDTO> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable)
                .map(recipe -> new RecipeDTO(
                        recipe.getRecipeId(),
                        recipe.getTitle(),
                        recipe.getInstructions(),
                        recipe.getPreppingTime(),
                        recipe.getServingPortion()
                ));
    }

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public RecipeIngredient addRecipeIngredient(RecipeIngredient recipeIngredient) {
        return recipeIngredientRepository.save(recipeIngredient);
    }

    public RecipeDTO findRecipeById(Long recipeId) {
        Recipe recipe = recipeRepository.findByIdWithIngredients(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + recipeId));
        return new RecipeDTO(
                recipe.getRecipeId(),
                recipe.getTitle(),
                recipe.getInstructions(),
                recipe.getPreppingTime(),
                recipe.getServingPortion()
        );
    }

    public Recipe findRecipeEntityById(Long recipeId) {
        return recipeRepository.findByIdWithIngredients(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + recipeId));
    }


    public void linkIngredientToRecipe(Long recipeId, Long ingredientId, Integer quantityRequired) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));

        RecipeIngredient recipeIngredient = new RecipeIngredient(recipe, ingredient, quantityRequired);
        recipeIngredientRepository.save(recipeIngredient);
    }



}
