// jest-dom adds custom jest matchers for asserting on DOM nodes.
// allows you to do things like:
// expect(element).toHaveTextContent(/react/i)
// learn more: https://github.com/testing-library/jest-dom
import '@testing-library/jest-dom';

import { render, screen, fireEvent } from "@testing-library/react";
import RecipeList from "./RecipeList";

describe("RecipeList Component", () => {
  const mockRecipes = [
    {
      id: 1,
      title: "Pasta",
      description: "Delicious pasta with tomato sauce",
      imageUrl: "pasta.jpg",
    },
    {
      id: 2,
      title: "Salad",
      description: "Fresh and healthy salad",
      imageUrl: "salad.jpg",
    },
  ];

  const mockOnSelectRecipe = jest.fn();

  test("renders all recipes correctly", () => {
    render(<RecipeList recipes={mockRecipes} onSelectRecipe={mockOnSelectRecipe} />);

    // Verify that all recipes are rendered
    expect(screen.getByText("Pasta")).toBeInTheDocument();
    expect(screen.getByText("Delicious pasta with tomato sauce")).toBeInTheDocument();
    expect(screen.getByText("Salad")).toBeInTheDocument();
    expect(screen.getByText("Fresh and healthy salad")).toBeInTheDocument();

    // Verify that the images are rendered
    const pastaImage = screen.getByAltText("Pasta");
    const saladImage = screen.getByAltText("Salad");
    expect(pastaImage).toHaveAttribute("src", "pasta.jpg");
    expect(saladImage).toHaveAttribute("src", "salad.jpg");
  });

  test("displays placeholder image if imageUrl is missing", () => {
    const recipesWithMissingImage = [
      {
        id: 1,
        title: "Pasta",
        description: "Delicious pasta with tomato sauce",
        imageUrl: "",
      },
    ];

    render(<RecipeList recipes={recipesWithMissingImage} onSelectRecipe={mockOnSelectRecipe} />);

    const placeholderImage = screen.getByAltText("Pasta");
    expect(placeholderImage).toHaveAttribute("src", "https://via.placeholder.com/300");
  });

  test("renders a 'no recipes' message when the list is empty", () => {
    render(<RecipeList recipes={[]} onSelectRecipe={mockOnSelectRecipe} />);

    // Verify that the fallback message is displayed
    expect(screen.getByText("No recipes available. Please add some!")).toBeInTheDocument();
  });

  test("calls onSelectRecipe when a recipe card is clicked", () => {
    render(<RecipeList recipes={mockRecipes} onSelectRecipe={mockOnSelectRecipe} />);

    // Simulate clicking the "Pasta" recipe card
    const pastaCard = screen.getByText("Pasta");
    fireEvent.click(pastaCard);

    // Verify that the callback function is called with the correct recipe
    expect(mockOnSelectRecipe).toHaveBeenCalledTimes(1);
    expect(mockOnSelectRecipe).toHaveBeenCalledWith(mockRecipes[0]);
  });
});

import { render, screen } from "@testing-library/react";
import IngredientTracker from "./IngredientTracker";

describe("IngredientTracker Component", () => {
  test("renders ingredient tracker with ingredients", () => {
    const ingredients = [
      { id: 1, name: "Tomato", quantity: 3, unit: "pcs" },
      { id: 2, name: "Milk", quantity: 1, unit: "litre" },
    ];

    render(<IngredientTracker ingredients={ingredients} />);

    // Check for each ingredient
    expect(screen.getByText("Tomato - 3 pcs")).toBeInTheDocument();
    expect(screen.getByText("Milk - 1 litre")).toBeInTheDocument();
  });

  test("renders empty message when no ingredients", () => {
    render(<IngredientTracker ingredients={[]} />);

    // Check for empty state message
    expect(screen.getByText("No ingredients found in the fridge.")).toBeInTheDocument();
  });

  test("handles missing units gracefully", () => {
    const ingredients = [{ id: 3, name: "Salt", quantity: 2, unit: null }];

    render(<IngredientTracker ingredients={ingredients} />);

    // Check if the unit-less ingredient renders correctly
    expect(screen.getByText("Salt - 2")).toBeInTheDocument();
  });
});
