package com.smarthouse.controller;

import com.smarthouse.model.CustomRecipeSuggestion;
import com.smarthouse.model.User;
import com.smarthouse.service.CustomRecipeSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/custom-recipe-suggestions")
public class CustomRecipeSuggestionController {

    @Autowired
    private CustomRecipeSuggestionService suggestionService;

    @GetMapping
    public List<CustomRecipeSuggestion> getSuggestionsForUser(@RequestParam Long userId) {
        User user = new User();
        user.setId(userId); // Assuming userId corresponds to the primary key
        return suggestionService.getSuggestionsForUser(user);
    }

    @PostMapping
    public CustomRecipeSuggestion addSuggestion(@RequestBody CustomRecipeSuggestion suggestion) {
        return suggestionService.addSuggestion(suggestion);
    }
}
