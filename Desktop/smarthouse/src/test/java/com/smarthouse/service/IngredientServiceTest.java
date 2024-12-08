package com.smarthouse.service;

import com.smarthouse.model.Ingredient;
import com.smarthouse.repository.IngredientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientService ingredientService;

    public IngredientServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllIngredients() {
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setName("Sugar");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("Salt");

        when(ingredientRepository.findAll()).thenReturn(Arrays.asList(ingredient1, ingredient2));

        List<Ingredient> ingredients = ingredientService.getAllIngredients();

        assertNotNull(ingredients);
        assertEquals(2, ingredients.size());
    }
}
