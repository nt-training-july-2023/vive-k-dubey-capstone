package com.backend.employee.dto;

import java.util.Objects;

public class RequestedOutDto {
 /**
  * Represents check on request resource.
  */
 private boolean isRequested;

 /**
  * @return the isRequested
  */
 public boolean isRequested() {
     return isRequested;
 }

 @Override
 public int hashCode() {
  return Objects.hash(isRequested);
 }

 @Override
 public boolean equals(Object obj) {
  if (this == obj)
   return true;
  if (obj == null)
   return false;
  if (getClass() != obj.getClass())
   return false;
  RequestedOutDto other = (RequestedOutDto) obj;
  return isRequested == other.isRequested;
 }

 @Override
 public String toString() {
  return "RequestedOutDto [isRequested=" + isRequested + "]";
 }

 /**
  * @param isRequested the isRequested to set
  */
 public void setIsRequested(boolean isRequested) {
     this.isRequested = isRequested;
 }
 
}
