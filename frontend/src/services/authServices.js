import api from "./api";

const authService = {
  login: async (credentials) => {
    // `credentials` should include username and password
    return api.post("/auth/login", credentials);
  },

  logout: async () => {
    // Optional: Invalidate session on the server
    return api.post("/auth/logout");
  },

  getUser: async () => {
    // Retrieve currently authenticated user
    return api.get("/auth/user");
  },
};

export default authService;
