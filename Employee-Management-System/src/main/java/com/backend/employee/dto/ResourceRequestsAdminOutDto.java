package com.backend.employee.dto;

import java.util.Objects;

public class ResourceRequestsAdminOutDto {
 /**
  * Instance of id.
  */
 private Long id;

 /**
  * @return the id
  */
 public Long getId() {
  return id;
 }

 /**
  * @param idLocal the id to set
  */
 public void setId(final Long idLocal) {
  this.id = idLocal;
 }

 /**
  * Instance of employee id.
  */
 private Long employeeId;
 /**
  * Represnts employee name.
  */
 private String employeeName;

 /**
  * To string method.
  */
 @Override
 public String toString() {
  return "ResourceRequestsAdminOutDto [id=" + id + ", employeeId="
   + employeeId + ", employeeName=" + employeeName + ", managerName="
   + managerName + ", projectName=" + projectName + ", comment=" + comment
   + ", managerId=" + managerId + ", projectId=" + projectId + "]";
 }

 /**
  * Hashcode method.
  */
 @Override
 public int hashCode() {
  return Objects.hash(comment, employeeId, employeeName, id, managerId,
   managerName, projectId, projectName);
 }

 /**
  * Equals method.
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
  ResourceRequestsAdminOutDto other = (ResourceRequestsAdminOutDto) obj;
  return Objects.equals(comment, other.comment)
   && Objects.equals(employeeId, other.employeeId)
   && Objects.equals(employeeName, other.employeeName)
   && Objects.equals(id, other.id)
   && Objects.equals(managerId, other.managerId)
   && Objects.equals(managerName, other.managerName)
   && Objects.equals(projectId, other.projectId)
   && Objects.equals(projectName, other.projectName);
 }

 /**
  * Represnts manager name.
  */
 private String managerName;
 /**
  * Represnts project name.
  */
 private String projectName;
 /**
  * Represnts comment of resource requests.
  */
 private String comment;
 /**
  * Reperesents managerId.
  */
 private Long managerId;
 /**
  * Represents Project Id.
  */
 private Long projectId;

 /**
  * @return the employeeName
  */
 public String getEmployeeName() {
  return employeeName;
 }

 /**
  * @param employeeNameLocal the employeeName to set
  */
 public void setEmployeeName(final String employeeNameLocal) {
  this.employeeName = employeeNameLocal;
 }

 /**
  * @return the managerName
  */
 public String getManagerName() {
  return managerName;
 }

 /**
  * @param managerNameLocal the managerName to set
  */
 public void setManagerName(final String managerNameLocal) {
  this.managerName = managerNameLocal;
 }

 /**
  * @return the projectName
  */
 public String getProjectName() {
  return projectName;
 }

 /**
  * @param projectNameLocal the projectName to set
  */
 public void setProjectName(final String projectNameLocal) {
  this.projectName = projectNameLocal;
 }

 /**
  * @return the comment
  */
 public String getComment() {
  return comment;
 }

 /**
  * @param commentLocal the comment to set
  */
 public void setComment(final String commentLocal) {
  this.comment = commentLocal;
 }

 /**
  * @return the employeeId
  */
 public Long getEmployeeId() {
  return employeeId;
 }

 /**
  * @param employeeIdLocal the employeeId to set
  */
 public void setEmployeeId(final Long employeeIdLocal) {
  this.employeeId = employeeIdLocal;
 }

 /**
  * @return the managerId
  */
 public Long getManagerId() {
  return managerId;
 }

 /**
  * @param managerIdLocal the managerId to set
  */
 public void setManagerId(final Long managerIdLocal) {
  this.managerId = managerIdLocal;
 }

 /**
  * @return the projectId
  */
 public Long getProjectId() {
  return projectId;
 }

 /**
  * @param projectIdLocal the projectId to set
  */
 public void setProjectId(final Long projectIdLocal) {
  this.projectId = projectIdLocal;
 }
}
