package com.backend.employee.dto;

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
 public final String getEmpName() {
  return empName;
 }

 /**
  * Setter.
  *
  * @param empNameLocal empNameLocal.
  */
 public final void setEmpName(final String empNameLocal) {
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
 public final String getEmpRole() {
  return empRole;
 }

 /**
  * Sets the role of the employee associated with the login.
  *
  * @param empRoleLocal The role of the employee.
  */
 public final void setEmpRole(final String empRoleLocal) {
  this.empRole = empRoleLocal;
 }

 /**
  * Retrieves the message associated with the login response.
  *
  * @return The login response message.
  */
 public final String getMessage() {
  return message;
 }

 /**
  * Sets the message associated with the login response.
  *
  * @param messageLocal The login response message.
  */
 public final void setMessage(final String messageLocal) {
  this.message = messageLocal;
 }

 /**
  * Returns a string representation of the LoginOutDto.
  *
  * @return A string containing employee role and message associated with the
  *         login response.
  */
 @Override
 public final String toString() {
  return "LoginOutDto [empRole=" + empRole + ", message=" + message + "]";
 }
}
