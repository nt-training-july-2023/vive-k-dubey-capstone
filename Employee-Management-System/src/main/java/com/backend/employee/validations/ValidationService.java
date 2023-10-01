package com.backend.employee.validations;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.employee.dto.RegisterDto;
import com.backend.employee.dto.AssignProjectDto;
import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.ProjectDto;
import com.backend.employee.dto.RequestResourceDto;
import com.backend.employee.dto.RequestedDto;
import com.backend.employee.dto.UpdateSkillsDto;
import com.backend.employee.entity.ProjectEntity;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.entity.RequestResource;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.UnauthorizedException;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.repo.ProjectRepo;
import com.backend.employee.repo.RequestResourceRepo;

@Service
public class ValidationService {
 @Autowired
 private BCryptPasswordEncoder passwordEncoders;
 /**
  * Represents instance of EmployeeRepository class.
  */
 @Autowired
 private RegisterRepo registerRepository;

 /**
  * Represents instance of ProjectRepository.
  */
 @Autowired
 private ProjectRepo projectRepository;

 /**
  * Represents instance of RequesrRepository.
  */
 @Autowired
 private RequestResourceRepo requestResourceRepository;

 public void requestResourceValidator(
  final RequestResourceDto requestResourceDto) throws WrongInputException {
  if (requestResourceDto.getEmpId().equals("")) {
   throw new WrongInputException("empId should not be empty");
  }
  if (requestResourceDto.getManagerEmail().equals("")) {
   throw new WrongInputException("Manager email id should not be empty");
  }
  if (requestResourceDto.getProjectId() == null) {
   throw new WrongInputException("Project id should not be empty");
  }
  Optional<RegisterEntity> employee = registerRepository
   .findByEmpId(requestResourceDto.getEmpId());
  Optional<RegisterEntity> manager = registerRepository
   .findByEmpEmail(requestResourceDto.getManagerEmail());
  ProjectEntity project = projectRepository
   .findByProjectId(requestResourceDto.getProjectId());
  employee
   .orElseThrow(() -> new DataNotFoundException("Employee not found"));
  manager.orElseThrow(() -> new DataNotFoundException("Manager not found"));
  if (project == null) {
   throw new DataNotFoundException("Project not found");
  }
  Long managerId = project.getManagerEmployeeId();
  Optional<RegisterEntity> managerOne = registerRepository
   .findById(managerId);

  if (managerOne.isPresent() && !managerOne.get().getEmpEmail()
   .equals(requestResourceDto.getManagerEmail())) {
   throw new WrongInputException(
    "Given project is not under the provided manager.");
  }

  if (employee.isPresent() && employee.get().getProjectId() != null) {
   throw new WrongInputException(
    "Employee is already assigned to a project");
  }
  RequestResource requestResource = null;
  if (employee.isPresent() && manager.isPresent()) {
   requestResource = requestResourceRepository.findByEmployeeIdAndManagerId(
    employee.get().getId(), manager.get().getId());

  } else {
  }

  if (requestResource != null) {
   throw new DataAlreadyExistsException(
    "The resource is already requested for this project");
  }
 }

 public void isRequestedValidator(final RequestedDto requestedDto)
  throws WrongInputException {
  if (requestedDto.getEmpId().equals("")) {
   throw new WrongInputException("empId should not be empty");
  }
  if (requestedDto.getManagerEmail().equals("")) {
   throw new WrongInputException("Manager email id should not be empty");
  }
  Optional<RegisterEntity> userOptional = registerRepository
   .findByEmpId(requestedDto.getEmpId());

  if (!userOptional.isPresent()) {
   String s = "Employee with emp id " + requestedDto.getEmpId()
    + " does not exist";
   throw new DataNotFoundException(s);
  }
  Optional<RegisterEntity> userOptionalManager = registerRepository
   .findByEmpEmail(requestedDto.getManagerEmail());
  if (!userOptionalManager.isPresent()) {
   String s = "Manager with email " + requestedDto.getManagerEmail()
    + " does not exist";
   throw new DataNotFoundException(s);
  }
 }

 public void loginValidation(final LoginDto loginDto)
  throws WrongInputException {

  String empEmail = loginDto.getEmpEmail();
  String password = loginDto.getEmpPassword();
  Optional<RegisterEntity> userOptional = registerRepository
   .findByEmpEmail(empEmail);

  if (!userOptional.isPresent()) {
   String s = "Employee with email id " + empEmail + " does not exist";
   throw new DataNotFoundException(s);
  }

  RegisterEntity user = userOptional.get();

  byte[] decodedBytes = Base64.getDecoder().decode(password);
  String decodedPassword = new String(decodedBytes,
   java.nio.charset.StandardCharsets.UTF_8);
  System.out.println(decodedPassword);
  System.out.println(
   passwordEncoders.matches(decodedPassword, user.getEmpPassword()));

  if (!passwordEncoders.matches(decodedPassword, user.getEmpPassword())) {
   System.out.println(user.getEmpPassword());
   System.out.println(
    passwordEncoders.matches(decodedPassword, user.getEmpPassword()));
   System.out.println(password);
   String message = "Wrong password";
   throw new UnauthorizedException(message);
  }

 }

 public void validateProjectDto(ProjectDto projectDto)
  throws WrongInputException, DataAlreadyExistsException {
  validateSkills(projectDto.getSkills());
  validateProjectName(projectDto.getName());
  validateProjectDescription(projectDto.getDescription());
  validateDuplicateProjectName(projectDto.getName());
  validateManagerExistence(projectDto.getManagerEmployeeId());
  validateStartDate(projectDto.getStartDate());
 }

 private void validateSkills(List<String> selectedSkills)
  throws WrongInputException {
  if (selectedSkills == null || selectedSkills.isEmpty()) {
   throw new WrongInputException("At least one skill is required");
  }
 }

 private static final String DATE_FORMAT_REGEX = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/(\\d{4})$";

 public void validateStartDate(String startDate)
  throws WrongInputException {
  try {
   if (!startDate.matches(DATE_FORMAT_REGEX)) {
    throw new WrongInputException(
     "Start date must be in the format DD/MM/YYYY");
   }
   DateTimeFormatter dateFormatter = DateTimeFormatter
    .ofPattern("dd/MM/yyyy");
   LocalDate parsedStartDate = LocalDate.parse(startDate, dateFormatter);
   LocalDate currentDate = LocalDate.now();
   if (parsedStartDate.isBefore(currentDate)) {
    throw new WrongInputException("Start date must be a future date");
   }
  } catch (Exception e) {
   throw new WrongInputException("Invalid date format");
  }
 }

 private void validateProjectName(String projectName)
  throws WrongInputException {
  try {
   if (projectName == null || projectName.isEmpty()
    || projectName.matches(".*\\d.*")) {
    throw new WrongInputException(
     "Project name is invalid. It shouldn't have digits and should not be empty");
   }
  } catch (Exception e) {
   throw new WrongInputException("Invalid name type");
  }
 }

 private void validateProjectDescription(String description)
  throws WrongInputException {
  if (description == null || description.isEmpty()) {
   throw new WrongInputException("Project description is required");
  }
 }

 private void validateDuplicateProjectName(String projectName)
  throws DataAlreadyExistsException {
  ProjectEntity existingProject = projectRepository.findByName(projectName);
  if (existingProject != null) {
   throw new DataAlreadyExistsException(
    "Project with the same name already exists");
  }
 }

 private void validateManagerExistence(Long managerEmployeeId)
  throws WrongInputException {
  Optional<RegisterEntity> managerEntity = registerRepository
   .findById(managerEmployeeId);
  if (managerEntity.isEmpty()) {
   throw new WrongInputException("Manager not found");
  }
  if (!managerEntity.get().getEmpRole().equals("manager")) {
   throw new WrongInputException("The specified manager is not a manager");
  }
 }

 public void validateManagerId(Long managerId)
  throws DataNotFoundException {

  Optional<RegisterEntity> managerEntity = registerRepository
   .findById(managerId);
  if (managerEntity.isEmpty()) {
   throw new DataNotFoundException("Manager not found");
  }

 }

 public void validateAssignProjectDto(AssignProjectDto assignProjectDto)
  throws WrongInputException {

  RegisterEntity employee = registerRepository
   .findByEmpId(assignProjectDto.getEmpId()).orElse(null);

  if (employee == null) {
   throw new WrongInputException("Employee does not exist");

  }

  if (employee.getProjectId() != null) {
   throw new WrongInputException("Employee is already assigned a project.");
  }

  ProjectEntity project = projectRepository
   .findByProjectId(assignProjectDto.getProjectId());
  if (project == null) {
   throw new WrongInputException("Project does not exist");
  }
 }

 public void validateManagerEmail(String email) throws WrongInputException {
  RegisterEntity employee = registerRepository.findByEmpEmail(email)
   .orElse(null);

  if (employee == null) {
   throw new WrongInputException("Employee does not exist");
  }
 }

 public void validateRejectResource(Long id) throws WrongInputException {
  Optional<RequestResource> resourceRequest = requestResourceRepository
   .findById(id);
  try {
   if (resourceRequest.isEmpty()) {
    throw new DataNotFoundException(
     "Resource request with ID " + id + " not found");
   }
  } catch (DataNotFoundException e) {
   throw e;
  }

 }

 public void validateUserEmail(String email)
  throws DataNotFoundException, WrongInputException {
  RegisterEntity employee = registerRepository.findByEmpEmail(email)
   .orElse(null);

  if (employee == null) {
   throw new WrongInputException("Employee does not exist");
  }

  if (employee.getEmpRole().equals("manager")
   || employee.getEmpRole().equals("admin")) {
   throw new WrongInputException("You cannot access this information.");
  }
 }

 public void validateUpdateSkillsDto(UpdateSkillsDto updateSkillsDto)
  throws DataNotFoundException, WrongInputException {

  String empEmail = updateSkillsDto.getEmpEmail();
  RegisterEntity employee = registerRepository.findByEmpEmail(empEmail)
   .orElse(null);

  if (employee == null) {
   throw new DataNotFoundException(
    "Employee with email " + empEmail + " not found");
  }

  List<String> empSkills = updateSkillsDto.getEmpSkills();
  if (empSkills == null || empSkills.isEmpty()) {
   throw new WrongInputException("Employee skills cannot be empty");
  }
 }

 public void unassignProjectValidator(String empId)
  throws WrongInputException {
  RegisterEntity employee = registerRepository.findByEmpId(empId)
   .orElse(null);
  if (employee == null) {
   throw new DataNotFoundException("Employee id does not exist");
  }
  if (employee.getProjectId() == null) {
   throw new WrongInputException(
    "Employee does not have a project in first place.");
  }
 }

}
