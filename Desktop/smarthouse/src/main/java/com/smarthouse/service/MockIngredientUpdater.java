package com.smarthouse.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockIngredientUpdater {

    private final List<String> mockIngredients = new ArrayList<>(List.of("tomato", "cheese"));

    @Scheduled(fixedRate = 15000) // Run every 15 seconds
    public void updateIngredients() {
        // Add a new random ingredient to the list every 15 seconds
        String[] possibleIngredients = {"basil", "garlic", "onion", "carrot"};
        String newIngredient = possibleIngredients[(int) (Math.random() * possibleIngredients.length)];
        mockIngredients.add(newIngredient);

        System.out.println("Updated ingredients: " + mockIngredients);
    }

    public List<String> getMockIngredients() {
        return mockIngredients;
    }
}
