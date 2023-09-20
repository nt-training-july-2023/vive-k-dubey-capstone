package com.backend.employee.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * A data transfer object (DTO) representing employee registration information.
 */
public class RegisterDto {

 /**
  * The unique identifier for the employee.
  */
 private Long id;

 /**
  * The employee ID.
  */
 private String empId;

 /**
  * The name of the employee.
  */
 private String empName;

 /**
  * The email address of the employee.
  */
 private String empEmail;

 /**
  * The date of birth of the employee.
  */
 private String empDOB;

 /**
  * The date of joining of the employee.
  */
 private String empDOJ;

 /**
  * The location of the employee.
  */
 private String empLocation;

 /**
  * The designation of the employee.
  */
 private String empDesignation;

 /**
  * The contact number of the employee.
  */
 private String empContactNo;

 /**
  * The password of the employee.
  */
 private String empPassword;

 /**
  * The role of the employee.
  */
 private String empRole;

 /**
  * The list of skills possessed by the employee.
  */
 private List<String> empSkills;

 /**
  * The name of the manager associated with the employee.
  */
 private String managerName;

 /**
  * The unique identifier of the project associated with the employee.
  */
 private Long projectId;

 /**
  * Identifier for projectName.
  */

 private String projectName;

 /**
  *
  * @return projectName Project name.
  */
 public final String getProjectName() {
  return projectName;
 }

 /**
  *
  * @param projectNameLocal projectNameLocal.
  */
 public final void setProjectName(final String projectNameLocal) {
  this.projectName = projectNameLocal;
 }

 /**
  * Constructs a new RegisterDto with no initial information.
  */
 public RegisterDto() {

 }

 /**
  * Gets the unique identifier for the employee.
  *
  * @return The unique identifier for the employee.
  */
 public final Long getId() {
  return id;
 }

 /**
  * Sets the unique identifier for the employee.
  *
  * @param idLocal The unique identifier for the employee.
  */
 public final void setId(final Long idLocal) {
  this.id = idLocal;
 }

 /**
  * Gets the employee ID.
  *
  * @return The employee ID.
  */
 public final String getEmpId() {
  return empId;
 }

 /**
  * Sets the employee ID.
  *
  * @param empIdLocal The employee ID.
  */
 public final void setEmpId(final String empIdLocal) {
  this.empId = empIdLocal;
 }

 /**
  * Gets the name of the employee.
  *
  * @return The name of the employee.
  */
 public final String getEmpName() {
  return empName;
 }

 /**
  * Sets the name of the employee.
  *
  * @param empNameLocal The name of the employee.
  */
 public final void setEmpName(final String empNameLocal) {
  this.empName = empNameLocal;
 }

 /**
  * Gets the email address of the employee.
  *
  * @return The email address of the employee.
  */
 public final String getEmpEmail() {
  return empEmail;
 }

 /**
  * Sets the email address of the employee.
  *
  * @param empEmailLocal The email address of the employee.
  */
 public final void setEmpEmail(final String empEmailLocal) {
  this.empEmail = empEmailLocal;
 }

 /**
  * Gets the date of birth of the employee.
  *
  * @return The date of birth of the employee.
  */
 public final String getEmpDOB() {
  return empDOB;
 }

 /**
  * Sets the date of birth of the employee.
  *
  * @param empDOBLocal The date of birth of the employee.
  */
 public final void setEmpDOB(final String empDOBLocal) {
  this.empDOB = empDOBLocal;
 }

 /**
  * Gets the date of joining of the employee.
  *
  * @return The date of joining of the employee.
  */
 public final String getEmpDOJ() {
  return empDOJ;
 }

 /**
  * Sets the date of joining of the employee.
  *
  * @param empDOJLocal The date of joining of the employee.
  */
 public final void setEmpDOJ(final String empDOJLocal) {
  this.empDOJ = empDOJLocal;
 }

 /**
  * Gets the location of the employee.
  *
  * @return The location of the employee.
  */
 public final String getEmpLocation() {
  return empLocation;
 }

 /**
  * Sets the location of the employee.
  *
  * @param empLocationLocal The location of the employee.
  */
 public final void setEmpLocation(final String empLocationLocal) {
  this.empLocation = empLocationLocal;
 }

 /**
  * Gets the designation of the employee.
  *
  * @return The designation of the employee.
  */
 public final String getEmpDesignation() {
  return empDesignation;
 }

 /**
  * Sets the designation of the employee.
  *
  * @param empDesignationLocal The designation of the employee.
  */
 public final void setEmpDesignation(final String empDesignationLocal) {
  this.empDesignation = empDesignationLocal;
 }

 /**
  * Gets the contact number of the employee.
  *
  * @return The contact number of the employee.
  */
 public final String getEmpContactNo() {
  return empContactNo;
 }

 /**
  * Sets the contact number of the employee.
  *
  * @param empContactNoLocal The contact number of the employee.
  */
 public final void setEmpContactNo(final String empContactNoLocal) {
  this.empContactNo = empContactNoLocal;
 }

 /**
  * Gets the password of the employee.
  *
  * @return The password of the employee.
  */
 public final String getEmpPassword() {
  return empPassword;
 }

 /**
  * Sets the password of the employee.
  *
  * @param empPasswordLocal The password of the employee.
  */
 public final void setEmpPassword(final String empPasswordLocal) {
  this.empPassword = empPasswordLocal;
 }

 /**
  * Gets the role of the employee.
  *
  * @return The role of the employee.
  */
 public final String getEmpRole() {
  return empRole;
 }

 /**
  * Sets the role of the employee.
  *
  * @param empRoleLocal The role of the employee.
  */
 public final void setEmpRole(final String empRoleLocal) {
  this.empRole = empRoleLocal;
 }

 /**
  * Gets the list of skills possessed by the employee.
  *
  * @return The list of skills possessed by the employee.
  */
 public final List<String> getEmpSkills() {
  if (empSkills != null) {
   return new ArrayList<>(empSkills);
  } else {
   return new ArrayList<>(); // Or handle it differently based on your use case
  }
 }

 /**
  * Sets the list of skills possessed by the employee.
  *
  * @param empSkillsLocal The list of skills possessed by the employee.
  */
 public final void setEmpSkills(final List<String> empSkillsLocal) {
  this.empSkills = new ArrayList<>(empSkillsLocal);
 }

 /**
  * Gets the name of the manager associated with the employee.
  *
  * @return The name of the manager associated with the employee.
  */
 public final String getManagerName() {
  return managerName;
 }

 /**
  * Sets the name of the manager associated with the employee.
  *
  * @param managerNameLocal The name of manager associated with the employee.
  */
 public final void setManagerName(final String managerNameLocal) {
  this.managerName = managerNameLocal;
 }

 /**
  * Gets the unique identifier of the project associated with the employee.
  *
  * @return The unique identifier of the project associated with the employee.
  */
 public final Long getProjectId() {
  return projectId;
 }

 /**
  * Sets the unique identifier of the project associated with the employee.
  *
  * @param projectIdLocal The unique identifier of the project associated with
  *                       the employee.
  */
 public final void setProjectId(final Long projectIdLocal) {
  this.projectId = projectIdLocal;
 }

 /**
  * Returns a string representation of the RegisterDto.
  *
  * @return A string containing information about the employee.
  */
 @Override
 public final String toString() {
  return "RegisterDto [id=" + id + ", empId=" + empId + ", empName="
   + empName + ", empEmail=" + empEmail + ", empDOB=" + empDOB + ", empDOJ="
   + empDOJ + ", empLocation=" + empLocation + ", empDesignation="
   + empDesignation + ", empContactNo=" + empContactNo + ", empPassword="
   + empPassword + ", empRole=" + empRole + ", empSkills=" + empSkills
   + ", managerName=" + managerName + ", projectId=" + projectId + "]";
 }
}
