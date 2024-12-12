import React, { useEffect, useState } from 'react';
import axios from '../../services/api';

import "./UserList.css";

const UserList = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    axios.get('/api/users').then((response) => {
      setUsers(response.data);
    });
  }, []);

  return (
    <div className="user-list-container">
      <h2 className="user-list-title">User List</h2>
      <div className="user-cards">
        {users.map((user) => (
          <div key={user.id} className="user-card">
            <h3>{user.username}</h3>
            <p>Email: {user.email}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default UserList;
