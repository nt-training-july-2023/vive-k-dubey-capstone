package com.backend.employee.dto;

import javax.validation.constraints.NotNull;

public class RequestedDto {
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
