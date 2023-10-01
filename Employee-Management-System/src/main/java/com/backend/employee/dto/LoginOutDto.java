package com.backend.employee.dto;

import java.util.Objects;

/**
 * A data transfer object (DTO) representing login-related information sent as a
 * response.
 */
public class LoginOutDto {

 /**
  * The role of the employee associated with the login.
  */
 private String empRole;

 /**
  * A message associated with the login response.
  */
 private String message;
 /**
  * Name of the logged in employee.
  */
 private String empName;

 /**
  * Getter.
  *
  * @return empName.
  */
 public String getEmpName() {
  return empName;
 }

 @Override
 public int hashCode() {
  return Objects.hash(empName, empRole, message);
 }

 @Override
 public boolean equals(Object obj) {
  if (this == obj)
   return true;
  if (obj == null)
   return false;
  if (getClass() != obj.getClass())
   return false;
  LoginOutDto other = (LoginOutDto) obj;
  return Objects.equals(empName, other.empName)
   && Objects.equals(empRole, other.empRole)
   && Objects.equals(message, other.message);
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
  * Constructs a new LoginOutDto with the provided employee role and message.
  *
  * @param empRoleLocal The role of the employee associated with the login.
  * @param messageLocal A message associated with the login response.
  * @param empNameLocal empNameLocal.
  */
 public LoginOutDto(final String empRoleLocal, final String messageLocal,
  final String empNameLocal) {
  this.empRole = empRoleLocal;
  this.message = messageLocal;
  this.empName = empNameLocal;
 }

 /**
  * Constructs a new LoginOutDto with no initial employee role or message.
  */
 public LoginOutDto() {
 }

 /**
  * Retrieves the role of the employee associated with the login.
  *
  * @return The role of the employee.
  */
 public String getEmpRole() {
  return empRole;
 }

 /**
  * Sets the role of the employee associated with the login.
  *
  * @param empRoleLocal The role of the employee.
  */
 public void setEmpRole(final String empRoleLocal) {
  this.empRole = empRoleLocal;
 }

 /**
  * Retrieves the message associated with the login response.
  *
  * @return The login response message.
  */
 public String getMessage() {
  return message;
 }

 /**
  * Sets the message associated with the login response.
  *
  * @param messageLocal The login response message.
  */
 public void setMessage(final String messageLocal) {
  this.message = messageLocal;
 }

 /**
  * Returns a string representation of the LoginOutDto.
  *
  * @return A string containing employee role and message associated with the
  *         login response.
  */
 @Override
 public String toString() {
     return "LoginOutDto [empRole=" + empRole + ", message=" + message + ", empName=" + empName + "]";
 }

}
