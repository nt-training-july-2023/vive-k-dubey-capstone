import React, { useState, useEffect } from "react";
import "../CSS/AllManagersList.css";
import ManagerCard from "../Components/ManagerCard.js";
import axios from "axios";
import { getRequest } from "../Services/Service";
import { ALL_MANAGER } from "../Services/url";
import Unauthorized from "../Components/Unauthorized";

function AllManagersList() {
  const [managerList, setmanagerList] = useState([]);
  async function apicall() {
    try {
      const res = await getRequest(
        ALL_MANAGER
      );
      setmanagerList(res.data);
    } catch (error) {}
  }

  useEffect(() => {
    apicall();
  }, []);
  const userRole=localStorage.getItem('role');
  if (!userRole) {
    return <Unauthorized/>;
  }
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
