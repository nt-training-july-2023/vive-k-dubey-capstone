import React, { useState, useEffect } from "react";
import "../CSS/AllManagersList.css";
import ManagerCard from "./ManagerCard.js";
import axios from "axios";

function AllManagersList() {
  const [managerList, setmanagerList] = useState([]);
  async function apicall() {
    try {
      const res = await axios.get(
        "http://localhost:8081/employee/getAllManagers"
      );
      setmanagerList(res.data);
    } catch (error) {}
  }

  useEffect(() => {
    apicall();
  }, []);

  return (
    <div className="content-all-managers">
      <div className="card-container">
        {managerList.length > 0 ? (
          managerList
            .sort(function (a, b) {
              return a.empName.localeCompare(b.empName);
            })
            .map((manager) => (
              <ManagerCard key={manager.id} manager={manager} />
            ))
        ) : (
          <p>No managers available.</p>
        )}
      </div>
    </div>
  );
}

export default AllManagersList;
