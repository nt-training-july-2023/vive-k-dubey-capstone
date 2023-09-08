import React, { useState } from 'react';
import { useEffect } from 'react';
import '../CSS/AdminDashBoard.css';
import AllEmployeesList from './AllEmployeesList';
import AllManagersList from './AllManagersList';
import AllProjectsList from './AllProjectsList';
import AddEmployee from './AddEmployee';
import AddManager from './AddManager';
import AddProject from './AddProject';
import { useNavigate } from 'react-router-dom';

function AdminDashboard({ setIsLoggedIn, isLoggedIn }) {
  const [selectedTab, setSelectedTab] = useState('employee');
  const [addAction, setAddAction] = useState('Add Employee');
  const [showAddEmployee, setShowAddEmployee] = useState(false);
  const [showAddManager, setShowAddManager] = useState(false);
  const [showAddProject, setShowAddProject] = useState(false);
  const navigate = useNavigate();

  /* testing code 
  */


  

   useEffect(() => {
     // If the user is not logged in, navigate them to the login page
     if (!isLoggedIn) {
      navigate('/');
     } else {
       // Replace the current history state with the admin dashboard route
       window.history.replaceState(null, null, '/admin-dashboard');
     }
   }, [isLoggedIn, navigate]);

   

  const handleTabChange = (tab) => {
    setSelectedTab(tab);

    if (tab === 'employee') {
      setAddAction('Add Employee');
    } else if (tab === 'manager') {
      setAddAction('Add Manager');
    } else if (tab === 'project') {
      setAddAction('Add Project');
    }

    // Reset showAddEmployee when changing tabs
    setShowAddEmployee(false);
    setShowAddManager(false);
    setShowAddProject(false);

  };

  const handleAddActionClick = () => {
    // Determine the action based on the selected tab
    if (selectedTab === 'employee') {
      setShowAddEmployee(true);
    } else if (selectedTab === 'manager') {
      setShowAddManager(true);
    } else if (selectedTab === 'project') {
      setShowAddProject(true);
    }
  };

   const handleLogout = () => {
     // Clear the isLoggedIn state and navigate to the login page
     setIsLoggedIn(false);
     navigate('/');
   };

  return (
    <div className="admin-dashboard">
      <nav className="navbar">
        <div className="navbar-section">
          <button
            className={`tab-button ${selectedTab === 'employee' ? 'selected' : ''}`}
            onClick={() => handleTabChange('employee')}
          >
            Employee
          </button>
          <button
            className={`tab-button ${selectedTab === 'manager' ? 'selected' : ''}`}
            onClick={() => handleTabChange('manager')}
          >
            Manager
          </button>
          <button
            className={`tab-button ${selectedTab === 'project' ? 'selected' : ''}`}
            onClick={() => handleTabChange('project')}
          >
            Project
          </button>
        </div>
        <div className="navbar-section">
            <button className="add-button" onClick={handleAddActionClick}>
              {addAction}
            </button>
            <button className="logout-button" onClick={handleLogout}>
            Logout
            </button>
         
        </div>
      </nav>
      {selectedTab === 'employee' && !showAddEmployee && <AllEmployeesList />}
      {showAddEmployee && <AddEmployee />} {/* Render AddEmployee when showAddEmployee is true */}
      {selectedTab === 'manager' && !showAddManager && <AllManagersList />}
      {showAddManager && <AddManager />} {/* Render AddEmployee when showAddEmployee is true */}
      {selectedTab === 'project' && !showAddProject && <AllProjectsList />}
      {showAddProject && <AddProject />}
    </div>
  );
}

export default AdminDashboard;
