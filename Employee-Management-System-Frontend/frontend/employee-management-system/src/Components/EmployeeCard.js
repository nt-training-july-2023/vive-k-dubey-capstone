import React from 'react';
import { useNavigate } from 'react-router-dom';



function EmployeeCard({ employee, userRole }) {

     const navigate = useNavigate();
     const buttonText = userRole === 'manager' ? 'Request Resource' : 'Assign Project';

     const handleAssignProjectClick = () => {
        if (userRole === 'manager') {
          
          alert('Resource request action for managers');
        } else {
          navigate('/assign-project', { state: { empId: employee.empId, empName: employee.empName } });
        }
      };


  return (
    <div className="card" key={employee.empId}>
      <div className="left-section-allemployees">
        <div className="employee-name larger">{employee.empName}</div>
        <div className="field smaller">{employee.empDesignation}</div>
        {(userRole === 'manager' || userRole === 'admin') && (
            <div className="field">
                <span style={{ fontWeight: 600 }}>Project Name:</span> 
                {employee.projectName ? employee.projectName : 'N/A'}
            </div>
            )}
        <div className="field">
            <span style={{ fontWeight: 600 }}>Manager:</span> 
            {employee.managerName ? employee.managerName : 'N/A'}
        </div>
        <div className="field"><span style={{ fontWeight: 600 }}>Contact:</span> {employee.empContactNo}</div>
        <div className="field"><span style={{ fontWeight: 600 }}>Email:</span> {employee.empEmail}</div>
      </div>
      <div className="right-section">
        <div className=" emp-id"><span style={{ fontWeight: 600 }}>Employee ID:</span> {employee.empId}</div>
        <div className="field"><span style={{ fontWeight: 600 }}>DOB:</span> {employee.empDOB}</div>
        <div className="field"><span style={{ fontWeight: 600 }}>DOJ:</span> {employee.empDOJ}</div>
        <div className="field"><span style={{ fontWeight: 600 }}>Location:</span> {employee.empLocation}</div>
        {userRole === 'manager' && (
            <div className="field">
                <span style={{ fontWeight: 600 }}>Skills:</span> 
                {employee.empSkills.join(', ')}
            </div>
            )}
        {employee.projectId === null && (userRole === 'manager' || userRole === 'admin') &&  (
          <button className="assign-button" onClick={handleAssignProjectClick}>
             {buttonText}
          </button>
        )}
      </div>
    </div>
  );
}

export default EmployeeCard;
