import React, { useState, useEffect } from "react";
import "../CSS/AddEmployee.css";
import { useNavigate } from "react-router-dom";
import bcrypt from "bcryptjs";
import InputField from "../Components/InputField";
import Popup from "../Components/Popup";
import CustomMultipleDropdown from "../Components/CustomMultipleDropdown";
import Button from "../Components/Button";
import Dropdown from "../Components/Dropdown";
import Designations from "../Assets/Designations";
import Locations from "../Assets/Locations";
import Roles from "../Assets/Roles";
import skillsList from "../Components/skillsList";
import axios from "axios";
import { ADD_EMPLOYEE } from "../Services/url";
import { postRequest } from "../Services/Service";

function AddEmployee({ handleTabChange, handleAddActionClick }) {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [employeeId, setEmployeeId] = useState("");
  const [dob, setDob] = useState("");
  const [doj, setDoj] = useState("");
  const [location, setLocation] = useState("");
  const [designation, setDesignation] = useState("");
  const [contactNo, setContactNo] = useState("");
  const [role, setRole] = useState("");
  const [nameError, setNameError] = useState("");
  const [popupMessage, setPopupMessage] = useState("");
  const [showPopup, setShowPopup] = useState(false);
  const [defaultPassword, setDefaultPassword] = useState("");
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [locationError, setLocationError] = useState("");
  const [designationError, setDesignationError] = useState("");
  const [roleError, setRoleError] = useState("");

  const [emailError, setEmailError] = useState("");
  const [employeeIdError, setEmployeeIdError] = useState("");
  const [contactNoError, setContactNoError] = useState("");
  const [dobError, setDobError] = useState("");
  const [dojError, setDojError] = useState("");
  const [skillsError, setSkillsError] = useState("");
  const [showAddEmployee, setShowAddEmployee] = useState(false);
  const [showAddManager, setShowAddManager] = useState(false);
  const [showAddProject, setShowAddProject] = useState(false);
  const navigate = useNavigate();

  const handleSkillChange = (selectedOptions) => {
    setSelectedSkills(selectedOptions);
    setSkillsError("");
  };

  const userFormData = {
    empName: name,
    empEmail: email,
    empId: employeeId,
    empDOB: dob,
    empDOJ: doj,
    empLocation: location,
    empDesignation: designation,
    empContactNo: contactNo,
    empRole: role,
    empSkills: selectedSkills.map((skill) => skill.value),
  };

  function createPassword() {
    const [day, month, year] = dob.split("/");
    const genPassword = employeeId + "@" + day + month + year;
    return genPassword;
  }

  useEffect(() => {
    setDefaultPassword(createPassword());
  }, [employeeId, dob]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    validateName();
    validateEmail();
    validateEmployeeId();
    validateDob();
    validateDoj();
    validateContactNo();
    validateLocation();
    validateDesignation();
    validateRole();

    if (
      !nameError &&
      !emailError &&
      !employeeIdError &&
      !dobError &&
      !dojError &&
      !contactNoError && 
      !locationError && 
      !roleError && 
      !designationError 
    ) {
      if (selectedSkills.length === 0) {
        setSkillsError("Please select at least one skill.");
        return;
      }

      if(nameError || emailError || employeeIdError || dobError || dojError || contactNoError || locationError || roleError || designationError){
        return;
      }

      const hashedPassword = await bcrypt.hash(defaultPassword, 10);

      const userFormData = {
        empName: name,
        empEmail: email,
        empId: employeeId,
        empDOB: dob,
        empDOJ: doj,
        empLocation: location,
        empDesignation: designation,
        empContactNo: contactNo,
        empRole: role,
        empSkills: selectedSkills.map((skill) => skill.value),
        empPassword: hashedPassword,
      };
      const resMessage = {};
      postRequest(ADD_EMPLOYEE, userFormData)
        .then((response) => {
          resMessage.message = response.data.message;
          setPopupMessage(resMessage.message);
          setShowPopup(true);
          setTimeout(() => {
            handleTabChange("employee");
          }, 800);
        })
        .catch((error) => {
          resMessage.message = error.response.data.message;
          setPopupMessage(resMessage.message);
          setShowPopup(true);
        });
    }
  };

  const validateName = () => {
    const trimmedName = name.trim();

    if (trimmedName === "") {
      setNameError("Name cannot be empty");
    } else if (trimmedName.match(/\d/)) {
      setNameError("Name cannot contain digits");
    } else {
      setNameError("");
    }
  };

  const closePopup = () => {
    setShowPopup(false);
  };

  const validateEmail = () => {
    const emailPattern = /^[A-Za-z][A-Za-z0-9._-]*@nucleusteq\.com$/;

    if (/^\d/.test(email)) {
      setEmailError("Email cannot start with a digit");
    } else if (email === "@nucleusteq.com") {
      setEmailError("Must have characters before domain");
    } else if (!emailPattern.test(email)) {
      setEmailError("Must end with @nucleusteq.com");
    } else {
      setEmailError("");
    }
  };

  const validateSkills = () => {
    const emailPattern = /@nucleusteq\.com$/;

    if (!emailPattern.test(email)) {
      setEmailError("Must end with @nucleusteq.com");
    } else {
      setEmailError("");
    }
  };

  const handleCancelClick = () => {
    handleTabChange("employee");
  };

  const validateEmployeeId = () => {
    const employeeIdPattern = /^N\d{4}$/;
    if (!employeeId.match(employeeIdPattern)) {
      setEmployeeIdError("Allowed pattern NXXXX, X is number");
    } else if (employeeId === "N0000") {
      setEmployeeIdError("Invalid employee id");
    } else {
      setEmployeeIdError("");
    }
  };

  const validateLocation = () => {
    if (location === "") {
      setLocationError("Location is required.");
    } else {
      setLocationError("");
    }
  };

  const validateDesignation = () => {
    if (designation === "") {
      setDesignationError("Designation is required.");
    } else {
      setDesignationError("");
    }
  };

  const validateRole = () => {
    if (role === "") {
      setRoleError("Role is required.");
    } else {
      setRoleError("");
    }
  };

  const validateContactNo = () => {
    const pattern = /^[0-9]+$/;

    if (contactNo.length !== 10 || !pattern.test(contactNo)) {
      setContactNoError("Exactly 10 digits allowed");
    } else {
      setContactNoError("");
    }
  };

  const validateDob = () => {
    const dobParts = dob.split("/");
    if (dobParts.length !== 3) {
      setDobError("Invalid date format (DD/MM/YYYY)");
      return;
    }

    const day = parseInt(dobParts[0], 10);
    const month = parseInt(dobParts[1], 10);
    const year = parseInt(dobParts[2], 10);

    if (isNaN(day) || isNaN(month) || isNaN(year)) {
      setDobError("Invalid date format (DD/MM/YYYY)");
      return;
    }

    const currentYear = new Date().getFullYear();
    const currentMonth = new Date().getMonth() + 1;
    const currentDay = new Date().getDate();
    if (
      year < 1971 ||
      year > currentYear ||
      (year === currentYear && month > currentMonth) ||
      (year === currentYear && month === currentMonth && day > currentDay)
    ) {
      setDobError(" Must be between 1971 and the current date");
      return;
    }
    if (month < 1 || month > 12) {
      setDobError("Month should be between 1 and 12");
      return;
    }

    const daysInMonth = new Date(year, month, 0).getDate();
    if (day < 1 || day > daysInMonth) {
      setDobError(`Day should be between 1 and ${daysInMonth}`);
      return;
    }

    setDobError("");
  };

  const validateDoj = () => {
    const dojParts = doj.split("/");
    if (dojParts.length !== 3) {
      setDojError("Invalid date format (DD/MM/YYYY)");
      return;
    }

    const day = parseInt(dojParts[0], 10);
    const month = parseInt(dojParts[1], 10);
    const year = parseInt(dojParts[2], 10);

    if (isNaN(day) || isNaN(month) || isNaN(year)) {
      setDojError("Invalid date format (DD/MM/YYYY)");
      return;
    }

    const currentYear = new Date().getFullYear();
    const currentMonth = new Date().getMonth() + 1;
    const currentDay = new Date().getDate();
    if (
      year < 1971 ||
      year > currentYear ||
      (year === currentYear && month > currentMonth) ||
      (year === currentYear && month === currentMonth && day > currentDay)
    ) {
      setDojError("Must be between 1981 and current date");
      return;
    }
    if (month < 1 || month > 12) {
      setDojError("Month should be between 1 and 12");
      return;
    }

    const daysInMonth = new Date(year, month, 0).getDate();
    if (day < 1 || day > daysInMonth) {
      setDojError(`Should be between 1 and ${daysInMonth}`);
      return;
    }

    setDojError("");
  };

  return (
    <>
      {showPopup && <Popup message={popupMessage} onClose={closePopup} />}
      <div className="addemployee-container">
        <h1 className="text-center">Add Employee Page</h1>
        <form className="register-form" onSubmit={handleSubmit}>
          <div className="grid-container">
            <div className="grid-item">
              <InputField
                label="Name"
                type="text"
                id="name"
                value={name}
                onChange={(e) => {
                  setName(e.target.value);
                  setNameError(false);
                }}
                onBlur={validateName}
              />
              <div className="error-message-container-addemployee">
                {nameError && <div className="error-message">{nameError}</div>}
              </div>
            </div>
            <div className="grid-item">
              <InputField
                label="Email"
                type="email"
                id="email"
                value={email}
                onChange={(e) => {
                  setEmail(e.target.value);
                  setEmailError(false);
                }}
                onBlur={validateEmail}
              />
              <div className="error-message-container-addemployee">
                {emailError && (
                  <div className="error-message">{emailError}</div>
                )}
              </div>
            </div>
            <div className="grid-item">
              <InputField
                label="Employee ID"
                type="text"
                id="employeeId"
                value={employeeId}
                onChange={(e) => {
                  setEmployeeId(e.target.value);
                  setEmployeeIdError(false);
                }}
                onBlur={validateEmployeeId}
              />
              <div className="error-message-container-addemployee">
                {employeeIdError && (
                  <div className="error-message">{employeeIdError}</div>
                )}
              </div>
            </div>
            <div className="grid-item">
              <InputField
                label="DOB (DD/MM/YYYY)"
                type="text"
                id="dob"
                value={dob}
                onChange={(e) => {
                  setDob(e.target.value);
                  setDobError(false);
                }}
                onBlur={validateDob}
              />
              <div className="error-message-container-addemployee">
                {dobError && <div className="error-message">{dobError}</div>}
              </div>
            </div>
            <div className="grid-item">
              <InputField
                label="DOJ (DD/MM/YYYY)"
                type="text"
                id="doj"
                value={doj}
                onChange={(e) => {
                  setDoj(e.target.value);
                  setDojError(false);
                }}
                onBlur={validateDoj}
              />
              <div className="error-message-container-addemployee">
                {dojError && <div className="error-message">{dojError}</div>}
              </div>
            </div>

            <Dropdown
              id="location"
              value={location}
              options={Locations}
              onChange={(e) => setLocation(e.target.value)}
              placeholder="Select Location"
              className="grid-dropdown"
              label="Location"
              selectClassname="select-classname"
              error={locationError} 
              onBlur={validateLocation} 
            />

            <Dropdown
              id="designation"
              value={designation}
              options={Designations}
              onChange={(e) => setDesignation(e.target.value)}
              className="grid-dropdown"
              placeholder="Select Designation"
              label="Designation"
              selectClassname="select-classname"
              error={designationError}
              onBlur={validateDesignation}
            />

            <Dropdown
              id="role"
              value={role}
              options={Roles}
              onChange={(e) => setRole(e.target.value)}
              className="grid-dropdown"
              placeholder="Select Role"
              label="Role"
              selectClassname="select-classname"
              error={roleError}
              onBlur={validateRole}
            />

            <div className="grid-item">
              <InputField
                label="Contact No"
                type="text"
                id="contactNo"
                value={contactNo}
                onChange={(e) => {
                  setContactNo(e.target.value);
                  setContactNoError(false);
                }}
                onBlur={validateContactNo}
              />

              <div className="error-message-container-addemployee">
                {contactNoError && (
                  <div className="error-message">{contactNoError}</div>
                )}
              </div>
            </div>
            <div className="grid-item">
              <label htmlFor="skills">Skills</label>
              <CustomMultipleDropdown
                options={skillsList.map((skill) => ({
                  value: skill,
                  label: skill,
                }))}
                selectedOptions={selectedSkills}
                onChange={handleSkillChange}
                placeholder="Select skills..."
                id="skills"
                customClassName="select-container-addemployee"
                onBlur={validateSkills}
                width={true}
              />
              <div className="error-message-container-addemployee">
                {skillsError && (
                  <div className="error-message">{skillsError}</div>
                )}
              </div>
            </div>
          </div>
          <div className="form-group">
            <Button
              type="submit"
              className="login-button"
              text="Add Employee"
              onClick={handleSubmit}
            />
            <Button
              type="button"
              className="cancel-button-addemployee"
              text="Cancel"
              onClick={handleCancelClick}
            />
          </div>
        </form>
      </div>
    </>
  );
}

export default AddEmployee;
