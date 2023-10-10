import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Popup from "./Popup";
import axios from "axios";
import Button from "./Button";
import { postRequest, putRequest, putRequestWithoutPayload } from "../Services/Service";
import { IS_REQUESTED_STATUS, UNASSIGN_PROJECT } from "../Services/url";

function EmployeeCard({ employee, userRole, fetchEmployeeData }) {
  const navigate = useNavigate();
  const managerEmail = localStorage.getItem("userEmail");
  const [showUnassignPopup, setShowUnassignPopup] = useState(false);
  const [isRequested, setIsRequested] = useState("false");

  async function getIsRequested() {
    try {
      const reqData = {
        empId: employee.empId,
        managerEmail: managerEmail,
      };
      const res = await postRequest(
        IS_REQUESTED_STATUS,
        reqData
      );
      setIsRequested(res.data.requested);
    } catch (error) {}
  }

  useEffect(() => {
    getIsRequested();
  }, []);

  const handleClick = () => {
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

  const handleUnassignConfirmation = () => {
    setShowUnassignPopup(true);
  };

  async function handleUnassign(empId) {
    try {
      const response = await putRequestWithoutPayload(
        UNASSIGN_PROJECT + empId
      );
      fetchEmployeeData();
    } catch (error) {

    }
  }

  const handleUnassignProject = (empId) => {
    handleUnassign(empId);
    setShowUnassignPopup(false);
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

        {employee.projectId === null &&
          (userRole === "manager" && isRequested ? (
            <Button
              className="assign-button"
              text="Requested"
              onClick={handleClick}
              isDisabled={true}
            />
          ) : (
            userRole === "manager" && (
              <Button
                className="assign-button"
                text="Request Resource"
                onClick={handleClick}
              />
            )
          ))}

        {employee.projectId === null && userRole === "admin" && (
          <Button
            className="assign-button"
            text="Assign Project"
            onClick={handleClick}
          />
        )}
        {employee.projectId !== null && userRole === "admin" && (
          <>
            <Button
              className="assign-button"
              text=" Unassign Project"
              onClick={handleUnassignConfirmation}
            />
            {showUnassignPopup && (
              <Popup
                message={`Are you sure you want to unassign ${employee.empName}'s project?`}
                confirmText="OK"
                onClose={() => setShowUnassignPopup(false)}
                onConfirm={() => handleUnassignProject(employee.empId)}
              />
            )}
          </>
        )}
      </div>
    </div>
  );
}

export default EmployeeCard;
