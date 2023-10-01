package com.backend.employee.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class RequestedDto {
    /**
     * Employee empId.
     */
    @NotBlank(message = "Employee Id can not be blank.")
    private String empId;

    /**
     * Email of manager.
     */
    @NotBlank(message = "Manager email can not be blank.")
    private String managerEmail;

    @Override
    public int hashCode() {
     return Objects.hash(empId, managerEmail);
    }

    @Override
    public boolean equals(Object obj) {
     if (this == obj)
      return true;
     if (obj == null)
      return false;
     if (getClass() != obj.getClass())
      return false;
     RequestedDto other = (RequestedDto) obj;
     return Objects.equals(empId, other.empId)
      && Objects.equals(managerEmail, other.managerEmail);
    }

    @Override
    public String toString() {
     return "RequestedDto [empId=" + empId + ", managerEmail="
      + managerEmail + "]";
    }

    /**
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * @return the managerEmail
     */
    public String getManagerEmail() {
        return managerEmail;
    }

    /**
     * @param managerEmail the managerEmail to set
     */
    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

}
