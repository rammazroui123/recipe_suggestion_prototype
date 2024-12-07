package com.smarthouse.service;

import com.smarthouse.model.CustomRecipeSuggestion;
import com.smarthouse.model.User;
import com.smarthouse.repository.CustomRecipeSuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomRecipeSuggestionService {

    @Autowired
    private CustomRecipeSuggestionRepository suggestionRepository;

    public List<CustomRecipeSuggestion> getSuggestionsForUser(User user) {
        return suggestionRepository.findByUser(user);
    }

    public CustomRecipeSuggestion addSuggestion(CustomRecipeSuggestion suggestion) {
        suggestion.setSuggestedOn(LocalDateTime.now());
        return suggestionRepository.save(suggestion);
    }
}
