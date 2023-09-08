package com.backend.employee.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectOutDto {

    /**
     * Project Id.
     */
    private Long id;
    /**
     * Name of Project.
     */
    private String projectName;
    /**
     * ManagerId of project manager.
     * Primary key of employee table.
     */
    private String managerId;
    /**
     * Start Date of Project.
     */
    private String startDate;
    /**
     * Required skills for the project.
     */
    private List<String> skillsRequired;

    /**
     * description of the project.
     */
    private String description;
}