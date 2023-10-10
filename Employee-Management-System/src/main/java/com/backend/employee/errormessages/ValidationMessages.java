package com.backend.employee.errormessages;

public final class ValidationMessages {
 /**
  * default constructor.
  */
 private ValidationMessages() {

 }

 /**
  * Not null validation message.
  */
 public static final String NOT_NULL = "Field can not be null.";
 /**
  * Not null employee dvalidation message.
  */
 public static final String NOT_NULL_EMPLOYEE_ID =
  "Employee Id should not be null";
 /**
  * Not N0000 employee dvalidation message.
  */
 public static final String NOT_N0000_EMPLOYEE_ID =
  "Employee Id should not be N0000";
 /**
  * Role can not null validation message.
  */
 public static final String NOT_ROLE_NULL = "Role can not be null.";
 /**
  * Empty field validation message.
  */
 public static final String NOT_EMPTY = "Field can not be empty.";
 /**
  * Empty email field validation message.
  */
 public static final String NOT_EMPTY_EMAIL = "Email should not be null";
 /**
  * Valid admin email field validation message.
  */
 public static final String VALID_ADMIN_EMAIL =
  "Admin should have email id as ankita.sharma@nucleusteq.com";
 /**
  * Invalid email format validation message.
  */
 public static final String INVALID_EMAIL_FORMAT = "Invalid email format.";
 /**
  * Email domain validation message.
  */
 public static final String EMAIL_DOMAIN =
  "Email should end with @nucleusteq.com and must have characters before @";
 /**
  * Only letter validation message.
  */
 public static final String ONLY_LETTER_NAME =
  "Name should only contain letters.";
 /**
  * EmpId format validation message.
  */
 public static final String EMPID_FORMAT =
  "Employee Id should be of pattern 'NXXXX'.";
 /**
  * Date pattern validation message.
  */
 public static final String DATE_PATTERN =
  "Date should be in pattrn 'DD/MM/YYYY'.";
 /**
  * Start date should be a future date validation message.
  */
 public static final String START_DATE_ERROR =
  "Start date must be a future date";
 /**
  * Invalid date validation message.
  */
 public static final String INVALID_DATE = "Invalid date.";
 /**
  * Date pattern validation message.
  */
 public static final String INVALID_DOB_PATTERN =
  "DOB should be in pattrn 'DD/MM/YYYY'.";
 /**
  * Date pattern validation message.
  */
 public static final String INVALID_DOJ_PATTERN =
  "DOJ should be in pattrn 'DD/MM/YYYY'.";
 /**
  * Invalid dob validation message.
  */
 public static final String INVALID_DOB = "DOB should not be empty";
 /**
  * Invalid name validation message.
  */
 public static final String EMPTY_NAME = "Employee name should not be empty";
 /**
  * Invalid password length validation message.
  */
 public static final String INVALID_PASSWORD_LENGTH =
  "Password should contain atleast 8 digit numbers or letters or both.";
 /**
  * Invalid age validation message.
  */
 public static final String AGE = "The age should be above 18 years.";
 /**
  * Invalid DOJ validation message.
  */
 public static final String NOT_NULL_DOJ = "DOJ should not be empty";
 /**
  * Invalid password validation message.
  */
 public static final String NOT_NULL_PASSWORD = "Password should not be empty";
 /**
  * Invalid date of joining validation message.
  */
 public static final String JOINING_GAP =
  "The joining date should be after 18 years of DOB.";
 /**
  * Contact length validation message.
  */
 public static final String CONTACT_LENGTH =
  "Contact No must be of length 10 digits only.";
 /**
  * Contact empty validation message.
  */
 public static final String CONTACT_EMPTY =
  "Contact number should not be empty";
 /**
  * Designation empty validation message.
  */
 public static final String DESIGNATION_EMPTY =
  "Employee Designation should not be empty";
 /**
  * Invalid Designation validation message.
  */
 public static final String INVALID_DESIGNATION =
  "Invalid employee designation";
 /**
  * Password length validation message.
  */
 public static final String PASSWORD_LENGTH =
  "Minimum length of password should be 8.";
 /**
  * Role validation message.
  */
 public static final String EMPTY_ROLE = "Employee role should not be empty";
 /**
  * Skill validation message.
  */
 public static final String EMPTY_SKILLS =
  "Employee Skills should not be empty";
 /**
  * Role validation message.
  */
 public static final String INVALID_ROLE =
  "Only Employee and Manager role is allowed";
 /**
  * Can not admin validation message.
  */
 public static final String CAN_NOT_ADMIN = "Role can not be admin.";
 /**
  * Email exists validation message.
  */
 public static final String EMAIL_EXISTS =
  "Employee with entered email already exists.";
 /**
  * Email not exists validation message.
  */
 public static final String EMAIL_NOT_EXISTS = "Email does not exists.";
 /**
  * EmapId exists validation message.
  */
 public static final String EMPID_EXISTS = "Employee id already exists.";
 /**
  * Contact exists validation message.
  */
 public static final String CONTACT_EXISTS = "Contact number already exists.";
 /**
  * Project name exists validation message.
  */
 public static final String PROJECT_NAME_EXISTS =
  "Project with the same name already exists.";
 /**
  * Employee not exists validation message.
  */
 public static final String EMPLOYEE_NOT_EXISTS = "Employee does not exist.";
 /**
  * Manager not exists validation message.
  */
 public static final String MANAGER_NOT_EXISTS = "Manager does not exist.";
 /**
  * Project not exists validation message.
  */
 public static final String PROJECT_NOT_EXISTS = "Project does not exist";
 /**
  * Invalid location validation message.
  */
 public static final String INVALID_LOCATION = "Invalid Employee Location";
 /**
  * Empty location validation message.
  */
 public static final String EMPTY_LOCATION =
  "Employee Location should not be empty";
 /**
  * Not a manager validation message.
  */
 public static final String NOT_MANAGER =
  "The provided manager is not a manager.";
 /**
  * Not a project manager validation message.
  */
 public static final String NOT_PROJECT_MANAGER =
  "Provided manager is not a manager of this project.";
 /**
  * Employee project already assigned validation message.
  */
 public static final String EMPLOYEE_PROJECT_ALREDY_ASSIGNED =
  "Employee is already assigned a project.";
 /**
  * Not available resource request validation message.
  */
 public static final String NO_AVAILABLE_RESOURCE_REQUEST =
  "There are no resource requests to be entertained.";
 /**
  * Invalid project name validation message.
  */
 public static final String INVALID_PROJECT =
  "Project is invalid. It shouldn't have digits and should not be empty";
 /**
  * Only unassign to employee validation message.
  */
 public static final String ONLY_UNASSIGN_TO_EMPLOYEE =
  "You can only unassign project to an employee.";
 /**
  * At least one skill validation message.
  */
 public static final String ATLEAST_ONE_SKILL =
  "At least one skill is required";
 /**
  * At least one skill validation message.
  */
 public static final String INVALID_SKILL = "Invalid Employee Skill: ";
 /**
  * Invalid project name type.
  */
 public static final String INVALID_PROJECT_TYPE = "Invalid name type";
 /**
  * Not accessible.
  */
 public static final String NOT_ACCESSIBLE =
  "You cannot access this information.";
 /**
  * Employee project not assigned validation message.
  */
 public static final String EMPLOYEE_PROJECT_NOT_ASSIGNED =
  "Employee does not have a project in first place.";
 /**
  * Resource request not exists validation message.
  */
 public static final String RESOURCE_REQUEST_NOT_EXISTS =
  "Resource request does not exist.";
 /**
  * Resource request already made validation message.
  */
 public static final String RESOURCE_REQUEST_ALREADY_MADE =
  "The resource is already requested for this project.";
 /**
  * Incorrect password validation message.
  */
 public static final String INCORRECT_PASSWORD = "Incorrect Password.";
 /**
  * Project description error.
  */
 public static final String INVALID_DESCRIPTION =
  "Project description is required";

}
