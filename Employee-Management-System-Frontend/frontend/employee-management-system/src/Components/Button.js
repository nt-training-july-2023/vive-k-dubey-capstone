
import React from "react";
import "../CSS/AllEmployeesList.css";

function Button({ className, text, onClick }) {
  return (
    <button className={className} onClick={onClick}>
      {text}
    </button>
  );
}

export default Button;
