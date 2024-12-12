package com.smarthouse.repository;

import com.smarthouse.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    // Existing method for paginated retrieval of recipes
    Page<Recipe> findAll(Pageable pageable);

    // New method to fetch a recipe with its ingredients eagerly loaded
    @Query("SELECT r FROM Recipe r LEFT JOIN FETCH r.recipeIngredients WHERE r.recipeId = :recipeId")
    Optional<Recipe> findByIdWithIngredients(@Param("recipeId") Long recipeId);
}