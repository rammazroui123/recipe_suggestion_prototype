import React, { createContext, useState } from "react";

export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState({
    username: "Guest",
    dietaryPreferences: "",
    notificationSettings: { email: true, push: false },
  });

  const updateUser = (newUser) => setUser(newUser);

  return (
    <UserContext.Provider value={{ user, updateUser }}>
      {children}
    </UserContext.Provider>
  );
};
