import api from "./api";

const recipeService = {
  getRecipes: async () => {
    // Fetch all recipes
    return api.get("/recipes");
  },

  getRecipeById: async (id) => {
    // Fetch a single recipe by its ID
    return api.get(`/recipes/${id}`);
  },

  addRecipe: async (recipe) => {
    // Add a new recipe
    return api.post("/recipes", recipe);
  },
  
  getSimilarRecipes: async (ingredients) => {
    // Query backend for recipes with similar ingredients
    const query = ingredients.join(",");
    return api.get(`/recipes/similar?ingredients=${query}`);
  },

  updateRecipe: async (id, updatedRecipe) => {
    // Update an existing recipe
    return api.put(`/recipes/${id}`, updatedRecipe);
  },

  deleteRecipe: async (id) => {
    // Delete a recipe by its ID
    return api.delete(`/recipes/${id}`);
  },
};

export default recipeService;

const addRecipe = async (recipe) => {
    const API_BASE_URL = "http://localhost:8080/api/recipes";
  
    const response = await fetch(API_BASE_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(recipe),
    });
  
    if (!response.ok) {
      throw new Error("Failed to save recipe. Please try again.");
    }
  
    return response.json();
  };
  
  export { addRecipe };
  