import React from 'react';
import '../CSS/UserNavbar.css';

function UserNavbar({ activeTab, setActiveTab,  handleLogout, employeeName }) {
  return (
    <div className="user-navbar">
      <div className="user-dashboard-title">Welcome, {employeeName}!</div>
      <div className="user-tabs">
        <button
          className={`user-tab ${activeTab === 'MyProfile' ? 'user-active' : ''}`}
          onClick={() => setActiveTab('MyProfile')}
        >
          My Profile
        </button>
        <button
          className={`user-tab ${activeTab === 'Organization' ? 'user-active' : ''}`}
          onClick={() => setActiveTab('Organization')}
        >
          Organization
        </button>

        
      </div>
      <div className="user-tabs">
      <button className="user-logout-button" onClick={handleLogout}>
        Logout
      </button>
      </div>
    </div>
  );
}

export default UserNavbar;
