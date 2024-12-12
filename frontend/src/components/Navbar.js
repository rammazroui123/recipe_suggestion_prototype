import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

const Navbar = () => {
    return (
        <nav className="navbar">
            <h1 className="navbar-title">Smart House</h1>
            <div className="navbar-links">
                <Link to="/">Home</Link>
                <Link to="/users">Users</Link>
                <Link to="/users/add">Add User</Link>
                <Link to="/recipes">Recipes</Link>
                <Link to="/recipes/add">Add Recipe</Link>
            </div>
        </nav>
    );
};

export default Navbar;
