package com.smarthouse.dto;

public class RecipeIngredientDTO {
    private Long id; // ID of the RecipeIngredient
    private Long recipeId; // ID of the associated Recipe
    private String recipeTitle; // Title of the associated Recipe
    private Long ingredientId; // ID of the associated Ingredient
    private String ingredientName; // Name of the associated Ingredient
    private Integer quantityRequired; // Quantity of the Ingredient required

    public RecipeIngredientDTO(Long id, Long recipeId, String recipeTitle, Long ingredientId, String ingredientName, Integer quantityRequired) {
        this.id = id;
        this.recipeId = recipeId;
        this.recipeTitle = recipeTitle;
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.quantityRequired = quantityRequired;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Integer getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(Integer quantityRequired) {
        this.quantityRequired = quantityRequired;
    }
}

