package com.backend.employee.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

/**
 * A data transfer object (DTO) representing the update of employee skills.
 */
public class UpdateSkillsDto {

 /**
  * The email address of the employee whose skills are being updated.
  */
 @NotBlank(message = "Employee email should not be empty.")
 private String empEmail;

 /**
  * The updated skills of the employee.
  */
 @NotEmpty(message = "Employee skills should not be empty.")
 private List<@NotBlank String> empSkills;

 @Override
 public String toString() {
  return "UpdateSkillsDto [empEmail=" + empEmail + ", empSkills="
   + empSkills + "]";
 }

 @Override
 public int hashCode() {
  return Objects.hash(empEmail, empSkills);
 }

 @Override
 public boolean equals(Object obj) {
  if (this == obj)
   return true;
  if (obj == null)
   return false;
  if (getClass() != obj.getClass())
   return false;
  UpdateSkillsDto other = (UpdateSkillsDto) obj;
  return Objects.equals(empEmail, other.empEmail)
   && Objects.equals(empSkills, other.empSkills);
 }

 /**
  * Gets the email address of the employee.
  *
  * @return The email address of the employee.
  */
 public String getEmpEmail() {
  return empEmail;
 }

 /**
  * Sets the email address of the employee.
  *
  * @param empEmailLocal The email address of the employee.
  */
 public void setEmpEmail(final String empEmailLocal) {
  this.empEmail = empEmailLocal;
 }

 /**
  * Gets the updated skills of the employee.
  *
  * @return The updated skills of the employee.
  */
 public List<String> getEmpSkills() {
  if (empSkills != null) {
   return new ArrayList<>(empSkills);
  } else {
   return new ArrayList<>();
  }
 }

 /**
  * Sets the updated skills of the employee.
  *
  * @param empSkillsLocal The updated skills of the employee.
  */
 public void setEmpSkills(final List<String> empSkillsLocal) {
  this.empSkills = new ArrayList<>(empSkillsLocal);
 }
}
