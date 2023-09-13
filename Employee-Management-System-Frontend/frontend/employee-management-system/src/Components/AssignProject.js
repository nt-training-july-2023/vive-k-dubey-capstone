// AssignProject.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../CSS/AssignProject.css';

function AssignProject() {
  const [selectedProject, setSelectedProject] = useState('');
  const [employeeName] = useState('Employee Name'); // Replace with actual manager name
  const userRole = localStorage.getItem('role');
  const [projectList, setProjectList] = useState([]);

  async function getProjectList(){

    const res = await axios.get('http://localhost:8081/employee/getAllProjects');
    setProjectList(res.data.data);

    
  }
  useEffect(() => {
    getProjectList();

  }, [])

  useEffect(() => {

  }, [projectList])


  const handleProjectChange = (event) => {
    setSelectedProject(event.target.value);
  };

  const handleAssignProject = () => {
    console.log(`Assigned project ${selectedProject} to ${employeeName}`);
  };

  console.log("project list", projectList);

  if (userRole !== 'admin') {
    return (     
       <h1>unauthrized access</h1>
    );
  }

  return (
    <div className="assign-project-container">
      <div className="assign-project-header">Assign Project</div>
      <div className="manager-name">{employeeName}</div>
      <div className="project-dropdown-container">
        <label htmlFor="project-select" className="project-label">
          Project:
        </label>
        <select
          id="project-select"
          className="project-select"
          value={selectedProject}
          onChange={handleProjectChange}
        >
          <option value="">Select Project</option>
          {/* Replace with actual project options */}
          <option value="project1">Project 1</option>
          <option value="project2">Project 2</option>
          <option value="project3">Project 3</option>
        </select>
      </div>
      <button className="assign-project-button" onClick={handleAssignProject}>
        Assign Project
      </button>
    </div>
  );
}

export default AssignProject;
