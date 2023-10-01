package com.backend.employee.dto;

import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Represents an input dto to filter employee.
 */
public class FilterDto {
    /**
     * List of skills.
     */

    private List<String> skills;
    
    @Override
    public String toString() {
     return "FilterDto [skills=" + skills + ", checked=" + checked + "]";
    }

    @Override
    public int hashCode() {
     return Objects.hash(checked, skills);
    }

    @Override
    public boolean equals(Object obj) {
     if (this == obj)
      return true;
     if (obj == null)
      return false;
     if (getClass() != obj.getClass())
      return false;
     FilterDto other = (FilterDto) obj;
     return checked == other.checked
      && Objects.equals(skills, other.skills);
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
    public final List<String> getSkills() {
        return skills;
    }

    /**
     * Setter method for skills.
     *
     * @param newSkills skills.
     */
    public final void setSkills(final List<String> newSkills) {
        this.skills =newSkills;
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
