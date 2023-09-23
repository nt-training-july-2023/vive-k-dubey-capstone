import React, { useState, useEffect } from 'react';
import '../CSS/UserProfile.css';
import Field from '../Components/UserProfileInputField';
import { useNavigate } from 'react-router';

function UserProfile({ onEmployeeNameChange }) {
  const [userData, setUserData] = useState({
    empId: '',
    empName: '',
    empEmail: '',
    empContactNo: '',
    empDOB: '',
    empDOJ: '',
    empLocation: '',
    empSkills: '',
    projectName: '',
    managerName: '',
  });
  const navigate = useNavigate();
  const handleUpdateSkills = () => {

    navigate('/userdashboard/upadteskills' , { state: { currentSkills: userData.empSkills } });
    
  }

  useEffect(() => {
    const userEmail = localStorage.getItem('userEmail');

    if (userEmail) {
      fetch(`http://localhost:8081/api/employee/${userEmail}`)
        .then((response) => response.json())
        .then((data) => {
          setUserData(data);

          onEmployeeNameChange(data.empName);
        })
        .catch((error) => {
          console.error('Error fetching user data:', error);
        });
    }
  }, [onEmployeeNameChange]);

  return (
    <div className="user-profile">
      <div className="user-profile-content">
        <div className="user-profile-left">
          <Field label="Emp ID" value={userData.empId} onChange={() => {}} />
          <Field label="Name" value={userData.empName} onChange={() => {}} />
          <Field label="Contact No" value={userData.empContactNo} onChange={() => {}} />
          <Field label="Email" value={userData.empEmail} onChange={() => {}} />
          <Field
            label="Skills"
            value={userData.empSkills}
            onChange={() => {}}
            className="user-input-multiline"
          />
        </div>
        <div className="user-profile-right">
          <Field label="DOB" value={userData.empDOB} onChange={() => {}} />
          <Field label="Manager" value={userData.managerName} onChange={() => {}} />
          
          <Field label="Project Name" value={userData.projectName} onChange={() => {}} />
          <Field label="DOJ" value={userData.empDOJ} onChange={() => {}} />
          <Field label="Location" value={userData.empLocation} onChange={() => {}} />
        </div>
      </div>
      <button className="user-update-button" onClick={handleUpdateSkills}>Update Skills</button>
    </div>
  );
}

export default UserProfile;
