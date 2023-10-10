import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import InputField from "../Components/InputField.js";
import axios from "axios";
import "../CSS/RequestResource.css";
import Popup from "../Components/Popup.js";
import Button from "../Components/Button.js";
import Dropdown from "../Components/Dropdown.js";
import { getRequest, postRequest } from "../Services/Service.jsx";
import { CREATE_REQUEST, GET_ALL_PROJECT_BY_MANAGER_EMAIL } from "../Services/url.jsx";
import Unauthorized from "../Components/Unauthorized.js";

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
    try{
    const res = await getRequest(
      GET_ALL_PROJECT_BY_MANAGER_EMAIL + managerEmail
    );
    setProjectList(res.data);}
    catch(error){

      const resMessage = {};
      resMessage.message = error.response.data.message;
      setShowPopUp(true);
      setPopUpMessage(resMessage);

    }
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

  function handleCancel(event) {
    navigate("/managerdashboard");
  }

  function handleChange(event) {
    if (event.target.value === "") {
      setErrorProject("Project is Required");
    } else {
      setErrorProject("");
    }
    setProjectId(event.target.value);
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
      const res = await postRequest(
        CREATE_REQUEST,
        reqData
      );
      navigate("/managerdashboard");
    } catch (error) {
      const resMessage = {};
      resMessage.message = error.response.data.message;
      setShowPopUp(true);
      setPopUpMessage(resMessage);
    }
  }
  function handleSubmit(event) {
    event.preventDefault();

    validateData();

    if (!checkErrors() || projectId === "" || comment === "") {
      return;
    } else {
      apiCall();
    }
  }

  const closePopup = () => {
    setShowPopUp(false);
  };

  if (!userRole) {
    navigate("/");
  }

  if (userRole !== "manager") {
    return <Unauthorized/>;
  }

  return (
    <>
      {showPopUp && (
        <Popup message={popUpMessage.message} onClose={closePopup} />
      )}
      <div className="assign-project-container">
        <div className="assign-project-header">Request Resource</div>
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
                <option key={project.id} value={project.id}>
                  {project.id}-{project.projectName}
                </option>
              );
            })}
          </select>

          <div></div>
          {errorProject && (
            <div>
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
            <div>
              <p className="request-error-message">{errorComment}</p>
            </div>
          )}
        </div>
        <Button
          className="resource-request-button"
          text="Request Resource"
          onClick={handleSubmit}
        />
        <Button
          className="resource-request-button-cancel"
          text="Cancel"
          onClick={handleCancel}
        />
      </div>
    </>
  );
}

export default AssignProject;
