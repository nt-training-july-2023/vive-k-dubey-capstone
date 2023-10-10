package com.backend.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.RequestResourceDto;
import com.backend.employee.dto.RequestResourceManagerProjectDto;
import com.backend.employee.dto.RequestedDto;
import com.backend.employee.dto.RequestedOutDto;
import com.backend.employee.dto.ResourceRequestsAdminOutDto;
import com.backend.employee.entity.ProjectEntity;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.entity.RequestResource;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.DataNotFoundException;
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
 /**
  * Instance of projectRepository.
  */
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

 /**
  *
  * @param requestedDto requestedDto.
  * @return RequestedOutDto.
  */
 public RequestedOutDto isRequested(final RequestedDto requestedDto) {
  RequestedOutDto requestedOutDto = new RequestedOutDto();
  Optional<RegisterEntity> employee = registerRepository
   .findByEmpId(requestedDto.getEmpId());
  Optional<RegisterEntity> manager = registerRepository
   .findByEmpEmail(requestedDto.getManagerEmail());
  if (requestResourceRepository.findByEmployeeIdAndManagerId(
   employee.orElse(null).getId(), manager.orElse(null).getId()) != null) {
   requestedOutDto.setIsRequested(true);
  } else {
   requestedOutDto.setIsRequested(false);
  }
  return requestedOutDto;
 }

 /**
  *
  * @param email email.
  * @return RequestResourceManagerProjectDto.
  */
 public List<RequestResourceManagerProjectDto> getAllByManagerEmail(
  final String email) {

  Optional<RegisterEntity> manager = registerRepository
   .findByEmpEmail(email);
  List<ProjectEntity> projectList = projectRepository
   .findAllByManagerEmployeeId(manager.get().getId());
  List<RequestResourceManagerProjectDto> projectOutList =
   new ArrayList<RequestResourceManagerProjectDto>();

  for (ProjectEntity project : projectList) {
   RequestResourceManagerProjectDto projectOutDto =
    new RequestResourceManagerProjectDto();

   projectOutDto.setId(project.getProjectId());
   projectOutDto.setProjectName(project.getName());

   projectOutList.add(projectOutDto);

  }
  return projectOutList;
 }

 /**
  *
  * @return getAllResourceRequests.
  */
 public List<ResourceRequestsAdminOutDto> getAllResourceRequests() {

  List<RequestResource> requestList = requestResourceRepository.findAll();
  if (requestList.isEmpty()) {
   throw new DataNotFoundException(
    "Currently, there are no resource requests to be entertained.");
  }
  List<ResourceRequestsAdminOutDto> outRequestList =
   new ArrayList<ResourceRequestsAdminOutDto>();
  for (RequestResource requestResource : requestList) {
   ResourceRequestsAdminOutDto resourceRequestsAdminOutDto =
    new ResourceRequestsAdminOutDto();

   RegisterEntity employee = registerRepository
    .findById(requestResource.getEmployeeId()).orElse(null);
   RegisterEntity manager = registerRepository
    .findById(requestResource.getManagerId()).orElse(null);

   ProjectEntity project = projectRepository
    .findByProjectId(requestResource.getProjectId());

   resourceRequestsAdminOutDto.setEmployeeName(employee.getEmpName());
   resourceRequestsAdminOutDto.setManagerName(manager.getEmpName());
   resourceRequestsAdminOutDto.setProjectName(project.getName());
   resourceRequestsAdminOutDto.setComment(requestResource.getComment());
   resourceRequestsAdminOutDto.setId(requestResource.getId());
   resourceRequestsAdminOutDto
    .setEmployeeId(requestResource.getEmployeeId());
   resourceRequestsAdminOutDto.setManagerId(requestResource.getManagerId());
   resourceRequestsAdminOutDto.setProjectId(requestResource.getProjectId());

   outRequestList.add(resourceRequestsAdminOutDto);
  }
  return outRequestList;
 }

 /**
  *
  * @param id id.
  * @return CommonResponseDto.
  * @throws WrongInputException
  */
 public CommonResponseDto acceptRequest(final Long id) {
  RequestResource request = requestResourceRepository.findById(id)
   .orElse(null);
  if (request == null) {
   throw new DataNotFoundException("Request with ID " + id + " not found");
}
  RegisterEntity employee = registerRepository
   .findById(request.getEmployeeId()).orElse(null);
  if (employee == null) {
   throw new DataNotFoundException("Employee with ID "
  + request.getEmployeeId() + " not found");
}

  if (employee.getProjectId() != null) {
   throw new DataAlreadyExistsException("Employee already has a project");
  }
  employee.setProjectId(request.getProjectId());
  employee.setManagerId(request.getManagerId());
  registerRepository.save(employee);
  rejectResourceRequest(id);

  List<RequestResource> employeeRequests = requestResourceRepository
   .findByEmployeeId(employee.getId());
  for (RequestResource req : employeeRequests) {

   rejectResourceRequest(req.getId());
  }
  CommonResponseDto response = new CommonResponseDto();
  response.setMessage("Request Accepted");
  return response;
 }

 /**
  *
  * @param id id.
  */
 public void rejectResourceRequest(final Long id) {

  requestResourceRepository.deleteById(id);

 }

}
