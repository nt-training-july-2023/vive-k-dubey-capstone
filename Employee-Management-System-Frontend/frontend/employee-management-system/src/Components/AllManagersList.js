import React, { useState, useEffect } from 'react';
import '../CSS/AllManagersList.css';
import ManagerCard from "./ManagerCard.js";
import axios from 'axios';

function AllManagersList() {
  const [managerList, setmanagerList] = useState([]);
    async function apicall() {

        try {

            const res = await axios.get("http://localhost:8081/employee/getAllManagers");
            console.log("Manager list", res.data);
            setmanagerList(res.data);
            console.log("employee data", managerList);
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
      
      apicall();
  }, []);

  console.log("Manager List outside useEffect:", managerList);

  return (
    <div className="content">
      <div className="card-container">
        {managerList.length > 0 ? (
          managerList.map((manager) => (
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
