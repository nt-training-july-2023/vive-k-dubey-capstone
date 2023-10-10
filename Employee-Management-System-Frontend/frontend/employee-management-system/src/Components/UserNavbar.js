import React from "react";
import "../CSS/UserNavbar.css";
import Tab from "./Tab";
import Button from "./Button";
import { useNavigate } from "react-router";

function UserNavbar({ activeTab, setActiveTab, handleLogout, employeeName }) {

    const userRole = localStorage.getItem("role");
    const navigate = useNavigate();

    if (!userRole) {
        navigate("/");
      }
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
