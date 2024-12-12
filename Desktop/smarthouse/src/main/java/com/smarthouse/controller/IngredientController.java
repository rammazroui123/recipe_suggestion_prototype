package com.smarthouse.controller;

import com.smarthouse.dto.IngredientDTO;
import com.smarthouse.model.Ingredient;
import com.smarthouse.model.User;
import com.smarthouse.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    // Fetch paginated ingredients for a specific user with validation
    @GetMapping
    public ResponseEntity<Page<IngredientDTO>> getAllIngredientsForUser(@RequestParam Long userId, Pageable pageable) {
        if (userId == null || userId <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = new User();
        user.setId(userId);
        return ResponseEntity.ok(ingredientService.getIngredientsForUser(user, pageable));
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        System.out.println("Received Ingredient: " + ingredient);
        return ingredientService.addIngredient(ingredient);
    }

    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Long ingredientId) {
        try {
            ingredientService.deleteIngredient(ingredientId);
            return ResponseEntity.noContent().build(); // Success: Return HTTP 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Error: Return HTTP 400 with the error message
        }
    }
}
