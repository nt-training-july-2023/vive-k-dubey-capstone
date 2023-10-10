import React from "react";

function Tab({ label, isSelected, onClick, isActive }) {
  const className =
    isSelected !== undefined
      ? `tab-button ${isSelected ? "selected" : ""}`
      : `user-tab ${isActive ? "user-active" : ""}`;
  return (
    <button className={className} onClick={onClick}>
      {label}
    </button>
  );
}

export default Tab;
