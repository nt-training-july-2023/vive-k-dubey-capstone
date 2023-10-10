package com.backend.employee.validations;

import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.backend.employee.errormessages.ValidationMessages;

/**
 * Validation Service.
 */
@Service
public class ValidationService {
 /**
  * Instance Variable.
  */
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

 /**
  *
  * @param requestResourceDto requestResourceDto.
  * @throws WrongInputException WrongInputException.
  */
 public void requestResourceValidator(
  final RequestResourceDto requestResourceDto) throws WrongInputException {
  Optional<RegisterEntity> employee = registerRepository
   .findByEmpId(requestResourceDto.getEmpId());
  Optional<RegisterEntity> manager = registerRepository
   .findByEmpEmail(requestResourceDto.getManagerEmail());
  ProjectEntity project = projectRepository
   .findByProjectId(requestResourceDto.getProjectId());
  employee.orElseThrow(
   () -> new DataNotFoundException(ValidationMessages.EMPLOYEE_NOT_EXISTS));
  manager.orElseThrow(
   () -> new DataNotFoundException(ValidationMessages.MANAGER_NOT_EXISTS));
  if (project == null) {
   throw new DataNotFoundException(ValidationMessages.PROJECT_NOT_EXISTS);
  }
  Long managerId = project.getManagerEmployeeId();
  Optional<RegisterEntity> managerOne = registerRepository
   .findById(managerId);

  if (managerOne.isPresent() && !managerOne.get().getEmpEmail()
   .equals(requestResourceDto.getManagerEmail())) {
   throw new WrongInputException(ValidationMessages.NOT_PROJECT_MANAGER);
  }

  if (employee.isPresent() && employee.get().getProjectId() != null) {
   throw new WrongInputException(
    ValidationMessages.EMPLOYEE_PROJECT_ALREDY_ASSIGNED);
  }
  RequestResource requestResource = null;
  if (employee.isPresent() && manager.isPresent()) {
   requestResource = requestResourceRepository.findByEmployeeIdAndManagerId(
    employee.get().getId(), manager.get().getId());

  }

  if (requestResource != null) {
   throw new DataAlreadyExistsException(
    ValidationMessages.RESOURCE_REQUEST_ALREADY_MADE);
  }
 }

 /**
  *
  * @param requestedDto requestedDto.
  * @throws WrongInputException WrongInputException.
  */
 public void isRequestedValidator(final RequestedDto requestedDto)
  throws WrongInputException {
  Optional<RegisterEntity> userOptional = registerRepository
   .findByEmpId(requestedDto.getEmpId());

  if (!userOptional.isPresent()) {
   String message = ValidationMessages.EMPLOYEE_NOT_EXISTS;
   throw new DataNotFoundException(message);
  }
  Optional<RegisterEntity> userOptionalManager = registerRepository
   .findByEmpEmail(requestedDto.getManagerEmail());
  if (!userOptionalManager.isPresent()) {
   String message = ValidationMessages.MANAGER_NOT_EXISTS;
   throw new DataNotFoundException(message);
  }
 }

 /**
  *
  * @param loginDto loginDto.
  * @throws WrongInputException WrongInputException.
  */
 public void loginValidation(final LoginDto loginDto)
  throws UnauthorizedException, DataNotFoundException  {

  String empEmail = loginDto.getEmpEmail();
  String password = loginDto.getEmpPassword();
  Optional<RegisterEntity> userOptional = registerRepository
   .findByEmpEmail(empEmail);

  if (!userOptional.isPresent()) {
   String message = ValidationMessages.EMPLOYEE_NOT_EXISTS;
   throw new DataNotFoundException(message);
  }

  RegisterEntity user = userOptional.get();

  if (!isEncodedPassword(password)) {
   if (!passwordEncoders.matches(password, user.getEmpPassword())) {
    String message = ValidationMessages.INCORRECT_PASSWORD;
    throw new UnauthorizedException(message);
   }
  } else {
   byte[] decodedBytes = Base64.getDecoder().decode(password);
   String decodedPassword = new String(decodedBytes,
    java.nio.charset.StandardCharsets.UTF_8);

   if (!passwordEncoders.matches(decodedPassword, user.getEmpPassword())) {
    String message = ValidationMessages.INCORRECT_PASSWORD;
    throw new UnauthorizedException(message);
   }
  }

 }

 /**
  *
  * @param password Password of user.
  * @return Whether encoded or not.
  */
 public boolean isEncodedPassword(final String password) {
  try {
   byte[] decodedBytes = Base64.getDecoder().decode(password);
   String decodedPassword = new String(decodedBytes,
    java.nio.charset.StandardCharsets.UTF_8);
   String validPasswordPattern =
    "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{}|;:'\",.<>/?]*$";
   return decodedPassword.matches(validPasswordPattern);
  } catch (IllegalArgumentException e) {
   return false;
  }
 }

 /**
  *
  * @param projectDto projectDto.
  * @throws WrongInputException        WrongInputException.
  * @throws DataAlreadyExistsException DataAlreadyExistsException.
  */
 public void validateProjectDto(final ProjectDto projectDto)
  throws WrongInputException, DataAlreadyExistsException {
  validateSkills(projectDto.getSkills());
  validateProjectName(projectDto.getName());
  validateProjectDescription(projectDto.getDescription());
  validateDuplicateProjectName(projectDto.getName());
  validateManagerExistence(projectDto.getManagerEmployeeId());
  validateStartDate(projectDto.getStartDate());
 }

 /**
  *
  * @param selectedSkills selectedSkills.
  * @throws WrongInputException WrongInputException.
  */
 public void validateSkills(final List<String> selectedSkills)
  throws WrongInputException {
  if (selectedSkills == null || selectedSkills.isEmpty()) {
   throw new WrongInputException(ValidationMessages.ATLEAST_ONE_SKILL);
  }
 }

 /**
  * Date format variable.
  */
 private static final String DATE_FORMAT_REGEX =
  "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/(\\d{4})$";

 /**
  *
  * @param startDate startDate.
  * @throws WrongInputException WrongInputException.
  */
 public void validateStartDate(final String startDate)
  throws WrongInputException {
  try {
   if (!startDate.matches(DATE_FORMAT_REGEX)) {
    throw new WrongInputException(ValidationMessages.DATE_PATTERN);
   }
   DateTimeFormatter dateFormatter = DateTimeFormatter
    .ofPattern("dd/MM/yyyy");
   LocalDate parsedStartDate = LocalDate.parse(startDate, dateFormatter);
   LocalDate currentDate = LocalDate.now();
   if (parsedStartDate.isBefore(currentDate)) {
    throw new WrongInputException(ValidationMessages.START_DATE_ERROR);
   }
  } catch (Exception e) {
   throw new WrongInputException(ValidationMessages.DATE_PATTERN);
  }
 }

 /**
  *
  * @param projectName projectName.
  * @throws WrongInputException WrongInputException.
  */
 public void validateProjectName(final String projectName)
  throws WrongInputException {
  try {
   if (projectName == null || projectName.isEmpty()
    || projectName.matches(".*\\d.*")) {
    throw new WrongInputException(ValidationMessages.INVALID_PROJECT);
   }
  } catch (Exception e) {
   throw new WrongInputException(ValidationMessages.INVALID_PROJECT_TYPE);
  }
 }

 /**
  *
  * @param description description.
  * @throws WrongInputException WrongInputException.
  */
 public void validateProjectDescription(final String description)
  throws WrongInputException {
  if (description == null || description.isEmpty()) {
   throw new WrongInputException(ValidationMessages.INVALID_DESCRIPTION);
  }
 }

 /**
  *
  * @param projectName projectName.
  * @throws DataAlreadyExistsException DataAlreadyExistsException.
  */
 public void validateDuplicateProjectName(final String projectName)
  throws DataAlreadyExistsException {
  ProjectEntity existingProject =
   projectRepository.findByName(projectName.trim());
  if (existingProject != null) {
   throw new DataAlreadyExistsException(
    ValidationMessages.PROJECT_NAME_EXISTS);
  }
 }

 /**
  *
  * @param managerEmployeeId managerEmployeeId.
  * @throws WrongInputException WrongInputException.
  */
 public void validateManagerExistence(final Long managerEmployeeId)
  throws WrongInputException {
  Optional<RegisterEntity> managerEntity = registerRepository
   .findById(managerEmployeeId);
  if (managerEntity.isEmpty()) {
   throw new WrongInputException(ValidationMessages.MANAGER_NOT_EXISTS);
  }
  if (!managerEntity.get().getEmpRole().equals("manager")) {
   throw new WrongInputException(ValidationMessages.NOT_MANAGER);
  }
 }

 /**
  *
  * @param managerId managerId.
  * @throws DataNotFoundException DataNotFoundException.
  * @throws WrongInputException   WrongInputException.
  */
 public void validateManagerId(final Long managerId)
  throws DataNotFoundException, WrongInputException {

  Optional<RegisterEntity> managerEntity = registerRepository
   .findById(managerId);
  if (managerEntity.isEmpty()) {
   throw new DataNotFoundException(ValidationMessages.MANAGER_NOT_EXISTS);
  }
  if (!managerEntity.get().getEmpRole().equals("manager")) {
   throw new WrongInputException(ValidationMessages.NOT_MANAGER);
  }

 }

 /**
  *
  * @param assignProjectDto assignProjectDto.
  * @throws WrongInputException WrongInputException.
  */
 public void validateAssignProjectDto(
  final AssignProjectDto assignProjectDto) throws WrongInputException {

  RegisterEntity employee = registerRepository
   .findByEmpId(assignProjectDto.getEmpId()).orElse(null);

  if (employee == null) {
   throw new WrongInputException(ValidationMessages.EMPLOYEE_NOT_EXISTS);

  }

  if (employee.getProjectId() != null) {
   throw new WrongInputException(
    ValidationMessages.EMPLOYEE_PROJECT_ALREDY_ASSIGNED);
  }

  ProjectEntity project = projectRepository
   .findByProjectId(assignProjectDto.getProjectId());
  if (project == null) {
   throw new WrongInputException(ValidationMessages.PROJECT_NOT_EXISTS);
  }
 }

 /**
  *
  * @param email email.
  * @throws WrongInputException WrongInputException.
  */
 public void validateManagerEmail(final String email)
  throws WrongInputException {
  RegisterEntity employee = registerRepository.findByEmpEmail(email)
   .orElse(null);

  if (employee == null) {
   throw new WrongInputException(ValidationMessages.EMPLOYEE_NOT_EXISTS);
  }
  if (!employee.getEmpRole().equals("manager")) {
   throw new WrongInputException(ValidationMessages.NOT_MANAGER);
  }
 }

 /**
  *
  * @param id id.
  * @throws WrongInputException WrongInputException.
  */
 public void validateRejectResource(final Long id)
  throws WrongInputException {
  Optional<RequestResource> resourceRequest = requestResourceRepository
   .findById(id);
  try {
   if (resourceRequest.isEmpty()) {
    throw new DataNotFoundException(
     ValidationMessages.RESOURCE_REQUEST_NOT_EXISTS);
   }
  } catch (DataNotFoundException exception) {
   throw exception;
  }

 }

 /**
  *
  * @param email email.
  * @throws DataNotFoundException DataNotFoundException.
  * @throws WrongInputException   WrongInputException.
  */
 public void validateUserEmail(final String email)
  throws DataNotFoundException, WrongInputException {
  RegisterEntity employee = registerRepository.findByEmpEmail(email)
   .orElse(null);
  if (employee == null) {
   throw new WrongInputException(ValidationMessages.EMPLOYEE_NOT_EXISTS);
  }

  if (employee.getEmpRole().equals("manager")
   || employee.getEmpRole().equals("admin")) {
   throw new WrongInputException(ValidationMessages.NOT_ACCESSIBLE);
  }
 }

 /**
  *
  * @param updateSkillsDto updateSkillsDto.
  * @throws DataNotFoundException DataNotFoundException.
  * @throws WrongInputException   WrongInputException.
  */
 public void validateUpdateSkillsDto(final UpdateSkillsDto updateSkillsDto)
  throws DataNotFoundException, WrongInputException {

  String empEmail = updateSkillsDto.getEmpEmail();
  RegisterEntity employee = registerRepository.findByEmpEmail(empEmail)
   .orElse(null);
  if (employee == null) {
   throw new DataNotFoundException(ValidationMessages.EMPLOYEE_NOT_EXISTS);
  }

  List<String> empSkills = updateSkillsDto.getEmpSkills();
  if (empSkills == null || empSkills.isEmpty()) {
   throw new WrongInputException(ValidationMessages.ATLEAST_ONE_SKILL);
  }
 }

 /**
  *
  * @param empId empId.
  * @throws WrongInputException WrongInputException.
  */
 public void unassignProjectValidator(final String empId)
  throws WrongInputException {
  RegisterEntity employee = registerRepository.findByEmpId(empId)
   .orElse(null);
  if (employee == null) {
   throw new DataNotFoundException(ValidationMessages.EMPLOYEE_NOT_EXISTS);
  }
  if (employee.getProjectId() == null) {
   throw new WrongInputException(
    ValidationMessages.EMPLOYEE_PROJECT_NOT_ASSIGNED);
  }
 }

}
