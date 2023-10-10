import React, { useState, useEffect } from "react";
import "../CSS/Register.css";
import { useNavigate } from "react-router-dom";
import bcrypt from "bcryptjs";
import Popup from "../Components/Popup";
import Button from "../Components/Button";
import Designations from "../Assets/Designations";
import Locations from "../Assets/Locations";
import Dropdown from "../Components/Dropdown";
import InputField from "../Components/InputField";
import axios from "axios";

function RegisterForm() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [employeeId, setEmployeeId] = useState("");
  const [dob, setDob] = useState("");
  const [doj, setDoj] = useState("");
  const [location, setLocation] = useState("");
  const [designation, setDesignation] = useState("");
  const [contactNo, setContactNo] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [role, setRole] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [nameError, setNameError] = useState("");
  const [popupMessage, setPopupMessage] = useState("");
  const [showPopup, setShowPopup] = useState(false);
  const [locationError, setLocationError] = useState("");
  const [designationError, setDesignationError] = useState("");

  const [emailError, setEmailError] = useState("");
  const [employeeIdError, setEmployeeIdError] = useState("");
  const [contactNoError, setContactNoError] = useState("");
  const [dobError, setDobError] = useState("");
  const [dojError, setDojError] = useState("");
  const userRole = localStorage.getItem("role");
  const navigate = useNavigate();

  useEffect(() => {
    if (userRole === "admin") {
      navigate("/admin-dashboard");
    } else if (userRole === "manager") {
      navigate("/managerdashboard");
    } else if (userRole === "employee") {
      navigate("/userdashboard");
    }
  }, [navigate]);

  const userFormData = {
    empName: name,
    empEmail: email,
    empId: employeeId,
    empDOB: dob,
    empDOJ: doj,
    empLocation: location,
    empDesignation: designation,
    empContactNo: contactNo,
    empPassword: password,
  };

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
    const isPasswordValid = validatePassword();

    const hashedPassword = await bcrypt.hash(password, 10);

    if (
      !nameError &&
      !emailError &&
      !employeeIdError &&
      !dobError &&
      !dojError &&
      !contactNoError &&
      !locationError &&
      !designationError &&
      isPasswordValid
    ) {

      if(nameError || emailError || employeeIdError || dobError || dojError || contactNoError || locationError || designationError){
        return;
      }
      const userFormData = {
        empName: name,
        empEmail: email,
        empId: employeeId,
        empDOB: dob,
        empDOJ: doj,
        empLocation: location,
        empDesignation: designation,
        empContactNo: contactNo,
        empPassword: hashedPassword,
      };

      try {
        const response = await axios.post("http://localhost:8081/admin", userFormData, {
          headers: {
            "Content-Type": "application/json",
          },
        });
      
        if (response.status === 200) {
          setPopupMessage("Registration successful!");
          setShowPopup(true);
        } 
      } catch (error) {
        const errorMessage = error.response.data.message;
        setPopupMessage(errorMessage);
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
    const validEmail = "ankita.sharma@nucleusteq.com";

    if (email !== validEmail) {
      setEmailError(`Email must be ${validEmail}`);
    } else {
      setEmailError("");
    }
  };

  const handleLoginClick = () => {
    navigate("/");
  };

  const validateEmployeeId = () => {
    const employeeIdPattern = /^N\d{4}$/;
    if (!employeeId.match(employeeIdPattern)) {
      setEmployeeIdError("Should be like NXXXX where X is a digit");
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


  const validateContactNo = () => {
    const pattern = /^[0-9]+$/;

    if (contactNo.length !== 10 || !pattern.test(contactNo)) {
      setContactNoError("Contact No should have 10 digits.");
    } else {
      setContactNoError("");
    }
  };

  const validatePassword = () => {
    if (password.length < 8) {
      setPasswordError("Password should be at least 8 characters long");
      return false;
    } else if (password !== confirmPassword) {
      setPasswordError("Passwords do not match");
    } else {
      setPasswordError("");
      return true;
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
    if (year > currentYear || year <= 1970) {
      setDobError("Must be between 1971 and current year");
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
    if (year <= 1980 || year > currentYear) {
      setDojError("Must be between 1981 and current year");
      return;
    }
    if (month < 1 || month > 12) {
      setDojError("Month should be between 1 and 12");
      return;
    }

    const daysInMonth = new Date(year, month, 0).getDate();
    if (day < 1 || day > daysInMonth) {
      setDojError(`Day should be between 1 and ${daysInMonth}`);
      return;
    }

    setDojError("");
  };

  return (
    <>
      {showPopup && <Popup message={popupMessage} onClose={closePopup} />}
      <div className="register-container">
        <h1 className="text-center">Admin Register Form</h1>
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
                  setNameError("");
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
                  setEmailError("");
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
                  setEmployeeIdError("");
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
                  setDobError("");
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
                  setDojError("");
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
            <div className="grid-item">
              <InputField
                label="Contact No"
                type="text"
                id="contactNo"
                value={contactNo}
                onChange={(e) => {
                  setContactNo(e.target.value);
                  setContactNoError("");
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
              <InputField
                label="Password"
                type="password"
                id="password"
                value={password}
                onChange={(e) => {
                  setPassword(e.target.value);
                  setPasswordError("");
                }}
                onBlur={validatePassword}
              />

              <div className="error-message-container-addemployee">
                {passwordError && (
                  <div className="error-message">{passwordError}</div>
                )}
              </div>
            </div>
            <div className="grid-item">
              <InputField
                label="Confirm Password"
                type="password"
                id="confirmPassword"
                value={confirmPassword}
                onChange={(e) => {
                  setConfirmPassword(e.target.value);
                }}
                onBlur={validatePassword}
              />

              {password !== confirmPassword && (
                <div className="error-message">Passwords do not match</div>
              )}
            </div>
          </div>
          <div className="form-group">
            <Button type="submit" className="register-button" text="Register" />
            <Button
              type="button"
              className="login-button"
              text="Login"
              onClick={handleLoginClick}
            />
          </div>
        </form>
      </div>
    </>
  );
}

export default RegisterForm;
