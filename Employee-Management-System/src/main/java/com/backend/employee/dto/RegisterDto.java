package com.backend.employee.dto;

import java.beans.JavaBean;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * register dto.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JavaBean
public class RegisterDto {

  /**
   * The unique identifier for the employee.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The employee ID.
   */
  @Column(unique = true)
  private String empId;

  /**
   * The name of the employee.
   */
  private String empName;

  /**
   * The email address of the employee.
   */
  @Column(unique = true)
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
  
  private List<String> empSkills;
  
  private String managerName;
  
  
  private Long projectId;

}