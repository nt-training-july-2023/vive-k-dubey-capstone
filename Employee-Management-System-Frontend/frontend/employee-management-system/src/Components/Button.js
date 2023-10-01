import React from "react";
import "../CSS/AllEmployeesList.css";

function Button({ className, text, onClick, type, isDisabled }) {
  const buttonClassName = `${className} ${isDisabled ? "disabled" : ""}`;
  return (
    <button
      className={buttonClassName}
      onClick={isDisabled ? null : onClick}
      type={type}
      disabled={isDisabled}
    >
      {text}
    </button>
  );
}

export default Button;
