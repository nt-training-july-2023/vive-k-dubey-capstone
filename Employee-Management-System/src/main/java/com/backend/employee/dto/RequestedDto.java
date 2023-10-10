package com.backend.employee.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class RequestedDto {
 /**
  * Employee empId.
  */
 @NotBlank(message = "Employee Id can not be blank.")
 private String empId;

 /**
  * Email of manager.
  */
 @NotBlank(message = "Manager email can not be blank.")
 private String managerEmail;

 /**
  * Hashcode.
  */
 @Override
 public int hashCode() {
  return Objects.hash(empId, managerEmail);
 }

 /**
  * Equals.
  */
 @Override
 public boolean equals(final Object obj) {
  if (this == obj) {
   return true;
  }
  if (obj == null) {
   return false;
  }
  if (getClass() != obj.getClass()) {
   return false;
  }
  RequestedDto other = (RequestedDto) obj;
  return Objects.equals(empId, other.empId)
   && Objects.equals(managerEmail, other.managerEmail);
 }

 /**
  * To string.
  */
 @Override
 public String toString() {
  return "RequestedDto [empId=" + empId + ", managerEmail=" + managerEmail
   + "]";
 }

 /**
  * @return the empId
  */
 public String getEmpId() {
  return empId;
 }

 /**
  * @param empIdLocal the empId to set
  */
 public void setEmpId(final String empIdLocal) {
  this.empId = empIdLocal;
 }

 /**
  * @return the managerEmail
  */
 public String getManagerEmail() {
  return managerEmail;
 }

 /**
  * @param managerEmailLocal the managerEmail to set
  */
 public void setManagerEmail(final String managerEmailLocal) {
  this.managerEmail = managerEmailLocal;
 }

}
