// src/components/UserList.js

import React, { useEffect, useState } from 'react';
import { getUsers } from '../services/api'; // Ensure this path is correct, where `getUsers` is defined in `api.js`

const UserList = () => {
  // State to hold the list of users
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  // useEffect hook to fetch data when the component mounts
  useEffect(() => {
    async function fetchUsers() {
      try {
        const usersData = await getUsers(); // Call the API function to get users
        setUsers(usersData); // Update the state with the fetched user data
      } catch (error) {
        console.error('Failed to fetch users:', error); // Log any errors to the console
      } finally {
        setLoading(false); // Set loading to false once the data fetching is complete
      }
    }

    // Fetch users when the component is mounted
    fetchUsers();
  }, []); // Empty dependency array ensures this runs only once

  // Loading state - shows a message while data is being fetched
  if (loading) {
    return <p>Loading users...</p>;
  }

  // Render users list if data is available
  return (
    <div>
      <h2>Users List</h2>
      {users.length > 0 ? (
        <ul>
          {users.map((user) => (
            <li key={user.id}>
              {user.name} - {user.email}
            </li>
          ))}
        </ul>
      ) : (
        <p>No users found.</p>
      )}
    </div>
  );
};

export default UserList;
