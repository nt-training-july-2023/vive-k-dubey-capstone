package com.backend.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.employee.dto.RequestResourceDto;
import com.backend.employee.dto.RequestResourceManagerProjectDto;
import com.backend.employee.dto.RequestedDto;
import com.backend.employee.dto.RequestedOutDto;
import com.backend.employee.entity.ProjectEntity;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.entity.RequestResource;
import com.backend.employee.repo.ProjectRepo;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.repo.RequestResourceRepo;

/**
 * Represents service for request resource.
 */
@Service
public class RequestResourceService {
 /**
  * Instance of EmployeeRepository.
  */
 @Autowired
 private RegisterRepo registerRepository;
 /**
  * Instance of RequestResourceRepository.
  */
 @Autowired
 private RequestResourceRepo requestResourceRepository;

 @Autowired
 private ProjectRepo projectRepository;

 /**
  * Service method to create resource request.
  *
  * @param requestResourceDto Input request resource data.
  */
 public void createRequestResource(
  final RequestResourceDto requestResourceDto) {
  Optional<RegisterEntity> employee = registerRepository
   .findByEmpId(requestResourceDto.getEmpId());
  Optional<RegisterEntity> manager = registerRepository
   .findByEmpEmail(requestResourceDto.getManagerEmail());

  RequestResource requestResource = new RequestResource();
  requestResource.setEmployeeId(employee.get().getId());
  requestResource.setManagerId(manager.get().getId());
  requestResource.setProjectId(requestResourceDto.getProjectId());
  requestResource.setComment(requestResourceDto.getComment());

  requestResourceRepository.save(requestResource);
 }

 public RequestedOutDto isRequested(final RequestedDto requestedDto) {
  RequestedOutDto requestedOutDto = new RequestedOutDto();
  Optional<RegisterEntity> employee = registerRepository
   .findByEmpId(requestedDto.getEmpId());
  Optional<RegisterEntity> manager = registerRepository
   .findByEmpEmail(requestedDto.getManagerEmail());
  if (requestResourceRepository.findByEmployeeIdAndManagerId(
   employee.get().getId(), manager.get().getId()) != null) {
   requestedOutDto.setIsRequested(true);
  } else {
   requestedOutDto.setIsRequested(false);
  }
  return requestedOutDto;
 }

 public List<RequestResourceManagerProjectDto> getAllByManagerEmail(
  final String email) {

  Optional<RegisterEntity> manager = registerRepository
   .findByEmpEmail(email);
  List<ProjectEntity> projectList = projectRepository
   .findAllByManagerEmployeeId(manager.get().getId());
  List<RequestResourceManagerProjectDto> projectOutList = new ArrayList<RequestResourceManagerProjectDto>();

  for (ProjectEntity project : projectList) {
   RequestResourceManagerProjectDto projectOutDto = new RequestResourceManagerProjectDto();

   projectOutDto.setId(project.getProjectId());
   projectOutDto.setProjectName(project.getName());

   projectOutList.add(projectOutDto);

  }
  return projectOutList;
 }

}
