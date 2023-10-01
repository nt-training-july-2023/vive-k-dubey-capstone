import React, { useEffect, useState } from "react";
import axios from "axios";
import "../CSS/RequestedResource.css";
import Button from "../Components/Button";
import ResourceRequestData from "../Assets/ResourceRequestData.js";
import { useNavigate } from "react-router-dom";
import Popup from "../Components/Popup";

function RequestedResources() {
  const [requestedResources, setRequestedResources] = useState([]);
  const [popUpMessage, setPopUpMessage] = useState({});
  const userRole = localStorage.getItem("role");
  const [showPopUp, setShowPopUp] = useState(false);
  const [reload, setReload] = useState(false);
  const navigate = useNavigate();

  async function getResourceRequestData() {
    try {
      const res = await axios.get(
        "http://localhost:8081/requestResource/getAll/requests"
      );
      setRequestedResources(res.data);
    } catch (error) {
      setShowPopUp(true);
      setPopUpMessage(error.response.data.message);
    }
  }
  useEffect(() => {
    getResourceRequestData();
  }, []);

  function handleGoBack(event) {
    navigate("/admin-dashboard");
  }

  const closePopup = () => {
    setShowPopUp(false);
    navigate("/admin-dashboard");
  };

  const refreshData = async () => {
    await getResourceRequestData();
  };

  if (userRole !== "admin") {
    return <h1>unauthrized access</h1>;
  }

  return (
    <>
      {showPopUp && <Popup message={popUpMessage} onClose={closePopup} />}
      <div className="requested-resources-container">
        <div className="center-section-request">
          <h1 className="resource-page-heading ">Requested Resources</h1>
          <Button
            className="goback-button"
            text="Go Back"
            onClick={handleGoBack}
          />
        </div>
        <table className="resource-table">
          <thead>
            <tr>
              <th>Employee Name</th>
              <th>Project Name</th>
              <th>Manager Name</th>
              <th>Comment</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {requestedResources.map((resourceRequest) => {
              return (
                <ResourceRequestData
                  employeeName={resourceRequest.employeeName}
                  projectName={resourceRequest.projectName}
                  managerName={resourceRequest.managerName}
                  comment={resourceRequest.comment}
                  employeeId={resourceRequest.employeeId}
                  id={resourceRequest.id}
                  managerId={resourceRequest.managerId}
                  projectId={resourceRequest.projectId}
                  refreshData={refreshData}
                />
              );
            })}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default RequestedResources;
