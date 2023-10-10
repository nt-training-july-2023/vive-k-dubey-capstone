
package com.backend.employee.service;

import com.backend.employee.dto.AssignProjectDto;
import com.backend.employee.dto.AssignProjectOutDto;
import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.FilterDto;
import com.backend.employee.dto.ManagerOutDto;
import com.backend.employee.dto.ManagerInfoDto;
import com.backend.employee.dto.ProjectDto;
import com.backend.employee.dto.ProjectOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.entity.ProjectEntity;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.ProjectRepo;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.validations.RegisterValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class.
 */
@Service
public class AdminService {
 /**
  * Object.
  */
 @Autowired
 private RegisterRepo registerRepository;
 /**
  * Object.
  */
 @Autowired
 private ProjectRepo projectRepository;
 /**
  * Object.
  */
 @Autowired
 private BCryptPasswordEncoder passwordEncoder;
 /**
  * Object.
  */
 @Autowired
 private RegisterValidationService inputFieldChecksUpdated;

 /**
  * Adds a new employee based on the provided RegisterDto.
  *
  * @param registerDto The RegisterDto containing employee information.
  * @return A CommonResponseDto indicating the success or failure of the
  *         operation.
  * @throws WrongInputException        If the input data is incorrect.
  * @throws DataAlreadyExistsException If the employee data already exists.
  */
 public CommonResponseDto addEmployee(final RegisterDto registerDto)
  throws WrongInputException, DataAlreadyExistsException {

  RegisterEntity registerEntity = new RegisterEntity(registerDto);
  registerEntity.setEmpSkills(registerDto.getEmpSkills());
  String empPassword = registerDto.getEmpPassword();
  registerEntity.setEmpPassword(empPassword);
  RegisterEntity managerEntity = registerRepository.findByEmpRole("admin");
  if (managerEntity != null) {
   registerEntity.setManagerId(managerEntity.getId());
  } else {
   registerEntity.setManagerId(null);
  }

  registerRepository.save(registerEntity);

  return new CommonResponseDto("Employee added successfully");
 }

 /**
  * Retrieves a list of all employees.
  *
  * @return A list of RegisterDto objects representing employees.
  */
 public List<RegisterDto> getAllEmployees() {
  List<RegisterEntity> managerEntities = registerRepository
   .findAllByEmpRole("employee");
  return convertToRegisterDtoList(managerEntities);
 }

 /**
  * Returns the list of managers and employees.
  *
  * @return List of entities.
  */
 public List<RegisterDto> getAllEmployeesAndManagers() {
  List<RegisterEntity> employeeAndManagerEntities = registerRepository
   .findAllByEmpRoleNot("admin");
  return convertToRegisterDtoList(employeeAndManagerEntities);
 }

 /**
  * Converts a list of RegisterEntity objects to a list of RegisterDto objects.
  *
  * @param entities The list of RegisterEntity objects to convert.
  * @return A list of RegisterDto objects.
  */
 private List<RegisterDto> convertToRegisterDtoList(
  final List<RegisterEntity> entities) {
  List<RegisterDto> dtos = new ArrayList<>();
  for (RegisterEntity entity : entities) {
   RegisterDto dto = new RegisterDto();
   dto.setEmpId(entity.getEmpId());
   dto.setEmpName(entity.getEmpName());
   dto.setEmpContactNo(entity.getEmpContactNo());
   dto.setEmpDesignation(entity.getEmpDesignation());
   dto.setEmpDOB(entity.getEmpDOB());
   dto.setEmpDOJ(entity.getEmpDOJ());
   dto.setEmpLocation(entity.getEmpLocation());
   dto.setEmpRole(entity.getEmpRole());
   dto.setEmpEmail(entity.getEmpEmail());
   dto.setEmpSkills(entity.getEmpSkills());
   dto.setProjectId(entity.getProjectId());

   if (entity.getManagerId() != null) {
    Optional<RegisterEntity> managerEntityOptional = registerRepository
     .findById(entity.getManagerId());
    if (managerEntityOptional.isPresent()) {
     RegisterEntity managerEntity = managerEntityOptional.get();
     dto.setManagerName(managerEntity.getEmpName());
    }
   }
   if (entity.getProjectId() != null) {
    ProjectEntity projectEntity = projectRepository
     .findByProjectId(entity.getProjectId());
    if (projectEntity != null) {

     dto.setProjectName(projectEntity.getName());
    }
   }
   dtos.add(dto);
  }
  return dtos;
 }

 /**
  * Retrieves a list of all managers.
  *
  * @return A list of ManagerDto objects representing managers.
  */
 public List<ManagerOutDto> getAllManagers() {
  List<RegisterEntity> managerEntities = registerRepository
   .findAllByEmpRole("manager");
  return convertToManagerDtoList(managerEntities);
 }

 /**
  * Converts a list of RegisterEntity objects to a list of ManagerDto objects.
  *
  * @param entities The list of RegisterEntity objects to convert.
  * @return A list of ManagerDto objects.
  */
 private List<ManagerOutDto> convertToManagerDtoList(
  final List<RegisterEntity> entities) {
  List<ManagerOutDto> dtos = new ArrayList<>();
  for (RegisterEntity entity : entities) {
   ManagerOutDto dto = new ManagerOutDto();
   dto.setId(entity.getId());
   dto.setEmpId(entity.getEmpId());
   dto.setEmpName(entity.getEmpName());
   dto.setEmpContactNo(entity.getEmpContactNo());
   dto.setEmpDesignation(entity.getEmpDesignation());
   dto.setEmpEmail(entity.getEmpEmail());
   dto.setEmpLocation(entity.getEmpLocation());
   dtos.add(dto);
  }
  return dtos;
 }

 /**
  * Retrieves a list of manager information.
  *
  * @return A list of ManagerInfoDto objects representing manager information.
  */
 public List<ManagerInfoDto> getAllManagersInfo() {
  List<RegisterEntity> managerEntities = registerRepository
   .findAllByEmpRole("manager");

  return managerEntities.stream().map(this::convertToManagerInfoDto)
   .collect(Collectors.toList());
 }

 /**
  * Converts a RegisterEntity object to a ManagerInfoDto object.
  *
  * @param entity The RegisterEntity object to convert.
  * @return A ManagerInfoDto object representing manager information.
  */
 private ManagerInfoDto convertToManagerInfoDto(
  final RegisterEntity entity) {
  ManagerInfoDto managerInfoDto = new ManagerInfoDto();
  managerInfoDto.setManagerName(entity.getEmpName());
  managerInfoDto.setManagerEmployeeId(entity.getEmpId());
  managerInfoDto.setId(entity.getId());
  return managerInfoDto;
 }

 /**
  * Adds a new project based on the provided ProjectDto.
  *
  * @param projectDto The ProjectDto containing project information.
  * @return A CommonResponseDto indicating the success or failure of the
  *         operation.
  * @throws WrongInputException        If the input data is incorrect.
  * @throws DataAlreadyExistsException If a project with the same name already
  *                                    exists.
  */
 public CommonResponseDto addProject(final ProjectDto projectDto)
  throws WrongInputException {

  ProjectEntity projectEntity = new ProjectEntity(projectDto);
  projectRepository.save(projectEntity);
  return new CommonResponseDto("Project added successfully");
 }

 /**
  * Retrieves a list of all projects.
  *
  * @return A ResponseDto containing a list of ProjectDto objects representing
  *         projects.
  * @throws DataNotFoundException DataNotFoundException.
  */

 public List<ProjectDto> getAllProjects() {
  List<ProjectEntity> projects = projectRepository.findAll();
  List<ProjectDto> projectDtos = convertToProjectDtoList(projects);
  return projectDtos;
 }

 /**
  * Converts a list of ProjectEntity objects to a list of ProjectDto objects.
  *
  * @param entities The list of ProjectEntity objects to convert.
  * @return A list of ProjectDto objects.
  */
 private List<ProjectDto> convertToProjectDtoList(
  final List<ProjectEntity> entities) {
  List<ProjectDto> dtos = new ArrayList<>();
  for (ProjectEntity entity : entities) {
   ProjectDto dto = new ProjectDto();

   dto.setProjectId(entity.getProjectId());
   dto.setName(entity.getName());
   dto.setDescription(entity.getDescription());
   dto.setStartDate(entity.getStartDate());
   dto.setManagerEmployeeId(entity.getManagerEmployeeId());
   dto.setSkills(entity.getSkills());
   List<RegisterEntity> teamMembers = registerRepository
    .findAllByProjectId(entity.getProjectId());
   Optional<RegisterEntity> headEntity = registerRepository
    .findById(entity.getManagerEmployeeId());
   if (headEntity.isPresent()) {
    dto.setHead(headEntity.get().getEmpName());
   }
   List<String> teamMemberNames = new ArrayList<>();
   for (RegisterEntity teamMember : teamMembers) {
    teamMemberNames.add(teamMember.getEmpName());
   }
   dto.setTeamMembers(teamMemberNames);
   dtos.add(dto);
  }
  return dtos;
 }

 /**
  *
  * @return getAllProjectsForAssign.
  */
 public List<AssignProjectOutDto> getAllProjectsForAssign() {
  List<ProjectEntity> projects = projectRepository.findAll();

  List<AssignProjectOutDto> projectDtos = projects.stream().map(project -> {
    AssignProjectOutDto dto = new AssignProjectOutDto();
    dto.setProjectId(project.getProjectId());
    dto.setName(project.getName());
    return dto;
  }).collect(Collectors.toList());

  return projectDtos;
}

 /**
  * Retrieves a list of projects managed by a manager with the given manager ID.
  *
  * @param managerId The ID of the manager whose projects are to be retrieved.
  * @return A list of ProjectOutDto objects representing the projects managed by
  *         the manager.
  */
 public List<ProjectOutDto> getAllByManagerId(final Long managerId) {

  List<ProjectEntity> projectList = projectRepository
   .findAllByManagerEmployeeId(managerId);

  List<ProjectOutDto> projectOutList = new ArrayList<ProjectOutDto>();

  for (ProjectEntity project : projectList) {
   ProjectOutDto projectOutDto = new ProjectOutDto();

   projectOutDto.setId(project.getProjectId());
   projectOutDto.setProjectName(project.getName());
   projectOutDto.setManagerId(project.getManagerEmployeeId() + "");
   projectOutDto.setSkillsRequired(project.getSkills());
   projectOutDto.setDescription(project.getDescription());
   projectOutDto.setStartDate(project.getStartDate());
   List<RegisterEntity> teamMembers = registerRepository
    .findAllByProjectId(project.getProjectId());

   List<String> teamMemberNames = new ArrayList<>();
   for (RegisterEntity teamMember : teamMembers) {
    teamMemberNames.add(teamMember.getEmpName());
   }
   projectOutDto.setTeamMembers(teamMemberNames);
   projectOutList.add(projectOutDto);
  }

  return projectOutList;
 }

 /**
  * Method for assign project.
  *
  * @param assignProjectDto assignProjectDto.
  * @return Returns response for assign project
  * @throws WrongInputException WrongInputException.
  */
 public CommonResponseDto assignProject(
  final AssignProjectDto assignProjectDto) throws WrongInputException {

  RegisterEntity employee = registerRepository
   .findByEmpId(assignProjectDto.getEmpId()).orElse(null);
  ProjectEntity project = projectRepository
   .findByProjectId(assignProjectDto.getProjectId());
  employee.setManagerId(project.getManagerEmployeeId());
  employee.setProjectId(assignProjectDto.getProjectId());
  registerRepository.save(employee);

  return new CommonResponseDto("Project assigned successfully");

 }

 /**
  *
  * @param filterEmployeeDto filterEmployeeDto.
  * @return getFilteredEmployee.
  */
 public List<RegisterDto> getFilteredEmployee(
  final FilterDto filterEmployeeDto) {
  List<RegisterEntity> employeeList = registerRepository
   .findAllByEmpRole("employee");
  List<RegisterDto> employeeDtoList = new ArrayList<RegisterDto>();

  for (RegisterEntity employee : employeeList) {
   RegisterDto empDto = new RegisterDto();
   empDto.setEmpName(employee.getEmpName());
   empDto.setEmpEmail(employee.getEmpEmail());
   empDto.setEmpId(employee.getEmpId());
   empDto.setEmpDesignation(employee.getEmpDesignation());
   empDto.setEmpContactNo(employee.getEmpContactNo());
   empDto.setEmpDOB(employee.getEmpDOB());
   empDto.setEmpDOJ(employee.getEmpDOJ());
   empDto.setEmpLocation(employee.getEmpLocation());
   empDto.setEmpSkills(employee.getEmpSkills());

   if (employee.getProjectId() == null) {
    empDto.setProjectName("N/A");
    empDto.setProjectId(employee.getProjectId());
   } else {
    ProjectEntity project = projectRepository
     .findById(employee.getProjectId()).get();
    empDto.setProjectName(project.getName());
    empDto.setProjectId(employee.getProjectId());
   }

   RegisterEntity manager = registerRepository
    .findById(employee.getManagerId()).orElse(null);
   empDto.setManagerName(manager.getEmpName());
   if (filterEmployeeDto.getSkills() == null
    || filterEmployeeDto.getSkills().isEmpty()) {
    if (filterEmployeeDto.getChecked()
     && empDto.getProjectName().equals("N/A")) {
     employeeDtoList.add(empDto);
    } else if (!filterEmployeeDto.getChecked()) {
     employeeDtoList.add(empDto);
    }
   } else {
    boolean hasMatchingSkills = empDto.getEmpSkills().stream()
     .anyMatch(skill -> filterEmployeeDto.getSkills().contains(skill));

    if (filterEmployeeDto.getChecked() && hasMatchingSkills
     && empDto.getProjectName().equals("N/A")) {
     employeeDtoList.add(empDto);
    } else if (!filterEmployeeDto.getChecked() && hasMatchingSkills) {
     employeeDtoList.add(empDto);
    }
   }
  }

  return employeeDtoList;
 }

 /**
  * unassigns project to employee.
  *
  * @param empId the empId of employee.
  */
 public void unassignProject(final String empId) {
  RegisterEntity employee = registerRepository.findByEmpId(empId)
   .orElse(null);
  RegisterEntity admin = registerRepository
   .findByEmpEmail("ankita.sharma@nucleusteq.com").orElse(null);
  employee.setProjectId(null);
  employee.setManagerId(admin.getId());
  registerRepository.save(employee);
 }

}
