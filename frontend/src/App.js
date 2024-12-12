import React from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import AddRecipe from './components/Recipes/AddRecipe';
import RecipeList from './components/Recipes/RecipeList';
import AddUser from './components/Users/AddUser';
import UserList from './components/Users/UserList';

import Home from './components/Home';

import Footer from './components/Footer';

const App = () => {
  return (
    <Router>
      <div style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
        <Navbar />
        <div style={{ flex: 1 }}>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/users" element={<UserList />} />
            <Route path="/users/add" element={<AddUser />} />
            <Route path="/recipes" element={<RecipeList />} />
            <Route path="/recipes/add" element={<AddRecipe />} />
          </Routes>
        </div>
        <Footer />
      </div>
    </Router>
  );
};

export default App;
