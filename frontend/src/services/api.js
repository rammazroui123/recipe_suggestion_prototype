const BASE_URL = "http://172.22.205.84:8080/api"; // Backend IP and base API path

/**
 * Handles API responses, throwing errors for non-OK responses.
 * @param {Response} response - The fetch response object.
 * @returns {Promise<JSON>} The JSON data if the response is OK.
 */
const handleResponse = async (response) => {
  if (!response.ok) {
    const error = await response.json();
    throw new Error(error.message || "Something went wrong");
  }
  return response.json();
};

/**
 * API utility object to make HTTP requests (GET, POST, PUT, DELETE).
 */
const api = {
  /**
   * GET request to a specific endpoint.
   * @param {string} endpoint - API endpoint.
   * @returns {Promise<JSON>} The response data.
   */
  get: async (endpoint) => {
    const response = await fetch(`${BASE_URL}${endpoint}`, {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    });
    return handleResponse(response);
  },

  /**
   * POST request to a specific endpoint with a JSON body.
   * @param {string} endpoint - API endpoint.
   * @param {object} body - Data to be sent as JSON.
   * @returns {Promise<JSON>} The response data.
   */
  post: async (endpoint, body) => {
    const response = await fetch(`${BASE_URL}${endpoint}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });
    return handleResponse(response);
  },

  /**
   * PUT request to a specific endpoint with a JSON body.
   * @param {string} endpoint - API endpoint.
   * @param {object} body - Data to be sent as JSON.
   * @returns {Promise<JSON>} The response data.
   */
  put: async (endpoint, body) => {
    const response = await fetch(`${BASE_URL}${endpoint}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });
    return handleResponse(response);
  },

  /**
   * DELETE request to a specific endpoint.
   * @param {string} endpoint - API endpoint.
   * @returns {Promise<JSON>} The response data.
   */
  delete: async (endpoint) => {
    const response = await fetch(`${BASE_URL}${endpoint}`, {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
    });
    return handleResponse(response);
  },
};

export default api; // Export the API utility object

/**
 * API-specific functions (e.g., `getRecipes` and `addRecipe`).
 */
export const getRecipes = async () => {
  return api.get("/recipes");
};

export const addRecipe = async (recipe) => {
  return api.post("/recipes", recipe);
};
