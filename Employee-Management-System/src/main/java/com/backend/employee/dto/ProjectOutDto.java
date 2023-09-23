package com.backend.employee.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * A data transfer object (DTO) representing information about a project.
 */
public class ProjectOutDto {

 /**
  * Project Id.
  */
 private Long id;

 /**
  * Name of Project.
  */
 private String projectName;

 /**
  * ManagerId of project manager. Primary key of employee table.
  */
 private String managerId;

 /**
  * Start Date of Project.
  */
 private String startDate;

 /**
  * Required skills for the project.
  */
 private List<String> skillsRequired;

 /**
  * Description of the project.
  */
 private String description;

 /**
  * Constructs a new ProjectOutDto with the provided information.
  *
  * @param idLocal             The unique identifier of the project.
  * @param projectNameLocal    The name of the project.
  * @param managerIdLocal      The ManagerId of the project manager. This is the
  *                            primary key of the employee table.
  * @param startDateLocal      The start date of the project.
  * @param skillsRequiredLocal The list of required skills for the project.
  * @param descriptionLocal    The description of the project.
  */
 public ProjectOutDto(final Long idLocal, final String projectNameLocal,
  final String managerIdLocal, final String startDateLocal,
  final List<String> skillsRequiredLocal, final String descriptionLocal) {
  this.id = idLocal;
  this.projectName = projectNameLocal;
  this.managerId = managerIdLocal;
  this.startDate = startDateLocal;
  this.skillsRequired = new ArrayList<>(skillsRequiredLocal);
  this.description = descriptionLocal;
 }

 /**
  * Constructs a new ProjectOutDto with no initial information.
  */
 public ProjectOutDto() {

 }

 /**
  * Gets the unique identifier of the project.
  *
  * @return The unique identifier of the project.
  */
 public Long getId() {
  return id;
 }

 /**
  * Sets the unique identifier of the project.
  *
  * @param idLocal The unique identifier of the project.
  */
 public void setId(final Long idLocal) {
  this.id = idLocal;
 }

 /**
  * Gets the name of the project.
  *
  * @return The name of the project.
  */
 public String getProjectName() {
  return projectName;
 }

 /**
  * Sets the name of the project.
  *
  * @param projectNameLocal The name of the project.
  */
 public void setProjectName(final String projectNameLocal) {
  this.projectName = projectNameLocal;
 }

 /**
  * Gets the ManagerId of the project manager. This is the primary key of the
  * employee table.
  *
  * @return The ManagerId of the project manager.
  */
 public String getManagerId() {
  return managerId;
 }

 /**
  * Sets the ManagerId of the project manager. This is the primary key of the
  * employee table.
  *
  * @param managerIdLocal The ManagerId of the project manager.
  */
 public void setManagerId(final String managerIdLocal) {
  this.managerId = managerIdLocal;
 }

 /**
  * Gets the start date of the project.
  *
  * @return The start date of the project.
  */
 public String getStartDate() {
  return startDate;
 }

 /**
  * Sets the start date of the project.
  *
  * @param startDateLocal The start date of the project.
  */
 public void setStartDate(final String startDateLocal) {
  this.startDate = startDateLocal;
 }

 /**
  * Gets the list of required skills for the project.
  *
  * @return The list of required skills for the project.
  */
 public List<String> getSkillsRequired() {
  if (skillsRequired != null) {
   return new ArrayList<>(skillsRequired);
  } else {
   return new ArrayList<>();
  }
 }

 /**
  * Sets the list of required skills for the project.
  *
  * @param skillsRequiredLocal The list of required skills for the project.
  */
 public void setSkillsRequired(final List<String> skillsRequiredLocal) {
  this.skillsRequired = new ArrayList<>(skillsRequiredLocal);
 }

 /**
  * Gets the description of the project.
  *
  * @return The description of the project.
  */
 public String getDescription() {
  return description;
 }

 /**
  * Sets the description of the project.
  *
  * @param descriptionLocal The description of the project.
  */
 public void setDescription(final String descriptionLocal) {
  this.description = descriptionLocal;
 }

 /**
  * List of team members.
  */
 private List<String> teamMembers;

 /**
  *
  * @return Team members.
  */
 public List<String> getTeamMembers() {
  if (teamMembers != null) {
   return new ArrayList<>(teamMembers);
  } else {
   return new ArrayList<>();
  }
 }

 /**
  * Sets the team members.
  *
  * @param teamMembersLocal team members.
  */
 public void setTeamMembers(final List<String> teamMembersLocal) {
  this.teamMembers = new ArrayList<>(teamMembersLocal);
 }

 /**
  * Returns a string representation of the ProjectOutDto.
  *
  * @return A string containing information about the project.
  */
 @Override
 public String toString() {
  return "ProjectOutDto [id=" + id + ", projectName=" + projectName
   + ", managerId=" + managerId + ", startDate=" + startDate
   + ", skillsRequired=" + skillsRequired + ", description=" + description
   + "]";
 }
}
