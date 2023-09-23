package com.backend.employee.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * A data transfer object (DTO) representing information about a project.
 */
public class ProjectDto {

 /**
  * The unique identifier of the project.
  */
 private Long projectId;

 /**
  * The name of the project.
  */
 private String name;

 /**
  * The description of the project.
  */
 private String description;

 /**
  * The start date of the project.
  */
 private String startDate;

 /**
  * The employee ID of the manager responsible for the project.
  */
 private Long managerEmployeeId;

 /**
  * The skills required for the project.
  */
 private List<String> skills;

 /**
  * The head or leader of the project.
  */
 private String head;
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
  * Constructs a new ProjectDto with no initial information.
  */
 public ProjectDto() {
  /**
   * Default constructor with no initial information.
   */
 }

 /**
  * Gets the unique identifier of the project.
  *
  * @return The unique identifier of the project.
  */
 public Long getProjectId() {
  return projectId;
 }

 /**
  * Sets the unique identifier of the project.
  *
  * @param projectIdLocal The unique identifier of the project.
  */
 public void setProjectId(final Long projectIdLocal) {
  this.projectId = projectIdLocal;
 }

 /**
  * Gets the name of the project.
  *
  * @return The name of the project.
  */
 public String getName() {
  return name;
 }

 /**
  * Sets the name of the project.
  *
  * @param nameLocal The name of the project.
  */
 public void setName(final String nameLocal) {
  this.name = nameLocal;
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
  * Gets the employee ID of the manager responsible for the project.
  *
  * @return The employee ID of the manager responsible for the project.
  */
 public Long getManagerEmployeeId() {
  return managerEmployeeId;
 }

 /**
  * Sets the employee ID of the manager responsible for the project.
  *
  * @param managerEmployeeIdLocal The employee ID of the manager responsible for
  *                               the project.
  */
 public void setManagerEmployeeId(final Long managerEmployeeIdLocal) {
  this.managerEmployeeId = managerEmployeeIdLocal;
 }

 /**
  * Gets the skills required for the project.
  *
  * @return The skills required for the project.
  */
 public List<String> getSkills() {
  if (skills != null) {
   return new ArrayList<>(skills);
  } else {
   return new ArrayList<>();
  }
 }

 /**
  * Sets the skills required for the project.
  *
  * @param skillsLocal The skills required for the project.
  */
 public void setSkills(final List<String> skillsLocal) {
  this.skills = new ArrayList<>(skillsLocal);
 }

 /**
  * Gets the head or leader of the project.
  *
  * @return The head or leader of the project.
  */
 public String getHead() {
  return head;
 }

 /**
  * Sets the head or leader of the project.
  *
  * @param headLocal The head or leader of the project.
  */
 public void setHead(final String headLocal) {
  this.head = headLocal;
 }

 /**
  * Returns a string representation of the ProjectDto.
  *
  * @return A string containing information about the project.
  */
 @Override
 public String toString() {
  return "ProjectDto [projectId=" + projectId + ", name=" + name
   + ", description=" + description + ", startDate=" + startDate
   + ", managerEmployeeId=" + managerEmployeeId + ", skills=" + skills
   + ", head=" + head + "]";
 }
}
