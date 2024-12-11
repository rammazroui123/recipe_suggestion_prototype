import React from "react";

const ProfilePage = () => {
  const user = {
    name: "John Doe",
    email: "johndoe@example.com",
    dietaryPreferences: "Vegetarian",
  };

  return (
    <div className="profile-page">
      <h1>My Profile</h1>
      <div className="profile-details">
        <p><strong>Name:</strong> {user.name}</p>
        <p><strong>Email:</strong> {user.email}</p>
        <p><strong>Dietary Preferences:</strong> {user.dietaryPreferences}</p>
      </div>
      <button
        className="btn btn-primary"
        onClick={() => alert("Feature to edit details coming soon!")}
      >
        Edit Profile
      </button>
    </div>
  );
};

export default ProfilePage;
