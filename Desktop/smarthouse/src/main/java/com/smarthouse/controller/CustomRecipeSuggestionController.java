package com.smarthouse.controller;

import com.smarthouse.dto.CustomRecipeSuggestionDTO;
import com.smarthouse.dto.RecipeDTO;
import com.smarthouse.model.CustomRecipeSuggestion;
import com.smarthouse.model.User;
import com.smarthouse.service.CustomRecipeSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/custom-recipe-suggestions")
public class CustomRecipeSuggestionController {

    private static final Logger logger = LoggerFactory.getLogger(CustomRecipeSuggestionController.class);

    @Autowired
    private CustomRecipeSuggestionService suggestionService;

    // Existing endpoint for non-paginated suggestions
    @GetMapping
    public List<RecipeDTO> getSuggestionsForUser(@RequestParam Long userId) {
        User user = new User();
        user.setId(userId);
        return suggestionService.getSuggestionsForUser(user);
    }

    // New endpoint for paginated suggestions
    @GetMapping("/paginated")
    public Page<RecipeDTO> getPaginatedSuggestionsForUser(@RequestParam Long userId, Pageable pageable) {
        User user = new User();
        user.setId(userId);
        return suggestionService.getPaginatedSuggestionsForUser(user, pageable);
    }

    @PostMapping
    public ResponseEntity<?> addSuggestion(@RequestBody CustomRecipeSuggestion suggestion) {
        logger.info("Received request to add suggestion: {}", suggestion);

        try {
            CustomRecipeSuggestion savedSuggestion = suggestionService.addSuggestion(suggestion);
            logger.info("Suggestion successfully added: {}", savedSuggestion);
            return ResponseEntity.ok(savedSuggestion);
        } catch (Exception e) {
            logger.error("Error adding suggestion: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}

