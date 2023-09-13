package com.backend.employee.entity;

import java.util.List;

import com.backend.employee.dto.RegisterDto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
 * entity class.
 */
@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEntity {

	/**
	 * Parameterized constructor.
	 * 
	 * @param registerDto
	 */
	public RegisterEntity(final RegisterDto registerDto) {
		this.empId = registerDto.getEmpId();
		this.empName = registerDto.getEmpName();
		this.empDesignation = registerDto.getEmpDesignation();
		this.empContactNo = registerDto.getEmpContactNo();
		this.empPassword = registerDto.getEmpPassword();
		this.empEmail = registerDto.getEmpEmail();
		this.empDOB = registerDto.getEmpDOB();
		this.empDOJ = registerDto.getEmpDOJ();
		this.empLocation = registerDto.getEmpLocation();
		this.empRole = registerDto.getEmpRole();
	}

	/**
	 * The unique identifier of the employee.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * The employee ID.
	 */
	@Column(unique = true, nullable = false)
	private String empId;
	/**
	 * The name of the employee.
	 */
	@Column(unique = false, nullable = false)
	private String empName;
	/**
	 * The designation of the employee.
	 */
	private String empDesignation;
	/**
	 * The contact number of the employee.
	 */
	@Column(unique = true)
	private String empContactNo;
	/**
	 * The password of the employee.
	 */
	private String empPassword;

	/**
	 * The email of the employee.
	 */
	@Column(unique = true)
	private String empEmail;
	/**
	 * The date of birth of the employee.
	 */
	private String empDOB;
	/**
	 * The date of joining of the employee.
	 */
	private String empDOJ;
	/**
	 * The location of the employee.
	 */
	@Column(nullable = false)
	private String empLocation;
	/**
	 * The role of the employee.
	 */
	private String empRole;

	/**
	 * The skills of the employee.
	 */

	@Column
	private List<String> empSkills;

	/**
	 * The managerId (primary key) of the employee.
	 */

	@Column
	private Long managerId;

	/**
	 * The id of project alloted to the employee.
	 */

	private Long projectId;

}
