import React, { useState } from "react";
import "../CSS/AdminDashBoard.css";
import AllEmployeesList from "./AllEmployeesList";
import AllManagersList from "./AllManagersList";
import AllProjectsList from "./AllProjectsList";
import AddEmployee from "../Pages/AddEmployee";
import AddProject from "../Pages/AddProject";
import { useNavigate } from "react-router-dom";
import Button from "./Button";
import Tab from "./Tab";

function AdminDashboard() {
  const [selectedTab, setSelectedTab] = useState("employee");
  const [addAction, setAddAction] = useState("Add Employee");
  const [showAddEmployee, setShowAddEmployee] = useState(false);
  const [showAddManager, setShowAddManager] = useState(false);
  const [showAddProject, setShowAddProject] = useState(false);
  const navigate = useNavigate();

  const userRole = localStorage.getItem("role");
  const userName = localStorage.getItem("userName");
  const firstName = userName ? userName.split(" ")[0] : "";

  const handleTabChange = (tab) => {
    setSelectedTab(tab);

    if (tab === "employee") {
      setAddAction("Add Employee");
    } else if (tab === "manager") {
      setAddAction("Add Manager");
    } else if (tab === "project") {
      setAddAction("Add Project");
    }

    setShowAddEmployee(false);
    setShowAddManager(false);
    setShowAddProject(false);
  };

  const handleAddActionClick = () => {
    if (selectedTab === "employee") {
      setShowAddEmployee(true);
    } else if (selectedTab === "manager") {
      setShowAddManager(true);
    } else if (selectedTab === "project") {
      setShowAddProject(true);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("isLoggedIn");
    localStorage.removeItem("role");

    navigate("/");
  };

  if (userRole === "employee") {
    return <h1>unauthrized access</h1>;
  }

  return (
    <div className="admin-dashboard">
      <nav className="navbar">
        <div className="navbar-section">
          <span className="welcome-message">Welcome, {firstName}!</span>
        </div>
        <div className="navbar-section">

          <Tab
            label="Employee"
            isSelected={selectedTab === "employee"}
            onClick={() => handleTabChange("employee")}
          />
          <Tab
            label="Manager"
            isSelected={selectedTab === "manager"}
            onClick={() => handleTabChange("manager")}
          />
          <Tab
            label="Project"
            isSelected={selectedTab === "project"}
            onClick={() => handleTabChange("project")}
          />
        </div>
        <div className="navbar-section ">
          {selectedTab !== "manager" &&
            userRole !== "manager" &&
            !showAddEmployee &&
            !showAddProject && (
               <button className="add-button" onClick={handleAddActionClick}>
                {addAction}
              </button>
             
            )}

          <Button
            className="logout-button"
            text="Logout"
            onClick={handleLogout}
          />
        </div>
      </nav>
      {selectedTab === "employee" && !showAddEmployee && <AllEmployeesList />}
      {showAddEmployee && <AddEmployee handleTabChange={handleTabChange} />}
      {selectedTab === "manager" && !showAddManager && <AllManagersList />}
      {selectedTab === "project" && !showAddProject && <AllProjectsList />}
      {showAddProject && <AddProject handleTabChange={handleTabChange} />}
    </div>
  );
}

export default AdminDashboard;
