package com.backend.employee.dto;

import java.util.Objects;

/**
 * A data transfer object (DTO) representing information about a manager's
 * details.
 */
public class ManagerInfoDto {

 /**
  * The unique identifier of the manager.
  */
 private Long id;

 /**
  * The name of the manager.
  */
 private String managerName;

 /**
  * The employee ID of the manager.
  */
 private String managerEmployeeId;

 /**
  * Constructs a new ManagerInfoDto with the provided information.
  *
  * @param idLocal                The unique identifier of the manager.
  * @param managerNameLocal       The name of the manager.
  * @param managerEmployeeIdLocal The employee ID of the manager.
  */
 public ManagerInfoDto(final Long idLocal, final String managerNameLocal,
  final String managerEmployeeIdLocal) {
  this.id = idLocal;
  this.managerName = managerNameLocal;
  this.managerEmployeeId = managerEmployeeIdLocal;
 }

 /**
  * Constructs a new ManagerInfoDto with no initial information.
  */
 public ManagerInfoDto() {

 }

 /**
  * Returns a string representation of the ManagerInfoDto.
  *
  * @return A string containing information about the manager's details.
  */
 @Override
 public String toString() {
  return "ManagerInfoDto [id=" + id + ", managerName=" + managerName
   + ", managerEmployeeId=" + managerEmployeeId + "]";
 }

 /**
  * Gets the unique identifier of the manager.
  *
  * @return The unique identifier of the manager.
  */
 public Long getId() {
  return id;
 }

 /**
  * Sets the unique identifier of the manager.
  *
  * @param idLocal The unique identifier of the manager.
  */
 public void setId(final Long idLocal) {
  this.id = idLocal;
 }

 /**
  * Gets the name of the manager.
  *
  * @return The name of the manager.
  */
 public String getManagerName() {
  return managerName;
 }

 /**
  * Sets the name of the manager.
  *
  * @param managerNameLocal The name of the manager.
  */
 public void setManagerName(final String managerNameLocal) {
  this.managerName = managerNameLocal;
 }

 /**
  * Gets the employee ID of the manager.
  *
  * @return The employee ID of the manager.
  */
 public String getManagerEmployeeId() {
  return managerEmployeeId;
 }

 @Override
 public int hashCode() {
  return Objects.hash(id, managerEmployeeId, managerName);
 }

 @Override
 public boolean equals(Object obj) {
  if (this == obj)
   return true;
  if (obj == null)
   return false;
  if (getClass() != obj.getClass())
   return false;
  ManagerInfoDto other = (ManagerInfoDto) obj;
  return Objects.equals(id, other.id)
   && Objects.equals(managerEmployeeId, other.managerEmployeeId)
   && Objects.equals(managerName, other.managerName);
 }

 /**
  * Sets the employee ID of the manager.
  *
  * @param managerEmployeeIdLocal The employee ID of the manager.
  */
 public void setManagerEmployeeId(final String managerEmployeeIdLocal) {
  this.managerEmployeeId = managerEmployeeIdLocal;
 }
}
