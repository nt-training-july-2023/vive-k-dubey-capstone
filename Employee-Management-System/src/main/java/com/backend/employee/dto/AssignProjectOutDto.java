package com.backend.employee.dto;

import java.util.Objects;

public class AssignProjectOutDto {
 
 /**
  * The unique identifier of the project to be assigned.
  */
 private Long projectId;
 /**
  * The name of the project.
  */
 private String name;
 public Long getProjectId() {
  return projectId;
 }
 public void setProjectId(Long projectId) {
  this.projectId = projectId;
 }
 @Override
 public String toString() {
  return "AssignProjectOutDto [projectId=" + projectId + ", name=" + name
   + "]";
 }
 @Override
 public int hashCode() {
  return Objects.hash(name, projectId);
 }
 @Override
 public boolean equals(Object obj) {
  if (this == obj)
   return true;
  if (obj == null)
   return false;
  if (getClass() != obj.getClass())
   return false;
  AssignProjectOutDto other = (AssignProjectOutDto) obj;
  return Objects.equals(name, other.name)
   && Objects.equals(projectId, other.projectId);
 }
 public String getName() {
  return name;
 }
 public void setName(String name) {
  this.name = name;
 }

}
