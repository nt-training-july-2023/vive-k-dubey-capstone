import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import EmployeeCard from './EmployeeCard'; // Import the EmployeeCard component
import '../CSS/AllEmployeesList.css';

function AllEmployeesList() {
  const [employees, setEmployees] = useState([]);
  const [popupMessage, setPopupMessage] = useState('');
  const [showPopup, setShowPopup] = useState(false);
  const navigate = useNavigate();
  const userRole = localStorage.getItem('role');

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

  if (userRole === 'employee') {
    return (     
       <h1>unauthrized access</h1>
    );
  }

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
      <div className="content-allemployees">
        <div className="card-container">
          {Array.isArray(employees) && employees.length > 0 ? (
            employees.map((employee) => (
              <EmployeeCard employee={employee} key={employee.empId} userRole = {userRole} /> 
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
