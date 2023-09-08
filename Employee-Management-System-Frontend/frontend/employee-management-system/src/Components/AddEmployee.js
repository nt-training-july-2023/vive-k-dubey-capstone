import React, { useState, useEffect} from 'react';
import '../CSS/AddEmployee.css'; 
import { useNavigate } from 'react-router-dom';
import Select from 'react-select';

function AddEmployee() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [employeeId, setEmployeeId] = useState('');
  const [dob, setDob] = useState('');
  const [doj, setDoj] = useState('');
  const [location, setLocation] = useState('');
  const [designation, setDesignation] = useState('');
  const [contactNo, setContactNo] = useState('');
  const[role, setRole] =useState('');
  const [nameError, setNameError] = useState('');
  const [popupMessage, setPopupMessage] = useState('');
  const [showPopup, setShowPopup] = useState(false);
  const [defaultPassword, setDefaultPassword] = useState('');
  const [selectedSkills, setSelectedSkills] = useState([]);

  const [emailError, setEmailError] = useState('');
  const [employeeIdError, setEmployeeIdError] = useState('');
  const [contactNoError, setContactNoError] = useState('');
  const [dobError, setDobError] = useState('');
  const [dojError, setDojError] = useState('');
  const [skillsError, setSkillsError] = useState('');
  const navigate = useNavigate();

  const [predefinedSkills]= useState([
    'JavaScript',
    'React',
    'Node.js',
    'Python',
    'Java',
    'Spark',
    'HTML',
    'CSS',
    'Big Data',
    'Spring Boot',
    'Postgres',
    'Snowflake',
    'Airflow',
    'SQL',
    'Machine Learning',
    'Data Analysis',
  ]);

  const handleSkillChange = (selectedOptions) => {
    setSelectedSkills(selectedOptions);
    // Clear the skills error message when a skill is selected.
    setSkillsError('');
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
    empSkills: selectedSkills.map(skill => skill.value),
  };

  const generateDefaultPassword = () => {
    return `${employeeId}@${dob.replace(/\//g, '')}`;
  };

  // Default password generation function
  useEffect(() => {
    const defaultPassword = generateDefaultPassword();
    console.log('Default Password:', defaultPassword);
  }, [employeeId, dob]);

  const handleSubmit = async (e) => {
    e.preventDefault();

  validateName();
  validateEmail();
  validateEmployeeId();
  validateDob();
  validateDoj();
  validateContactNo();
  //validateSkills();

  if (!nameError && !emailError && !employeeIdError && !dobError && !dojError   && !contactNoError ) {
 

    const defaultPassword = generateDefaultPassword();
    if (selectedSkills.length === 0) {
      setSkillsError('Please select at least one skill.');
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
      empRole: role,
      empSkills: selectedSkills.map(skill => skill.value),
      empPassword: defaultPassword,
    };

    try {
      const response = await fetch('http://localhost:8081/employee/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userFormData),
      });
    
      if (response.ok) {
        const responseData = await response.json();
        setPopupMessage(responseData.message);
        setShowPopup(true);
      } else {
        const errorMessage = await response.json();
        setPopupMessage(errorMessage.message);
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

  //const validateSkills = () =>{
  //   if (skills.trim() === '') {
  //       setSkillsError('Skills cannot be empty');
   //   }
    // if(selectedSkills.length === 0){
    //   setSkillsError('Skills cannot be empty');
    // }
 // }

  const closePopup = () => {
    setShowPopup(false);
  };

  const validateEmail = () => {
    const emailPattern = /@nucleusteq\.com$/; // Regex pattern for emails ending with @nucleusteq.com
  
    if (!emailPattern.test(email)) {
      setEmailError('Email must end with @nucleusteq.com');
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
              <option value="Indore">Indore</option>
              <option value="Raipur">Raipur</option>
              <option value="Bangalore">Bangalore</option>
              <option value="Phoenix">Phoenix</option>
              <option value="Canada">Canada</option>
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
              <option value="Software Engineer">Software Engineer</option>
              <option value="Data Engineer">Data Engineer</option>
              <option value="Senior Engineer">Senior Engineer</option>
              <option value="Architect">Architect</option>
              <option value="Technical Lead">Technical Lead</option>
              <option value="Senior Architect">Senior Architect</option>
              <option value="Recruiter">Recruiter</option>
              <option value="Operation Analyst">Operation Analyst</option>
            </select>
          </div>
           <div className="grid-item">
            <label htmlFor="role">Role</label>
            <select
              id="role"
              value={role}
              onChange={(e) => setRole(e.target.value)}
              required
            >
              <option value="">Select Role</option>
              <option value="manager">Manager</option>
              <option value="employee">Employee</option>
            </select>
          </div> 
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
          <label htmlFor="skills">Skills</label>
          <Select
            id="skills"
            isMulti
            options={predefinedSkills.map((skill) => ({
              value: skill,
              label: skill,
            }))}
            value={selectedSkills}
            onChange={handleSkillChange}
            placeholder="Select skills..."
          />
          {skillsError && (
            <div className="error-message">{skillsError}</div>
          )}
        </div>
        </div>
        <div className="form-group">
          <button type="submit" className="login-button" onClick={handleSubmit} >
              Add Employee
            </button>
        </div>
      </form>
    </div>
    </>
  );
}

export default AddEmployee;
