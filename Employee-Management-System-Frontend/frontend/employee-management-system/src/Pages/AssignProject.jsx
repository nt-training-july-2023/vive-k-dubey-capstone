import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import "../CSS/AssignProject.css";
import Button from "../Components/Button";
import { getRequest, postRequest } from "../Services/Service";
import { ASSIGN_PROJECT, GET_ALL_PROJECT_FOR_ASSIGN } from "../Services/url";
import Unauthorized from "../Components/Unauthorized";
function AssignProject() {
  const [selectedProject, setSelectedProject] = useState("");
  const [employeeName] = useState("Employee Name");
  const userRole = localStorage.getItem("role");
  const [projectList, setProjectList] = useState([]);
  const [projectId, setProjectId] = useState("");
  const navigate = useNavigate();
  const location = useLocation();
  const empData = location.state;
  const [errorProject, setErrorProject] = useState("error");

  async function getProjectList() {
    const res = await getRequest(GET_ALL_PROJECT_FOR_ASSIGN);
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
  }

  function handleCancel(event) {
    navigate("/admin-dashboard");
  }

  async function apiCall() {
    if (!errorProject) {
      try {
        const reqData = {
          empId: empData.empId,
          projectId: projectId,
        };
        const res = await postRequest(ASSIGN_PROJECT, reqData);
        navigate("/admin-dashboard");
      } catch (error) {}
    }
  }

  function handleSubmit(event) {
    event.preventDefault();
    validateData();
    if (checkErrors()) {
      apiCall();
    }
  }

  if (!userRole) {
    navigate("/");
  }

  if (userRole !== "admin") {
    return <Unauthorized />;
  }

  return (
    <div className="assign-project-container">
      <div className="manager-name">{empData && empData.empName}</div>
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
          {projectList &&
            projectList.length > 0 &&
            projectList.map((project) => {
              return (
                <option key={project.projectId} value={project.projectId}>
                  {project.projectId}-{project.name}
                </option>
              );
            })}
        </select>
        {errorProject && errorProject !== "error" && (
          <div className="error-message">{errorProject}</div>
        )}
      </div>

      <Button
        className="assign-project-button"
        text="Assign Project"
        onClick={handleSubmit}
      />

      <Button
        className="assign-project-button-cancel"
        text="Cancel"
        onClick={handleCancel}
      />
    </div>
  );
}

export default AssignProject;
