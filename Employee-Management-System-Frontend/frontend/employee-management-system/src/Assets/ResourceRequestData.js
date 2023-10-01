import "../CSS/RequestedResource.css";
import Button from "../Components/Button";
import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Popup from "../Components/Popup";

export default function ResourceRequestData({
  employeeName,
  projectName,
  managerName,
  comment,
  id,
  refreshData,
  employeeId,
  managerId,
  projectId,
}) {
  const [popUpMessage, setPopUpMessage] = useState({});
  const [showPopUp, setShowPopUp] = useState(false);

  const handleAccept = async () => {
    try {
      const response = await axios.post(
        `http://localhost:8081/request/accept/${id}`
      );
      refreshData();
    } catch (error) {
      setShowPopUp(true);
      setPopUpMessage(error.response.data.message);
    }
  };

  async function rejectResourceRequest() {
    try {
      const res = await axios.delete(
        `http://localhost:8081/requestResource/reject/${id}`
      );
      refreshData();
    } catch (error) {
      setShowPopUp(true);
      setPopUpMessage(error.response.data.message);
    }
  }
  return (
    <>
      {showPopUp && (
        <Popup
          message={popUpMessage}
          onClose={() => {
            setShowPopUp(false);
          }}
        />
      )}
      <tr>
        <td>{employeeName}</td>
        <td>{projectName}</td>
        <td>{managerName}</td>
        <td>{comment}</td>
        <td>
          <Button
            text="Accept"
            onClick={handleAccept}
            className="action-button accept-button"
          />

          <Button
            text="Reject"
            onClick={rejectResourceRequest}
            className="action-button reject-button"
          />
        </td>
      </tr>
    </>
  );
}
