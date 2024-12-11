// components/AddRecipeForm.js
import React, { useState } from 'react';

const AddRecipeForm = ({ onAddRecipe }) => {
  const [name, setName] = useState('');
  const [ingredients, setIngredients] = useState('');
  const [instructions, setInstructions] = useState('');
  const [cookingTime, setCookingTime] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Create a recipe object with form values
    const newRecipe = {
      name,
      ingredients: ingredients.split(','), // Split ingredients into an array
      instructions,
      cookingTime: parseInt(cookingTime), // Ensure cookingTime is a number
    };
    // Call the onAddRecipe function passed as a prop
    onAddRecipe(newRecipe);
    // Clear form fields after submission
    setName('');
    setIngredients('');
    setInstructions('');
    setCookingTime('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Recipe Name</label>
        <input
          type="text"
          placeholder="Enter recipe name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Ingredients (comma-separated)</label>
        <textarea
          placeholder="Enter ingredients separated by commas"
          value={ingredients}
          onChange={(e) => setIngredients(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Instructions</label>
        <textarea
          placeholder="Enter cooking instructions"
          value={instructions}
          onChange={(e) => setInstructions(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Cooking Time (minutes)</label>
        <input
          type="number"
          placeholder="Enter cooking time"
          value={cookingTime}
          onChange={(e) => setCookingTime(e.target.value)}
          required
        />
      </div>
      <button type="submit">Add Recipe</button>
    </form>
  );
};

export default AddRecipeForm;
