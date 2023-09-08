package com.backend.employee.repo;

import com.backend.employee.entity.ProjectEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<ProjectEntity, Long> {
    
  
  List<ProjectEntity> findAll();

  ProjectEntity findByProjectId(Long projectId);

  List<ProjectEntity> findAllByManagerEmployeeId(Long managerId);

  ProjectEntity findByName(String name);
}
