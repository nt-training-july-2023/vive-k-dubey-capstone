package com.backend.employee.dto;

/**
 * Data Transfer Object (DTO) representing the login credentials of an employe.
 */
public class LoginDto {

 /**
  * The email of the employee.
  */
 private String empEmail;

 /**
  * The password of the employee.
  */
 private String empPassword;

 /**
  * Constructs a new LoginDto with the provided email and password.
  *
  * @param empEmailLocal    The email of the employee.
  * @param empPasswordLocal The password of the employee.
  */
 public LoginDto(final String empEmailLocal,
  final String empPasswordLocal) {
  this.empEmail = empEmailLocal;
  this.empPassword = empPasswordLocal;
 }

 /**
  * Constructs a new LoginDto with no initial email or password.
  */
 public LoginDto() {

 }

 /**
  * Retrieves the email of the employee.
  *
  * @return The email of the employee.
  */
 public final String getEmpEmail() {
  return empEmail;
 }

 /**
  * Sets the email of the employee.
  *
  * @param empEmailLocal The email of the employee.
  */
 public final void setEmpEmail(final String empEmailLocal) {
  this.empEmail = empEmailLocal;
 }

 /**
  * Retrieves the password of the employee.
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
  * Returns a string representation of the LoginDto.
  *
  * @return A string containing the email and password of the employee.
  */
 @Override
 public final String toString() {
  return "LoginDto [empEmail=" + empEmail + ", empPassword=" + empPassword
   + "]";
 }
}
