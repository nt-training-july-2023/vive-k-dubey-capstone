import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import EmployeeCard from "../Components/EmployeeCard";
import "../CSS/AllEmployeesList.css";
import skillsList from "../Components/skillsList";
import CustomMultipleDropdown from "../Components/CustomMultipleDropdown";
import Button from "../Components/Button";
import Popup from "../Components/Popup";
import InputField from "../Components/InputField";
import { getRequest, postRequest } from "../Services/Service";
import { ALL_EMPLOYEE, GET_ALL_FILTERED_EMPLOYEE } from "../Services/url";
import Unauthorized from "../Components/Unauthorized";

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
        const response = await getRequest(
          ALL_EMPLOYEE
        );
        setEmployees(response.data);
        setFilteredEmployees(response.data);
      } catch (error) {}
    }

    fetchEmployeeData();
  }, []);

  async function fetchEmployeeData() {
    try {
      const response = await getRequest(
        ALL_EMPLOYEE
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
      const res = await postRequest(
        GET_ALL_FILTERED_EMPLOYEE,
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
    return <Unauthorized/>;
  }

  if (!userRole) {
    return <Unauthorized/>;
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
            <p>No employees found.</p>
          )}
        </div>
      </div>
    </>
  );
}

export default AllEmployeesList;
