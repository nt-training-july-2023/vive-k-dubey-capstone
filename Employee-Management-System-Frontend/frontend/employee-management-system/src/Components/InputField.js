
import React from "react";

function InputField({ label, value, onChange, onBlur, type, id, name, className, labelClassName, placeholder }) {
  return (
    <>
      <label htmlFor={id} labelClassName = {labelClassName}>{label}</label>
      <input
        type={type}
        name={name}
        className={className}
        id={id}
        value={value}
        onChange={onChange}
        placeholder = {placeholder}
        onBlur={onBlur}
      />
    </>
  );
}

export default InputField;
