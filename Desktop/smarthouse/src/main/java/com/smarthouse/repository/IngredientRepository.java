package com.smarthouse.repository;

import com.smarthouse.model.Ingredient;
import com.smarthouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByOwner(User owner);
}
