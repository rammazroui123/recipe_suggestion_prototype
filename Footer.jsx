import React from "react";
import "./Shared.css";

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">
        {/* About Section */}
        <div className="footer-section">
          <h4>About Recipe Tool</h4>
          <p>
          Recipe Tool is your ultimate smart assistant designed to help you reduce food waste, streamline meal planning, and
      explore delicious recipes. Perfect for shared accommodations, it brings everyone together by simplifying
      decision-making and promoting sustainable practices. Whether you're looking for a quick meal idea or want to save
      ingredients from going to waste, Recipe Tool has you covered.
          </p>
        </div>

        {/* Quick Links Section */}
        <div className="footer-section">
          <h4>Quick Links</h4>
          <ul>
            <li>
              <a href="/recipes">Recipes</a>
            </li>
            <li>
              <a href="/inventory">Inventory</a>
            </li>
            <li>
              <a href="/settings">Settings</a>
            </li>
            <li>
              <a href="/help">Help</a>
            </li>
          </ul>
        </div>

        {/* Social Media Links */}
        <div className="footer-section">
          <h4>Follow Us</h4>
          <div className="social-icons">
            <a href="https://facebook.com" target="_blank" rel="noreferrer">
              <i className="fab fa-facebook"></i>
            </a>
            <a href="https://twitter.com" target="_blank" rel="noreferrer">
              <i className="fab fa-twitter"></i>
            </a>
            <a href="https://instagram.com" target="_blank" rel="noreferrer">
              <i className="fab fa-instagram"></i>
            </a>
          </div>
        </div>
      </div>

      {/* Footer Bottom Section */}
      <div className="footer-bottom">
        <p>© 2024 Recipe Suggestion Tool. All rights reserved.</p>
      </div>
    </footer>
  );
};

export default Footer;
