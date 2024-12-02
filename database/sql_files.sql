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