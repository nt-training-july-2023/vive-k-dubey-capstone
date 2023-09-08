package com.backend.employee.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {
  private String empName;
  private String empDesignation;
  private String empContactNo;
  private String empEmail;
  private String empLocation;
  private String empId;
  private List<String> empSkills;
  private String managerName; // Name of the manager
  private List<String> projectNames; // Names of projects under this manager
}

