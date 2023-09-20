import React, { useEffect, useState } from 'react';
import axios from 'axios'; 
import '../CSS/AllProjectsList.css';

function ProjectsPage() {
  const [projects, setProjects] = useState([]); 

  useEffect(() => {

    axios.get('http://localhost:8081/employee/getAllProjects')
      .then((response) => {
        const responseData = response.data; 
        setProjects(responseData); 
      })
      .catch((error) => {
        console.error('Error fetching project data:', error);
      });
  }, []); 

  const [showPopup, setShowPopup] = useState(false);
  const [selectedProject, setSelectedProject] = useState(null);

  const handleReadMoreClick = (project) => {
    setSelectedProject(project);
    setShowPopup(true);
  };

  const handleClosePopup = () => {
    setShowPopup(false);
    setSelectedProject(null);
  };

  return (
    <div className="projects-container">
      {projects.map((project) => (
        <div key={project.managerEmployeeId} className="project-card">
          <div className="project-name larger">{project.name}</div>
          <div className="project-head smaller"><span style={{ fontWeight: 600 }}>Head:</span> {project.head}</div>
          <div className="project-id larger">Project ID: {project.projectId}</div>
          <div className="start-date smaller"><span style={{ fontWeight: 600 }}>Start Date:</span> {project.startDate}</div>
          <div className="project-description">
          <span style={{ fontWeight: 600 }}>Project Description:</span>
            {project.description.length > 50 ? (
              <>
                {project.description.substring(0, 50)}{' '}
                <span className="read-more-link" onClick={() => handleReadMoreClick(project)}>
                  Read More
                </span>
              </>
            ) : (
              project.description
            )}
        </div>
          <div className="project-team">
          <span style={{ fontWeight: 600 }}>Team Members:</span> {project.teamMembers.join(', ')}
          </div>
          <div className="project-skills">
          <span style={{ fontWeight: 600 }}>Skills: </span>{project.skills.join(', ')}
          </div>
        </div>
      ))}

          {showPopup && selectedProject && (
        <div className="popup-allproject">
          <div className="popup-content-allproject">
            <span className="popup-close-allproject" onClick={handleClosePopup}>
              &times;
            </span>
            <h2>Project Description</h2>
            <p>{selectedProject.description}</p>
            <button onClick={handleClosePopup}>Close</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default ProjectsPage;
