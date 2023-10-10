package com.backend.employee.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * A data transfer object (DTO) representing the assignment of a project to an
 * employee.
 */
public class AssignProjectDto {
 /**
  * Hashcode method.
  */
 @Override
 public int hashCode() {
  return Objects.hash(empId, projectId);
 }

 /**
  * Equals method.
  */
 @Override
 public boolean equals(final Object obj) {
  if (this == obj) {
   return true;
  }
  if (obj == null) {
   return false;
  }
  if (getClass() != obj.getClass()) {
   return false;
  }
  AssignProjectDto other = (AssignProjectDto) obj;
  return Objects.equals(empId, other.empId)
   && Objects.equals(projectId, other.projectId);
 }

 /**
  * To string method.
  */
 @Override
 public String toString() {
  return "AssignProjectDto [projectId=" + projectId + ", empId=" + empId
   + "]";
 }

 /**
  * The unique identifier of the project to be assigned.
  */
 @NotNull(message = "ProjectId cannot be blank")
 private Long projectId;

 /**
  * The employee identifier to whom the project will be assigned.
  */
 @NotBlank(message = "Employee Id cannot be blank")
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
