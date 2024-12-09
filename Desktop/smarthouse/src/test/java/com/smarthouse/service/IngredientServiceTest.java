package com.smarthouse.service;

import com.smarthouse.model.Ingredient;
import com.smarthouse.model.User;
import com.smarthouse.repository.IngredientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientService ingredientService;

    public IngredientServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getIngredientsForUser_ShouldReturnPaginatedIngredients() {
        // Arrange
        User user = new User();
        user.setId(1L);
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Salt");
        Page<Ingredient> ingredientPage = new PageImpl<>(Collections.singletonList(ingredient));
        when(ingredientRepository.findByOwner(user, PageRequest.of(0, 10))).thenReturn(ingredientPage);

        // Act
        Page<Ingredient> result = ingredientService.getIngredientsForUser(user, PageRequest.of(0, 10));

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals("Salt", result.getContent().get(0).getName());
        verify(ingredientRepository, times(1)).findByOwner(user, PageRequest.of(0, 10));
    }
}
