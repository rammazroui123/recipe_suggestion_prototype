package com.smarthouse.service;

import com.smarthouse.dto.IngredientDTO;
import com.smarthouse.model.Ingredient;
import com.smarthouse.model.User;
import com.smarthouse.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    // Fetch ingredients for a specific user and map to DTO
    public Page<IngredientDTO> getIngredientsForUser(User owner, Pageable pageable) {
        return ingredientRepository.findByOwner(owner, pageable)
                .map(ingredient -> new IngredientDTO(
                        ingredient.getIngredientId(),
                        ingredient.getName(),
                        ingredient.getQuantity(),
                        ingredient.getExpiryDate().toString(),
                        ingredient.getOwner().getId(),
                        ingredient.getOwner().getUsername()
                ));
    }

    // Fetch all ingredients (for testing or admin functionality)
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    // Fetch expired ingredients for a specific user
    public List<Ingredient> getExpiredIngredientsForUser(User owner) {
        return ingredientRepository.findByOwner(owner)
                .stream()
                .filter(ingredient -> ingredient.getExpiryDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

    // Add a new ingredient with validation
    public Ingredient addIngredient(Ingredient ingredient) {
        System.out.println("Attempting to save Ingredient: " + ingredient);
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        System.out.println("Saved Ingredient: " + savedIngredient);
        return savedIngredient;
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

    // Fetch a specific ingredient by ID
    public Ingredient findIngredientById(Long ingredientId) {
        return ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found with id: " + ingredientId));
    }
}
