package com.smarthouse.controller;

import com.smarthouse.config.TestSecurityConfig;
import com.smarthouse.model.Recipe;
import com.smarthouse.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
@Import(TestSecurityConfig.class) // Import updated TestSecurityConfig
public class RecipeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void testGetAllRecipes() throws Exception {
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipe1.setTitle("Steamed Rice");

        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipe2.setTitle("Pasta");

        when(recipeService.getAllRecipes()).thenReturn(Arrays.asList(recipe1, recipe2));

        mockMvc.perform(get("/api/recipes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Steamed Rice"))
                .andExpect(jsonPath("$[1].title").value("Pasta"));

        verify(recipeService, times(1)).getAllRecipes();
    }

    @Test
    public void testAddRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setTitle("Steamed Rice");

        when(recipeService.addRecipe(any(Recipe.class))).thenReturn(recipe);

        mockMvc.perform(post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Steamed Rice\",\"instructions\":\"Cook rice for 20 minutes.\",\"ingredientsRequired\":\"Rice, Water\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Steamed Rice"));

        verify(recipeService, times(1)).addRecipe(any(Recipe.class));
    }
}
