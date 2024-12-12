package com.smarthouse.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Schema(description = "User entity representing a system user")
@Entity
@Table(name = "users") // Name of the database table
public class User {

    @Schema(description = "The unique identifier for the user")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key
    private Long id;

    @Schema(description = "The username of the user", example = "Deloris")
    @Column(nullable = false, unique = true) // Make username unique and required
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Schema(description = "The email address of the user", example = "Deloris@example.com")
    @Column(nullable = false) // Enforce NOT NULL
    @Email(message = "Invalid email format")
    private String email;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CustomRecipeSuggestion> suggestions;

    @Column(nullable = true)
    private String dietaryPreferences;

    @Column(nullable = true)
    private String profilePicture;

    // Getters and setters
    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    public void setDietaryPreferences(String dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }


    // Default constructor
    public User() {}

    // Parameterized constructor
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
