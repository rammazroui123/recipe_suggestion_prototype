import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './RecipeList.css'; // Import the CSS file for styling
import { getRecipes } from '../services/api'; // Import your API call function

const RecipeList = () => {
  const [recipes, setRecipes] = useState([]); // State to store recipes
  const [loading, setLoading] = useState(true); // Loading state
  const [error, setError] = useState(null); // Error state

  // Fetch recipes from the backend when the component is mounted
  useEffect(() => {
    const fetchRecipes = async () => {
      try {
        const data = await getRecipes(); // API call
        setRecipes(data); // Update state with fetched recipes
      } catch (err) {
        console.error('Error fetching recipes:', err);
        setError('Failed to load recipes. Please try again later.'); // Set error message
      } finally {
        setLoading(false); // Stop the loading state
      }
    };

    fetchRecipes();
  }, []);

  if (loading) return <p>Loading recipes...</p>; // Display loading message
  if (error) return <p style={{ color: 'red' }}>{error}</p>; // Display error message

  return (
    <div className="recipe-list">
      <h1>Recipe List</h1>
      {recipes.length === 0 ? (
        <p>No recipes available. Please add some!</p>
      ) : (
        <div className="recipe-grid">
          {recipes.map((recipe) => (
            <RecipeCard key={recipe.id} recipe={recipe} />
          ))}
        </div>
      )}
    </div>
  );
};

const RecipeCard = ({ recipe }) => (
  <div className="recipe-card">
    <img
      src={recipe.imageUrl || 'https://via.placeholder.com/300'}
      alt={recipe.name}
      className="recipe-image"
    />
    <h3>{recipe.name}</h3>
    <p>{recipe.description || 'No description available.'}</p>
    <Link to={`/recipes/${recipe.id}`}>
      <button className="view-recipe-btn">View Recipe</button>
    </Link>
  </div>
);

export default RecipeList;
