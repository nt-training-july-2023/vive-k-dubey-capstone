import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function EmployeeCard({ employee, userRole }) {
  const navigate = useNavigate();
  const managerEmail = localStorage.getItem("userEmail");
  const [isRequested, setIsRequested] = useState("false");
  const buttonText =
    userRole === "manager" ? "Request Resource" : "Assign Project";

  async function getIsRequested() {
    try {
      const reqData = {
        empId: employee.empId,
        managerEmail: managerEmail,
      };
      const res = await axios.post(
        "http://localhost:8081/requestResource/isRequested",
        reqData
      );
      console.log(employee.empId, res.data);
      setIsRequested(res.data.requested);
    } catch (error) {
      console.log(error);
    }
  }

  const handleAssignProjectClick = () => {
    if (userRole === "manager") {
      navigate("/requestResource", {
        state: { empId: employee.empId, empName: employee.empName },
      });
    } else {
      navigate("/assign-project", {
        state: { empId: employee.empId, empName: employee.empName },
      });
    }
  };

  return (
    <div className="card" key={employee.empId}>
      <div className="left-section-allemployees">
        <div className="employee-name larger">{employee.empName}</div>
        <div className="field smaller">{employee.empDesignation}</div>
        {(userRole === "manager" || userRole === "admin") && (
          <div className="field">
            <span style={{ fontWeight: 600 }}>Project Name:</span>
            {employee.projectName ? employee.projectName : "N/A"}
          </div>
        )}
        <div className="field">
          <span style={{ fontWeight: 600 }}>Manager:</span>
          {employee.managerName ? employee.managerName : "N/A"}
        </div>
        <div className="field">
          <span style={{ fontWeight: 600 }}>Contact:</span>{" "}
          {employee.empContactNo}
        </div>
        <div className="field">
          <span style={{ fontWeight: 600 }}>Email:</span> {employee.empEmail}
        </div>
        {/* {employee.projectId === null && (userRole === 'manager' || userRole === 'admin') &&  (
          <button className="assign-button" onClick={handleAssignProjectClick}>
             {buttonText}
          </button>
        )} */}
      </div>
      <div className="right-section">
        <div className=" emp-id">
          <span style={{ fontWeight: 600 }}>Employee ID:</span> {employee.empId}
        </div>
        <div className="field">
          <span style={{ fontWeight: 600 }}>DOB:</span> {employee.empDOB}
        </div>
        <div className="field">
          <span style={{ fontWeight: 600 }}>DOJ:</span> {employee.empDOJ}
        </div>
        <div className="field">
          <span style={{ fontWeight: 600 }}>Location:</span>{" "}
          {employee.empLocation}
        </div>
        {userRole === "manager" && (
          <div className="field">
            <span style={{ fontWeight: 600 }}>Skills:</span>
            {employee.empSkills.join(", ")}
          </div>
        )}
        {/* {employee.projectId === null &&
          (userRole === "manager" || userRole === "admin") && (
            <button
              className="assign-button"
              onClick={handleAssignProjectClick}
            >
              {buttonText}
            </button>
          )} */}

        {employee.projectId === null &&
          (userRole === "manager" && isRequested ? (
            <button
              className="assign-button"
              onClick={handleAssignProjectClick}
              disabled
            >
              Requested
            </button>
          ) : ( userRole === "manager" &&
            <button
              className="assign-button"
              onClick={handleAssignProjectClick}
            >
              Request Resource
            </button>
          ))}

        {employee.projectId === null && userRole === "admin" && (
          <button className="assign-button" onClick={handleAssignProjectClick}>
            Assign Project
          </button>
        )}
      </div>
    </div>
  );
}

export default EmployeeCard;
