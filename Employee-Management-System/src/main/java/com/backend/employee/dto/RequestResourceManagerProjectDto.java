package com.backend.employee.dto;

public class RequestResourceManagerProjectDto {

 /**
  * The unique identifier of the project.
  */
 private Long id;

 /**
  * The name of the project.
  */
 private String projectName;

 /**
  * Gets the unique identifier of the project.
  *
  * @return The project's identifier.
  */
 public Long getId() {
     return id;
 }

 /**
  * Sets the unique identifier of the project.
  *
  * @param idParam The project's identifier.
  */
 public void setId(final Long idParam) {
     this.id = idParam;
 }

 /**
  * Gets the name of the project.
  *
  * @return The project's name.
  */
 public String getProjectName() {
     return projectName;
 }

 /**
  * Sets the name of the project.
  *
  * @param projectNameParam The project's name.
  */
 public void setProjectName(final String projectNameParam) {
     this.projectName = projectNameParam;
 }
}