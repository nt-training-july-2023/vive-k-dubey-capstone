import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import '../CSS/UpdateSkills.css';

function UpdateSkillsPage() {
  const [currentSkills, setCurrentSkills] = useState([]);
  const [selectedSkill, setSelectedSkill] = useState('');
  const navigate = useNavigate();
  const predefinedSkills = [
    'JavaScript',
    'React',
    'Node.js',
    'Python',
    'Java',
    'Spark',
    'HTML',
    'CSS',
    'Big Data',
    'Spring Boot',
    'Postgres',
    'Snowflake',
    'Airflow',
    'SQL',
    'Machine Learning',
    'Data Analysis',
  ];

 
  const location = useLocation();
  const skillsData = location.state;

  useEffect(() => {
    if (skillsData && skillsData.currentSkills) {
      setCurrentSkills(skillsData.currentSkills);
    }
  }, [skillsData]);

  
  const availableSkills = predefinedSkills.filter((skill) => !currentSkills.includes(skill));

  
  const handleSkillSelect = (event) => {
    setSelectedSkill(event.target.value);
  };
  const handleAddSkill = () => {
    if (selectedSkill) {
      setCurrentSkills([...currentSkills, selectedSkill]);
      setSelectedSkill(''); 
    }
  };

  const handleRemoveSkill = (skillToRemove) => {
    const updatedSkills = currentSkills.filter((skill) => skill !== skillToRemove);
    setCurrentSkills(updatedSkills);
  };
  const updateSkills = async () => {
    try {
      const email = localStorage.getItem('userEmail');
      const response = await fetch(`http://localhost:8081/api/employee/updateskills`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          empEmail: email,
          empSkills: currentSkills, 
        }),
      });

      if (response.ok) {
        console.log('Skills updated successfully');
        navigate('/userdashboard');
      } else {
        console.error('Failed to update skills');
      }
    } catch (error) {
      console.error('Error updating skills:', error);
    }
  };

  return (
    <div className="update-skills-container">
      <div className="update-skills-current">
        <h3 className="update-skills-subheading">Current Skills</h3>
        {currentSkills.map((skill) => (
          <div key={skill} className="current-skill-item">
            {skill}
            <button onClick={() => handleRemoveSkill(skill)} className="remove-skill-button">
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
        <button onClick={handleAddSkill} className="add-skill-button">
          Add Skill
        </button>
      </div>
      <button onClick={updateSkills} className="update-button" disabled={currentSkills.length === 0}>
        Update Skills
      </button>
      
    </div>
  );
}

export default UpdateSkillsPage;
