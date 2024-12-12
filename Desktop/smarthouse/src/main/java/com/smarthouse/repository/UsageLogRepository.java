package com.smarthouse.repository;

import com.smarthouse.model.UsageLog;
import com.smarthouse.model.User;
import com.smarthouse.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsageLogRepository extends JpaRepository<UsageLog, Long> {

    List<UsageLog> findByUser(User user);

    List<UsageLog> findByIngredient(Ingredient ingredient);

    // New method to find usage logs by both user and ingredient
    List<UsageLog> findByUserAndIngredient(User user, Ingredient ingredient);
}

