import React, { useState, useEffect } from 'react';
import '../CSS/Login.css';
import { useNavigate } from 'react-router-dom';

function Login({ setIsLoggedIn }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [popupMessage, setPopupMessage] = useState('');
  const [showPopup, setShowPopup] = useState(false);
  const navigate = useNavigate();

  

  const validateEmail = () => {
    const emailPattern = /^[A-Za-z0-9._%+-]+@nucleusteq\.com$/;
    if (!emailPattern.test(email)) {
      setEmailError('Email should be in valid format ending with @nucleusteq.com');
      return false;
    }
    setEmailError('');
    return true;
  };

  const handleSignupClick = () => {
    
    navigate('/register');
  };

  const validatePassword = () => {
    if (password.length < 8) {
      setPasswordError('Password should be at least 8 characters long');
      return false;
    }
    setPasswordError('');
    return true;
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const isEmailValid = validateEmail();
    const isPasswordValid = validatePassword();

    const requestBody = {
      empEmail: email,
      empPassword: password,
    };

    if (isEmailValid && isPasswordValid) {

      try {
        const response = await fetch('http://localhost:8081/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(requestBody),
        });
  
        if (response.ok) {
          setPopupMessage('Login successful!');
          setShowPopup(true);
          setIsLoggedIn(true);
          setTimeout(() => {
          navigate('/admin-dashboard');
        }, 800);
        }else if(response.status === 401){
          const errorMessage = await response.text();
          setPopupMessage(errorMessage);
          setShowPopup(true);
        } else if(response.status === 404){
          const errorMessage = await response.text();
          setPopupMessage(errorMessage);
          setShowPopup(true);
        } else {
          console.log(response.status);
          setPopupMessage('Login failed. Please try again.');
          setShowPopup(true);
        }
      } catch (error) {
        setPopupMessage('An error occurred. Please try again later.');
        setShowPopup(true);
      }
      
    }
  };
  const closePopup = () => {
    setShowPopup(false);
  };

  return (
    <>
    {showPopup && (
      <div className="popup">
      <div className="popup-content">
        <p>{popupMessage}</p>
        <button onClick={closePopup}>Close</button>
      </div>
      </div>
  )}
    <div className="container">
    <div className="row justify-center">
      <h1 className="text-center">Employee Management Portal</h1>
    </div>
    <div className="top-right-text">
      <h3>Login Page</h3>
    </div>
    <div className="row justify-center">
      <form className="login-form" style={{paddingRight:35}} onSubmit={handleSubmit}>
        <div className="form-group">
        <label htmlFor="email" className="label">Email</label>
          <input
            type="email"
            id="email"
            className="input-field"
            placeholder="Example@nucleusteq.com"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            onBlur={validateEmail}
            required
          />
          {emailError && <div className="error-message">{emailError}</div>}
        </div>
        <div className="form-group">
        <label htmlFor="password" className="label">Password</label>
          <input
            type="password"
            id="password"
            className="input-field"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            onBlur={validatePassword}
            required
          />
          {passwordError && <div className="error-message">{passwordError}</div>}
        </div>
        <div className="button-group">
          <button type="submit" className="primary-button">Sign In</button>
          
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
