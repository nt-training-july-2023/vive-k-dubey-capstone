import React, { useState, useEffect } from "react";
import "../CSS/Login.css";
import { useNavigate } from "react-router-dom";
import { Base64 } from "js-base64";
import bcrypt from "bcryptjs";
import Popup from "../Components/Popup";
import Button from "../Components/Button";
import axios from "axios";

function Login({ setIsLoggedIn }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [popupMessage, setPopupMessage] = useState("");
  const [showPopup, setShowPopup] = useState(false);
  const [emailBlurred, setEmailBlurred] = useState(false);
  const [passwordBlurred, setPasswordBlurred] = useState(false);
  const navigate = useNavigate();

  const validateEmail = () => {
    const emailPattern = /^[A-Za-z0-9._%+-]+@nucleusteq\.com$/;
    if (!emailPattern.test(email)) {
      setEmailError("Email should end with @nucleusteq.com");
      return false;
    }
    setEmailError("");
    return true;
  };

  const handleSignupClick = () => {
    navigate("/register");
  };

  const validatePassword = () => {
    if (password.length < 8) {
      setPasswordError("Atleast 8 characters needed");
      return false;
    }
    setPasswordError("");
    return true;
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (email.trim() === "" && password.trim() === "") {
      setEmailError("Email cannot be blank");
      setPasswordError("Password cannot be blank");
      return;
    }
    const isEmailValid = validateEmail();
    const isPasswordValid = validatePassword();

    const encodedPassword = Base64.encode(password);

    const requestBody = {
      empEmail: email,
      empPassword: encodedPassword,
    };

    if (isEmailValid && isPasswordValid) {
      try {
        const response = await axios.post(
          "http://localhost:8081/login",
          requestBody,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
        console.log("api call");

        if (response.status === 200) {
          const responseData = response.data;
          localStorage.setItem("isLoggedIn", "true");
          localStorage.setItem("role", responseData.empRole);
          localStorage.setItem("userEmail", email);
          localStorage.setItem("userName", responseData.empName);
          setIsLoggedIn(true);

          if (localStorage.getItem("role") === "admin") {
            setTimeout(() => {
              navigate("/admin-dashboard");
            }, 500);
          } else if (localStorage.getItem("role") === "manager") {
            setTimeout(() => {
              navigate("/managerdashboard");
            }, 500);
          } else if (localStorage.getItem("role") === "employee") {
            setTimeout(() => {
              navigate("/userdashboard");
            }, 500);
          }
        }
      } catch (error) {
        const errorMessage = error.response.data;
        setPopupMessage(errorMessage.message);
        setShowPopup(true);
      }
    }
  };
  const closePopup = () => {
    setShowPopup(false);
  };

  return (
    <>
      {showPopup && <Popup message={popupMessage} onClose={closePopup} />}
      <div className="container">
        <div className="row justify-center">
          <h1 className="text-center">Employee Management System</h1>
        </div>
        <div className="top-right-text">
          <h3>Login Page</h3>
        </div>
        <div className="row justify-center">
          <form
            className="login-form"
            style={{ paddingRight: 35 }}
            onSubmit={handleSubmit}
          >
            <div className="form-group">
              <label htmlFor="email" className="label">
                Email
              </label>
              <input
                type="email"
                id="email"
                className="input-field"
                placeholder="Example@nucleusteq.com"
                value={email}
                onChange={(e) => {
                  setEmail(e.target.value);
                  setEmailError(false);
                }}
                onBlur={() => {
                  setEmailBlurred(true);
                  validateEmail();
                }}
              />
              <div className="error-message-container">
                {emailError && (
                  <div className="error-message">{emailError}</div>
                )}
              </div>
            </div>
            <div className="form-group">
              <label htmlFor="password" className="label">
                Password
              </label>
              <input
                type="password"
                id="password"
                className="input-field"
                placeholder="Password"
                value={password}
                onChange={(e) => {
                  setPassword(e.target.value);
                  setPasswordError(false);
                }}
                onBlur={() => {
                  setPasswordBlurred(true);
                  validatePassword();
                }}
              />
              <div className="error-message-container">
                {passwordError && (
                  <div className="error-message">{passwordError}</div>
                )}
              </div>
            </div>
            <div className="button-group">
              <Button type="submit" className="primary-button" text="Log In" />
            </div>
            <h4>Not a registered user ?</h4>{" "}
            <span className="link-text" onClick={handleSignupClick}>
              Sign Up
            </span>
          </form>
        </div>
      </div>
    </>
  );
}

export default Login;
