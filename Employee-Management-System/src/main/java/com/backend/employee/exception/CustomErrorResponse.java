package com.backend.employee.exception;

/**
 * Custom error.
 */
public class CustomErrorResponse {

 /**
  * Message field.
  */
 private String message;

 /**
  *
  * @param messageLocal messageLocal.
  */
 public CustomErrorResponse(final String messageLocal) {
  this.message = messageLocal;
 }

 /**
  *
  * @return message.
  */
 public String getMessage() {
  return message;
 }

 /**
  *
  * @param messageLocal messageLocal.
  */
 public void setMessage(final String messageLocal) {
  this.message = messageLocal;
 }
}
