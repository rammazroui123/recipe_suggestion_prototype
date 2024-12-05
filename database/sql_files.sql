--Create DATABASE
CREATE DATABASE recipe_suggestion 
--create users table
CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
	dietery_preferences TEXT,
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
INSERT INTO Recipes (title, instructions, ingridients_required, prepping_time, serving_portion)
VALUES (
    'Steamed Rice',
    '1. Rinse the rice thoroughly.\n2. Add rice and water to a pot.\n3. Bring to a boil, then simmer on low heat with the lid on for 18-20 minutes.\n4. Let it rest for 5 minutes before serving.',
    'Rice, Water, Salt (optional)',
    20,
    4
);
