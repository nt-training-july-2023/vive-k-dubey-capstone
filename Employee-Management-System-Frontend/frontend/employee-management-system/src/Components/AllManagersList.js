import React, { useState, useEffect } from 'react';
import '../CSS/AllManagersList.css';

function AllManagersList() {
  const [managers, setManagers] = useState([]);

  useEffect(() => {
    
    fetch('http://localhost:8081/employee/getAllManagers')
      .then((response) => response.json())
      .then((data) => {
        
        setManagers(data);
      })
      .catch((error) => {
        console.error('Error fetching managers:', error);
      });
  }, []);

  return (
    <div className="content">
      <div className="card-container">
        {managers.map((manager, index) => (
          <div className="card" key={index}>
            {console.log(index)}
            <div className="left-section">
              <div className="employee-name larger">{manager.empName}</div>
              <div className="field smaller">{manager.empDesignation}</div>
              <div className="field">
                <select id="projectName">
                    <option value="Actual Project"> Project</option>
                    <option value="Project 1">Project 1</option>
                    <option value="Project 2">Project 2</option>
                </select>
            </div>
            {/* <div className="field">
                <select id="projectName">
                  <option value="Actual Project">Project</option>
                  {manager.projectNames.map((projectName, projectIndex) => (
                    <option key={projectIndex} value={projectName}>
                      {projectName}
                    </option>
                  ))}
                </select>
              </div> */}
              <div className="field">Contact: {manager.empContactNo}</div>
              <div className="field">Email: {manager.empEmail}</div>
              <div className="field">Location: {manager.empLocation}</div>
            </div>
            <div className="right-section">
              <div className="employee-id ">Employee ID: {manager.empId}</div>
              {/* <div className="field">Skills: {manager.empSkills.join(', ')}</div> */}
              <div className="field">Skills: React, Java</div>
              <div className="field">Team: {manager.managerName}</div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default AllManagersList;
