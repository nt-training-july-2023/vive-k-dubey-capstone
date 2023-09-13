package com.backend.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A data transfer object (DTO) representing login-related information sent as a
 * response.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginOutDto {

	

	/**
	 * The role of the employee associated with the login.
	 */
	private String empRole;

	/**
	 * A message associated with the login response.
	 */
	private String message;
}
