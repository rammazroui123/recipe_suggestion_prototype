import React, { useContext } from "react";
import { UserContext } from "../../contexts/UserContext";

const UserSettingsPage = () => {
  const { user, updateUser } = useContext(UserContext);

  const handleChange = (e) => {
    updateUser({ ...user, [e.target.name]: e.target.value });
  };

  return (
    <div>
      <h1>User Settings</h1>
      <form>
        <label>
          Username:
          <input
            type="text"
            name="username"
            value={user.username || ""}
            onChange={handleChange}
          />
        </label>
        <br />
        <label>
          Dietary Preferences:
          <input
            type="text"
            name="dietaryPreferences"
            value={user.dietaryPreferences || ""}
            onChange={handleChange}
          />
        </label>
      </form>
    </div>
  );
};

export default UserSettingsPage;
