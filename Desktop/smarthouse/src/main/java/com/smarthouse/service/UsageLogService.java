package com.smarthouse.service;

import com.smarthouse.dto.UsageLogDTO;
import com.smarthouse.model.Ingredient;
import com.smarthouse.model.UsageLog;
import com.smarthouse.model.User;
import com.smarthouse.repository.UsageLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsageLogService {

    @Autowired
    private UsageLogRepository usageLogRepository;

    public UsageLog logIngredientUsage(User user, Ingredient ingredient, Integer quantityUsed) {
        // Log the input parameters for debugging
        System.out.println("Logging usage: user=" + user.getId() + ", ingredient=" + ingredient.getIngredientId() + ", quantityUsed=" + quantityUsed);

        // Create a new UsageLog object and populate its fields
        UsageLog usageLog = new UsageLog();
        usageLog.setUser(user);
        usageLog.setIngredient(ingredient);
        usageLog.setQuantityUsed(quantityUsed);
        usageLog.setTimestamp(LocalDateTime.now());

        // Save the UsageLog object to the database
        UsageLog savedLog = usageLogRepository.save(usageLog);

        // Log the saved UsageLog object for debugging
        System.out.println("Saved usage log: " + savedLog);

        // Return the saved UsageLog object
        return savedLog;
    }


    public List<UsageLogDTO> getUsageLogsForUser(User user) {
        return usageLogRepository.findByUser(user).stream()
                .map(log -> new UsageLogDTO(
                        log.getId(),
                        log.getUser().getId(),
                        log.getUser().getUsername(),
                        log.getIngredient().getIngredientId(),
                        log.getIngredient().getName(),
                        log.getQuantityUsed(),
                        log.getTimestamp().toString()
                ))
                .collect(Collectors.toList());
    }

    public List<UsageLogDTO> getUsageLogsForIngredient(Ingredient ingredient) {
        return usageLogRepository.findByIngredient(ingredient).stream()
                .map(log -> new UsageLogDTO(
                        log.getId(),
                        log.getUser().getId(),
                        log.getUser().getUsername(),
                        log.getIngredient().getIngredientId(),
                        log.getIngredient().getName(),
                        log.getQuantityUsed(),
                        log.getTimestamp().toString()
                ))
                .collect(Collectors.toList());
    }

    public List<UsageLog> getUsageLogsForUserAndIngredient(User user, Ingredient ingredient) {
        return usageLogRepository.findByUserAndIngredient(user, ingredient);
    }
}
