package com.smarthouse.service;

import com.smarthouse.dto.CustomRecipeSuggestionDTO;
import com.smarthouse.dto.RecipeDTO;
import com.smarthouse.model.CustomRecipeSuggestion;
import com.smarthouse.model.Recipe;
import com.smarthouse.model.User;
import com.smarthouse.repository.CustomRecipeSuggestionRepository;
import com.smarthouse.repository.RecipeRepository;
import com.smarthouse.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomRecipeSuggestionService {

    private static final Logger logger = LoggerFactory.getLogger(CustomRecipeSuggestionService.class);

    @Autowired
    private CustomRecipeSuggestionRepository suggestionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    public Page<RecipeDTO> getPaginatedSuggestionsForUser(User user, Pageable pageable) {
        return suggestionRepository.findByUser(user, pageable).map(suggestion ->
                new RecipeDTO(
                        suggestion.getRecipe().getRecipeId(),
                        suggestion.getRecipe().getTitle(),
                        suggestion.getRecipe().getInstructions(),
                        suggestion.getRecipe().getPreppingTime(),
                        suggestion.getRecipe().getServingPortion()
                ));
    }

    public List<RecipeDTO> getSuggestionsForUser(User user) {
        return suggestionRepository.findByUser(user).stream()
                .map(suggestion -> new RecipeDTO(
                        suggestion.getRecipe().getRecipeId(),
                        suggestion.getRecipe().getTitle(),
                        suggestion.getRecipe().getInstructions(),
                        suggestion.getRecipe().getPreppingTime(),
                        suggestion.getRecipe().getServingPortion()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public CustomRecipeSuggestion addSuggestion(CustomRecipeSuggestion suggestion) {
        // Fetch user and ensure it's not null
        logger.info("Fetching user with ID: {}", suggestion.getUser().getId());
        User user = userRepository.findById(suggestion.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + suggestion.getUser().getId()));
        logger.info("User fetched: {}", user);

        // Fetch recipe and ensure it's not null
        logger.info("Fetching recipe with ID: {}", suggestion.getRecipe().getRecipeId());
        Recipe recipe = recipeRepository.findById(suggestion.getRecipe().getRecipeId())
                .orElseThrow(() -> new RuntimeException("Recipe not found with ID: " + suggestion.getRecipe().getRecipeId()));
        logger.info("Recipe fetched: {}", recipe);

        // Attach fetched entities to the suggestion
        suggestion.setUser(user);
        suggestion.setRecipe(recipe);

        // Set the timestamp
        suggestion.setSuggestedOn(LocalDateTime.now());

        // Log before saving
        logger.info("Saving suggestion: User ID: {}, Recipe ID: {}, Reason: {}",
                suggestion.getUser().getId(),
                suggestion.getRecipe().getRecipeId(),
                suggestion.getReason());

        // Save the suggestion
        CustomRecipeSuggestion savedSuggestion = suggestionRepository.save(suggestion);

        // Log after saving
        logger.info("Saved suggestion with ID: {}", savedSuggestion.getSuggestionId());

        return savedSuggestion;
    }

}
