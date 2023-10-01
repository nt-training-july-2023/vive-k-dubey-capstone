import React from "react";
import "../CSS/UserNavbar.css";
import Tab from "./Tab";
import Button from "./Button";

function UserNavbar({ activeTab, setActiveTab, handleLogout, employeeName }) {
  return (
    <div className="user-navbar">
      <div className="user-dashboard-title">Welcome, {employeeName}!</div>
      <div className="user-tabs">

        <Tab
          label="My Profile"
          isActive={activeTab === "MyProfile"}
          onClick={() => setActiveTab("MyProfile")}
        />

        <Tab
          label="Organization"
          isActive={activeTab === "Organization"} 
          onClick={() => setActiveTab("Organization")}
        />
      </div>
      <div className="user-tabs">
        <Button
          className="logout-button"
          text="Logout"
          onClick={handleLogout}
        />
      </div>
    </div>
  );
}

export default UserNavbar;
