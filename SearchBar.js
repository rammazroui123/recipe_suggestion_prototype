import React, { useState } from 'react';
import './SearchBar.css'; // Optional: Include custom styles if needed

const SearchBar = ({ onSearch, placeholder = "Search recipes..." }) => {
  const [error, setError] = useState(null); // Store error message
  const [searchTerm, setSearchTerm] = useState(''); // Track the current input value

  // Handle input change and validate input
  const handleInputChange = (event) => {
    const value = event.target.value; // Get input value
    setSearchTerm(value); // Update the input state

    // Example validation: Prevent numbers in the search term
    if (/\d/.test(value)) {
      setError('Search term cannot contain numbers.'); // Set error if validation fails
    } else {
      setError(null); // Reset error if validation passes
      if (onSearch) {
        onSearch(value.trim()); // Trigger the parent callback with the trimmed input
      }
    }
  };

  return (
    <div className="search-bar-container">
      <input
        type="text"
        value={searchTerm} // Controlled input
        className={`search-bar ${error ? 'error' : ''}`} // Add 'error' class if there's an error
        placeholder={placeholder} // Display default or custom placeholder
        onChange={handleInputChange} // Handle input change
        aria-label="Search recipes"
        aria-invalid={!!error} // Accessibility: Mark as invalid if there's an error
      />
      {error && <p className="error-message">{error}</p>} {/* Display error message */}
    </div>
  );
};

export default SearchBar;
