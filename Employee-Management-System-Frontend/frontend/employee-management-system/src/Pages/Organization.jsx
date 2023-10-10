import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import EmployeeCard from '../Components/EmployeeCard'; 
import '../CSS/AllEmployeesList.css';
import Popup from '../Components/Popup';
import { getRequest } from '../Services/Service';
import { ALL_ORGANIZATION_EMPLOYEE } from '../Services/url';

function Organization() {
  const [employees, setEmployees] = useState([]);
  const [popupMessage, setPopupMessage] = useState('');
  const [showPopup, setShowPopup] = useState(false);
  const navigate = useNavigate();
  const userRole = localStorage.getItem('role');

  useEffect(() => {
    async function fetchEmployeeData() {
      try {
        const response = await getRequest(ALL_ORGANIZATION_EMPLOYEE);
        setEmployees(response.data);
      } catch (error) {
        if (error.response) {
          const errorMessage = error.response.data;
          setPopupMessage(errorMessage.message);
          setShowPopup(true);
        } else {
          setPopupMessage("Server is not running.");
          setShowPopup(true);
        }
      }
    }

    fetchEmployeeData();
  }, []);
  const closePopup = () => {
    setShowPopup(false);
  };

  if (!userRole) {
    navigate("/");
  }

  return (
    <>
      {showPopup && (
        <Popup message={popupMessage} onClose={closePopup} />
      )}
      <div className="content-allemployees">
        <div className="card-container">
          {Array.isArray(employees) && employees.length > 0 ? (
            employees.sort(function (a, b) {
              return a.empName.localeCompare(b.empName);
            })
            .map((employee) => (
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
