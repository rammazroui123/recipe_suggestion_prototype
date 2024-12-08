package com.smarthouse.service;

import com.smarthouse.model.Ingredient;
import com.smarthouse.model.User;
import com.smarthouse.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    // Fetch all ingredients for a specific user
    public List<Ingredient> getIngredientsForUser(User owner) {
        return ingredientRepository.findByOwner(owner);
    }

    // Fetch all ingredients (for testing or admin functionality)
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    // Add a new ingredient
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    // Update an existing ingredient
    public Ingredient updateIngredient(Long ingredientId, Ingredient updatedIngredient) {
        return ingredientRepository.findById(ingredientId)
                .map(existingIngredient -> {
                    existingIngredient.setName(updatedIngredient.getName());
                    existingIngredient.setQuantity(updatedIngredient.getQuantity());
                    existingIngredient.setExpiryDate(updatedIngredient.getExpiryDate());
                    return ingredientRepository.save(existingIngredient);
                }).orElseThrow(() -> new IllegalArgumentException("Ingredient not found with id: " + ingredientId));
    }

    // Delete an ingredient by ID
    public void deleteIngredient(Long ingredientId) {
        if (!ingredientRepository.existsById(ingredientId)) {
            throw new IllegalArgumentException("Ingredient not found with id: " + ingredientId);
        }
        ingredientRepository.deleteById(ingredientId);
    }
}

