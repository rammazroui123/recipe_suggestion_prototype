package com.smarthouse.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class SpoonacularService {

    private static final String API_KEY = "your-api-key"; // Replace with your Spoonacular API key
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/findByIngredients";

    @Cacheable(value = "recipes", key = "#ingredients.toString() + #dietaryPreference")
    public List<Map<String, Object>> fetchRecipes(List<String> ingredients, String dietaryPreference) {
        RestTemplate restTemplate = new RestTemplate();

        // Join ingredients into a comma-separated string
        String ingredientsParam = String.join(",", ingredients);

        // Construct the URL, adding dietary preferences if provided
        String url = dietaryPreference != null && !dietaryPreference.isEmpty()
                ? String.format("%s?ingredients=%s&diet=%s&apiKey=%s", BASE_URL, ingredientsParam, dietaryPreference, API_KEY)
                : String.format("%s?ingredients=%s&apiKey=%s", BASE_URL, ingredientsParam, API_KEY);

        // Make the API request and get the response
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        // Return the response body (list of recipes)
        return response.getBody();
    }
}

