import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import EmployeeCard from "./EmployeeCard";
import "../CSS/AllEmployeesList.css";
import skillsList from "./skillsList";
import CustomMultipleDropdown from "./CustomMultipleDropdown";
import Button from "./Button";

function AllEmployeesList() {
  const [employees, setEmployees] = useState([]);
  const [popupMessage, setPopupMessage] = useState("");
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [showPopup, setShowPopup] = useState(false);
  const navigate = useNavigate();
  const userRole = localStorage.getItem("role");
  const [searchSkills, setSearchSkills] = useState("");
  const [showUnassignedOnly, setShowUnassignedOnly] = useState(false);
  const [filteredEmployees, setFilteredEmployees] = useState([]);
  const [filteredSkills, setFilteredSkills] = useState([]);

  useEffect(() => {
    async function fetchEmployeeData() {
      try {
        const response = await axios.get(
          "http://localhost:8081/employee/getAllEmployees"
        );
        setEmployees(response.data);
        setFilteredEmployees(response.data);
        console.log(response.data);
      } catch (error) {}
    }

    fetchEmployeeData();
  }, []);

  const handleSearch = () => {
    console.log("function called");
    let filteredSkillsArray = [];

    if (filteredSkills) {
      filteredSkillsArray = filteredSkills.map((skill) => skill.trim());
    }

    let filteredResults = employees;

    if (filteredSkillsArray.length > 0) {
      filteredResults = filteredResults.filter((employee) =>
      filteredSkillsArray.some((filteredSkill) =>
          employee.empSkills.some((skill) =>
            skill.toLowerCase().includes(filteredSkill.toLowerCase())
          )
        )
      );
    }
    if (showUnassignedOnly) {
      filteredResults = filteredResults.filter(
        (employee) => employee.projectName === null
      );
    }

    setFilteredEmployees(filteredResults);
    console.log("Filtered Skills:", filteredSkills);
console.log("Filtered Employees:", filteredResults);
  };

  function handleSkillChange(selectedOptions) {
    // setSelectedSkills(selectedOptions);
    const selectedSkillsValues = selectedOptions.map(option => option.value);
    setFilteredSkills(selectedSkillsValues);
    console.log("Selected Skills:", selectedOptions);
  }

  useEffect(() => {
    handleSearch();
  }, [showUnassignedOnly]);

  const closePopup = () => {
    setShowPopup(false);
  };

  if (userRole === "employee") {
    return <h1>unauthrized access</h1>;
  }
  console.log("m", showUnassignedOnly);

  return (
    <>
      {showPopup && (
        <div className="popup">
          <div className="popup-content">
            <p>{popupMessage}</p>
            <button onClick={closePopup}>Close</button>
          </div>
        </div>
      )}
      {userRole === "manager" && (
        <div className="allemployee-search-form">
          <div className='searching-by-manager-dropdown'>
          <CustomMultipleDropdown
            options={skillsList.map((skill) => ({
              value: skill,
              label: skill,
            }))}
            selectedOptions={filteredSkills.map((skill) => ({
              value: skill,
              label: skill,
            }))}
            onChange={handleSkillChange}
            placeholder="Select Skills"
          />
        </div>
          <label className="checkbox-label">
            <input
              type="checkbox"
              className="checkbox-input"
              onChange={() => {
                setShowUnassignedOnly((prevState) => !prevState);
              }}
              checked={showUnassignedOnly}
            />
            Show Unassigned Only
          </label>
          <button className="search-button" onClick={handleSearch}>
            Search
          </button>
        </div>
      )}

      <div className="content-allemployees">
      {userRole === "admin" && (
        <Button
          text="Requested Resources"
          onClick={() => {
          }}
          className="admindashboard-button"
        />
      )}
        <div className="card-container">
          {Array.isArray(filteredEmployees) && filteredEmployees.length > 0 ? (
            filteredEmployees.map((employee) => (
              <EmployeeCard
                employee={employee}
                key={employee.empId}
                userRole={userRole}
              />
            ))
          ) : (
            <p>No results found.</p>
          )}
        </div>
      </div>
    </>
  );
}

export default AllEmployeesList;
