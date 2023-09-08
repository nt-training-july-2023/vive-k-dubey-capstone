import React, { useState, useEffect} from 'react';
import '../CSS/Register.css'; 
import { useNavigate } from 'react-router-dom';

function RegisterForm() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [employeeId, setEmployeeId] = useState('');
  const [dob, setDob] = useState('');
  const [doj, setDoj] = useState('');
  const [location, setLocation] = useState('');
  const [designation, setDesignation] = useState('');
  const [contactNo, setContactNo] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const[role, setRole] =useState('');
  const [passwordError, setPasswordError] = useState('');
  const [nameError, setNameError] = useState('');
  const [popupMessage, setPopupMessage] = useState('');
  const [showPopup, setShowPopup] = useState(false);

  const [emailError, setEmailError] = useState('');
  const [employeeIdError, setEmployeeIdError] = useState('');
  const [contactNoError, setContactNoError] = useState('');
  const [dobError, setDobError] = useState('');
  const [dojError, setDojError] = useState('');
  const navigate = useNavigate();

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
    // empRole: role,
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

  validateName();
  validateEmail();
  validateEmployeeId();
  validateDob();
  validateDoj();
  validateContactNo();
  const isPasswordValid = validatePassword();

  if (!nameError && !emailError && !employeeIdError && !dobError && !dojError   && !contactNoError && isPasswordValid) {
 

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
      // empRole: role,
    };

    try {
      
      const response = await fetch('http://localhost:8081/admin', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userFormData),
      });
      
      console.log("message of status",response.status);
      if (response.status === 200) {
        setPopupMessage('Registration successful!');
        setShowPopup(true);
      } else if(response.status === 400){
        const errorMessage = await response.text();
        setPopupMessage(errorMessage);
        setShowPopup(true);
      } else {
        setPopupMessage('Registration failed. Please try again.');
        setShowPopup(true);
      }
    } catch (error) {
      setPopupMessage('An error occurred. Please try again later.');
      setShowPopup(true);
    }
  }
    
  };

  const validateName = () => {
    if (name === '') {
      setNameError('Name cannot be empty');
    } else if (name.match(/\d/)) {
      setNameError('Name cannot contain digits');
    } else {
      setNameError('');
    }
  };

  const closePopup = () => {
    setShowPopup(false);
  };

  const validateEmail = () => {
    const validEmail = 'ankita.sharma@nucleusteq.com';
    
    if (email !== validEmail) {
      setEmailError(`Email must be ${validEmail}`);
    } else {
      setEmailError('');
    }
  };

  const handleLoginClick = () => {
    navigate('/');
  };

  const validateEmployeeId = () => {
    const employeeIdPattern = /^N\d{4}$/;
    if (!employeeId.match(employeeIdPattern)) {
      setEmployeeIdError('Employee ID should be in the pattern NXXXX (X should be numbers)');
    } else {
      setEmployeeIdError('');
    }
  };

  const validateContactNo = () => {
    const pattern = /^[0-9]+$/;
  
    if (contactNo.length !== 10 || !pattern.test(contactNo)) {
      setContactNoError('Contact No should have 10 digits and only contain digits.');
    } else {
      setContactNoError('');
    }
  };

  const validatePassword = () => {
    if (password.length < 8) {
      setPasswordError('Password should be at least 8 characters long');
      return false;
    }
    else if (password !== confirmPassword) {
        setPasswordError('Passwords do not match');
    }
    else{
        setPasswordError('');
        return true;
    }
    
  };

  const validateDob = () => {
    const dobParts = dob.split('/');
    if (dobParts.length !== 3) {
      setDobError('Invalid date format (DD/MM/YYYY)');
      return;
    }

    const day = parseInt(dobParts[0], 10);
    const month = parseInt(dobParts[1], 10);
    const year = parseInt(dobParts[2], 10);

    if (isNaN(day) || isNaN(month) || isNaN(year)) {
      setDobError('Invalid date format (DD/MM/YYYY)');
      return;
    }

    const currentYear = new Date().getFullYear();
  if (year > 2003 || year <= 1980) {
    setDobError('Year should be between 1981 and the 2003');
    return;
  }
    if (month < 1 || month > 12) {
        setDobError('Month should be between 1 and 12');
        return;
      }
  
     
      const daysInMonth = new Date(year, month, 0).getDate();
      if (day < 1 || day > daysInMonth) {
        setDobError(`Day should be between 1 and ${daysInMonth}`);
        return;
      }
  
      setDobError('');
    };

    const validateDoj = () => {
        const dojParts = doj.split('/');
        if (dojParts.length !== 3) {
          setDojError('Invalid date format (DD/MM/YYYY)');
          return;
        }
    
        const day = parseInt(dojParts[0], 10);
        const month = parseInt(dojParts[1], 10);
        const year = parseInt(dojParts[2], 10);
    
        if (isNaN(day) || isNaN(month) || isNaN(year)) {
          setDojError('Invalid date format (DD/MM/YYYY)');
          return;
        }
    
        const currentYear = new Date().getFullYear();
      if (year <= 2018 || year > currentYear) {
        setDojError('Year should be between 2018 and the current year');
        return;
      }
        if (month < 1 || month > 12) {
            setDojError('Month should be between 1 and 12');
            return;
          }
      
          
          const daysInMonth = new Date(year, month, 0).getDate();
          if (day < 1 || day > daysInMonth) {
            setDojError(`Day should be between 1 and ${daysInMonth}`);
            return;
          }
      
          setDojError('');
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
    <div className="register-container">
      <h1 className="text-center">Admin Register Form</h1>
      <div className="top-right-text">
        <h3>Register Page</h3>
      </div>
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
                validateName(e.target.value);}}
              onBlur={validateName}
              required
            />
            {nameError && <div className="error-message">{nameError}</div>}
          </div>
          <div className="grid-item">
            <label htmlFor="email">Email</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => {
                setEmail(e.target.value);
                validateEmail(e.target.value); 
              }}
              onBlur={validateEmail}
              required
            />
            {emailError && <div className="error-message">{emailError}</div>}
          </div>
          <div className="grid-item">
            <label htmlFor="employeeId">Employee ID</label>
            <input
              type="text"
              id="employeeId"
              value={employeeId}
              onChange={(e) => {
                setEmployeeId(e.target.value);
                validateEmployeeId(e.target.value);}}
              onBlur={validateEmployeeId}
              required
            />
            {employeeIdError && <div className="error-message">{employeeIdError}</div>}
          </div>
          <div className="grid-item">
            <label htmlFor="dob">DOB (DD/MM/YYYY)</label>
            <input
              type="text"
              id="dob"
              value={dob}
              onChange={(e) => {
                setDob(e.target.value);
                validateDob(e.target.value);}}
              onBlur={validateDob}
              required
            />
            {dobError && <div className="error-message">{dobError}</div>}
          </div>
          <div className="grid-item">
            <label htmlFor="doj">DOJ (DD/MM/YYYY)</label>
            <input
              type="text"
              id="doj"
              value={doj}
              onChange={(e) => setDoj(e.target.value)}
              onBlur={validateDoj}
              required
            />
            {dojError && <div className="error-message">{dojError}</div>}
          </div>
          <div className="grid-item">
            <label htmlFor="location">Location</label>
            <select
              id="location"
              value={location}
              onChange={(e) => setLocation(e.target.value)}
              required
            >
              <option value="">Select Location</option>
              <option value="indore">Indore</option>
              <option value="raipur">Raipur</option>
              <option value="bangalore">Bangalore</option>
              <option value="phoenix">Phoenix</option>
              <option value="canada">Canada</option>
            </select>
          </div>
          <div className="grid-item">
            <label htmlFor="designation">Designation</label>
            <select
              id="designation"
              value={designation}
              onChange={(e) => setDesignation(e.target.value)}
              required
            >
              <option value="">Select Designation</option>
              <option value="softwareEngineer">Software Engineer</option>
              <option value="dataEngineer">Data Engineer</option>
              <option value="seniorEngineer">Senior Engineer</option>
              <option value="architect">Architect</option>
              <option value="technicalLead">Technical Lead</option>
              <option value="seniorArchitect">Senior Architect</option>
              <option value="recruiter">Recruiter</option>
              <option value="operationAnalyst">Operation Analyst</option>
            </select>
          </div>
          {/* <div className="grid-item">
            <label htmlFor="role">Role</label>
            <select
              id="role"
              value={role}
              onChange={(e) => setRole(e.target.value)}
              required
            >
              <option value="">Select Role</option>
              <option value="admin">Admin</option>
              <option value="manager">Manager</option>
              <option value="employee">Employee</option>
            </select>
          </div> */}
          <div className="grid-item">
            <label htmlFor="contactNo">Contact No</label>
            <input
              type="text"
              id="contactNo"
              value={contactNo}
              onChange={(e) => setContactNo(e.target.value)}
              onBlur={validateContactNo}
              required
            />
            {contactNoError && <div className="error-message">{contactNoError}</div>}
          </div>
          <div className="grid-item">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              onBlur={validatePassword}
              required
            />
            {passwordError && <div className="error-message">{passwordError}</div>}
          </div>
          <div className="grid-item">
            <label htmlFor="confirmPassword">Confirm Password</label>
            <input
              type="password"
              id="confirmPassword"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              onBlur={validatePassword}
              required
            />
            {password !== confirmPassword && (
              <div className="error-message">Passwords do not match</div>
            )}
          </div>
        </div>
        <div className="form-group">
          <button type="submit" className="register-button">
            Register
          </button>
          <button type="button" className="login-button" onClick={handleLoginClick}>
              Login
            </button>
        </div>
      </form>
    </div>
    </>
  );
}

export default RegisterForm;
