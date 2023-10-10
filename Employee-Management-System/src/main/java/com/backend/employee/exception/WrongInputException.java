package com.backend.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * exception when input is wrong.
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongInputException extends Exception {
 /**
  * Constructor to call constructor of Exception class.
  *
  * @param message accept a message.
  */
 public WrongInputException(final String message) {
  super(message);
 }

}
