package com.smarthouse.dto;

public class IngredientDTO {
    private Long ingredientId;
    private String name;
    private Integer quantity;
    private String expiryDate;
    private Long ownerId;
    private String ownerUsername;

    public IngredientDTO(Long ingredientId, String name, Integer quantity, String expiryDate, Long ownerId, String ownerUsername) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.ownerId = ownerId;
        this.ownerUsername = ownerUsername;
    }

    // Getters and Setters
    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}
