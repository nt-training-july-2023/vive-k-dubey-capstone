package com.backend.employee.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * A data transfer object (DTO) representing the update of employee skills.
 */
public class UpdateSkillsDto {

 /**
  * The email address of the employee whose skills are being updated.
  */
 private String empEmail;

 /**
  * The updated skills of the employee.
  */
 private List<String> empSkills;

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
