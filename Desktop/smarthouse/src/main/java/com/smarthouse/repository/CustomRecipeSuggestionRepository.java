package com.smarthouse.repository;

import com.smarthouse.model.CustomRecipeSuggestion;
import com.smarthouse.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomRecipeSuggestionRepository extends JpaRepository<CustomRecipeSuggestion, Long> {

    // Method to fetch suggestions for a user without pagination
    List<CustomRecipeSuggestion> findByUser(User user);

    // Paginated method to fetch suggestions for a user
    @EntityGraph(attributePaths = {"user", "recipe"})
    Page<CustomRecipeSuggestion> findByUser(User user, Pageable pageable);
}
