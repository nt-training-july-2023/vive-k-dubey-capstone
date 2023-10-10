package com.backend.employee.entity;

import java.util.ArrayList;
import java.util.List;

import com.backend.employee.dto.RegisterDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * entity class.
 */
@Entity
@Table(name = "employee")
public class RegisterEntity {

 /**
  * Parameterized constructor.
  *
  * @param registerDto registerDto.
  */
 public RegisterEntity(final RegisterDto registerDto) {
  this.empId = registerDto.getEmpId();
  this.empName = registerDto.getEmpName();
  this.empDesignation = registerDto.getEmpDesignation();
  this.empContactNo = registerDto.getEmpContactNo();
  this.empPassword = registerDto.getEmpPassword();
  this.empEmail = registerDto.getEmpEmail();
  this.empDOB = registerDto.getEmpDOB();
  this.empDOJ = registerDto.getEmpDOJ();
  this.empLocation = registerDto.getEmpLocation();
  this.empRole = registerDto.getEmpRole().toLowerCase();
 }

 /**
  * Constructor.
  */

 public RegisterEntity() {
  super();
 }

 /**
  * Getter.
  *
  * @return id.
  */

 public Long getId() {
  return id;
 }

 /**
  * Setter.
  *
  * @param idLocal idLocal.
  */
 public void setId(final Long idLocal) {
  this.id = idLocal;
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
  * Setter.
  *
  * @param empIdLocal empIdLocal.
  */
 public void setEmpId(final String empIdLocal) {
  this.empId = empIdLocal;
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
  * Setter.
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
  * Setter.
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
  * Setter.
  *
  * @param empContactNoLocal empContactNoLocal.
  */
 public void setEmpContactNo(final String empContactNoLocal) {
  this.empContactNo = empContactNoLocal;
 }

 /**
  * Getter.
  *
  * @return empPassword.
  */
 public String getEmpPassword() {
  return empPassword;
 }

 /**
  * Setter.
  *
  * @param empPasswordLocal empPasswordLocal.
  */
 public void setEmpPassword(final String empPasswordLocal) {
  this.empPassword = empPasswordLocal;
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
  * Setter.
  *
  * @param empEmailLocal empEmailLocal.
  */
 public void setEmpEmail(final String empEmailLocal) {
  this.empEmail = empEmailLocal;
 }

 /**
  * Getter.
  *
  * @return empDOB.
  */
 public String getEmpDOB() {
  return empDOB;
 }

 /**
  * Setter.
  *
  * @param empDOBLocal This is the parameter.
  */
 public void setEmpDOB(final String empDOBLocal) {
  this.empDOB = empDOBLocal;
 }

 /**
  * Getter.
  *
  * @return empDOJ.
  */
 public String getEmpDOJ() {
  return empDOJ;
 }

 /**
  * Setter.
  *
  * @param empDOJLocal This is the empDOJLocal.
  */
 public void setEmpDOJ(final String empDOJLocal) {
  this.empDOJ = empDOJLocal;
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
  * Setter.
  *
  * @param empLocationLocal empLocationLocal.
  */
 public void setEmpLocation(final String empLocationLocal) {
  this.empLocation = empLocationLocal;
 }

 /**
  * Getter.
  *
  * @return empRole.
  */
 public String getEmpRole() {
  return empRole;
 }

 /**
  * Setter.
  *
  * @param empRoleLocal empRoleLocal.
  */
 public void setEmpRole(final String empRoleLocal) {
  this.empRole = empRoleLocal;
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
  * @return managerId.
  */
 public Long getManagerId() {
  return managerId;
 }

 /**
  * Setter.
  *
  * @param managerIdLocal managerIdLocal.
  */
 public void setManagerId(final Long managerIdLocal) {
  this.managerId = managerIdLocal;
 }

 /**
  * Getter.
  *
  * @return projectId.
  */
 public Long getProjectId() {
  return projectId;
 }

 /**
  * Setter.
  *
  * @param projectIdLocal projectIdLocal.
  */
 public void setProjectId(final Long projectIdLocal) {
  this.projectId = projectIdLocal;
 }

 /**
  * The unique identifier of the employee.
  */
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 /**
  * The employee ID.
  */
 @Column(unique = true, nullable = false)
 private String empId;
 /**
  * The name of the employee.
  */
 @Column(unique = false, nullable = false)
 private String empName;
 /**
  * The designation of the employee.
  */
 @Column(nullable = false)
 private String empDesignation;
 /**
  * The contact number of the employee.
  */
 @Column(unique = true, nullable = false)
 private String empContactNo;
 /**
  * The password of the employee.
  */
 @Column(nullable = false)
 private String empPassword;

 /**
  * The email of the employee.
  */
 @Column(unique = true, nullable = false)
 private String empEmail;
 /**
  * The date of birth of the employee.
  */
 @Column(nullable = false)
 private String empDOB;
 /**
  * The date of joining of the employee.
  */
 @Column(nullable = false)
 private String empDOJ;
 /**
  * The location of the employee.
  */
 @Column(nullable = false)
 private String empLocation;
 /**
  * The role of the employee.
  */
 @Column(nullable = false)
 private String empRole;

 /**
  * The skills of the employee.
  */
 private List<String> empSkills;

 /**
  * The managerId (primary key) of the employee.
  */

 @Column
 private Long managerId;

 /**
  * The id of project alloted to the employee.
  */

 private Long projectId;

}
