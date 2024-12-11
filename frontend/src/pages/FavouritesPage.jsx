import React, { useContext } from "react";
import { RecipeContext } from "../context/RecipeContext"; // Access the Recipe Context
import RecipeList from "../components/RecipeList"; // Reuse RecipeList for displaying recipes

const FavoritesPage = () => {
  const { favorites, recipes } = useContext(RecipeContext); // Access favorites and recipes from context

  // Filter recipes to only include those marked as favorites
  const favoriteRecipes = recipes.filter((recipe) =>
    favorites.includes(recipe.id)
  );

  return (
    <div className="favorites-page">
      <h1>My Favorites</h1>
      {/* Display favorite recipes or a fallback message */}
      {favoriteRecipes.length > 0 ? (
        <RecipeList recipes={favoriteRecipes} />
      ) : (
        <p className="no-favorites-message">
          You haven't added any recipes to your favorites yet. Start exploring recipes and add some!
        </p>
      )}
    </div>
  );
};

export default FavoritesPage;
