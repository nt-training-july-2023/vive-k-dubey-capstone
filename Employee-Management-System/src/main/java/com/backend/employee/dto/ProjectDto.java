package com.backend.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * A data transfer object (DTO) representing information about a project.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
