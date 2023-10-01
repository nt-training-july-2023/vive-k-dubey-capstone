import React from "react";

function Dropdown({
  id,
  value,
  options,
  onChange,
  placeholder,
  className,
  label,
  selectClassname,
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
        required={true}
        aria-invalid={isInvalid}
        title={isInvalid ? "This field is required" : ""}
      >
        <option value="">{placeholder}</option>
        {options.map((option) => (
          <option key={option} value={option}>
            {option}
          </option>
        ))}
      </select>
    </div>
  );
}

export default Dropdown;

// import React from "react";

// function Dropdown({ id, value, options, onChange, placeholder, className, label, selectClassname }) {
//   const isInvalid = value === "";
  
//   return (
//     <div className={className}>
//       <label htmlFor={id}>{label}</label>
//       <select
//         className={selectClassname}
//         id={id}
//         value={value}
//         onChange={onChange}
//         required={true}
//         aria-invalid={isInvalid}
//         title={isInvalid ? "This field is required" : ""}
//       >
//         <option value="">{placeholder}</option>
//         {options.map((option) => (
//           <option key={option.value} value={option.value}>
//             {option.label}
//           </option>
//         ))}
//       </select>
//     </div>
//   );
// }

// export default Dropdown;


// <Dropdown
//   id="project-select"
//   value={selectedProjectId}
//   options={projectList.map((project) => ({
//     value: project.id,
//     label: `${project.id}-${project.projectName}`,
//   }))}
//   onChange={handleChange}
//   placeholder="Select Project"
//   className="project-dropdown-container"
//   label="Project"
//   selectClassname="project-select"
// />

