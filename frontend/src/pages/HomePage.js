import React from "react";
import "./HomePage.css";

const HomePage = () => {
  const meals = [
    {
      name: "Spaghetti Bolognese",
      image: "https://www.themealdb.com/images/media/meals/sutysw1468247559.jpg",
    },
    {
      name: "Sushi Platter",
      image: "https://www.themealdb.com/images/media/meals/g046bb1663960946.jpg",
    },
    {
      name: "Sundae's",
      image: "https://www.themealdb.com/images/media/meals/uuxwvq1483907861.jpg",
    },
    {
      name: "Bean Roast",
      image: "https://www.themealdb.com/images/media/meals/vxuyrx1511302687.jpg",
    },
    {
      name: "Chicken Curry",
      image: "https://www.themealdb.com/images/media/meals/1529444830.jpg",
    },
  ];

  return (
    <div className="homepage-container">
      {/* Header */}
      <header className="text-center">
        <h1 className="app-title">HOME</h1>
      </header>

      {/* Explore New Recipes Section */}
      <section className="explore-recipes-section">
        <h2 className="section-title">Explore New Recipes</h2>
        <div className="meal-grid">
          {meals.map((meal, index) => (
            <div key={index} className="meal-card">
              <img src={meal.image} alt={meal.name} className="meal-img" />
              <h5 className="meal-name">{meal.name}</h5>
              <button className="btn meal-btn">View Recipe</button>
            </div>
          ))}
        </div>
      </section>

      {/* Footer */}
      <footer className="footer">
        <p>&copy; 2024 Recipe Suggestion Tool. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default HomePage;

