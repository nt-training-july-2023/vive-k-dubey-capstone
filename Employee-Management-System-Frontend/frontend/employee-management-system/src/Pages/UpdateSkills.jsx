import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../CSS/UpdateSkills.css";
import Button from "../Components/Button";
import skillsList from "../Components/skillsList";
import axios from 'axios';
import { postRequest } from "../Services/Service";
import { UPDATE_SKILLS_OF_USER } from "../Services/url";
import Unauthorized from "../Components/Unauthorized";
const userRole = localStorage.getItem("role");

function UpdateSkillsPage() {
  const [currentSkills, setCurrentSkills] = useState([]);
  const [selectedSkill, setSelectedSkill] = useState("");
  const navigate = useNavigate();

  const location = useLocation();
  const skillsData = location.state;

  useEffect(() => {
    if (skillsData && skillsData.currentSkills) {
      setCurrentSkills(skillsData.currentSkills);
    }
  }, [skillsData]);

  const availableSkills = skillsList.filter(
    (skill) => !currentSkills.includes(skill)
  );

  const handleSkillSelect = (event) => {
    setSelectedSkill(event.target.value);
  };
  const handleAddSkill = () => {
    if (selectedSkill) {
      setCurrentSkills([...currentSkills, selectedSkill]);
      setSelectedSkill("");
    }
  };

  const handleRemoveSkill = (skillToRemove) => {
    const updatedSkills = currentSkills.filter(
      (skill) => skill !== skillToRemove
    );
    setCurrentSkills(updatedSkills);
  };
  const updateSkills = async () => {
    try {
      
      const email = localStorage.getItem("userEmail");
      const payload = {
        empEmail: email,
        empSkills: currentSkills,
      };
      const response = await postRequest(
        UPDATE_SKILLS_OF_USER,
        payload
      );
    
      if (response.status === 200) {
        navigate('/userdashboard');
      }
    } catch (error) {
    }
  };

  if (!userRole) {
    navigate("/");
  }

  if (userRole === "admin" || userRole ==="manager") {
    return <Unauthorized/>;
  }

  return (
    <div className="update-skills-container">
      <div className="update-skills-current">
        <h3 className="update-skills-subheading">Current Skills</h3>
        {currentSkills.map((skill) => (
          <div key={skill} className="current-skill-item">
            {skill}
            <Button
               className="remove-skill-button"
              text="Remove"
              onClick={() => handleRemoveSkill(skill)}
            />
          </div>
        ))}
      </div>

      <div className="update-skills-add">
        <h3 className="update-skills-subheading">Add New Skills</h3>
        <select
          value={selectedSkill}
          onChange={handleSkillSelect}
          className="skill-select"
        >
          <option value="">Select a skill</option>
          {availableSkills.map((skill) => (
            <option key={skill} value={skill}>
              {skill}
            </option>
          ))}
        </select>

        {currentSkills.length === 0 && (
          <p className="error-message">Select at least one skill</p>
        )}

        <Button
          className="add-skill-button"
          text="Add Skill"
          onClick={handleAddSkill}
        />
      </div>

      <Button
        className="update-button"
        text="Update Skills and Go back"
        onClick={updateSkills}
        isDisabled={currentSkills.length === 0}
      />
    </div>
  );
}

export default UpdateSkillsPage;
