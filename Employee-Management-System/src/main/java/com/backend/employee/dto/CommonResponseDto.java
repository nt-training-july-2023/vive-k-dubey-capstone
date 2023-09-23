package com.backend.employee.dto;

/**
 * This class represents a Common Response Data Transfer Object (DTO) used for
 * returning messages as responses in the application.
 */
public class CommonResponseDto {

 /**
  * Field for storing the message.
  */
 private String message;

 /**
  * Constructs a new CommonResponseDto with a given message.
  *
  * @param messageLocal The message to be stored in the DTO.
  */
 public CommonResponseDto(final String messageLocal) {
  this.message = messageLocal;
 }

 /**
  * Constructs a new CommonResponseDto with no initial message.
  */
 public CommonResponseDto() {
 }

 /**
  * Retrieves the message stored in this CommonResponseDto.
  *
  * @return The message stored in the DTO.
  */
 public String getMessage() {
  return message;
 }

 /**
  * Sets the message for this CommonResponseDto.
  *
  * @param messageLocal The message to be stored in the DTO.
  */
 public void setMessage(final String messageLocal) {
  this.message = messageLocal;
 }

 /**
  * Returns a string representation of the CommonResponseDto.
  *
  * @return A string containing the message stored in the DTO.
  */
 @Override
 public String toString() {
  return "CommonResponseDto [message=" + message + "]";
 }
}
