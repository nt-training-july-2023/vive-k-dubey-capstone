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

  const userRole = localStorage.getItem('role');
  const userName = localStorage.getItem('userName');
  const firstName = userName ? userName.split(' ')[0] : '';
  
  // useEffect(() => {
  //   // Check if the user is an employee and disable the back button
  //   if (userRole === 'admin') {
  //     disableBackButton();
  //   }

  //   // Cleanup the effect when the component unmounts
  //   return () => {
  //     // Re-enable the back button when the component is unmounted
  //     enableBackButton();
  //   };
  // }, [userRole]);

  // const disableBackButton = () => {
  //   window.history.pushState(null, '', window.location.href);
  //   window.onpopstate = function () {
  //     window.history.pushState(null, '', window.location.href);
  //   };
  // };

  // const enableBackButton = () => {
  //   window.onpopstate = null;
  // };

  /* testing code 
  */


  

  //  useEffect(() => {
  //    // If the user is not logged in, navigate them to the login page
  //    if (!isLoggedIn) {
  //     navigate('/');
  //    } else {
  //      // Replace the current history state with the admin dashboard route
  //      window.history.replaceState(null, null, '/admin-dashboard');
  //    }
  //  }, [isLoggedIn, navigate]);

   

  const handleTabChange = (tab) => {
    setSelectedTab(tab);

    if (tab === 'employee') {
      setAddAction('Add Employee');
    } else if (tab === 'manager') {
      setAddAction('Add Manager');
    } else if (tab === 'project') {
      setAddAction('Add Project');
    }

    setShowAddEmployee(false);
    setShowAddManager(false);
    setShowAddProject(false);

  };

  const handleAddActionClick = () => {
   
    if (selectedTab === 'employee') {
      setShowAddEmployee(true);
    } else if (selectedTab === 'manager') {
      setShowAddManager(true);
    } else if (selectedTab === 'project') {
      setShowAddProject(true);
    }
  };

   const handleLogout = () => {
   
     localStorage.removeItem('isLoggedIn');
     localStorage.removeItem('role');
  
     navigate('/');
   };

   if (userRole === 'employee') {
    return (     
       <h1>unauthrized access</h1>
    );
  }

  return (
    <div className="admin-dashboard">
      <nav className="navbar">
      <div className="navbar-section">
          <span className="welcome-message">Welcome, {firstName}!</span>
          {/* Rest of the code for tabs */}
        </div>
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
        <div className="navbar-section ">
        {selectedTab !== 'manager' && userRole !== 'manager' &&  !showAddEmployee && !showAddProject &&  (
          <button className="add-button" onClick={handleAddActionClick}>
            {addAction}
        </button>
       
  )}
            <button className="logout-button" onClick={handleLogout}>
            Logout
            </button>
         
        </div>
      </nav>
      {selectedTab === 'employee' && !showAddEmployee && <AllEmployeesList />}
      {showAddEmployee && <AddEmployee handleTabChange={handleTabChange} />} 
      {selectedTab === 'manager' && !showAddManager && <AllManagersList />}
      {showAddManager && <AddManager handleTabChange={handleTabChange}/>} 
      {selectedTab === 'project' && !showAddProject && <AllProjectsList />}
      {showAddProject && <AddProject handleTabChange={handleTabChange}/>}
    </div>
  );
}

export default AdminDashboard;
