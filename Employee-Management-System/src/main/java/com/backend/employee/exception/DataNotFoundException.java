package com.backend.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to represent a situation where requested data is not found.
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

  /**
   * Constructs a new DataNotFoundException with the specified detail message.
   *
   * @param message The detail message.
   */
  public DataNotFoundException(final String message) {
    super(message);
  }
}
