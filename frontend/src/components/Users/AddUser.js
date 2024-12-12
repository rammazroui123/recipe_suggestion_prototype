import React, { useState } from 'react';
import axios from '../../services/api';

import './AddUser.css';



const AddUser = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post('/api/users', { username, email })
      .then(() => {
        alert('User added successfully');
        setUsername(''); // Clear the form after submission
        setEmail('');
      })
      .catch((error) => {
        console.error('Error adding user:', error);
        alert('Failed to add user. Please try again.');
      });
  };

  return (
    <div className="add-user-container">
      <div className="add-user-card">
        <h2>Add User</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Username:</label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              placeholder="Enter username"
              required
            />
          </div>
          <div className="form-group">
            <label>Email:</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Enter email"
              required
            />
          </div>
          <button type="submit" className="add-user-btn">
            Add User
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddUser;