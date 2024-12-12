import React, { useEffect, useState } from 'react';
import axios from '../../services/api'; // Ensure this points to your Axios setup
import './RecipeList.css'; // Add this for styling

const RecipeList = () => {
  const [recipes, setRecipes] = useState([]);

  useEffect(() => {
    axios
      .get('/api/recipes')
      .then((response) => setRecipes(response.data.content)) // Handle paginated data
      .catch((error) => console.error('Error fetching recipes:', error));
  }, []);

  return (
    <div className="recipe-list-container">
      <h1>Recipe List</h1>
      <div className="recipe-card-grid">
        {recipes.map((recipe) => (
          <div className="recipe-card" key={recipe.recipeId}>
            <h2>{recipe.title}</h2>
            <p>Prepping Time: {recipe.preppingTime} mins</p>
            <p>Serving Portion: {recipe.servingPortion}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default RecipeList;
