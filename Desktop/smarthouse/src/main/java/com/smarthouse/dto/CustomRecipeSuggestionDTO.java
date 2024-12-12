package com.smarthouse.dto;

public class CustomRecipeSuggestionDTO {

    private Long suggestionId;
    private String reason;
    private String suggestedOn;
    private Long userId;
    private Long recipeId;

    public CustomRecipeSuggestionDTO(Long suggestionId, String reason, String suggestedOn, Long userId, Long recipeId) {
        this.suggestionId = suggestionId;
        this.reason = reason;
        this.suggestedOn = suggestedOn;
        this.userId = userId;
        this.recipeId = recipeId;
    }

    // Getters and Setters
    public Long getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(Long suggestionId) {
        this.suggestionId = suggestionId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSuggestedOn() {
        return suggestedOn;
    }

    public void setSuggestedOn(String suggestedOn) {
        this.suggestedOn = suggestedOn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }
}

