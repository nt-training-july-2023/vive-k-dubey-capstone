package com.backend.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * A data transfer object (DTO) representing information about a manager and
 * projects under their supervision.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {

	/**
	 * The unique identifier of the manager.
	 */
	private Long id;

	/**
	 * The name of the manager.
	 */
	private String empName;

	/**
	 * The designation of the manager.
	 */
	private String empDesignation;

	/**
	 * The contact number of the manager.
	 */
	private String empContactNo;

	/**
	 * The email address of the manager.
	 */
	private String empEmail;

	/**
	 * The location of the manager.
	 */
	private String empLocation;

	/**
	 * The employee ID of the manager.
	 */
	private String empId;

	/**
	 * The skills possessed by the manager.
	 */
	private List<String> empSkills;

	/**
	 * The name of the manager.
	 */
	private String managerName; // Name of the manager

	/**
	 * The names of the projects under this manager's supervision.
	 */
	private List<String> projectNames; // Names of projects under this manager

	/**
	 * Calculates a hash code value for the ManagerDto object.
	 *
	 * @return The hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(empContactNo, empDesignation, empEmail, empId, id, empLocation, empName);
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
		ManagerDto other = (ManagerDto) obj;
		return Objects.equals(empContactNo, other.empContactNo) && empDesignation.equals(other.empDesignation)
				&& Objects.equals(empEmail, other.empEmail) && Objects.equals(empId, other.empId)
				&& Objects.equals(id, other.id) && empLocation.equals(other.empLocation)
				&& Objects.equals(empName, other.empName);
	}
}
