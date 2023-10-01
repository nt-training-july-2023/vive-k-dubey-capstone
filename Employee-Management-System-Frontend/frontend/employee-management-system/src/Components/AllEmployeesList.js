import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import EmployeeCard from "./EmployeeCard";
import "../CSS/AllEmployeesList.css";
import skillsList from "./skillsList";
import CustomMultipleDropdown from "./CustomMultipleDropdown";
import Button from "./Button";
import Popup from "./Popup";

function AllEmployeesList() {
  const [employees, setEmployees] = useState([]);
  const [popupMessage, setPopupMessage] = useState("");
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [showPopup, setShowPopup] = useState(false);
  const navigate = useNavigate();
  const userRole = localStorage.getItem("role");
  const [showUnassignedOnly, setShowUnassignedOnly] = useState(false);
  const [filteredEmployees, setFilteredEmployees] = useState([]);
  const [filteredSkills, setFilteredSkills] = useState([]);
  const [isChecked, setIsChecked] = useState(false);

  useEffect(() => {
    async function fetchEmployeeData() {
      try {
        const response = await axios.get(
          "http://localhost:8081/employee/getAllEmployees"
        );
        setEmployees(response.data);
        setFilteredEmployees(response.data);
      } catch (error) {}
    }

    fetchEmployeeData();
  }, []);

  async function fetchEmployeeData() {
    try {
      const response = await axios.get(
        "http://localhost:8081/employee/getAllEmployees"
      );
      setEmployees(response.data);
      setFilteredEmployees(response.data);
    } catch (error) {}
  }

  const config = {
    headers: {
      "Content-Type": "application/json",
    },
  };
  async function getFilterEmployee(filterData) {
    try {
      const res = await axios.post(
        "http://localhost:8081/employee/filter",
        filterData,
        config
      );
      setFilteredEmployees(res.data);
    } catch (error) {
      setPopupMessage(error.response.data.message);
      setShowPopup(true);
    }
  }

  function handleSubmit(event) {
    const filterData = {
      skills: filteredSkills,
      checked: isChecked,
    };

    getFilterEmployee(filterData);
    console.log("filterdata", filterData);
  }

  function handleSkillChange(selectedOptions) {
    setSelectedSkills(selectedOptions);
    const selectedSkillsValues = selectedOptions.map((option) => option.value);
    setFilteredSkills(selectedSkillsValues);
  }

  function handleCheckBox() {
    setIsChecked(!isChecked);
  }
  const closePopup = () => {
    setShowPopup(false);
  };

  if (userRole === "employee") {
    return <h1>unauthrized access</h1>;
  }

  return (
    <>
      {showPopup && <Popup message={popupMessage} onClose={closePopup} />}
      {userRole === "manager" && (
        <div className="allemployee-search-form">
          <div className="searching-by-manager-dropdown">
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
              onChange={handleCheckBox}
              checked={isChecked}
            />
            Show Unassigned Only
          </label>
          <Button
            text="Search"
            onClick={handleSubmit}
            className="search-button"
          />
        </div>
      )}

      <div className="content-allemployees">
        {userRole === "admin" && (
          <Button
            text="Requested Resources"
            onClick={() => {
              navigate("/requestedResource");
            }}
            className="admindashboard-button"
          />
        )}
        <div className="card-container">
          {Array.isArray(filteredEmployees) && filteredEmployees.length > 0 ? (
            filteredEmployees
              .sort(function (a, b) {
                return a.empName.localeCompare(b.empName);
              })
              .map((employee) => (
                <EmployeeCard
                  employee={employee}
                  key={employee.empId}
                  userRole={userRole}
                  fetchEmployeeData={fetchEmployeeData}
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
