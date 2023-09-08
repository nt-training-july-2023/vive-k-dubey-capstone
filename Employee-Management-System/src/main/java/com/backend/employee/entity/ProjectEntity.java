package com.backend.employee.entity;

import java.util.List;

import com.backend.employee.dto.ProjectDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "manager_employee_id", nullable = false)
    private Long managerEmployeeId;

    
    @Column(name = "skill", nullable = false)
    private List<String> skills;
    
    public ProjectEntity(ProjectDto projectDto) {
      this.name = projectDto.getName();
      this.description = projectDto.getDescription();
      this.startDate = projectDto.getStartDate();
      this.managerEmployeeId = projectDto.getManagerEmployeeId();
      this.skills = projectDto.getSkills();
  }

}
