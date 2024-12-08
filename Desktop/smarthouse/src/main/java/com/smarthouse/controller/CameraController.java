package com.smarthouse.controller;

import com.smarthouse.service.MockIngredientUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camera")
public class CameraController {

    @Autowired
    private MockIngredientUpdater mockIngredientUpdater;

    @GetMapping("/mock-ingredients")
    public ResponseEntity<List<String>> getMockIngredients() {
        // Use the mock ingredient updater to fetch the current list of ingredients
        return ResponseEntity.ok(mockIngredientUpdater.getMockIngredients());
    }
}


