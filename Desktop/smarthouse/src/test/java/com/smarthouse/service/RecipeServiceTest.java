package com.smarthouse.service;

import com.smarthouse.model.Recipe;
import com.smarthouse.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRecipes() {
        Recipe recipe1 = new Recipe();
        recipe1.setTitle("Steamed Rice");

        Recipe recipe2 = new Recipe();
        recipe2.setTitle("Fried Rice");

        when(recipeRepository.findAll()).thenReturn(Arrays.asList(recipe1, recipe2));

        List<Recipe> recipes = recipeService.getAllRecipes();

        assertNotNull(recipes);
        assertEquals(2, recipes.size());
        assertEquals("Steamed Rice", recipes.get(0).getTitle());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void testAddRecipe() {
        Recipe recipe = new Recipe();
        recipe.setTitle("Pasta");

        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        Recipe savedRecipe = recipeService.addRecipe(recipe);

        assertNotNull(savedRecipe);
        assertEquals("Pasta", savedRecipe.getTitle());
        verify(recipeRepository, times(1)).save(recipe);
    }

    @Test
    public void testFindRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setTitle("Steamed Rice");

        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        Recipe foundRecipe = recipeService.findRecipeById(1L);

        assertNotNull(foundRecipe);
        assertEquals("Steamed Rice", foundRecipe.getTitle());
        verify(recipeRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindRecipeById_NotFound() {
        when(recipeRepository.findById(999L)).thenReturn(Optional.empty());

        // Assert that the service throws an exception
        assertThrows(RuntimeException.class, () -> {
            recipeService.findRecipeById(999L); // 999L is an ID that does not exist
        });
    }



}
