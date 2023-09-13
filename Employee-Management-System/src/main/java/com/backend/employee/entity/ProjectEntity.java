package com.backend.employee.entity;

import java.util.List;
import java.util.Objects;

import com.backend.employee.dto.ProjectDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An entity class representing a project.
 */
@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity {

	/**
	 * The unique identifier of the project.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Long projectId;

	/**
	 * The name of the project.
	 */
	@Column(name = "name", nullable = false)
	private String name;

	/**
	 * The description of the project.
	 */
	@Column(name = "description", nullable = false)
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
	public ProjectEntity(ProjectDto projectDto) {
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
		return Objects.hash(description, projectId, managerEmployeeId, name, skills, startDate);
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param obj The reference object with which to compare.
	 * @return True if this object is the same as the obj argument; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectEntity other = (ProjectEntity) obj;
		return Objects.equals(description, other.description) && Objects.equals(projectId, other.projectId)
				&& Objects.equals(managerEmployeeId, other.managerEmployeeId) && Objects.equals(name, other.name)
				&& Objects.equals(skills, other.skills) && Objects.equals(startDate, other.startDate);
	}

}
