import React from 'react';
import '../CSS/UserProfile.css';

function UserProfileInputField({ className, label, value, onChange, type }) {
  return (
    <div className="user-input-field">
      <label className="user-input-label">{label}:</label>
      <input
        type="text"
        className="user-input"
        value={value}
        readOnly
      />
    </div>
  );
}

export default UserProfileInputField;