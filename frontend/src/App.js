import React, { Suspense, lazy, useEffect, useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Footer from "./components/Footer";
import Navbar from "./components/Navbar";
import Header from "./components/Header";
import { UserProvider } from "./context/UserContext";
import { RecipeProvider } from "./context/RecipeContext";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./App.css";

// Lazy load pages for performance optimization
const HomePage = lazy(() => import("./pages/HomePage"));
const AddRecipePage = lazy(() => import("./pages/AddRecipePage"));
const RecipesPage = lazy(() => import("./pages/RecipePages"));
const RecipeDetailPage = lazy(() => import("./pages/RecipeDetailPage"));
const ProfilePage = lazy(() => import("./pages/ProfilePage"));
const FavouritesPage = lazy(() => import("./pages/FavouritesPage"));
const NotFound = lazy(() => import("./pages/NotFound"));

const App = () => {
  const [message, setMessage] = useState("");

  // Fetch data from the backend to test connectivity
  useEffect(() => {
    fetch("http://localhost:8080/api/test/hello") // Replace with your backend URL
      .then((response) => {
        if (!response.ok) throw new Error("Failed to fetch data");
        return response.text();
      })
      .then((data) => setMessage(data))
      .catch((error) => console.error("Error:", error));
  }, []);

  return (
    <UserProvider>
      <RecipeProvider>
        <Router>
          <div className="app-container">
            {/* Header */}
            <Header />

            {/* Navbar */}
            <Navbar />

            {/* Main Content */}
            <main className="main-content mt-4">
              <Suspense fallback={<div className="spinner">Loading...</div>}>
                <Routes>
                  <Route path="/" element={<HomePage />} />
                  <Route path="/recipes" element={<RecipesPage />} />
                  <Route path="/add-recipe" element={<AddRecipePage />} />
                  <Route path="/recipes/:id" element={<RecipeDetailPage />} />
                  <Route path="/profile" element={<ProfilePage />} />
                  <Route path="/favourites" element={<FavouritesPage />} />
                  <Route path="*" element={<NotFound />} />
                </Routes>
              </Suspense>
              {/* Display Backend Test Message */}
              <div className="backend-message">
                <h2>Backend Test Message:</h2>
                <p>{message || "Connecting to backend..."}</p>
              </div>
            </main>

            {/* Footer */}
            <Footer />
          </div>

          {/* Toast Notifications */}
          <ToastContainer />
        </Router>
      </RecipeProvider>
    </UserProvider>
  );
};

export default App;
