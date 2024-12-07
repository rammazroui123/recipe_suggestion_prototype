package com.smarthouse.service;

import com.smarthouse.model.Ingredient;
import com.smarthouse.model.User;
import com.smarthouse.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getIngredientsForUser(User owner) {
        return ingredientRepository.findByOwner(owner);
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }
}
