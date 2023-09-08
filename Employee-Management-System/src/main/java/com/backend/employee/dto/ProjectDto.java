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
public class ProjectDto {

  private Long projectId;
  private String name;
  private String description;
  private String startDate;
  private Long managerEmployeeId; 
  private List<String> skills;
  private String head;
}