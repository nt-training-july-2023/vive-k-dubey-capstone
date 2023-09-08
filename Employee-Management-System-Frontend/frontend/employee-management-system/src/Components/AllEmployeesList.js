import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../CSS/AllEmployeesList.css';

function AllEmployeesList() {
  const [employees, setEmployees] = useState([]);
  const [popupMessage, setPopupMessage] = useState('');
  const [showPopup, setShowPopup] = useState(false);

  useEffect(() => {
    async function fetchEmployeeData() {
      try {
        const response = await axios.get('http://localhost:8081/employee/getAllEmployees');
        setEmployees(response.data);
        console.log(response.data);
      } catch (error) {
        // const errorMessage = await response.json();
        // setPopupMessage(errorMessage.message);
        // setShowPopup(true);
      }
    }

    fetchEmployeeData();
  }, []);

  const closePopup = () => {
    setShowPopup(false);
  };

  return (
<>
    {showPopup && (
      <div className="popup">
      <div className="popup-content">
        <p>{popupMessage}</p>
        <button onClick={closePopup}>Close</button>
      </div>
      </div>
  )}
    <div className="content">
      <div className="card-container">
      {Array.isArray(employees) && employees.length > 0 ? (
            employees.map((employee) => (
          <div className="card" key={employee.empId}>
      <div className="left-section">
        <div className="employee-name larger">{employee.empName}</div>
        <div className="field smaller">{employee.empDesignation}</div>
        <div className="field">Project Name: {employee.projectId}</div>
        <div className="field">Manager: {employee.managerName}</div>
        <div className="field">Contact: {employee.empContactNo}</div>
        <div className="field">Email: {employee.empEmail}</div>
      </div>
      <div className="right-section">
        <div className="field">Employee ID: {employee.empId}</div>
        <div className="field">DOB: {employee.empDOB}</div>
        <div className="field">DOJ: {employee.empDOJ}</div>
        <div className="field">Location: {employee.empLocation}</div>
        {employee.projectId === null && <button className="assign-button">Assign Project</button>}
      </div>
    </div>
  ))
) : (
  <p>Loading...</p>
)}
      </div>
    </div>
    </>
  );
}

export default AllEmployeesList;
