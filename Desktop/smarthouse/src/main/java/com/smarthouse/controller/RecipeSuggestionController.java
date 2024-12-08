package com.smarthouse.controller;

import com.smarthouse.service.SpoonacularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipe-suggestions")
public class RecipeSuggestionController {

    @Autowired
    private SpoonacularService spoonacularService;

    @GetMapping
    public List<Map<String, Object>> getRecipeSuggestions(
            @RequestParam List<String> ingredients,
            @RequestParam(required = false) String dietaryPreference // Optional parameter
    ) {
        return spoonacularService.fetchRecipes(ingredients, dietaryPreference);
    }
}


