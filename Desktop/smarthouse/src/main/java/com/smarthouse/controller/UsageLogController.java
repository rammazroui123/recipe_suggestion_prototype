package com.smarthouse.controller;

import com.smarthouse.dto.UsageLogDTO;
import com.smarthouse.model.Ingredient;
import com.smarthouse.model.UsageLog;
import com.smarthouse.model.User;
import com.smarthouse.service.IngredientService;
import com.smarthouse.service.UsageLogService;
import com.smarthouse.service.UserService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage-logs")
public class UsageLogController {

    @Autowired
    private UsageLogService usageLogService;

    @Autowired
    private UserService userService;

    @Autowired
    private IngredientService ingredientService;

    // Log ingredient usage
    @PostMapping
    public ResponseEntity<UsageLog> logUsage(
            @RequestParam @NotNull Long userId,
            @RequestParam @NotNull Long ingredientId,
            @RequestParam @Min(1) Integer quantityUsed) {
        // Validate and fetch User
        User user = userService.getUserEntityById(userId);
        if (user == null) {
            return ResponseEntity.badRequest().body(null); // User does not exist
        }

        // Validate and fetch Ingredient
        Ingredient ingredient = ingredientService.findIngredientById(ingredientId);
        if (ingredient == null) {
            return ResponseEntity.badRequest().body(null); // Ingredient does not exist
        }

        // Log ingredient usage
        UsageLog usageLog = usageLogService.logIngredientUsage(user, ingredient, quantityUsed);

        // Return saved log
        return ResponseEntity.ok(usageLog);
    }

    // Get all usage logs for a specific user
    @GetMapping("/user/{userId}")
    public List<UsageLogDTO> getLogsForUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return usageLogService.getUsageLogsForUser(user);
    }

    // Get all usage logs for a specific ingredient
    @GetMapping("/ingredient/{ingredientId}")
    public List<UsageLogDTO> getLogsForIngredient(@PathVariable Long ingredientId) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(ingredientId);
        return usageLogService.getUsageLogsForIngredient(ingredient);
    }

    @GetMapping("/user/{userId}/ingredient/{ingredientId}")
    public List<UsageLog> getLogsForUserAndIngredient(@PathVariable Long userId, @PathVariable Long ingredientId) {
        User user = new User();
        user.setId(userId);
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(ingredientId);
        return usageLogService.getUsageLogsForUserAndIngredient(user, ingredient);
    }
}
