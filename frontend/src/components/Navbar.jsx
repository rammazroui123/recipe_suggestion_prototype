import React from "react";
import { NavLink } from "react-router-dom";
import "./Shared.css";

const Navbar = () => {
  // Navigation links array for better maintainability
  const navLinks = [
    { name: "Home", path: "/" },
    { name: "Recipes", path: "/recipes" },
    { name: "Add Recipe", path: "/add-recipe" },
    { name: "My Favorites", path: "/favorites" }, // Added "My Favorites"
    { name: "Settings", path: "/settings" },
    { name: "My Profile", path: "/profile" }, // Retained "My Profile"
  ];

  return (
    <nav className="navbar">
      {/* Application Logo */}
      <div className="logo">Recipe Tool</div>

      {/* Navigation Links */}
      <ul className="nav-links">
        {navLinks.map((link) => (
          <li key={link.path}>
            <NavLink
              to={link.path}
              className={({ isActive }) =>
                isActive ? "active-link" : "inactive-link"
              }
            >
              {link.name}
            </NavLink>
          </li>
        ))}
      </ul>
    </nav>
  );
};

export default Navbar;
