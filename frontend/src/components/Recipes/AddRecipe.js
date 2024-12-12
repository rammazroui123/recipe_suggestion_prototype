import React, { useState } from 'react';
import axios from '../../services/api';

import './AddRecipe.css'; // Ensure you have this CSS file created and styled as mentioned earlier.

function AddRecipe() {
  const [title, setTitle] = useState('');
  const [instructions, setInstructions] = useState('');
  const [preppingTime, setPreppingTime] = useState(0);
  const [servingPortion, setServingPortion] = useState(0);

  const handleAddRecipe = (e) => {
    e.preventDefault();
    const recipe = { title, instructions, preppingTime, servingPortion };

    // Add the API call using axios
    axios
      .post('/api/recipes', recipe)
      .then(() => {
        alert('Recipe added successfully!');
      })
      .catch((error) => {
        console.error('There was an error adding the recipe!', error);
        alert('Failed to add recipe');
      });
  };

  return (
    <div className="add-recipe-container">
      <div className="recipe-card">
        <h2>Add Recipe</h2>
        <form onSubmit={handleAddRecipe}>
          <input
            type="text"
            placeholder="Title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
          <input
            type="text"
            placeholder="Instructions"
            value={instructions}
            onChange={(e) => setInstructions(e.target.value)}
            required
          />
          <input
            type="number"
            placeholder="Prepping Time (in mins)"
            value={preppingTime}
            onChange={(e) => setPreppingTime(parseInt(e.target.value))}
            required
          />
          <input
            type="number"
            placeholder="Serving Portion"
            value={servingPortion}
            onChange={(e) => setServingPortion(parseInt(e.target.value))}
            required
          />
          <button type="submit">Add Recipe</button>
        </form>
      </div>
    </div>
  );
}

export default AddRecipe;
