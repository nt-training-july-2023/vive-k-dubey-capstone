import logo from './logo.svg';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import React, { useState } from 'react';
import './App.css';
import Login from './Components/Login';
import Register from './Components/Register';
import AdminDashboard from './Components/AdminDashboard';
import AddEmployee from './Components/AddEmployee';

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
    <Route
          path="/admin-dashboard"
          element={
            <AdminDashboard
              setIsLoggedIn={setIsLoggedIn}
              isLoggedIn={isLoggedIn}
            />
          }
        />
      <Route path="/register" element={<Register />} />
      <Route
          path="/"
          element={<Login setIsLoggedIn={setIsLoggedIn} isLoggedIn={isLoggedIn} />}
        />
    </Routes>
  </Router>
  );
}

export default App;