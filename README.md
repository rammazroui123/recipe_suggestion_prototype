# Recipe_Suggestion_Prototype

**Smart Recipe Suggestion System**

**Project Overview**
This project addresses the challenge of reducing food waste and improving meal planning for students in shared accommodations. By leveraging the SSH Camera, SSH Cloud, and a recipe database, the system provides personalized recipe suggestions based on real-time fridge inventory. This prototype integrates seamlessly with the Student Smart Homes (SSH) ecosystem to enhance the student experience.

**Features**
- Real-Time Fridge Inventory Tracking:
  The SSH Camera captures images of fridge contents using motion detection and processes them for ingredient recognition.

- Recipe Suggestions:
  Recipes are suggested based on available ingredients, minimizing the need for additional purchases.
  Dietary preferences and portion sizes are supported.

- Shared Usage Logs:
  Track who uses which ingredients, reducing conflicts among housemates.

- User-Friendly Interface:
  Intuitive design for accessing recipes, logging usage, and customizing preferences.

**Goals**
1. Suggest recipes using ingredients detected in the fridge.
2. Log ingredient usage by user and timestamp.
3. Provide a seamless interface for recipe access via the SSH App and Console Table.
4. Enable communication between SSH Cloud, Camera, and Hub for smooth operation.

**Non-Goals**
1. The system will not include grocery shopping features or price comparisons.
2. No AI-based learning for recipe suggestions; the system relies on straightforward algorithms.

**Tech Stack**
- Backend: Node.js, Python Flask
- Database: MongoDB (NoSQL)
- Frontend: React.js (or any preferred framework)
- Third-Party API: Spoonacular API

**System Architecture**

The system integrates the following components:
1. SSH Camera:
   - Captures fridge contents using motion sensors.
   - Transfers data to the SSH Cloud via SSH Hub.

2. SSH Cloud:
   - Stores data in a NoSQL database.
   - Runs the recipe suggestion algorithm using the Spoonacular API.

3. SSH App and Console Table:
   - Displays recipe suggestions and logs in an intuitive interface.

**How to Run the Prototype**

Prerequisites:
- MongoDB installed and running locally or on a cloud service.
- Node.js and npm installed.
- API Key for Spoonacular API.

Steps:
1. Clone the repository:
   git clone https://github.com/<your-repo-url>.git
   cd <your-repo-name>

2. Install dependencies:
   npm install

3. Set up environment variables:
   - Create a .env file in the root directory and add:
     MONGO_URI=<your-mongodb-uri>
     SPOONACULAR_API_KEY=<your-api-key>

4. Run the backend server:
   npm start

5. Open the frontend:
   - Navigate to http://localhost:3000 in your browser.

**Team Members**

- [Your Name]: Backend Development, Database Design
- Collaborator 1: Frontend Development
- Collaborator 2: API Integration
- Collaborator 3: Testing & Debugging

**Milestones**

1. Implement backend image recognition logic and test.
2. Develop database schema and API endpoints.
3. Integrate Spoonacular API for recipe suggestions.
4. Build and refine the user interface.
5. Conduct user testing and iterate based on feedback.

**Contributing**

We welcome contributions! Follow these steps to contribute:
1. Fork the repository.
2. Create a feature branch:
   git checkout -b feature-name
3. Commit your changes and push:
   git commit -m "feat: description of feature"
   git push origin feature-name
4. Open a pull request.

**License**

This project is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.

