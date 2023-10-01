import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../CSS/UpdateSkills.css";
import Button from "../Components/Button";
import skillsList from "../Components/skillsList";
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
      const response = await fetch(
        `http://localhost:8081/api/employee/updateskills`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            empEmail: email,
            empSkills: currentSkills,
          }),
        }
      );

      if (response.ok) {
        navigate("/userdashboard");
      }
    } catch (error) {}
  };

  if (userRole !== "employee") {
    return <h1>unauthrized access</h1>;
  }

  return (
    <div className="update-skills-container">
      <div className="update-skills-current">
        <h3 className="update-skills-subheading">Current Skills</h3>
        {currentSkills.map((skill) => (
          <div key={skill} className="current-skill-item">
            {skill}
            <button
              onClick={() => handleRemoveSkill(skill)}
              className="remove-skill-button"
            >
              Remove
            </button>
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
