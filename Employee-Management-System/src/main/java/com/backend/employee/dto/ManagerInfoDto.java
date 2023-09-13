package com.backend.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A data transfer object (DTO) representing information about a manager's
 * details.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerInfoDto {

	/**
	 * The unique identifier of the manager.
	 */
	private Long id;

	/**
	 * The name of the manager.
	 */
	private String managerName;

	/**
	 * The employee ID of the manager.
	 */
	private String managerEmployeeId;
}
