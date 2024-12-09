package com.smarthouse.controller;

import com.smarthouse.model.Ingredient;
import com.smarthouse.model.UsageLog;
import com.smarthouse.model.User;
import com.smarthouse.service.UsageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usage-logs")
public class UsageLogController {

    @Autowired
    private UsageLogService usageLogService;

    // Log ingredient usage
    @PostMapping
    public UsageLog logUsage(@RequestParam Long userId, @RequestParam Long ingredientId, @RequestParam Integer quantityUsed) {
        User user = new User();
        user.setId(userId);
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(ingredientId);

        return usageLogService.logIngredientUsage(user, ingredient, quantityUsed);
    }

    // Get all usage logs for a specific user
    @GetMapping("/user/{userId}")
    public List<UsageLog> getLogsForUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return usageLogService.getUsageLogsForUser(user);
    }

    // Get all usage logs for a specific ingredient
    @GetMapping("/ingredient/{ingredientId}")
    public List<UsageLog> getLogsForIngredient(@PathVariable Long ingredientId) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(ingredientId);
        return usageLogService.getUsageLogsForIngredient(ingredient);
    }
}
