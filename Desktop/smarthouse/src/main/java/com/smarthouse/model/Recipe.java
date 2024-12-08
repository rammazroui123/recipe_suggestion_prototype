package com.smarthouse.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String instructions;

    @Column(nullable = false)
    private String ingredientsRequired;

    @Column(nullable = false)
    private Integer preppingTime;

    @Column(nullable = false)
    private Integer servingPortion;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients;
    private Long id;

    // Getters and setters
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

    public String getIngredientsRequired() {
        return ingredientsRequired;
    }

    public void setIngredientsRequired(String ingredientsRequired) {
        this.ingredientsRequired = ingredientsRequired;
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

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
