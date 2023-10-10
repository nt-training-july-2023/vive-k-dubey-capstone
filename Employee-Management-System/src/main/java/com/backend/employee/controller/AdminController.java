package com.backend.employee.controller;

import com.backend.employee.dto.AssignProjectDto;
import com.backend.employee.dto.AssignProjectOutDto;
import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.FilterDto;
import com.backend.employee.dto.ManagerOutDto;
import com.backend.employee.dto.ManagerInfoDto;
import com.backend.employee.dto.ProjectDto;
import com.backend.employee.dto.ProjectOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.service.AdminService;
import com.backend.employee.validations.RegisterValidationService;
import com.backend.employee.validations.ValidationService;

import jakarta.validation.Valid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller class for managing employee-related operations.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employee")
public class AdminController {
 /**
  * Object.
  */
 @Autowired
 private AdminService adminService;
 /**
  * Logger instance.
  */
 private static Logger logger = LoggerFactory
  .getLogger(AdminController.class);

 /**
  * Instance of register validation.
  */
 @Autowired
 private RegisterValidationService registerValidationService;
 /**
  * Instance of register validation.
  */
 @Autowired
 private ValidationService validationService;

 /**
  * Endpoint to add an employee.
  *
  * @param registerDto The employee information to add.
  * @return A response indicating the success or failure of the operation.
  * @throws DataAlreadyExistsException If the employee data already exists.
  * @throws WrongInputException        If the input data is incorrect.
  */
 @PostMapping("/add")
 public CommonResponseDto addEmployee(
  @RequestBody final RegisterDto registerDto)
  throws DataAlreadyExistsException, WrongInputException {
  logger.info("Started add employee controller");
  registerValidationService.validateRegisterDto(registerDto);
  CommonResponseDto result = adminService.addEmployee(registerDto);
  logger.info("Finished add employee controller");
  return result;

 }

 /**
  * Endpoint to retrieve all employees.
  *
  * @return A list of employee data.
  */
 @GetMapping("/getAllEmployees")
 public List<RegisterDto> getAllEmployees() {
  logger.info("Started get all employees controller");
  List<RegisterDto> employees = adminService.getAllEmployees();
  logger.info("Finished get all employees controller");
  return employees;
 }

 /**
  *
  * @return getAllEmployeesAndManagers.
  */
 @GetMapping("/getAllEmployeesAndManagers")
 public List<RegisterDto> getAllEmployeesAndManagers() {
  logger.info("Started getAllEmployeesAndManagers controller");
  List<RegisterDto> employeesAndManagers = adminService
   .getAllEmployeesAndManagers();
  logger.info("Finished getAllEmployeesAndManagers controller");
  return employeesAndManagers;
 }

 /**
  * Endpoint to retrieve all managers.
  *
  * @return A list of manager data.
  */
 @GetMapping("/getAllManagers")
 public List<ManagerOutDto> getAllManagers() {
  logger.info("Started getAllManagers controller");
  List<ManagerOutDto> allManagers = adminService.getAllManagers();
  logger.info("Finished getAllManagers controller");
  return allManagers;
 }

 /**
  * Endpoint to retrieve all manager information.
  *
  * @return A list of manager information data.
  * @throws DataNotFoundException If no manager information data is found.
  */
 @GetMapping("/getAllManagersInfo")
 public List<ManagerInfoDto> getAllManagersInfo() {
  logger.info("Started getAllManagersInfo controller");
  List<ManagerInfoDto> managerInfoList = adminService.getAllManagersInfo();
  logger.info("Finished getAllManagersInfo controller");
  return managerInfoList;
 }

 /**
  * Endpoint to add a project.
  *
  * @param projectDto The project information to add.
  * @return A response indicating the success or failure of the operation.
  * @throws WrongInputException If the input data is incorrect.
  */
 @PostMapping("/addProject")
 public CommonResponseDto addProject(
  @Valid @RequestBody final ProjectDto projectDto)
  throws WrongInputException {
  logger.info("Started addProject controller");
  validationService.validateProjectDto(projectDto);
  CommonResponseDto result = adminService.addProject(projectDto);
  logger.info("Finished addProject controller");
  return result;
 }

 /**
  * Endpoint to retrieve all projects.
  *
  * @throws DataNotFoundException DataNotFoundException.
  * @return A response containing a list of project data.
  */
 @GetMapping("/getAllProjects")
 public List<ProjectDto> getAllProjects() {
  logger.info("Started getAllProjects controller");
  List<ProjectDto> projectDtoList = adminService.getAllProjects();
  logger.info("Finished getAllProjects controller");
  return projectDtoList;
 }

 /**
  *
  * @return The projects for assigning.
  * @throws DataNotFoundException DataNotFoundException.
  */
 @GetMapping("/getAllProjectsForAssign")
 public List<AssignProjectOutDto> getAllProjectsForAssign() {
  logger.info("Started getAllProjectsForAssign controller");
  List<AssignProjectOutDto> assignProjectOutDtoList = adminService
   .getAllProjectsForAssign();
  logger.info("Finished getAllProjectsForAssign controller");
  return assignProjectOutDtoList;
 }

 /**
  * Endpoint to retrieve all projects by manager ID.
  *
  * @param managerId The ID of the manager.
  * @return A list of project data associated with the manager.
  * @throws WrongInputException wrong input throws.
  * @throws DataNotFoundException when no projects found.
  */
 @GetMapping("/getAll/project/{managerId}")
 public List<ProjectOutDto> getAllByManagerId(
  @PathVariable final Long managerId) throws DataNotFoundException,
 WrongInputException {
  logger.info("Started /getAll/project/{managerId} controller");
  validationService.validateManagerId(managerId);
  List<ProjectOutDto> projectOutDtoList = adminService
   .getAllByManagerId(managerId);
  logger.info("Finished /getAll/project/{managerId} controller");
  return projectOutDtoList;
 }

 /**
  *
  * @param assignProjectDto assignProjectDto.
  * @return CommonResponseDto.
  * @throws WrongInputException WrongInputException.
  */
 @PostMapping("/assignProject")
 public CommonResponseDto assignProejct(
  @Valid @RequestBody final AssignProjectDto assignProjectDto)
  throws WrongInputException {
  logger.info("Started assignProject controller");
  validationService.validateAssignProjectDto(assignProjectDto);
  CommonResponseDto commonResponseDto = adminService
   .assignProject(assignProjectDto);
  logger.info("Finished assignProject controller");
  return commonResponseDto;
 }

 /**
  *
  * @param filterEmployeeDto Dto for filtering.
  * @return Filtered Employees.
  */
 @PostMapping("/filter")
 public List<RegisterDto> getFilteredEmployee(
  @Valid @RequestBody final FilterDto filterEmployeeDto) {
  logger.info("Started filter employee list controller");
  List<RegisterDto> employeeList = adminService
   .getFilteredEmployee(filterEmployeeDto);
  logger.info("End of filter employee list controller");
  return employeeList;

 }

 /**
  *
  * @param empId Employee id of the employee.
  * @return CommonResponseDto.
  * @throws WrongInputException WrongInputException.
  */
 @PutMapping("/unassignProject/{empId}")
 public CommonResponseDto unassignProject(@PathVariable final String empId)
  throws WrongInputException {
  logger.info("Started unassign project controller");
  validationService.unassignProjectValidator(empId);
  adminService.unassignProject(empId);
  CommonResponseDto commonResponseDto = new CommonResponseDto();
  commonResponseDto.setMessage("Project unassigned successfully");
  logger.info("End of unassign projectcontroller");
  return commonResponseDto;

 }

}
