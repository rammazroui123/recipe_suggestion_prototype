package com.smarthouse.dto;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String dietaryPreferences;
    private String profilePicture;

    public UserDTO(Long id, String username, String email, String dietaryPreferences, String profilePicture) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.dietaryPreferences = dietaryPreferences;
        this.profilePicture = profilePicture;
    }

    // Getters and Setters
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
}
