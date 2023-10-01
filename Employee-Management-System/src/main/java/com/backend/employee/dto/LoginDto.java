package com.backend.employee.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

/**
 * Data Transfer Object (DTO) representing the login credentials of an employe.
 */
public class LoginDto {

 @Override
 public int hashCode() {
  return Objects.hash(empEmail, empPassword);
 }

 @Override
 public boolean equals(Object obj) {
  if (this == obj)
   return true;
  if (obj == null)
   return false;
  if (getClass() != obj.getClass())
   return false;
  LoginDto other = (LoginDto) obj;
  return Objects.equals(empEmail, other.empEmail)
   && Objects.equals(empPassword, other.empPassword);
 }

 /**
  * The email of the employee.
  */
 
 @NotEmpty(message = "Email cannot be blank")
 private String empEmail;

 /**
  * The password of the employee.
  */
 @NotEmpty(message = "Password cannot be blank")
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
 public String getEmpEmail() {
  return empEmail;
 }

 /**
  * Sets the email of the employee.
  *
  * @param empEmailLocal The email of the employee.
  */
 public void setEmpEmail(final String empEmailLocal) {
  this.empEmail = empEmailLocal;
 }

 /**
  * Retrieves the password of the employee.
  *
  * @return The password of the employee.
  */
 public String getEmpPassword() {
  return empPassword;
 }

 /**
  * Sets the password of the employee.
  *
  * @param empPasswordLocal The password of the employee.
  */
 public void setEmpPassword(final String empPasswordLocal) {
  this.empPassword = empPasswordLocal;
 }

 /**
  * Returns a string representation of the LoginDto.
  *
  * @return A string containing the email and password of the employee.
  */
 @Override
 public String toString() {
  return "LoginDto [empEmail=" + empEmail + ", empPassword=" + empPassword
   + "]";
 }
}
