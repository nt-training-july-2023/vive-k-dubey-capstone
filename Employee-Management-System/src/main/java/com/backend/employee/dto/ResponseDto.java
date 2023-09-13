package com.backend.employee.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A response data transfer object (DTO) containing a message, status code, and
 * a list of data.
 *
 * @param <T> The type of data contained in the response.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {

	/**
	 * The message associated with the response.
	 */
	private String message;

	/**
	 * The status code indicating the outcome of the operation.
	 */
	private int statusCode;

	/**
	 * The list of data included in the response.
	 */
	private List<T> data;
}
