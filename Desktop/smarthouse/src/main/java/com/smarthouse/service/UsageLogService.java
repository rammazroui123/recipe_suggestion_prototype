package com.smarthouse.service;

import com.smarthouse.model.Ingredient;
import com.smarthouse.model.UsageLog;
import com.smarthouse.model.User;
import com.smarthouse.repository.UsageLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsageLogService {

    @Autowired
    private UsageLogRepository usageLogRepository;

    public UsageLog logIngredientUsage(User user, Ingredient ingredient, Integer quantityUsed) {
        UsageLog usageLog = new UsageLog();
        usageLog.setUser(user);
        usageLog.setIngredient(ingredient);
        usageLog.setQuantityUsed(quantityUsed);
        usageLog.setTimestamp(LocalDateTime.now());

        return usageLogRepository.save(usageLog);
    }

    public List<UsageLog> getUsageLogsForUser(User user) {
        return usageLogRepository.findByUser(user);
    }

    public List<UsageLog> getUsageLogsForIngredient(Ingredient ingredient) {
        return usageLogRepository.findByIngredient(ingredient);
    }
}
