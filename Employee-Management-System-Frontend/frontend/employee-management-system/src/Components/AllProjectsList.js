import React, { useEffect, useState } from 'react';
import axios from 'axios'; // Import Axios for making API requests
import '../CSS/AllProjectsList.css';

function ProjectsPage() {
  const [projects, setProjects] = useState([]); // Initialize projects state as an empty array

  useEffect(() => {
    // Make an API request to fetch project data
    axios.get('http://localhost:8081/employee/getAllProjects')
      .then((response) => {
        const responseData = response.data.data; // Assuming the project data is in response.data.data
        setProjects(responseData); // Update the projects state with the fetched data
      })
      .catch((error) => {
        console.error('Error fetching project data:', error);
      });
  }, []); // Empty dependency array means this effect runs once on component mount

  return (
    <div className="projects-container">
      {projects.map((project) => (
        <div key={project.managerEmployeeId} className="project-card">
          <div className="project-name larger">{project.name}</div>
          <div className="project-head smaller">Head: {project.head}</div>
          <div className="project-id larger">Project ID: {project.projectId}</div>
          <div className="start-date smaller">Start Date: {project.startDate}</div>
          <div className="project-description">
              <span style={{ fontWeight: 600 }}>Project Description:</span> {project.description}
        </div>
          <div className="project-team">
            Team Members: Abhay, Ashish, Praveen
          </div>
          <div className="project-skills">
            Skills: {project.skills.join(', ')}
          </div>
        </div>
      ))}
    </div>
  );
}

export default ProjectsPage;
