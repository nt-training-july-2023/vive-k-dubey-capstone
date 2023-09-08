package com.backend.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing the login credentials of an employee.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

  /**
   * The email of the employee.
   */
  private String empEmail;

  /**
   * The password of the employee.
   */
  private String empPassword;

}

