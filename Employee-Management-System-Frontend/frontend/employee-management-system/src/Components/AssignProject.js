// AssignProject.js
import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import "../CSS/AssignProject.css";

function AssignProject() {
  const [selectedProject, setSelectedProject] = useState("");
  const [employeeName] = useState("Employee Name");
  const userRole = localStorage.getItem("role");
  const [projectList, setProjectList] = useState([]);
  const [projectId, setProjectId] = useState("");
  const navigate = useNavigate();
  const location = useLocation();
  const empData = location.state;
  const [errorProject, setErrorProject] = useState("");

  async function getProjectList() {
    const res = await axios.get(
      "http://localhost:8081/employee/getAllProjects"
    );
    setProjectList(res.data);
  }
  useEffect(() => {
    getProjectList();
  }, []);

  useEffect(() => {}, [projectList]);

  function validateData() {
    if (projectId === "") {
      setErrorProject("Project is Required");
    } else {
      setErrorProject("");
    }
  }
  function checkErrors() {
    if (errorProject === "") {
      return true;
    }
    return false;
  }

  function handleChange(event) {
    if (event.target.value === "") {
      setErrorProject("Project is Required");
    } else {
      setErrorProject("");
    }
    setProjectId(event.target.value);
    console.log("Project Id", event.target.value);
  }

  async function apiCall() {
    try {
      const reqData = {
        empId: empData.empId,
        projectId: projectId,
      };
      const res = await axios.post(
        "http://localhost:8081/employee/assignProject",
        reqData
      );
      console.log(res.data);
      navigate("/admin-dashboard");
    } catch (error) {
      console.log(error);
    }
  }

  function handleSubmit(event) {
    event.preventDefault();
    validateData();
    console.log(projectId);
    if (checkErrors()) {
      console.log("Submitting data");
      console.log("ProjectId", projectId, "empData", empData);
      apiCall();
    } else {
      console.log("Not working");
    }
  }
  console.log("project list", projectList);

  if (userRole !== "admin") {
    return <h1>unauthrized access</h1>;
  }

  return (
    <div className="assign-project-container">
      <div className="assign-project-header">Assign Project</div>
      <div className="manager-name">{empData.empName}</div>
      <div className="project-dropdown-container">
        <label htmlFor="project-select" className="project-label">
          Project:
        </label>
        <select
          id="project-select"
          className="project-select"
          placeholder="Enter Designation"
          onChange={handleChange}
        >
          <option value="">Select Project</option>
          {projectList.map((project) => {
            return (
              <option key={project.projectId} value={project.projectId}>
                {project.projectId}-{project.name}
              </option>
            );
          })}
        </select>
        {errorProject && <div className="error-message">{errorProject}</div>}
      </div>
      <button className="assign-project-button" onClick={handleSubmit}>
        Assign Project
      </button>
    </div>
  );
}

export default AssignProject;
