Recipe_Suggestion_Prototype
Smart Recipe Suggestion System

Project Overview This project addresses the challenge of reducing food waste and improving meal planning for students in shared accommodations. By leveraging the SSH Camera, SSH Cloud, and a recipe database, the system provides personalized recipe suggestions based on real-time fridge inventory. This prototype integrates seamlessly with the Student Smart Homes (SSH) ecosystem to enhance the student experience.

Features

Real-Time Fridge Inventory Tracking: The SSH Camera captures images of fridge contents using motion detection and processes them for ingredient recognition.

Recipe Suggestions: Recipes are suggested based on available ingredients, minimizing the need for additional purchases. Dietary preferences and portion sizes are supported.

Shared Usage Logs: Track who uses which ingredients, reducing conflicts among housemates.

User-Friendly Interface: Intuitive design for accessing recipes, logging usage, and customizing preferences.

Goals

Suggest recipes using ingredients detected in the fridge.
Log ingredient usage by user and timestamp.
Provide a seamless interface for recipe access via the SSH App and Console Table.
Enable communication between SSH Cloud, Camera, and Hub for smooth operation.
Non-Goals

 backend-work
The system will not include grocery shopping features or price comparisons.
No AI-based learning for recipe suggestions; the system relies on straightforward algorithms.
Tech Stack

**Tech Stack**
- Backend: Node.js, Python Flask
- Database:postgreSQL
- Frontend: React.js (or any preferred framework)
- Third-Party API: Spoonacular API
 main

Backend: Node.js, Python Flask
Database:postgreSQL
Frontend: React.js (or any preferred framework)
Third-Party API: Spoonacular API
System Architecture

The system integrates the following components:

backend-work
SSH Camera:


Captures fridge contents using motion sensors.
Transfers data to the SSH Cloud via SSH Hub.
SSH Cloud:

Stores data in a postgreSQL database.
Runs the recipe suggestion algorithm using the Spoonacular API.
SSH App and Console Table:

Displays recipe suggestions and logs in an intuitive interface.
How to Run the Prototype

Prerequisites:
backend-work
- postgreSQL installed and running locally or on a cloud service.
- Node.js, npm and React installed.
- API Key for Spoonacular API.
main

postgreSQL installed and running locally or on a cloud service.
Node.js, npm and React installed.
API Key for Spoonacular API.
Steps:

backend-work
Clone the repository: git clone https://github.com/.git cd

3. Set up environment variables:
   - Create a .env file in the root directory and add:
     POSTGRES_URI=<your-postgresql-uri>
     SPOONACULAR_API_KEY=<your-api-key>
main

Install dependencies: npm install

Set up environment variables:

Create a .env file in the root directory and add: POSTGRES_URI= SPOONACULAR_API_KEY=
Run the backend server: npm start

Open the frontend:

Navigate to http://localhost:3000 in your browser.
Team Members

Naaz: Backend Development
Tawana and Ramah: Frontend Development
Deloris: Database Design


Implement backend image recognition logic and test.
Develop database schema and API endpoints.
Integrate Spoonacular API for recipe suggestions.
Build and refine the user interface.
Conduct user testing and iterate based on feedback.
Contributing

We welcome contributions! Follow these steps to contribute:

Fork the repository.
Create a feature branch: git checkout -b feature-name
Commit your changes and push: git commit -m "feat: description of feature" git push origin feature-name
Open a pull request.
License

This project is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
