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

    if (
      !nameError &&
      !emailError &&
      !employeeIdError &&
      !dobError &&
      !dojError &&
      !contactNoError
    ) {
      if (selectedSkills.length === 0) {
        setSkillsError("Please select at least one skill.");
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

      try {
        const response = await fetch("http://localhost:8081/employee/add", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(userFormData),
        });

        if (response.ok) {
          const responseData = await response.json();
          setPopupMessage(responseData.message);
          setShowPopup(true);
          setTimeout(() => {
            handleTabChange("employee");
          }, 800);
        } else {
          const errorMessage = await response.json();
          setPopupMessage(errorMessage.message);
          setShowPopup(true);
        }
      } catch (error) {
        setPopupMessage("An error occurred. Please try again later.");
        setShowPopup(true);
      }
    }
  };

  const validateName = () => {
    if (name === "") {
      setNameError("Name cannot be empty");
    } else if (name.match(/\d/)) {
      setNameError("Name cannot contain digits");
    } else {
      setNameError("");
    }
  };

  const closePopup = () => {
    setShowPopup(false);
  };

  const validateEmail = () => {
    const emailPattern = /@nucleusteq\.com$/;

    if (!emailPattern.test(email)) {
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
    if (year > 2003 || year <= 1980) {
      setDobError("Should be between 1981 and the 2003");
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
    if (year <= 2018 || year > currentYear) {
      setDojError("Must be between 2018 and current year");
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
      <div className="register-container">
        <h1 className="text-center">Add Employee Page</h1>
        <form className="register-form" onSubmit={handleSubmit}>
          <div className="grid-container">
            <div className="grid-item">
              <label htmlFor="name">Name</label>
              <input
                type="text"
                id="name"
                value={name}
                onChange={(e) => {
                  setName(e.target.value);
                  setNameError(false);
                }}
                onBlur={validateName}
                required
              />
              <div className="error-message-container-addemployee">
                {nameError && <div className="error-message">{nameError}</div>}
              </div>
            </div>
            <div className="grid-item">
              <label htmlFor="email">Email</label>
              <input
                type="email"
                id="email"
                value={email}
                onChange={(e) => {
                  setEmail(e.target.value);
                  setEmailError(false);
                }}
                onBlur={validateEmail}
                required
              />
              <div className="error-message-container-addemployee">
                {emailError && (
                  <div className="error-message">{emailError}</div>
                )}
              </div>
            </div>
            <div className="grid-item">
              <label htmlFor="employeeId">Employee ID</label>
              <input
                type="text"
                id="employeeId"
                value={employeeId}
                onChange={(e) => {
                  setEmployeeId(e.target.value);
                  setEmployeeIdError(false);
                }}
                onBlur={validateEmployeeId}
                required
              />
              <div className="error-message-container-addemployee">
                {employeeIdError && (
                  <div className="error-message">{employeeIdError}</div>
                )}
              </div>
            </div>
            <div className="grid-item">
              <label htmlFor="dob">DOB (DD/MM/YYYY)</label>
              <input
                type="text"
                id="dob"
                value={dob}
                onChange={(e) => {
                  setDob(e.target.value);
                  setDobError(false);
                }}
                onBlur={validateDob}
                required
              />
              <div className="error-message-container-addemployee">
                {dobError && <div className="error-message">{dobError}</div>}
              </div>
            </div>
            <div className="grid-item">
              <label htmlFor="doj">DOJ (DD/MM/YYYY)</label>
              <input
                type="text"
                id="doj"
                value={doj}
                onChange={(e) => {
                  setDoj(e.target.value);
                  setDojError(false);
                }}
                onBlur={validateDoj}
                required
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
            />

            <div className="grid-item">
              <label htmlFor="contactNo">Contact No</label>
              <input
                type="text"
                id="contactNo"
                value={contactNo}
                onChange={(e) => {
                  setContactNo(e.target.value);
                  setContactNoError(false);
                }}
                onBlur={validateContactNo}
                required
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
