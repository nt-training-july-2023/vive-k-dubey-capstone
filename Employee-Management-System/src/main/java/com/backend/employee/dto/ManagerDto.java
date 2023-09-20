package com.backend.employee.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A data transfer object (DTO) representing information about a manager and
 * projects under their supervision.
 */

public class ManagerDto {

 @Override
 public final String toString() {
  return "ManagerDto [id=" + id + ", empName=" + empName
   + ", empDesignation=" + empDesignation + ", empContactNo=" + empContactNo
   + ", empEmail=" + empEmail + ", empLocation=" + empLocation + ", empId="
   + empId + ", empSkills=" + empSkills + ", managerName=" + managerName
   + ", projectNames=" + projectNames + "]";
 }

 /**
  * Constructs a new ManagerDto with no initial information.
  */
 public ManagerDto() {

 }

 /**
  * The unique identifier of the manager.
  *
  * @return id of this class.
  */
 public final Long getId() {
  return id;
 }

 /**
  *
  * @param idLocal idLocal.
  */

 public final void setId(final Long idLocal) {
  this.id = idLocal;
 }

 /**
  * Getter.
  *
  * @return empName.
  */

 public final String getEmpName() {
  return empName;
 }

 /**
  * setter.
  *
  * @param empNameLocal empNameLocal.
  */
 public final void setEmpName(final String empNameLocal) {
  this.empName = empNameLocal;
 }

 /**
  * Getter.
  *
  * @return empDesignation.
  */
 public final String getEmpDesignation() {
  return empDesignation;
 }

 /**
  * setter.
  *
  * @param empDesignationLocal empDesignationLocal.
  */
 public final void setEmpDesignation(final String empDesignationLocal) {
  this.empDesignation = empDesignationLocal;
 }

 /**
  * Getter.
  *
  * @return empContactNo.
  */
 public final String getEmpContactNo() {
  return empContactNo;
 }

 /**
  * setter.
  *
  * @param empContactNoLocal empContactNoLocal.
  */
 public final void setEmpContactNo(final String empContactNoLocal) {
  this.empContactNo = empContactNoLocal;
 }

 /**
  * Getter.
  *
  * @return empEmail.
  */
 public final String getEmpEmail() {
  return empEmail;
 }

 /**
  * setter.
  *
  * @param empEmailLocal empEmailLocal.
  */
 public final void setEmpEmail(final String empEmailLocal) {
  this.empEmail = empEmailLocal;
 }

 /**
  * Getter.
  *
  * @return empLocation.
  */
 public final String getEmpLocation() {
  return empLocation;
 }

 /**
  * setter.
  *
  * @param empLocationLocal empLocationLocal.
  */
 public final void setEmpLocation(final String empLocationLocal) {
  this.empLocation = empLocationLocal;
 }

 /**
  * Getter.
  *
  * @return empId.
  */
 public final String getEmpId() {
  return empId;
 }

 /**
  * setter.
  *
  * @param empIdLocal empIdLocal.
  */
 public final void setEmpId(final String empIdLocal) {
  this.empId = empIdLocal;
 }

 /**
  * Getter.
  *
  * @return empSkills.
  */
 public final List<String> getEmpSkills() {
  if (empSkills != null) {
   return new ArrayList<>(empSkills);
  } else {
   return new ArrayList<>();
  }
 }

 /**
  * Setter.
  *
  * @param empSkillsLocal empSkillsLocal.
  */
 public final void setEmpSkills(final List<String> empSkillsLocal) {
  this.empSkills = new ArrayList<>(empSkillsLocal);
 }

 /**
  * Getter.
  *
  * @return managerName.
  */
 public final String getManagerName() {
  return managerName;
 }

 /**
  * Setter.
  *
  * @param managerNameLocal managerNameLocal.
  */
 public final void setManagerName(final String managerNameLocal) {
  this.managerName = managerNameLocal;
 }

 /**
  * Getter.
  *
  * @return projectNames.
  */
 public final List<String> getProjectNames() {
  if (projectNames != null) {
   return new ArrayList<>(projectNames);
  } else {
   return new ArrayList<>();
  }
 }

 /**
  * Setter.
  *
  * @param projectNamesLocal projectNamesLocal.
  */
 public final void setProjectNames(final List<String> projectNamesLocal) {
  this.projectNames = new ArrayList<>(projectNamesLocal);
 }

 /**
  * The unique identifier of the manager.
  */
 private Long id;

 /**
  * The name of the manager.
  */
 private String empName;

 /**
  * The designation of the manager.
  */
 private String empDesignation;

 /**
  * The contact number of the manager.
  */
 private String empContactNo;

 /**
  * The email address of the manager.
  */
 private String empEmail;

 /**
  * The location of the manager.
  */
 private String empLocation;

 /**
  * The employee ID of the manager.
  */
 private String empId;

 /**
  * The skills possessed by the manager.
  */
 private List<String> empSkills;

 /**
  * The name of the manager.
  */
 private String managerName; // Name of the manager

 /**
  * The names of the projects under this manager's supervision.
  */
 private List<String> projectNames; // Names of projects under this manager

 /**
  * Calculates a hash code value for the ManagerDto object.
  *
  * @return The hash code value for this object.
  */
 @Override
 public final int hashCode() {
  return Objects.hash(empContactNo, empDesignation, empEmail, empId, id,
   empLocation, empName);
 }

 /**
  * Indicates whether some other object is "equal to" this one.
  *
  * @param obj The reference object with which to compare.
  * @return True if this object is same as the obj argument false otherwise.
  */
 @Override
 public final boolean equals(final Object obj) {
  if (this == obj) {
   return true;
  }
  if (obj == null) {
   return false;
  }
  if (getClass() != obj.getClass()) {
   return false;
  }
  ManagerDto other = (ManagerDto) obj;
  return Objects.equals(empContactNo, other.empContactNo)
   && empDesignation.equals(other.empDesignation)
   && Objects.equals(empEmail, other.empEmail)
   && Objects.equals(empId, other.empId) && Objects.equals(id, other.id)
   && empLocation.equals(other.empLocation)
   && Objects.equals(empName, other.empName);
 }
}
