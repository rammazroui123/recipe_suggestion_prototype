package com.smarthouse.dto;

public class RecipeDTO {
    private Long recipeId;
    private String title;
    private String instructions;
    private Integer preppingTime;
    private Integer servingPortion;

    public RecipeDTO(Long recipeId, String title, String instructions, Integer preppingTime, Integer servingPortion) {
        this.recipeId = recipeId;
        this.title = title;
        this.instructions = instructions;
        this.preppingTime = preppingTime;
        this.servingPortion = servingPortion;
    }

    // Getters and Setters
    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getPreppingTime() {
        return preppingTime;
    }

    public void setPreppingTime(Integer preppingTime) {
        this.preppingTime = preppingTime;
    }

    public Integer getServingPortion() {
        return servingPortion;
    }

    public void setServingPortion(Integer servingPortion) {
        this.servingPortion = servingPortion;
    }
}
