package com.smarthouse.service;

import com.smarthouse.model.Recipe;
import com.smarthouse.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    public RecipeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRecipes_ShouldReturnPaginatedRecipes() {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setTitle("Test Recipe");
        Page<Recipe> recipePage = new PageImpl<>(Collections.singletonList(recipe));
        when(recipeRepository.findAll(PageRequest.of(0, 10))).thenReturn(recipePage);

        // Act
        Page<Recipe> result = recipeService.getAllRecipes(PageRequest.of(0, 10));

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals("Test Recipe", result.getContent().get(0).getTitle());
        verify(recipeRepository, times(1)).findAll(PageRequest.of(0, 10));
    }

    @Test
    void addRecipe_ShouldSaveRecipe() {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setTitle("New Recipe");
        when(recipeRepository.save(recipe)).thenReturn(recipe);

        // Act
        Recipe result = recipeService.addRecipe(recipe);

        // Assert
        assertNotNull(result);
        assertEquals("New Recipe", result.getTitle());
        verify(recipeRepository, times(1)).save(recipe);
    }

    @Test
    void findRecipeById_ShouldThrowException_WhenNotFound() {
        // Arrange
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> recipeService.findRecipeById(1L));
        assertEquals("Recipe not found", exception.getMessage()); // Update the expected message
        verify(recipeRepository, times(1)).findById(1L);
    }
}
