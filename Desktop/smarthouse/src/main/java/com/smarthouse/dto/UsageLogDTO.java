package com.smarthouse.dto;

public class UsageLogDTO {
    private Long logId;
    private Long userId;
    private String userName;
    private Long ingredientId;
    private String ingredientName;
    private Integer quantityUsed;
    private String timestamp;

    public UsageLogDTO(Long logId, Long userId, String userName, Long ingredientId, String ingredientName, Integer quantityUsed, String timestamp) {
        this.logId = logId;
        this.userId = userId;
        this.userName = userName;
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.quantityUsed = quantityUsed;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Integer getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(Integer quantityUsed) {
        this.quantityUsed = quantityUsed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
