package com.smarthouse.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class SpoonacularService {

    private static final String API_KEY = "your-api-key"; // Replace with your Spoonacular API key
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/findByIngredients";

    public List<Map<String, Object>> fetchRecipes(List<String> ingredients, String dietaryPreference) {
        RestTemplate restTemplate = new RestTemplate();
        String ingredientsParam = String.join(",", ingredients);

        // Construct the URL with or without dietary preferences
        String url = dietaryPreference != null && !dietaryPreference.isEmpty()
                ? String.format("%s?ingredients=%s&diet=%s&apiKey=%s", BASE_URL, ingredientsParam, dietaryPreference, API_KEY)
                : String.format("%s?ingredients=%s&apiKey=%s", BASE_URL, ingredientsParam, API_KEY);

        try {
            // Make the API request
            ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
            return response.getBody();
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            // Throw a runtime exception with a meaningful error message
            throw new RuntimeException("Failed to fetch recipes from Spoonacular API. Check API key, URL, or network connection.");
        }
    }
}

