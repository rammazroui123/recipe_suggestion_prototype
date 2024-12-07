package com.smarthouse.repository;

import com.smarthouse.model.CustomRecipeSuggestion;
import com.smarthouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomRecipeSuggestionRepository extends JpaRepository<CustomRecipeSuggestion, Long> {
    List<CustomRecipeSuggestion> findByUser(User user);
}
