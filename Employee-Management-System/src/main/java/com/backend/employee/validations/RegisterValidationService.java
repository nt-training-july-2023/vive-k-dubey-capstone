package com.backend.employee.validations;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.errormessages.ValidationMessages;
import com.backend.employee.repo.RegisterRepo;

/**
 * Class to check if all the input fields fulfill the input requirement or not.
 */
@Component
@Service
public class RegisterValidationService {

 /**
  * minimum length of password.
  */
 private static final int MIN_PASSWORD_LENGTH = 8;

 /**
  * Age difference value.
  */

 private static final int AGE_DIFFERENCE = 18;

 /**
  * hashed password length.
  */
 private static final int HASHCODE_PWD_LENGTH = 20;

 /**
  * object of employee repository.
  */
 @Autowired
 private RegisterRepo registerRepo;

 /**
  * object of password encoder class to match password.
  */
 @Autowired
 private PasswordEncoder passwordEncoder;

 /**
  * check for employee id entered by user.
  *
  * @param empId getting employee id to check.
  * @throws WrongInputException throw an exception.
  */
 public void checkEmpId(final String empId) throws WrongInputException {
  if (empId == null || empId.trim().isEmpty()) {
   throw new WrongInputException(ValidationMessages.NOT_NULL_EMPLOYEE_ID);
  }
  String empIdPattern = "^N\\d{4}$";
  if (empId.equals("N0000")) {
   throw new WrongInputException(ValidationMessages.NOT_N0000_EMPLOYEE_ID);
  }

  if (!Pattern.matches(empIdPattern, empId)) {
   String message = ValidationMessages.EMPID_FORMAT;
   throw new WrongInputException(message);
  }
 }

 /**
  * check for date format.
  *
  * @param date get date.
  * @throws WrongInputException throw exception if not a valid DOB.
  */
 public void checkDob(final String date) throws WrongInputException {
  if (date == null || date.equals("")) {
   throw new WrongInputException(ValidationMessages.INVALID_DOB);
  }
  String datePattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/"
   + "(19|20)\\d\\d$";
  if (!Pattern.matches(datePattern, date)) {
   String message = ValidationMessages.INVALID_DOB_PATTERN;
   throw new WrongInputException(message);
  }
 }

 /**
  * check for valid DOJ.
  *
  * @param date accept a date.
  * @throws WrongInputException throw exception if date is not in valid format.
  */
 public void checkDoj(final String date) throws WrongInputException {
  if (date == null || date.trim().isEmpty()) {
   throw new WrongInputException(ValidationMessages.NOT_NULL_DOJ);
  }
  String datePattern = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/"
   + "(19|20)\\d\\d$";
  if (!Pattern.matches(datePattern, date)) {
   String message = ValidationMessages.INVALID_DOJ_PATTERN;
   throw new WrongInputException(message);
  }
 }

 /**
  * check if DOJ is 18 year older than DOB.
  *
  * @param dob accept DOB.
  * @param doj accept DOJ.
  * @throws WrongInputException throw an exception.
  */
 public void checkDatesDifference(final String dob, final String doj)
  throws WrongInputException {
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  LocalDate date1 = LocalDate.parse(dob, formatter);
  LocalDate date2 = LocalDate.parse(doj, formatter);

  Period period = Period.between(date1, date2);

  int yearsDiff = period.getYears();
  int monthsDiff = period.getMonths();
  int daysDiff = period.getDays();

  if (yearsDiff < AGE_DIFFERENCE || (yearsDiff == AGE_DIFFERENCE
   && (monthsDiff < 0 || (monthsDiff == 0 && daysDiff < 0)))) {
   String message = ValidationMessages.JOINING_GAP;
   throw new WrongInputException(message);
  }
 }

 /**
  * check for employee email format.
  *
  * @param empEmail getting email.
  * @throws WrongInputException throw exception for wrong email.
  */
 public void checkEmpEmail(final String empEmail)
  throws WrongInputException {
  if (empEmail == null || empEmail.trim().isEmpty()) {
   throw new WrongInputException(ValidationMessages.NOT_EMPTY_EMAIL);
  }
  String empEmailPattern = "^[a-zA-Z0-9._%+-]+@nucleusteq\\.com$";
  if (!Pattern.matches(empEmailPattern, empEmail)) {
   String message = ValidationMessages.EMAIL_DOMAIN;
   throw new WrongInputException(message);
  }
 }

 /**
  * Validation Check.
  *
  * @param empEmail empEmail.
  * @throws DataAlreadyExistsException DataAlreadyExistsException.
  */
 public void checkValidAdminEmail(final String empEmail)
  throws DataAlreadyExistsException {
  String validEmail = "ankita.sharma@nucleusteq.com";
  if (!empEmail.equals(validEmail)) {
   String message = ValidationMessages.VALID_ADMIN_EMAIL;
   throw new DataAlreadyExistsException(message);
  }
 }

 /**
  * check for employee contact number.
  *
  * @param empContactNo getting employee contact.
  * @throws WrongInputException throw exception if contact pattern not match.
  */
 public void checkEmpContactNo(final String empContactNo)
  throws WrongInputException {
  if (empContactNo == null || empContactNo.trim().isEmpty()) {
   throw new WrongInputException(ValidationMessages.CONTACT_EMPTY);
  }
  String empContactNoPattern = "^\\d{10}$";
  if (!Pattern.matches(empContactNoPattern, empContactNo.trim())) {
   String message = ValidationMessages.CONTACT_LENGTH;
   throw new WrongInputException(message);
  }
 }

 /**
  * check for password length.
  *
  * @param empPassword taking password to check.
  * @throws WrongInputException throw exception if not a valid password.
  */
 public void checkEmpPassword(final String empPassword)
  throws WrongInputException {
  if (empPassword == null || empPassword.trim().isEmpty()) {
   throw new WrongInputException(ValidationMessages.NOT_NULL_PASSWORD);
  }
  if (empPassword.length() < MIN_PASSWORD_LENGTH) {
   String message = ValidationMessages.INVALID_PASSWORD_LENGTH;
   throw new WrongInputException(message);
  }
 }

 /**
  * check for valid name.
  *
  * @param name getting name from user.
  * @throws WrongInputException throw exception for wrong name.
  */
 public void checkEmpName(final String name) throws WrongInputException {
  if (name == null || name.trim().isEmpty()) {
   throw new WrongInputException(ValidationMessages.EMPTY_NAME);
  }
  String namePattern = "^[A-Za-z\\s]+$";
  if (!Pattern.matches(namePattern, name)) {
   String message = ValidationMessages.ONLY_LETTER_NAME;
   throw new WrongInputException(message);
  }
 }

 /**
  * checks if email id is already registered or not.
  *
  * @param email takes an email id.
  * @throws DataAlreadyExistsException throw an exception if email already
  *                                    registered.
  */
 public void checkEmailExistence(final String email)
  throws DataAlreadyExistsException {
  Optional<RegisterEntity> registerEntity = registerRepo
   .findByEmpEmail(email);
  if (registerEntity.isPresent()) {
   String message = ValidationMessages.EMAIL_EXISTS;
   throw new DataAlreadyExistsException(message);
  }
 }

 /**
  * checks if emp id is already registered or not.
  *
  * @param empId accepts employee Id.
  * @throws DataAlreadyExistsException throw exception if id already exist.
  */
 public void checkEmpIdExistence(final String empId)
  throws DataAlreadyExistsException {
  Optional<RegisterEntity> registerEntity = registerRepo.findByEmpId(empId);
  if (registerEntity.isPresent()) {
   String message = ValidationMessages.EMPID_EXISTS;
   throw new DataAlreadyExistsException(message);
  }
 }

 /**
  * checks if contact number is already registered or not.
  *
  * @param empContact accept a contact number.
  * @throws DataAlreadyExistsException throw exception if id already exist.
  */
 public void checkEmpContactExistence(final String empContact)
  throws DataAlreadyExistsException {
  Optional<RegisterEntity> registerEntity = registerRepo
   .findByEmpContactNo(empContact);
  if (registerEntity.isPresent()) {
   String message = ValidationMessages.CONTACT_EXISTS;
   throw new DataAlreadyExistsException(message);
  }
 }

 /**
  *
  * @param empLocation empLocation.
  * @throws WrongInputException WrongInputException.
  */
 public void checkEmpLocation(final String empLocation)
  throws WrongInputException {
  if (empLocation == null || empLocation.trim().isEmpty()) {
   throw new WrongInputException(ValidationMessages.EMPTY_LOCATION);
  }
  List<String> validLocations = Arrays.asList("Indore", "Raipur",
   "Bangalore", "Phoenix", "Canada");
  if (!validLocations.contains(empLocation.trim())) {
   throw new WrongInputException(ValidationMessages.INVALID_LOCATION);
  }
 }

 /**
  *
  * @param empDesignation empDesignation.
  * @throws WrongInputException WrongInputException.
  */
 public void checkEmpDesignation(final String empDesignation)
  throws WrongInputException {
  if (empDesignation == null || empDesignation.trim().isEmpty()) {
   throw new WrongInputException(ValidationMessages.DESIGNATION_EMPTY);
  }

  String[] validDesignations = {"Software Engineer", "Data Engineer",
   "Senior Engineer", "Architect", "Technical Lead", "Senior Architect",
   "Recruiter", "Operation Analyst" };

  boolean isValidDesignation = false;
  for (String validDesignation : validDesignations) {
   if (empDesignation.trim().equals(validDesignation)) {
    isValidDesignation = true;
    break;
   }
  }

  if (!isValidDesignation) {
   throw new WrongInputException(ValidationMessages.INVALID_DESIGNATION);
  }
 }

 /**
  *
  * @param empSkills Skills of the employee.
  * @throws WrongInputException Throws wrong input exception.
  */
 public void checkEmpSkills(final List<String> empSkills)
  throws WrongInputException {
  if (empSkills == null || empSkills.isEmpty()) {
   throw new WrongInputException(ValidationMessages.EMPTY_SKILLS);
  }

  List<String> validSkills = Arrays.asList("JavaScript", "React", "Node.js",
   "Python", "Java", "HTML", "CSS", "SQL", "Machine Learning",
   "Data Analysis", "Spark", "Big data", "SpringBoot", "Postgres",
   "Snowflake", "Airflow");

  for (String skill : empSkills) {
   if (!validSkills.contains(skill)) {
    throw new WrongInputException(ValidationMessages.INVALID_SKILL + skill);
   }
  }
 }

 /**
  *
  * @param empRole empRole.
  * @throws WrongInputException WrongInputException.
  */
 public void checkEmpRole(final String empRole) throws WrongInputException {
  if (empRole == null || empRole.trim().isEmpty()) {
   throw new WrongInputException(ValidationMessages.EMPTY_ROLE);
  }
  String lowercaseEmpRole = empRole.trim().toLowerCase();

  if (!lowercaseEmpRole.equals("employee")
   && !lowercaseEmpRole.equals("manager")) {
   throw new WrongInputException(ValidationMessages.INVALID_ROLE);
  }
 }

 /**
  *
  * @param registerDto registerDto.
  * @throws WrongInputException        WrongInputException.
  * @throws DataAlreadyExistsException DataAlreadyExistsException.
  */
 public void validateRegisterDto(final RegisterDto registerDto)
  throws WrongInputException, DataAlreadyExistsException {
  checkEmpId(registerDto.getEmpId());
  checkDob(registerDto.getEmpDOB());
  checkDoj(registerDto.getEmpDOJ());
  checkEmpEmail(registerDto.getEmpEmail());
  checkEmpContactNo(registerDto.getEmpContactNo());
  checkEmpPassword(registerDto.getEmpPassword());
  checkEmpSkills(registerDto.getEmpSkills());
  checkEmpName(registerDto.getEmpName());
  checkDatesDifference(registerDto.getEmpDOB(), registerDto.getEmpDOJ());
  checkEmailExistence(registerDto.getEmpEmail());
  checkEmpIdExistence(registerDto.getEmpId());
  checkEmpContactExistence(registerDto.getEmpContactNo());
  checkEmpRole(registerDto.getEmpRole());
  checkEmpDesignation(registerDto.getEmpDesignation());
  checkEmpLocation(registerDto.getEmpLocation());
 }

 /**
  *
  * @param registerDto registerDto.
  * @throws WrongInputException        WrongInputException.
  * @throws DataAlreadyExistsException DataAlreadyExistsException.
  */
 public void validateRegisterDtoAdmin(final RegisterDto registerDto)
  throws WrongInputException, DataAlreadyExistsException {
  checkEmpId(registerDto.getEmpId());
  checkDob(registerDto.getEmpDOB());
  checkDoj(registerDto.getEmpDOJ());
  checkDatesDifference(registerDto.getEmpDOB(), registerDto.getEmpDOJ());
  checkEmpEmail(registerDto.getEmpEmail());
  checkValidAdminEmail(registerDto.getEmpEmail());
  checkEmpContactNo(registerDto.getEmpContactNo());
  checkEmpPassword(registerDto.getEmpPassword());
  checkEmpName(registerDto.getEmpName());
  checkEmailExistence(registerDto.getEmpEmail());
  checkEmpIdExistence(registerDto.getEmpId());
  checkEmpContactExistence(registerDto.getEmpContactNo());
  checkEmpDesignation(registerDto.getEmpDesignation());
  checkEmpLocation(registerDto.getEmpLocation());
 }

}
