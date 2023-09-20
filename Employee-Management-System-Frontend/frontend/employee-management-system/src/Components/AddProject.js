import React, { useState, useEffect } from 'react';
import '../CSS/AddProjectForm.css';
import Select from 'react-select';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function AddProject({handleTabChange}) {
  const [projectName, setProjectName] = useState('');
  const [managerId, setManagerId] = useState('');
  const [startDate, setStartDate] = useState('');
  const [skills, setSkills] = useState('');
  const [description, setDescription] = useState('');

  const [projectNameError, setProjectNameError] = useState('');
  const [startDateError, setStartDateError] = useState('');
  const [skillsError, setSkillsError] = useState('');
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [selectedManagerId, setSelectedManagerId] = useState(null);
  const [popupMessage, setPopupMessage] = useState('');
  const [showPopup, setShowPopup] = useState(false);
  const [projectAdded, setProjectAdded] = useState(false);


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

  const [managerList, setManagerList] = useState([]);
  const [projectData, setProjectData] = useState({
    name: "",
    managerEmployeeId: "",
    startDate: "",
    skills: [],
    description: ""
})

  const navigate = useNavigate(); 


  const handleCancel = () => {
    
    handleTabChange('project');
  };
  const handleChangeManager = (selectedOption) => {
    setSelectedManagerId(parseInt(selectedOption.value));
    setManagerId(selectedOption.value);
  };

  async function getManagerList() {
    try{
        const res = await axios.get("http://localhost:8081/employee/getAllManagersInfo");
        console.log("manager list",res.data);
        setManagerList(res.data);
        console.log("manager list new", managerList);
    }catch(error) {
        console.log(error);
    }
}

async function apiCall() {
  const resMessage = {};
  try{
      const res = await axios.post("http://localhost:8081/employee/addProject",projectData);
      console.log(res.data);
      console.log(res.data);
      setPopupMessage(res.data.message); 
      setShowPopup(true);

      setProjectAdded(true);

      setTimeout(() => {
        handleTabChange('project');
      }, 800);
  }catch(error){
    console.log(error);
    setPopupMessage(error.response.data.message); // Set the popup message from the API error response
    setShowPopup(true);
  }
  //setPopUpMessage(resMessage);
  //setShowPopUp(true);
}
//use effect

  const formatManagerOption = (manager) => ({
    value: manager.managerEmployeeId,
    label: `${manager.managerEmployeeId} - ${manager.managerName}`,
  });

  const handleSkillChange = (selectedOptions) => {
    setSelectedSkills(selectedOptions);
    const selectedSkillValues = selectedOptions.map(option => option.value);
      setProjectData({ ...projectData, skills: selectedSkillValues });
    // Clear the skills error message when a skill is selected.
    setSkillsError('');
  };

  function handleChange(event) {
    //validateData(event.target.name, event.target.value);
    setProjectData({ ...projectData, [event.target.name]: event.target.value });
    //event.target.blur();

}

  const handleProjectNameBlur = () => {
    if (/[\d]/.test(projectName)) {
      setProjectNameError('Project name cannot contain digits.');
    } else {
      setProjectNameError('');
    }
  };

  const handleStartDateBlur = () => {

    const currentDate = new Date(); // Get the current date
    const inputDate = new Date(startDate);

    if (inputDate < currentDate) {
      setStartDateError('Start date cannot be before the current date.');
    } else {
      setStartDateError('');
    }

    const startDateParts = startDate.split('/');
    if (startDateParts.length !== 3) {
      setStartDateError('Invalid start date format (DD/MM/YYYY)');
      return;
    }

    const day = parseInt(startDateParts[0], 10);
    const month = parseInt(startDateParts[1], 10);
    const year = parseInt(startDateParts[2], 10);

    if (isNaN(day) || isNaN(month) || isNaN(year)) {
      setStartDateError('Invalid start date format (DD/MM/YYYY)');
      return;
    }
  };

  const handleSkillsBlur = () => {
    if (selectedSkills.length === 0) {
      setSkillsError('Please select at least one skill.');
      return;
    }
  };

  const closePopup = () => {
    setShowPopup(false);
  };

  // const handleSubmit = async (e) => {
  //   e.preventDefault();

  //   // Validation logic goes here
  //   if (!projectName || !selectedManagerId || !startDate || !skills || !description) {
  //     alert('All fields are mandatory.');
  //   } else {
  //     try {
  //       // Step 3: Use selectedManagerId as managerEmployeeId
  //       const formData = {
  //         name: projectName,
  //         managerEmployeeId: selectedManagerId, // Use selectedManagerId here
  //         startDate,
  //         skills: selectedSkills.map((skill) => skill.value),
  //         description,
  //       };

  //       // Make an API call to add the project
  //       const response = await axios.post('http://localhost:8081/employee/addProject', formData);

  //       // Handle the response as needed
  //       console.log('Project added successfully:', response.data);

  //       // Reset the form
  //       setProjectName('');
  //       setSelectedManagerId(null); // Reset selected manager ID
  //       setStartDate('');
  //       setSkills('');
  //       setDescription('');
  //     } catch (error) {
  //       console.error('Error adding project:', error);
  //     }
  //   }
  // };

  useEffect(() => {
    getManagerList();
    
  },[projectData])
  

  function handleSubmit(event) {
    event.preventDefault();
     
        // console.log("Prod data",projectData);
        apiCall();
        console.log("Project Data",projectData);
    
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
    <div className="add-project-heading">Add Project Page</div>
    <div className="add-project-container">
      
      <form onSubmit={handleSubmit}>
        <div className="add-project-group">
          <label htmlFor="add-project-name">Project Name:</label>
          <input
            type="text"
            id="add-project-name"
            name='name'
            className="add-project-input"
            // value={projectName}
            // onChange={(e) => setProjectName(e.target.value)}
            onChange={handleChange}
            onBlur={handleProjectNameBlur}
            required
          />
          {projectNameError && <div className="error-message">{projectNameError}</div>}
        </div>
        <div className="add-project-group">
            <label htmlFor="add-project-manager">Manager ID:</label>
            <select
              id="add-project-manager"
              name = 'managerEmployeeId'
              type='select'
              className="add-project-select"
              onChange={handleChange}
              required
            >
              <option value="">Select Manager</option>
                                {managerList.map((manager) => {
                                    // console.log(manager.name, manager.id)
                                    console.log("id value", manager.id);
                                    return <option key={manager.id} value={manager.id}>{manager.managerEmployeeId} - {manager.managerName}</option>
              })}
                 
            </select>
          </div>
        <div className="add-project-group">
            <label htmlFor="add-project-start-date">Start Date (DD/MM/YYYY):</label>
            <input
                type="text"
                id="add-project-start-date"
                className="add-project-input"
                name='startDate'
                onChange={handleChange}
                //onBlur={handleStartDateBlur}
                placeholder="DD/MM/YYYY"
                required
            />
            {startDateError && <div className="error-message">{startDateError}</div>}
        </div>
        <div className="add-project-group">
          <label htmlFor="add-project-skills">Skills Required:</label>
          <Select
            id="skills"
            name='skills'
            isMulti
            className="react-select__control"
            options={predefinedSkills.map((skill) => ({
              value: skill,
              label: skill,
            }))}
            onChange={handleSkillChange}
            onBlur={handleSkillsBlur}
            placeholder="Select skills..."
          />
          {skillsError && <div className="error-message">{skillsError}</div>}
        </div>
        <div className="add-project-group">
          <label htmlFor="add-project-description">Description:</label>
          <textarea
            id="add-project-description"
            className="add-project-input"
            name='description'
            onChange={handleChange}
            rows="4"
            required
          ></textarea>
        </div>
        <button type="submit" className="add-project-button">Add Project</button>
        <button type="button" className="add-project-cancel-button" onClick={handleCancel}>
            Cancel
          </button>
      </form>
    </div>
    </>
  );
}

export default AddProject;
