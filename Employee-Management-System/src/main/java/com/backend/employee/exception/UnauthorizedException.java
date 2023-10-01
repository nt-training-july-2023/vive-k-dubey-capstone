package com.backend.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to represent a situation where a duplicate email is
 * detected.
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

 private static final long serialVersionUID = 1L;

 /**
  * Constructs a new DuplicateEmailException with the specified detail message.
  *
  * @param message The detail message.
  */
 public UnauthorizedException(final String message) {
  super(message);
 }
}
