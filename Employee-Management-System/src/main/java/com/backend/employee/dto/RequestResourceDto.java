package com.backend.employee.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents input dto for request resource.
 */
public class RequestResourceDto {
    /**
     * Employee empId.
     */
    @NotBlank(message = "Employee Id can not be null")
    private String empId;

    @Override
    public String toString() {
     return "RequestResourceDto [empId=" + empId + ", managerEmail="
      + managerEmail + ", projectId=" + projectId + ", comment=" + comment
      + "]";
    }

    @Override
    public int hashCode() {
     return Objects.hash(comment, empId, managerEmail, projectId);
    }

    @Override
    public boolean equals(Object obj) {
     if (this == obj)
      return true;
     if (obj == null)
      return false;
     if (getClass() != obj.getClass())
      return false;
     RequestResourceDto other = (RequestResourceDto) obj;
     return Objects.equals(comment, other.comment)
      && Objects.equals(empId, other.empId)
      && Objects.equals(managerEmail, other.managerEmail)
      && Objects.equals(projectId, other.projectId);
    }

    /**
     * Email of manager.
     */
    @NotBlank(message = "Manager email can not be null.")
    private String managerEmail;
    /**
     * Id of project.
     */
    @NotNull(message = "Project Id can not be null.")
    private Long projectId;
    /**
     * Comment for request resource.
     */
    @NotBlank(message = "Comment can not be empty.")
    private String comment;

    /**
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empIdParam the empId to set
     */
    public void setEmpId(final String empIdParam) {
        this.empId = empIdParam;
    }

    /**
     * @return the managerEmail
     */
    public String getManagerEmail() {
        return managerEmail;
    }

    /**
     * @param managerEmailParam the managerEmail to set
     */
    public void setManagerEmail(final String managerEmailParam) {
        this.managerEmail = managerEmailParam;
    }

    /**
     * @return the projectid
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * @param projectIdParam the projectid to set
     */
    public void setProjectId(final Long projectIdParam) {
        this.projectId = projectIdParam;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param commentParam the comment to set
     */
    public void setComment(final String commentParam) {
        this.comment = commentParam;
    }

}