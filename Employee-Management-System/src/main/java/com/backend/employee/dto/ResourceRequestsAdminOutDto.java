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
  * @param id the id to set
  */
 public void setId(Long id) {
     this.id = id;
 }
 /**
  * Instance of employee id.
  */
 private Long employeeId;
 /**
  * Represnts employee name.
  */
 private String employeeName;
 @Override
 public String toString() {
  return "ResourceRequestsAdminOutDto [id=" + id + ", employeeId="
   + employeeId + ", employeeName=" + employeeName + ", managerName="
   + managerName + ", projectName=" + projectName + ", comment=" + comment
   + ", managerId=" + managerId + ", projectId=" + projectId + "]";
 }
 @Override
 public int hashCode() {
  return Objects.hash(comment, employeeId, employeeName, id, managerId,
   managerName, projectId, projectName);
 }
 @Override
 public boolean equals(Object obj) {
  if (this == obj)
   return true;
  if (obj == null)
   return false;
  if (getClass() != obj.getClass())
   return false;
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
  * Represnts  comment of resource requests.
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
  * @param employeeName the employeeName to set
  */
 public void setEmployeeName(String employeeName) {
     this.employeeName = employeeName;
 }
 /**
  * @return the managerName
  */
 public String getManagerName() {
     return managerName;
 }
 /**
  * @param managerName the managerName to set
  */
 public void setManagerName(String managerName) {
     this.managerName = managerName;
 }
 /**
  * @return the projectName
  */
 public String getProjectName() {
     return projectName;
 }
 /**
  * @param projectName the projectName to set
  */
 public void setProjectName(String projectName) {
     this.projectName = projectName;
 }
 /**
  * @return the comment
  */
 public String getComment() {
     return comment;
 }
 /**
  * @param comment the comment to set
  */
 public void setComment(String comment) {
     this.comment = comment;
 }
 /**
  * @return the employeeId
  */
 public Long getEmployeeId() {
     return employeeId;
 }
 /**
  * @param employeeId the employeeId to set
  */
 public void setEmployeeId(Long employeeId) {
     this.employeeId = employeeId;
 }
 /**
  * @return the managerId
  */
 public Long getManagerId() {
     return managerId;
 }
 /**
  * @param managerId the managerId to set
  */
 public void setManagerId(Long managerId) {
     this.managerId = managerId;
 }
 /**
  * @return the projectId
  */
 public Long getProjectId() {
     return projectId;
 }
 /**
  * @param projectId the projectId to set
  */
 public void setProjectId(Long projectId) {
     this.projectId = projectId;
 }
}
