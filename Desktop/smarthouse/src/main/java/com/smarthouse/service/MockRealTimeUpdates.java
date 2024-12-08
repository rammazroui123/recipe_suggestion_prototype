package com.smarthouse.service;

import com.smarthouse.model.Ingredient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MockRealTimeUpdates {

    private final IngredientService ingredientService;

    public MockRealTimeUpdates(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Scheduled(fixedRate = 10000) // Run every 10 seconds
    public void simulateIngredientUpdate() {
        // Add a mock ingredient every 10 seconds
        Ingredient mockIngredient = new Ingredient();
        mockIngredient.setName("Mock Ingredient");
        mockIngredient.setQuantity(1);
        mockIngredient.setExpiryDate(LocalDate.now().plusDays(7));

        ingredientService.addIngredient(mockIngredient); // Add to the database

        System.out.println("Added mock ingredient: " + mockIngredient.getName());
    }
}
