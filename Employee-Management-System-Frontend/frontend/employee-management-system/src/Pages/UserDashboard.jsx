import React, { useState, useEffect } from "react";
import Navbar from "../Components/UserNavbar";
import { useNavigate } from "react-router";
import UserProfile from "./UserProfile";
import Organization from "./Organization";

function UserDashboard() {
  const [activeTab, setActiveTab] = useState("MyProfile");

  const [employeeName, setEmployeeName] = useState("");

  const handleEmployeeNameChange = (name) => {
    setEmployeeName(name);
  };

  const userRole = localStorage.getItem("role");
  const navigate = useNavigate();

  useEffect(() => {
    if (userRole === "employee") {
      disableBackButton();
    }
    return () => {
      enableBackButton();
    };
  }, [userRole]);

  const disableBackButton = () => {
    window.history.pushState(null, "", window.location.href);
    window.onpopstate = function () {
      window.history.pushState(null, "", window.location.href);
    };
  };

  const enableBackButton = () => {
    window.onpopstate = null;
  };

  const handleLogout = () => {
    localStorage.removeItem("isLoggedIn");
    localStorage.removeItem("role");
    navigate("/");
  };

  const handleTabChange = (tab) => {
    setActiveTab(tab);
    navigate(`/userdashboard/${tab.toLowerCase()}`);
  };

  if (userRole !== "employee") {
    return <h1>unauthrized access</h1>;
  }

  return (
    <div className="user-app">
      <Navbar
        activeTab={activeTab}
        setActiveTab={setActiveTab}
        handleLogout={handleLogout}
        employeeName={employeeName}
      />
      {activeTab === "MyProfile" ? (
        <UserProfile onEmployeeNameChange={handleEmployeeNameChange} />
      ) : (
        <Organization />
      )}
    </div>
  );
}

export default UserDashboard;
