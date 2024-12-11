import React, { memo } from "react";
import PropTypes from "prop-types";
import "./IngredientTracker.css";

const IngredientTracker = ({ ingredients }) => {
  const isEmpty = !ingredients || ingredients.length === 0;

  return (
    <div className="ingredient-tracker">
      <h2>Fridge Contents</h2>
      {isEmpty ? (
        <p className="empty-message">No ingredients found in the fridge.</p>
      ) : (
        <ul className="ingredient-list">
          {ingredients.map(({ id, name, quantity, unit }) => (
            <li key={id} className="ingredient-item">
              <span className="ingredient-name">{name}</span> -{" "}
              <span className="ingredient-quantity">
                {quantity} {unit || ""}
              </span>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

IngredientTracker.propTypes = {
  ingredients: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
      name: PropTypes.string.isRequired,
      quantity: PropTypes.oneOfType([PropTypes.string, PropTypes.number])
        .isRequired,
      unit: PropTypes.string,
    })
  ),
};

IngredientTracker.defaultProps = {
  ingredients: [],
};

export default memo(IngredientTracker);
