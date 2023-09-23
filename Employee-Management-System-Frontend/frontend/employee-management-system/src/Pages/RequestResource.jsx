// AssignProject.js
import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import InputField from "../Components/InputField.js";
import axios from "axios";
import "../CSS/RequestResource.css";

function AssignProject() {
  const [selectedProject, setSelectedProject] = useState("");
  const [employeeName] = useState("Employee Name");
  const userRole = localStorage.getItem("role");
  const managerEmail = localStorage.getItem("userEmail");
  const [projectList, setProjectList] = useState([]);
  const [projectId, setProjectId] = useState("");
  const navigate = useNavigate();
  const location = useLocation();
  const empData = location.state;
  const [errorProject, setErrorProject] = useState("");
  const [comment, setComment] = useState("");
  const [errorComment, setErrorComment] = useState("");
  const [popUpMessage, setPopUpMessage] = useState({});
  const [showPopUp, setShowPopUp] = useState(false);

  async function getProjectList() {
    const res = await axios.get(
      `http://localhost:8081/getAll/project/byManager/${managerEmail}`
    );
    setProjectList(res.data);
    console.log(projectList);
    console.log("projectid", res.data.id);
  }
  useEffect(() => {
    getProjectList();
  }, []);

  useEffect(() => {}, [projectList]);

  function validateData() {
    if (projectId === "") {
      setErrorProject("Project is required");
    } else {
      setErrorProject("");
    }
    if (comment === "") {
      setErrorComment("Comment is required");
    } else {
      setErrorComment("");
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

  function handleCommentChange(event) {
    if (event.target.value === "") {
      setErrorComment("Comment is Required");
    } else {
      setErrorComment("");
    }
    setComment(event.target.value);
  }

  async function apiCall() {
    try {
      const reqData = {
        empId: empData.empId,
        projectId: projectId,
        managerEmail: managerEmail,
        comment: comment,
      };
      const res = await axios.post(
        "http://localhost:8081/requestResource/create",
        reqData
      );
      console.log(res.data);
      navigate("/managerdashboard");
      console.log("Working request resource");
    } catch (error) {
      console.log(error.response.data.message);
      const resMessage = {};
      resMessage.message = error.response.data.message;
      setShowPopUp(true);
      setPopUpMessage(resMessage);
    }
  }
  function handleSubmit(event) {
    event.preventDefault();

    validateData();
    console.log(projectId);

    if (!checkErrors() || projectId === "" || comment === "") {
      console.log("Not working");
    } else {
      console.log("Submitting data");
      console.log("ProjectId", projectId, "empData", empData);
      apiCall();
    }
  }
  console.log("project list", projectList);

  const closePopup = () => {
    setShowPopUp(false);
  };

  if (userRole !== "manager") {
    return <h1>unauthrized access</h1>;
  }

  return (
    <>
      {showPopUp && (
        <div className="popup">
          <div className="popup-content">
            <p>{popUpMessage.message}</p>
            <button onClick={closePopup}>Close</button>
          </div>
        </div>
      )}
      <div className="assign-project-container">
        <div className="assign-project-header">Request Resource</div>
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
                <option key={project.id} value={project.id}>
                  {project.id}-{project.projectName}
                </option>
              );
            })}
          </select>
          <div>
            
          </div>
          {errorProject && (
      <div >
        <p className="request-error-message">{errorProject}</p>
      </div>
      
    )}
        </div>
        <div className="comment-textarea-container">
          <label htmlFor="comments" className="comment-label">
            Comments:
          </label>
          <textarea
            id="comments"
            className="comment-textarea"
            value={comment}
            onChange={handleCommentChange}
          />
          {errorComment && (
      <div >
        <p className="request-error-message">{errorComment}</p>
      </div>
    )}
        </div>

        <button className="resource-request-button" onClick={handleSubmit}>
          Request Resource
        </button>
      </div>
    </>
  );
}

export default AssignProject;
