import logo from './logo.svg';
import { BrowserRouter as Router, Route, Routes ,Navigate} from 'react-router-dom';
import React, { useState } from 'react';
import './App.css';
import Login from './Components/Login';
import Register from './Components/Register';
import AdminDashboard from './Components/AdminDashboard';
import AddEmployee from './Components/AddEmployee';
import ProjectsPage from './Components/AllProjectsList';
import EmployeeHome from './Components/EmployeeHome';
import AssignProject from './Components/AssignProject';
import AllEmployeesList from './Components/AllEmployeesList';

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

    <Route exact path="/employee-dashboard" element={<PrivateRoute Component={EmployeeHome} isLoggedIn={localStorage.getItem('isLoggedIn')} />} />

    <Route exact path="/admin-dashboard/allEmployees" element={<PrivateRoute Component={AllEmployeesList} isLoggedIn={localStorage.getItem('isLoggedIn')} />} />
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