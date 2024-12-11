--Create DATABASE
CREATE DATABASE recipe_suggestion 
--create users table
CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
	dietary_preferences TEXT,
	profile_picture TEXT
);
--insert  sample
INSERT INTO Users (username, email, dietary_preferences, profile_picture)
VALUES ('Sasha Soi', 'sashasoi@gmail.com', 'Vegetarian', 'sasha.png');
--create ingredients table
CREATE TABLE Ingredients (
    ingredient_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    expiry_date DATE NOT NULL,
    owner_id INT REFERENCES Users(user_id)
);
--Insert sample 
INSERT INTO Ingredients (name, quantity, expiry_date, owner_id)
VALUES ('Juice', 2, '2024-12-31', 1);

--create  recipes table
CREATE TABLE Recipes (
    recipe_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    instructions TEXT NOT NULL,
    ingredients_required TEXT NOT NULL,
    prepping_time INT NOT NULL,
    serving_portion INT NOT NULL
);
-- Insert sample data into Recipes
INSERT INTO Recipes (title, instructions, ingredients_required, prepping_time, serving_portion)
VALUES (
    'Steamed Rice',
    '1. Rinse the rice thoroughly.\n2. Add rice and water to a pot.\n3. Bring to a boil, then simmer on low heat with the lid on for 18-20 minutes.\n4. Let it rest for 5 minutes before serving.',
    'Rice, Water, Salt (optional)',
    20,
    4
);

CREATE TABLE UsageLogs (
    log_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(user_id),
    ingredient_id INT REFERENCES ingredients(ingredient_id),
    quantity_used INT NOT NULL,
    used_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- sample 
INSERT INTO UsageLogs (user_id, ingredient_id, quantity_used)
VALUES (1, 2, 3);

CREATE TABLE CustomRecipeSuggestions (
    suggestion_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(user_id),
    recipe_id INT REFERENCES Recipes(recipe_id),
    reason TEXT,
    suggested_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
--sample
INSERT INTO CustomRecipeSuggestions (user_id, recipe_id)
VALUES (1, 1);
--create table
CREATE TABLE RecipeIngredients (
    recipe_id INT REFERENCES Recipes(recipe_id),
    ingredient_id INT REFERENCES ingredients(ingredient_id),
    quantity_required INT NOT NULL,
    PRIMARY KEY (recipe_id, ingredient_id)
);
--insert samples
INSERT INTO RecipeIngredients (recipe_id, ingredient_id, quantity_required)
VALUES 
    (1, 1, 2);
	
	
-- ===============
-- TEST QUERIES
-- ===============
--all users	
SELECT u.username, u.email, i.name AS ingredient_name, i.quantity, i.expiry_date
FROM Users AS u
LEFT JOIN Ingredients AS i ON u.user_id = i.owner_id;
--recipes with ingredients
SELECT r.title AS recipe_name, ri.quantity_required, i.name AS ingredient_name
FROM Recipes AS r
JOIN RecipeIngredients AS ri ON r.recipe_id = ri.recipe_id
JOIN Ingredients AS i ON ri.ingredient_id = i.ingredient_id
WHERE r.recipe_id = 1;
recipe suffestion with ingredient
SELECT u.username, r.title AS recipe_name, r.instructions
FROM CustomRecipeSuggestions AS c
JOIN Recipes AS r ON c.recipe_id = r.recipe_id
JOIN Users AS u ON c.user_id = u.user_id
WHERE c.user_id = 1;