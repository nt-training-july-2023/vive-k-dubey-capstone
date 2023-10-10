import React, { useState, useEffect } from "react";
import "../CSS/UserProfile.css";
import Field from "../Components/UserProfileInputField";
import { useNavigate } from "react-router";
import axios from "axios";
import Button from "../Components/Button";
import { getRequest } from "../Services/Service";
import { GET_USER_DETAILS } from "../Services/url";
import Popup from '../Components/Popup';

function UserProfile({ onEmployeeNameChange }) {
  const [popupMessage, setPopupMessage] = useState('');
  const [showPopup, setShowPopup] = useState(false);
  const [userData, setUserData] = useState({
    empId: "",
    empName: "",
    empEmail: "",
    empContactNo: "",
    empDOB: "",
    empDOJ: "",
    empLocation: "",
    empSkills: "",
    projectName: "",
    managerName: "",
  });
  const closePopup = () => {
    setShowPopup(false);
  };
  const navigate = useNavigate();
  const userEmail = localStorage.getItem("userEmail");

  const handleUpdateSkills = () => {
    navigate("/userdashboard/upadteskills", {
      state: { currentSkills: userData.empSkills },
    });
  };

  useEffect(() => {
    if (userEmail) {
        getRequest(GET_USER_DETAILS + userEmail)
        .then((response) => {
          setUserData(response.data);
          onEmployeeNameChange(response.data.empName);
        })
        .catch((error) => {
          if (error.response) {
            const errorMessage = error.response.data;
            setPopupMessage(errorMessage.message);
            setShowPopup(true);
          } else {
            setPopupMessage("Server is not running.");
            setShowPopup(true);
          }

        });
    }
  }, [userEmail]);

  return (
    <>
    {showPopup && (
      <Popup message={popupMessage} onClose={closePopup} />
    )}
    <div className="user-profile">
      <div className="user-profile-content">
        <div className="user-profile-left">
          <Field label="Emp ID" value={userData.empId} onChange={() => {}} />
          <Field label="Name" value={userData.empName} onChange={() => {}} />
          <Field
            label="Contact No"
            value={userData.empContactNo}
            onChange={() => {}}
          />
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
          <Field
            label="Manager"
            value={userData.managerName}
            onChange={() => {}}
          />

          <Field
            label="Project Name"
            value={userData.projectName}
            onChange={() => {}}
          />
          <Field label="DOJ" value={userData.empDOJ} onChange={() => {}} />
          <Field
            label="Location"
            value={userData.empLocation}
            onChange={() => {}}
          />
        </div>
      </div>
      <Button
        className="user-update-button"
        text="Update Skills"
        onClick={handleUpdateSkills}
      />
    </div>
    </>
  );
}

export default UserProfile;
