import React from "react";

function Dropdown({
  id,
  value,
  options,
  onChange,
  placeholder,
  className,
  onBlur,
  label,
  selectClassname,
  error,
}) {
  const isInvalid = value === "";
  return (
    <div className={className}>
      <label htmlFor={id}>{label}</label>
      <select
        className={selectClassname}
        id={id}
        value={value}
        onChange={onChange}
        onBlur={onBlur}
        aria-invalid={isInvalid}
        title={isInvalid ? error : ""}
      >
        <option value="">{placeholder}</option>
        {options.map((option) => (
          <option key={option} value={option}>
            {option}
          </option>
        ))}
      </select>
      <div className="error-message-container-addemployee">
        {error && <div className="error-message">{error}</div>}
      </div>
    </div>
  );
}

export default Dropdown;
