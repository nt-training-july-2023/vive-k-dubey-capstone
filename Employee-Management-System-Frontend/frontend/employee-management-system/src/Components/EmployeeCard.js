import React from 'react';
import { useNavigate } from 'react-router-dom';



function EmployeeCard({ employee }) {

     const navigate = useNavigate();

    const handleAssignProjectClick = () => {
        navigate('/assign-project');
      };


  return (
    <div className="card" key={employee.empId}>
      <div className="left-section">
        <div className="employee-name larger">{employee.empName}</div>
        <div className="field smaller">{employee.empDesignation}</div>
        <div className="field"><span style={{ fontWeight: 600 }}>Project Name:</span> N/A</div>
        <div className="field"><span style={{ fontWeight: 600 }}>Manager:</span> {employee.managerName}</div>
        <div className="field"><span style={{ fontWeight: 600 }}>Contact:</span> {employee.empContactNo}</div>
        <div className="field"><span style={{ fontWeight: 600 }}>Email:</span> {employee.empEmail}</div>
      </div>
      <div className="right-section">
        <div className="field emp-id"><span style={{ fontWeight: 600 }}>Employee ID:</span> {employee.empId}</div>
        <div className="field"><span style={{ fontWeight: 600 }}>DOB:</span> {employee.empDOB}</div>
        <div className="field"><span style={{ fontWeight: 600 }}>DOJ:</span> {employee.empDOJ}</div>
        <div className="field"><span style={{ fontWeight: 600 }}>Location:</span> {employee.empLocation}</div>
        {employee.projectId === null && (
          <button className="assign-button" onClick={handleAssignProjectClick}>
            Assign Project
          </button>
        )}
      </div>
    </div>
  );
}

export default EmployeeCard;
