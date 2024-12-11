import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AddRecipeForm from "../components/AddRecipeForm";
import recipeService from "../services/recipeService";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./AddRecipePage.css"; // Importing custom CSS for styles

const AddRecipePage = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleAddRecipe = async (newRecipe) => {
    if (!newRecipe.name.trim()) {
      toast.error("Recipe name is required.");
      return;
    }
    if (!newRecipe.ingredients.length) {
      toast.error("Please add at least one ingredient.");
      return;
    }
    if (newRecipe.cookingTime <= 0) {
      toast.error("Cooking time must be greater than 0.");
      return;
    }

    setError(null);
    setIsLoading(true);

    try {
      await recipeService.addRecipe(newRecipe);
      toast.success("Recipe added successfully!");
      navigate("/recipes");
    } catch (err) {
      const errorMessage = err.message || "Failed to add recipe. Please try again.";
      setError(errorMessage);
      toast.error(errorMessage);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="add-recipe-page">
      <header className="add-recipe-header">
        <h1>Add a New Recipe</h1>
        <p>Share your favorite dishes with the community!</p>
      </header>

      {error && <p className="error-message">{error}</p>}

      {isLoading ? (
        <div className="loading-message">
          <p>Saving your recipe...</p>
        </div>
      ) : (
        <div className="form-container">
          <AddRecipeForm onAddRecipe={handleAddRecipe} />
        </div>
      )}
    </div>
  );
};

export default AddRecipePage;
