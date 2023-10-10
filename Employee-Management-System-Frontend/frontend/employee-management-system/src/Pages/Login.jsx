import React, { useState, useEffect } from "react";
import "../CSS/Login.css";
import { useNavigate } from "react-router-dom";
import { Base64 } from "js-base64";
import bcrypt from "bcryptjs";
import Popup from "../Components/Popup";
import Button from "../Components/Button";
import axios from "axios";
import InputField from "../Components/InputField";
import { postRequest } from "../Services/Service";
import { LOGIN } from "../Services/url";
import Unauthorized from "../Components/Unauthorized";

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
  const userRole = localStorage.getItem("role");

  useEffect(() => {
    if (userRole === "admin") {
      navigate("/admin-dashboard");
    } else if (userRole === "manager") {
      navigate("/managerdashboard");
    } else if (userRole === "employee") {
      navigate("/userdashboard");
    }
  }, [navigate]);

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
        const response = await postRequest(LOGIN, requestBody, {
          headers: {
            "Content-Type": "application/json",
          },
        });

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
        if (error.response) {
          const errorMessage = error.response.data;
          setPopupMessage(errorMessage.message);
          setShowPopup(true);
        } else {
          console.log("error");
          setPopupMessage("Server is not running.");
          setShowPopup(true);
        }
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
        <div className="row justify-center">
          <form
            className="login-form"
            style={{ paddingRight: 35 }}
            onSubmit={handleSubmit}
          >
            <div className="form-group">
              <InputField
                label="Email"
                labelClassName="label"
                type="email"
                id="email"
                onChange={(e) => {
                  setEmail(e.target.value);
                  setEmailError(false);
                }}
                onBlur={() => {
                  setEmailBlurred(true);
                  validateEmail();
                }}
                placeholder="Example@nucleusteq.com"
                className="input-field"
              />
              <div className="error-message-container">
                {emailError && (
                  <div className="error-message">{emailError}</div>
                )}
              </div>
            </div>
            <div className="form-group">
              <InputField
                label="Password"
                labelClassName="label"
                type="password"
                id="password"
                onChange={(e) => {
                  setPassword(e.target.value);
                  setPasswordError(false);
                }}
                onBlur={() => {
                  setPasswordBlurred(true);
                  validatePassword();
                }}
                placeholder="Password"
                className="input-field"
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
