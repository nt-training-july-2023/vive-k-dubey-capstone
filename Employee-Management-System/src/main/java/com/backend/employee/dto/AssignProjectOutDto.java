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

 /**
  *
  * @return projectId.
  */
 public Long getProjectId() {
  return projectId;
 }

 /**
  *
  * @param projectIdLocal projectId.
  */
 public void setProjectId(final Long projectIdLocal) {
  this.projectId = projectIdLocal;
 }

 /**
  * To string.
  */
 @Override
 public String toString() {
  return "AssignProjectOutDto [projectId=" + projectId + ", name=" + name
   + "]";
 }

 /**
  * Hashcode method.
  */
 @Override
 public int hashCode() {
  return Objects.hash(name, projectId);
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
  AssignProjectOutDto other = (AssignProjectOutDto) obj;
  return Objects.equals(name, other.name)
   && Objects.equals(projectId, other.projectId);
 }

 /**
  *
  * @return name of the employee.
  */
 public String getName() {
  return name;
 }

 /**
  *
  * @param nameLocal
  */
 public void setName(final String nameLocal) {
  this.name = nameLocal;
 }

}
