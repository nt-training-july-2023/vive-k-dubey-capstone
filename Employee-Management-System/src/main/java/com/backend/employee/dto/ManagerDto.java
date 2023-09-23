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
 public String toString() {
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
 public Long getId() {
  return id;
 }

 /**
  *
  * @param idLocal idLocal.
  */

 public void setId(final Long idLocal) {
  this.id = idLocal;
 }

 /**
  * Getter.
  *
  * @return empName.
  */

 public String getEmpName() {
  return empName;
 }

 /**
  * setter.
  *
  * @param empNameLocal empNameLocal.
  */
 public void setEmpName(final String empNameLocal) {
  this.empName = empNameLocal;
 }

 /**
  * Getter.
  *
  * @return empDesignation.
  */
 public String getEmpDesignation() {
  return empDesignation;
 }

 /**
  * setter.
  *
  * @param empDesignationLocal empDesignationLocal.
  */
 public void setEmpDesignation(final String empDesignationLocal) {
  this.empDesignation = empDesignationLocal;
 }

 /**
  * Getter.
  *
  * @return empContactNo.
  */
 public String getEmpContactNo() {
  return empContactNo;
 }

 /**
  * setter.
  *
  * @param empContactNoLocal empContactNoLocal.
  */
 public void setEmpContactNo(final String empContactNoLocal) {
  this.empContactNo = empContactNoLocal;
 }

 /**
  * Getter.
  *
  * @return empEmail.
  */
 public String getEmpEmail() {
  return empEmail;
 }

 /**
  * setter.
  *
  * @param empEmailLocal empEmailLocal.
  */
 public void setEmpEmail(final String empEmailLocal) {
  this.empEmail = empEmailLocal;
 }

 /**
  * Getter.
  *
  * @return empLocation.
  */
 public String getEmpLocation() {
  return empLocation;
 }

 /**
  * setter.
  *
  * @param empLocationLocal empLocationLocal.
  */
 public void setEmpLocation(final String empLocationLocal) {
  this.empLocation = empLocationLocal;
 }

 /**
  * Getter.
  *
  * @return empId.
  */
 public String getEmpId() {
  return empId;
 }

 /**
  * setter.
  *
  * @param empIdLocal empIdLocal.
  */
 public void setEmpId(final String empIdLocal) {
  this.empId = empIdLocal;
 }

 /**
  * Getter.
  *
  * @return empSkills.
  */
 public List<String> getEmpSkills() {
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
 public void setEmpSkills(final List<String> empSkillsLocal) {
  this.empSkills = new ArrayList<>(empSkillsLocal);
 }

 /**
  * Getter.
  *
  * @return managerName.
  */
 public String getManagerName() {
  return managerName;
 }

 /**
  * Setter.
  *
  * @param managerNameLocal managerNameLocal.
  */
 public void setManagerName(final String managerNameLocal) {
  this.managerName = managerNameLocal;
 }

 /**
  * Getter.
  *
  * @return projectNames.
  */
 public List<String> getProjectNames() {
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
 public void setProjectNames(final List<String> projectNamesLocal) {
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
 public int hashCode() {
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
