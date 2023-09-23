package com.backend.employee.dto;

/**
 * A data transfer object (DTO) representing the assignment of a project to an
 * employee.
 */
public class AssignProjectDto {

 /**
  * The unique identifier of the project to be assigned.
  */
 private Long projectId;

 /**
  * The employee identifier to whom the project will be assigned.
  */
 private String empId;

 /**
  * Gets the unique identifier of the project.
  *
  * @return The unique identifier of the project.
  */
 public final Long getProjectId() {
  return projectId;
 }

 /**
  * Sets the unique identifier of the project.
  *
  * @param projectIdLocal The unique identifier of the project.
  */
 public void setProjectId(final Long projectIdLocal) {
  this.projectId = projectIdLocal;
 }

 /**
  * Gets the employee identifier to whom the project will be assigned.
  *
  * @return The employee identifier.
  */
 public String getEmpId() {
  return empId;
 }

 /**
  * Sets the employee identifier to whom the project will be assigned.
  *
  * @param empIdLocal The employee identifier.
  */
 public void setEmpId(final String empIdLocal) {
  this.empId = empIdLocal;
 }
}
