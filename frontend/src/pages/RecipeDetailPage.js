import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import recipeService from "../services/recipeService";
import "./RecipeDetailPage.css";

const RecipeDetailPage = () => {
  const { id } = useParams(); // Get the recipe ID from the URL
  const [recipe, setRecipe] = useState(null);
  const [similarRecipes, setSimilarRecipes] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch recipe details and similar recipes
  useEffect(() => {
    const fetchRecipeDetails = async () => {
      try {
        const fetchedRecipe = await recipeService.getRecipeById(id);
        setRecipe(fetchedRecipe);

        // Fetch similar recipes based on ingredients
        if (fetchedRecipe.ingredients.length > 0) {
          const fetchedSimilarRecipes = await recipeService.getSimilarRecipes(
            fetchedRecipe.ingredients
          );
          setSimilarRecipes(fetchedSimilarRecipes);
        }
      } catch (err) {
        console.error(err);
        setError(err.message || "Something went wrong while fetching the recipe.");
      } finally {
        setIsLoading(false);
      }
    };

    fetchRecipeDetails();
  }, [id]);

  const handlePrint = () => {
    window.print(); // Trigger the browser's print functionality
  };

  if (isLoading) return <p>Loading...</p>;
  if (error) return <p className="error-message">{error}</p>;

  return (
    <div className="recipe-detail-page">
      <h1>{recipe.name}</h1>
      {recipe.imageUrl && (
        <img
          src={recipe.imageUrl}
          alt={recipe.name}
          className="recipe-image"
        />
      )}
      <p>
        <strong>Cooking Time:</strong> {recipe.cookingTime} minutes
      </p>
      <h3>Ingredients:</h3>
      <ul>
        {recipe.ingredients.map((ingredient, index) => (
          <li key={index}>{ingredient}</li>
        ))}
      </ul>
      <h3>Instructions:</h3>
      <p>{recipe.instructions}</p>

      {/* Print Button */}
      <button onClick={handlePrint} className="btn btn-secondary mt-3">
        Print Recipe
      </button>

      {/* Similar Recipes Section */}
      <h3>Similar Recipes</h3>
      {similarRecipes.length > 0 ? (
        <ul>
          {similarRecipes.map((similar) => (
            <li key={similar.id}>
              <a href={`/recipes/${similar.id}`} className="similar-recipe-link">
                {similar.name}
              </a>
            </li>
          ))}
        </ul>
      ) : (
        <p>No similar recipes found.</p>
      )}
    </div>
  );
};

export default RecipeDetailPage;
