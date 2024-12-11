// src/components/AddUserForm.js

import React, { useState } from 'react';
import { addUser } from '../services/api';

const AddUserForm = () => {
  const [user, setUser] = useState({ name: '', email: '' });
  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const newUser = await addUser(user);
      setMessage(`User ${newUser.name} added successfully!`);
      setUser({ name: '', email: '' }); // Clear form fields
    } catch (error) {
      setMessage('Failed to add user.');
      console.error('Error adding user:', error);
    }
  };

  return (
    <div>
      <h2>Add a New User</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          value={user.name}
          onChange={handleChange}
          placeholder="Enter name"
          required
        />
        <input
          type="email"
          name="email"
          value={user.email}
          onChange={handleChange}
          placeholder="Enter email"
          required
        />
        <button type="submit">Add User</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default AddUserForm;
