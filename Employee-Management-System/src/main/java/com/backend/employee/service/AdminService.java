
package com.backend.employee.service;

import com.backend.employee.dto.AssignProjectDto;
import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.ManagerDto;
import com.backend.employee.dto.ManagerInfoDto;
import com.backend.employee.dto.ProjectDto;
import com.backend.employee.dto.ProjectOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.entity.ProjectEntity;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.ProjectRepo;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.validations.RegisterValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

  /*
   * Create a new RegisterEntity from the RegisterDto
   */
  RegisterEntity registerEntity = new RegisterEntity(registerDto);

  /*
   * Set employee skills
   */
  registerEntity.setEmpSkills(registerDto.getEmpSkills());

  /**
   * Encrypt the employee password
   */
  registerEntity.setEmpPassword(registerDto.getEmpPassword());

  /*
   * Determine and set the manager for the employee based on their role
   */
  RegisterEntity managerEntity = registerRepository.findByEmpRole("admin");
  if (managerEntity != null) {
   registerEntity.setManagerId(managerEntity.getId());
  } else {
   registerEntity.setManagerId(null);
  }

  /*
   * Save the new employee entity to the repository
   */
  registerRepository.save(registerEntity);

  return new CommonResponseDto("Employee added successfully");
 }

 /**
  * Retrieves a list of all employees.
  *
  * @return A list of RegisterDto objects representing employees.
  * @throws DataNotFoundException If no employee data is found.
  */
 public List<RegisterDto> getAllEmployees() {
  /*
   * Retrieve all employee entities
   */
  List<RegisterEntity> managerEntities = registerRepository
   .findAllByEmpRole("employee");

  if (!managerEntities.isEmpty()) {
   /*
    * Convert employee entities to RegisterDto objects
    */
   List<RegisterDto> managerDtos = convertToRegisterDtoList(
    managerEntities);
   return managerDtos;
  } else {
   throw new DataNotFoundException("No employees found");
  }
 }

 /**
  * Returns the list of managers and employees.
  *
  * @return List of entities.
  */
 public List<RegisterDto> getAllEmployeesAndManagers() {
  /*
   * Retrieve all employee and manager entities except admin
   */
  List<RegisterEntity> employeeAndManagerEntities = registerRepository
   .findAllByEmpRoleNot("admin");

  if (!employeeAndManagerEntities.isEmpty()) {
   /*
    * Convert employee and manager entities to RegisterDto objects
    */
   List<RegisterDto> employeeAndManagerDtos = convertToRegisterDtoList(
    employeeAndManagerEntities);
   return employeeAndManagerDtos;
  } else {
   throw new DataNotFoundException("No employees and managers found");
  }
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
  * @throws DataNotFoundException If no manager data is found.
  */
 public List<ManagerDto> getAllManagers() {
  /*
   * Retrieve all manager entities
   */
  List<RegisterEntity> managerEntities = registerRepository
   .findAllByEmpRole("manager");

  if (!managerEntities.isEmpty()) {
   /*
    * Convert manager entities to ManagerDto objects
    */
   List<ManagerDto> managerDtos = convertToManagerDtoList(managerEntities);
   return managerDtos;
  } else {
   throw new DataNotFoundException("No managers found");
  }
 }

 /**
  * Converts a list of RegisterEntity objects to a list of ManagerDto objects.
  *
  * @param entities The list of RegisterEntity objects to convert.
  * @return A list of ManagerDto objects.
  */
 private List<ManagerDto> convertToManagerDtoList(
  final List<RegisterEntity> entities) {
  List<ManagerDto> dtos = new ArrayList<>();
  for (RegisterEntity entity : entities) {
   ManagerDto dto = new ManagerDto();
   // Map entity fields to dto fields
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
  * @throws DataNotFoundException If no manager information data is found.
  */
 public List<ManagerInfoDto> getAllManagersInfo()
  throws DataNotFoundException {
  /*
   * Retrieve all manager entities
   */
  List<RegisterEntity> managerEntities = registerRepository
   .findAllByEmpRole("manager");

  if (!managerEntities.isEmpty()) {
   /*
    * Convert manager entities to ManagerInfoDto objects
    */
   List<ManagerInfoDto> managerInfoList = managerEntities.stream()
    .map(this::convertToManagerInfoDto).collect(Collectors.toList());

   return managerInfoList;
  } else {
   throw new DataNotFoundException("Manager information not found");
  }
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

  /*
   * Create a ProjectEntity from the ProjectDto
   */
  ProjectEntity projectEntity = new ProjectEntity(projectDto);

  /*
   * Save the project to the database
   */
  projectRepository.save(projectEntity);

  /*
   * Return a success response
   */
  return new CommonResponseDto("Project added successfully");
 }

 /**
  * Retrieves a list of all projects.
  *
  * @return A ResponseDto containing a list of ProjectDto objects representing
  *         projects.
  * @throws DataNotFoundException DataNotFoundException.
  */

 public List<ProjectDto> getAllProjects() throws DataNotFoundException {
  /**
   * Retrieve all project entities
   */
  List<ProjectEntity> projects = projectRepository.findAll();

  /**
   * Check if projects list is empty and throw DataNotFoundException if it is.
   */
  if (projects.isEmpty()) {
   throw new DataNotFoundException("No projects found");
  }

  /**
   * Convert project entities to a list of ProjectDto objects
   */
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

   /*
    * Map entity fields to dto fields
    */
   dto.setProjectId(entity.getProjectId());
   dto.setName(entity.getName());
   dto.setDescription(entity.getDescription());
   dto.setStartDate(entity.getStartDate());
   dto.setManagerEmployeeId(entity.getManagerEmployeeId());
   dto.setSkills(entity.getSkills());
   List<RegisterEntity> teamMembers = registerRepository
    .findAllByProjectId(entity.getProjectId());

   /*
    * optional
    */
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
  * Retrieves a list of projects managed by a manager with the given manager ID.
  *
  * @param managerId The ID of the manager whose projects are to be retrieved.
  * @return A list of ProjectOutDto objects representing the projects managed by
  *         the manager.
  */
 public List<ProjectOutDto> getAllByManagerId(final Long managerId) {
  /*
   * Retrieve a list of project entities managed by the manager
   */
  List<ProjectEntity> projectList = projectRepository
   .findAllByManagerEmployeeId(managerId);

  /*
   * Create a list to store the output DTOs
   */
  List<ProjectOutDto> projectOutList = new ArrayList<ProjectOutDto>();

  for (ProjectEntity project : projectList) {
   ProjectOutDto projectOutDto = new ProjectOutDto();

   /*
    * Map entity fields to DTO fields
    */
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

}
