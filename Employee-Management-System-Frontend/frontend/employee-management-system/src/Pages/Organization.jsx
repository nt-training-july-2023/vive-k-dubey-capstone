import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import EmployeeCard from '../Components/EmployeeCard'; 
import '../CSS/AllEmployeesList.css';
import Popup from '../Components/Popup';

function Organization() {
  const [employees, setEmployees] = useState([]);
  const [popupMessage, setPopupMessage] = useState('');
  const [showPopup, setShowPopup] = useState(false);
  const navigate = useNavigate();
  const userRole = localStorage.getItem('role');

  useEffect(() => {
    async function fetchEmployeeData() {
      try {
        const response = await axios.get('http://localhost:8081/employee/getAllEmployeesAndManagers');
        setEmployees(response.data);
        console.log(response.data);
      } catch (error) {
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
        <Popup message={popupMessage} onClose={closePopup} />
      )}
      <div className="content-allemployees">
        <div className="card-container">
          {Array.isArray(employees) && employees.length > 0 ? (
            employees.map((employee) => (
              <EmployeeCard employee={employee} key={employee.empId} /> 
            ))
          ) : (
            <p>Loading...</p>
          )}
        </div>
      </div>
    </>
  );
}

export default Organization;
