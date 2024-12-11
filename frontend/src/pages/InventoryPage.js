import React, { useState, useEffect } from "react";
import recipeService from "../services/recipeService";

const InventoryPage = () => {
  const [recipes, setRecipes] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [sortOption, setSortOption] = useState("default");
  const [favorites, setFavorites] = useState([]);

  useEffect(() => {
    const fetchRecipes = async () => {
      try {
        const fetchedRecipes = await recipeService.getRecipes();
        setRecipes(fetchedRecipes);
      } catch (error) {
        console.error("Error fetching recipes:", error);
      }
    };

    fetchRecipes();
  }, []);

  const handleSearch = (query) => {
    setSearchQuery(query.toLowerCase());
  };

  const handleSort = (option) => {
    setSortOption(option);
    let sortedRecipes = [...recipes];
    if (option === "cookingTime") {
      sortedRecipes.sort((a, b) => a.cookingTime - b.cookingTime);
    }
    setRecipes(sortedRecipes);
  };

  const toggleFavorite = (id) => {
    setFavorites((prev) =>
      prev.includes(id) ? prev.filter((fav) => fav !== id) : [...prev, id]
    );
  };

  const filteredRecipes = recipes.filter((recipe) =>
    recipe.name.toLowerCase().includes(searchQuery)
  );

  return (
    <div>
      <h1>Recipe Inventory</h1>

      {/* Search Bar */}
      <input
        type="text"
        placeholder="Search recipes..."
        value={searchQuery}
        onChange={(e) => handleSearch(e.target.value)}
      />

      {/* Sort Options */}
      <select
        value={sortOption}
        onChange={(e) => handleSort(e.target.value)}
        style={{ marginLeft: "10px" }}
      >
        <option value="default">Default</option>
        <option value="cookingTime">Sort by Cooking Time</option>
      </select>

      {/* Recipe List */}
      <ul>
        {filteredRecipes.map((recipe) => (
          <li key={recipe.id}>
            <span>{recipe.name} - {recipe.cookingTime} min</span>
            <button
              onClick={() => toggleFavorite(recipe.id)}
              style={{ marginLeft: "10px" }}
            >
              {favorites.includes(recipe.id) ? "Unfavorite" : "Favorite"}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default InventoryPage;
