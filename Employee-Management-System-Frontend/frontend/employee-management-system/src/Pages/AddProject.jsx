import React, { useState, useEffect } from "react";
import "../CSS/AddProjectForm.css";
import Select from "react-select";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Popup from "../Components/Popup";
import CustomMultipleDropdown from "../Components/CustomMultipleDropdown";
import Button from "../Components/Button";
import skillsList from "../Components/skillsList";
import InputField from "../Components/InputField";
import { getRequest, postRequest } from "../Services/Service";
import { ADD_PROJECT, ALL_MANAGER_INFO_PROJECT } from "../Services/url";

function AddProject({ handleTabChange }) {
  const [projectName, setProjectName] = useState("");
  const [errorManagerId, setErrorManagerId] = useState("");
  const [managerId, setManagerId] = useState("");
  const [startDate, setStartDate] = useState("");
  const [skills, setSkills] = useState("");
  const [description, setDescription] = useState("");
  const [descriptionError, setDescriptionError] = useState("");

  const [projectNameError, setProjectNameError] = useState("");
  const [startDateError, setStartDateError] = useState("");
  const [skillsError, setSkillsError] = useState("");
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [selectedManagerId, setSelectedManagerId] = useState(null);
  const [popupMessage, setPopupMessage] = useState("");
  const [showPopup, setShowPopup] = useState(false);
  const [projectAdded, setProjectAdded] = useState(false);

  const [managerList, setManagerList] = useState([]);
  const [projectData, setProjectData] = useState({
    name: "",
    managerEmployeeId: "",
    startDate: "",
    skills: [],
    description: "",
  });

  const navigate = useNavigate();

  const handleCancel = () => {
    handleTabChange("project");
  };

  async function getManagerList() {
    try {
      const res = await getRequest(
        ALL_MANAGER_INFO_PROJECT
      );
      setManagerList(res.data);
    } catch (error) {
    }
  }

  async function apiCall() {
    try {
      const res = await postRequest(
        ADD_PROJECT,
        projectData
      );
      setPopupMessage(res.data.message);
      setShowPopup(true);
      setProjectAdded(true);

      setTimeout(() => {
        handleTabChange("project");
      }, 800);
    } catch (error) {
      setPopupMessage(error.response.data.message);
      setShowPopup(true);
    }
  }

  const handleSkillChange = (selectedOptions) => {
    setSelectedSkills(selectedOptions);
    const selectedSkillValues = selectedOptions.map((option) => option.value);
    setProjectData({ ...projectData, skills: selectedSkillValues });
    validateData("skills", selectedSkillValues);
  };

  function handleChange(event) {
    setProjectData({ ...projectData, [event.target.name]: event.target.value });
    validateData(event.target.name, event.target.value);
  }

  const closePopup = () => {
    setShowPopup(false);
  };

  useEffect(() => {
    getManagerList();
  }, []);

  function handleSubmit(event) {
    event.preventDefault();
    checkEmptyData();
    if (
      !checkErrors() ||
      Object.values(projectData).some((value) => value === "")
    ) {
    } else {
      apiCall();
    }
  }

  function checkEmptyData() {
    if (!projectData.name) {
      setProjectNameError("Project name is required");
    }
    if (!projectData.managerEmployeeId) {
      setErrorManagerId("Manager is required");
    }
    if (!projectData.startDate) {
      setStartDateError("Start date is required");
    }
    if (!projectData.description) {
      setDescriptionError("Description is required");
    }
    if (projectData.skills.length === 0) {
      setSkillsError("Skills are required");
    }
  }

  function validateData(name, value) {
    if (name === "name") {
      const namePattern = /^^[A-Za-z ]+$/;
      if (value.trim() === "") {
        setProjectNameError("Project name is required");
      } else if (!namePattern.test(value)) {
        setProjectNameError("Project Name must contain only letters.");
      } else {
        setProjectNameError("");
      }
    }

    if (name === "managerId") {
      if (value === "") {
        setErrorManagerId("Manager is required");
      } else {
        setErrorManagerId("");
      }
    }

    const datePattern =
      /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/(19|20)\d\d$/;
    if (name === "startDate") {
      if (value === "") {
        setStartDateError("Start date is required");
      } else if (!datePattern.test(value)) {
        setStartDateError("Start Date Should be in form of DD/MM/YYYY");
      } else if (!validDay(value)) {
        setStartDateError("Start Date should only be a future date.");
      } else {
        setStartDateError("");
      }
    }

    if (name === "skills") {
      if (value.length === 0) {
        setSkillsError("Skills are required");
      } else {
        setSkillsError("");
      }
    }

    if (name === "description") {
      if (value === "") {
        setDescriptionError("Description is required");
      } else {
        setDescriptionError("");
      }
    }
  }

  function checkErrors() {
    if (
      projectNameError === "" &&
      errorManagerId === "" &&
      startDateError === "" &&
      skillsError === "" &&
      descriptionError === ""
    ) {
      return true;
    }
    return false;
  }

  const validDay = (inputDate) => {
    const [day, month, year] = inputDate.split("/").map(Number);

    const givenDate = new Date(year, month - 1, day);
    const currentDate = new Date();

    if (givenDate.getTime() <= currentDate.getTime()) {
      return false;
    }
    return true;
  };

  return (
    <>
      {showPopup && <Popup message={popupMessage} onClose={closePopup} />}
      <div className="add-project-container">
        <form onSubmit={handleSubmit}>
          <div className="add-project-group">
            <InputField
                label="Project Name:"
                type="text"
                name="name"
                id="add-project-name"
                onChange={handleChange}
                className="add-project-input"
              />
            {projectNameError && (
              <div className="error-message">{projectNameError}</div>
            )}
          </div>
          <div className="add-project-group">
             <label htmlFor="add-project-manager">Manager :</label>
            <select
              id="add-project-manager"
              name="managerEmployeeId"
              type="select"
              className="add-project-select"
              onChange={handleChange}
            >

              <option value="">Select Manager</option>
              {managerList.map((manager) => {
                return (
                  <option key={manager.id} value={manager.id}>
                    {manager.managerEmployeeId} - {manager.managerName}
                  </option>
                );
              })}
            </select>
            {errorManagerId && (
              <div className="error-message">{errorManagerId}</div>
            )}
          </div>
          <div className="add-project-group">
             <InputField
                label="Start Date (DD/MM/YYYY):"
                type="text"
                name="startDate"
                id="add-project-start-date"
                onChange={handleChange}
                placeholder="DD/MM/YYYY"
                className="add-project-input"
              />
            {startDateError && (
              <div className="error-message">{startDateError}</div>
            )}
          </div>
          <div className="add-project-group">
            <label htmlFor="add-project-skills">Skills Required:</label>
            <CustomMultipleDropdown
              options={skillsList.map((skill) => ({
                value: skill,
                label: skill,
              }))}
              selectedOptions={selectedSkills}
              onChange={handleSkillChange}
              placeholder="Select skills..."
              id="skills"
              customClassName="react-select__control"
            />

            {skillsError && <div className="error-message">{skillsError}</div>}
          </div>
          <div className="add-project-group">
            <label htmlFor="add-project-description">Description:</label>
            <textarea
              id="add-project-description"
              className="add-project-input"
              name="description"
              onChange={handleChange}
              rows="4"
            ></textarea>
            {descriptionError && (
              <div className="error-message">{descriptionError}</div>
            )}
          </div>
          <Button
            type="submit"
            className="add-project-button"
            text="Add Project"
          />
          <Button
            type="button"
            className="add-project-cancel-button"
            text="Cancel"
            onClick={handleCancel}
          />
        </form>
      </div>
    </>
  );
}

export default AddProject;
