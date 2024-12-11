import React, { createContext, useState } from "react";

// Create the RecipeContext
export const RecipeContext = createContext();

// RecipeProvider Component
export const RecipeProvider = ({ children }) => {
  const [recipes, setRecipes] = useState([]); // State to store recipes
  const [favorites, setFavorites] = useState([]); // State to store favorite recipes

  // Function to add a new recipe
  const addRecipe = (recipe) => setRecipes((prev) => [...prev, recipe]);

  // Function to remove a recipe by ID
  const removeRecipe = (id) =>
    setRecipes((prev) => prev.filter((recipe) => recipe.id !== id));

  // Function to toggle a recipe as favorite
  const toggleFavorite = (recipeId) => {
    setFavorites((prev) =>
      prev.includes(recipeId)
        ? prev.filter((id) => id !== recipeId) // Remove from favorites
        : [...prev, recipeId] // Add to favorites
    );
  };

  return (
    <RecipeContext.Provider
      value={{
        recipes,
        favorites,
        setRecipes,
        addRecipe,
        removeRecipe,
        toggleFavorite,
      }}
    >
      {children}
    </RecipeContext.Provider>
  );
};
