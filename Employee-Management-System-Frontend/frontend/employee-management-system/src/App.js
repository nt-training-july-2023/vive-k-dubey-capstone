import logo from './logo.svg';
import { BrowserRouter as Router, Route, Routes ,Navigate} from 'react-router-dom';
import React, { useState } from 'react';
import './App.css';
import Login from './Components/Login';
import UserDashboard from './Pages/UserDashboard';
import Register from './Components/Register';
import AdminDashboard from './Components/AdminDashboard';

import AddEmployee from './Components/AddEmployee';
import ProjectsPage from './Components/AllProjectsList';
import EmployeeHome from './Components/EmployeeHome';
import AssignProject from './Components/AssignProject';
import AllEmployeesList from './Components/AllEmployeesList';
import UpdateSkills from './Pages/UpdateSkills';
import RequestResource from './Pages/RequestResource';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

   function login() {
     setIsLoggedIn(true);
  }

   function logout() {
     setIsLoggedIn(false);
   }
  return (
    <Router>
    <Routes>
    <Route exact path="/admin-dashboard" element={<PrivateRoute Component={AdminDashboard} isLoggedIn={localStorage.getItem('isLoggedIn')} />} />

    <Route exact path="/userdashboard" element={<PrivateRoute Component={UserDashboard} isLoggedIn={localStorage.getItem('isLoggedIn')} />} />
    
    <Route exact path="/managerdashboard" element={<PrivateRoute Component={AdminDashboard} isLoggedIn={localStorage.getItem('isLoggedIn')} />} />
    {/* <Route exact path="/admin-dashboard/allProjects" element={<PrivateRoute Component={AllProjectsList} isLoggedIn={localStorage.getItem('isLoggedIn')} />} /> */}
    {/* <Route
          path="/admin-dashboard"
          element={
            <AdminDashboard
              setIsLoggedIn={setIsLoggedIn}
              isLoggedIn={isLoggedIn}
            />
          }
        /> */}
      <Route path="/register" element={<Register />} />
      <Route path="/assign-project" element={<AssignProject />} />
      <Route path="/userdashboard/upadteskills" element={<UpdateSkills />} />
      <Route path="/requestResource" element={<RequestResource />} />


      <Route
          path="/"
          element={<Login setIsLoggedIn={setIsLoggedIn} isLoggedIn={isLoggedIn} />}
        />
    </Routes>
  </Router>
  );
}
const PrivateRoute = ({ Component }) => {
  const isLoggedIn = localStorage.getItem('isLoggedIn')
  return isLoggedIn ? <Component /> : <Navigate to="/" replace />;
}

export default App;