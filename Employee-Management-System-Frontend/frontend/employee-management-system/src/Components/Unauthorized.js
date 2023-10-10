import React from 'react';
import '../CSS/Unauthorized.css'; 

const Unauthorized = () => {
  return (
    <div className="unauthorized-container">
      <div className="unauthorized-content">
        <h1 className='unauthorized-heading'>Unauthorized Access</h1>
        <p>You do not have permission to access this page.</p>
      </div>
    </div>
  );
};

export default Unauthorized;
