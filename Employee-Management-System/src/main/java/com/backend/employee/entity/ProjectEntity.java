package com.backend.employee.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.backend.employee.dto.ProjectDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * An entity class representing a project.
 */
@Entity
@Table(name = "project")
public class ProjectEntity {

 /**
  * The unique identifier of the project.
  */
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "project_id")
 private Long projectId;

 /**
  * Constructor.
  */
 public ProjectEntity() {
  super();
 }

 /**
  * Projectid.
  *
  * @return projectId.
  */
 public Long getProjectId() {
  return projectId;
 }

 /**
  * Setter.
  *
  * @param projectIdLocal The local project id.
  */

 public void setProjectId(final Long projectIdLocal) {
  this.projectId = projectIdLocal;
 }

 /**
  * Getter.
  *
  * @return name.
  */
 public String getName() {
  return name;
 }

 /**
  * Setter.
  *
  * @param nameLocal name field.
  */
 public void setName(final String nameLocal) {
  this.name = nameLocal;
 }

 /**
  * Getter.
  *
  * @return The description.
  */
 public String getDescription() {
  return description;
 }

 /**
  * Setter.
  *
  * @param descriptionLocal The description.
  */
 public void setDescription(final String descriptionLocal) {
  this.description = descriptionLocal;
 }

 /**
  * Getter.
  *
  * @return startDate.
  */
 public String getStartDate() {
  return startDate;
 }

 /**
  * Setter.
  *
  * @param startDateLocal startDate.
  */
 public void setStartDate(final String startDateLocal) {
  this.startDate = startDateLocal;
 }

 /**
  * Getter.
  *
  * @return managerEmployeeId.
  */
 public Long getManagerEmployeeId() {
  return managerEmployeeId;
 }

 /**
  * Setter.
  *
  * @param managerEmployeeIdLocal managerEmployeeId.
  */
 public void setManagerEmployeeId(final Long managerEmployeeIdLocal) {
  this.managerEmployeeId = managerEmployeeIdLocal;
 }

 /**
  * Getter.
  *
  * @return skills.
  */
 public List<String> getSkills() {
  if (skills != null) {
   return new ArrayList<>(skills);
  } else {
   return new ArrayList<>(); // Or handle it differently based on your use case
  }
 }

 /**
  * Setter.
  *
  * @param skillsLocal Skills of the.
  */
 public void setSkills(final List<String> skillsLocal) {
  this.skills = new ArrayList<>(skillsLocal);
 }

 /**
  * The name of the project.
  */
 @Column(name = "name", nullable = false)
 private String name;

 /**
  * The description of the project.
  */
 @Column(name = "description",columnDefinition = "TEXT", nullable = false)
 private String description;

 /**
  * The start date of the project.
  */
 @Column(name = "start_date", nullable = false)
 private String startDate;

 /**
  * The employee ID of the manager responsible for the project.
  */
 @Column(name = "manager_employee_id", nullable = false)
 private Long managerEmployeeId;

 /**
  * The skills required for the project.
  */
 @Column(name = "skill", nullable = false)
 private List<String> skills;

 /**
  * Constructs a new ProjectEntity object from a ProjectDto.
  *
  * @param projectDto The ProjectDto containing project information.
  */
 public ProjectEntity(final ProjectDto projectDto) {
  this.name = projectDto.getName();
  this.description = projectDto.getDescription();
  this.startDate = projectDto.getStartDate();
  this.managerEmployeeId = projectDto.getManagerEmployeeId();
  this.skills = projectDto.getSkills();
 }

 /**
  * Calculates a hash code value for the ProjectEntity object.
  *
  * @return The hash code value for this object.
  */
 @Override
 public int hashCode() {
  return Objects.hash(description, projectId, managerEmployeeId, name,
   skills, startDate);
 }

 /**
  * Indicates whether some other object is "equal to" this one.
  *
  * @param obj The reference object with which to compare.
  * @return True if object is the same as the obj argument; false otherwise.
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
  ProjectEntity other = (ProjectEntity) obj;
  return Objects.equals(description, other.description)
   && Objects.equals(projectId, other.projectId)
   && Objects.equals(managerEmployeeId, other.managerEmployeeId)
   && Objects.equals(name, other.name)
   && Objects.equals(skills, other.skills)
   && Objects.equals(startDate, other.startDate);
 }

}
