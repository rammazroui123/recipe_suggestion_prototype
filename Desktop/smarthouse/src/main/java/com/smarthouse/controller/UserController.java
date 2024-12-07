package com.smarthouse.controller;

import com.smarthouse.model.User;
import com.smarthouse.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @Operation(summary = "Get all users", description = "Fetches all users from the database")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Received request to fetch all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Add a new user", description = "Adds a new user to the database")
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        logger.info("Received request to add a new user: {}", user.getUsername());
        return ResponseEntity.ok(userService.addUser(user));
    }
}
