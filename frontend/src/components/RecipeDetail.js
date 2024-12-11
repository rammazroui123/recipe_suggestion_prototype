import React from "react";
import "./RecipeDetail.css";

const RecipeDetail = ({ recipe }) => {
  return (
    <div className="recipe-detail">
      <h2>{recipe.name}</h2>
      {recipe.imageUrl && (
        <img
          src={recipe.imageUrl}
          alt={recipe.name}
          style={{ width: "100%", maxWidth: "400px", height: "auto" }}
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
    </div>
  );
};

export default RecipeDetail;
