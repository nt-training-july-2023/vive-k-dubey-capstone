package com.backend.employee.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.NotNull;

/**
 * Represents an input dto to filter employee.
 */
public class FilterDto {
 /**
  * List of skills.
  */
 @NotNull(message = "Field can not be null")
 private List<String> skills;

 /**
  * To string method.
  */
 @Override
 public String toString() {
  return "FilterDto [skills=" + skills + ", checked=" + checked + "]";
 }

 /**
  * Hashcode method.
  */
 @Override
 public int hashCode() {
  return Objects.hash(checked, skills);
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
  FilterDto other = (FilterDto) obj;
  return checked == other.checked && Objects.equals(skills, other.skills);
 }

 /**
  * Contains boolean value.
  */
 private boolean checked;

 /**
  * getter method for skills.
  *
  * @return List of skills.
  */
 public List<String> getSkills() {
  if (skills != null) {
   return new ArrayList<>(skills);
  } else {
   return new ArrayList<>();
  }
 }

 /**
  * Setter method for skills.
  *
  * @param newSkills skills.
  */
 public final void setSkills(final List<String> newSkills) {
  this.skills = new ArrayList<>(newSkills);
 }

 /**
  * @return the isChecked
  */
 public boolean getChecked() {
  return checked;
 }

 /**
  * @param checkedParam the isChecked to set
  */
 public void setChecked(final boolean checkedParam) {
  this.checked = checkedParam;
 }
}
