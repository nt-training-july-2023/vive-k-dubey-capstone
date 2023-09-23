package com.backend.employee.dto;

import javax.validation.constraints.NotNull;

/**
 * Represents input dto for request resource.
 */
public class RequestResourceDto {
    /**
     * Employee empId.
     */
    @NotNull(message = "empId can not be null")
    private String empId;

    /**
     * Email of manager.
     */
    @NotNull(message = "manager email can not be null")
    private String managerEmail;
    /**
     * Id of project.
     */
    @NotNull(message = "project id can not be null")
    private Long projectId;
    /**
     * Comment for request resource.
     */
    @NotNull(message = "comment can not be null")
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