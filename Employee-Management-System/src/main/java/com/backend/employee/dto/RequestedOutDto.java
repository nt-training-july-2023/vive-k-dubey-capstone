package com.backend.employee.dto;

import java.util.Objects;

/**
 * Requested class.
 */
public class RequestedOutDto {
 /**
  * Represents check on request resource.
  */
 private boolean isRequested;

 /**
  * @return the isRequested status.
  */
 public boolean isRequested() {
  return isRequested;
 }

 /**
  * Hashcode method.
  */
 @Override
 public int hashCode() {
  return Objects.hash(isRequested);
 }

 /**
  * Equals method.
  */
 @Override
 public boolean equals(final Object obj) {
  if (this == obj) {
   return true;
  }
  if (obj == null) {
   return false;
  }
  if (getClass() != obj.getClass()) {
   return false;
  }
  RequestedOutDto other = (RequestedOutDto) obj;
  return isRequested == other.isRequested;
 }

 /**
  * To string.
  */
 @Override
 public String toString() {
  return "RequestedOutDto [isRequested=" + isRequested + "]";
 }

 /**
  * @param isRequestedLocal the isRequested to set
  */
 public void setIsRequested(final boolean isRequestedLocal) {
  this.isRequested = isRequestedLocal;
 }

}
