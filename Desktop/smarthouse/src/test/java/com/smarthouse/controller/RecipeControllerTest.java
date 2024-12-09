package com.smarthouse.controller;

import com.smarthouse.model.Recipe;
import com.smarthouse.repository.RecipeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        recipeRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        recipeRepository.deleteAll();
    }

    @Test
    void getAllRecipes_ShouldReturnPaginatedRecipes() throws Exception {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setTitle("Test Recipe"); // Required field
        recipe.setInstructions("Mix ingredients together."); // Required field
        recipe.setPreppingTime(15); // Required field
        recipe.setServingPortion(4); // Required field
        recipeRepository.save(recipe);

        // Act & Assert
        mockMvc.perform(get("/api/recipes")
                        .param("page", "0") // Pagination parameter
                        .param("size", "1") // Pagination parameter
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Print the response for debugging
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(jsonPath("$.content[0].title").value("Test Recipe")); // Validate response
    }






    @Test
    void addRecipe_ShouldReturnSavedRecipe() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Recipe\",\"instructions\":\"Do something...\",\"ingredientsRequired\":\"Salt\",\"preppingTime\":15,\"servingPortion\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Recipe"));
    }

    @Test
    void getAllRecipes_ShouldReturnEmptyList_WhenNoRecipesExist() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/recipes?page=0&size=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isEmpty());
    }
}
