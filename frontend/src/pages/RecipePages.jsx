import React, { useContext, useEffect, useState } from "react";
import { RecipeContext } from "../context/RecipeContext"; // Recipe Context
import RecipeList from "../components/RecipeList"; // Recipe List Component
import SearchBar from "../components/SearchBar"; // Search Bar Component
import { getRecipes } from "../services/api"; // API function to fetch recipes

const RecipesPage = () => {
  const { recipes, setRecipes } = useContext(RecipeContext); // Access Recipe Context
  const [filteredRecipes, setFilteredRecipes] = useState([]); // Filtered recipes state
  const [loading, setLoading] = useState(true); // Loading state
  const [error, setError] = useState(null); // Error state

  // Fetch recipes from backend when the component mounts
  useEffect(() => {
    const fetchRecipes = async () => {
      try {
        const data = await getRecipes(); // Fetch recipes from API
        setRecipes(data); // Update recipes in context
        setFilteredRecipes(data); // Initialize filtered recipes
      } catch (err) {
        console.error("Error fetching recipes:", err);
        setError("Failed to load recipes. Please try again later.");
      } finally {
        setLoading(false); // Stop loading state
      }
    };

    fetchRecipes();
  }, [setRecipes]);

  // Filter recipes based on search query
  const handleSearch = (query) => {
    const filtered = recipes.filter((recipe) =>
      recipe.name.toLowerCase().includes(query.toLowerCase())
    );
    setFilteredRecipes(filtered);
  };

  // Render appropriate UI based on state
  if (loading) return <p>Loading recipes...</p>;
  if (error) return <p style={{ color: "red" }}>{error}</p>;

  return (
    <div className="recipes-page">
      <h1>Recipes</h1>
      <SearchBar onSearch={handleSearch} placeholder="Search recipes..." />
      {filteredRecipes.length > 0 ? (
        <RecipeList recipes={filteredRecipes} />
      ) : (
        <p>No recipes found. Try searching for another recipe!</p>
      )}
    </div>
  );
};

export default RecipesPage;
