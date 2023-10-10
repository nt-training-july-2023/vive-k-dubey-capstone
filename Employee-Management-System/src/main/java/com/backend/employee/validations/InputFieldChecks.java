package com.backend.employee.validations;

import java.beans.JavaBean;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.RegisterRepo;

import java.util.Base64;
import java.util.Optional;

/**
 * Class to perform validation checks on input fields based on specific
 * patterns.
 */
@Component
@JavaBean
public class InputFieldChecks {
 /**
  * defines minimum length of password.
  */
 private static final int PASSWORDLENGTH = 8;
 /**
  * Object of register-repo.
  */
 @Autowired
 private RegisterRepo registerRepo;
 /**
  * Password encoder.
  */
 @Autowired
 private PasswordEncoder passwordEncoder;
 /**
  * Password-encoder.
  */
 @Autowired
 private BCryptPasswordEncoder passwordEncoders;

 /**
  * Checks if the provided employee ID matches the expected pattern.
  *
  * @param empId The employee ID to be validated.
  * @return True if the employee ID matches the pattern, otherwise false.
  */
 public boolean checkEmpId(final String empId) {
  String empIdPattern = "N\\d{4}$";
  return Pattern.matches(empIdPattern, empId);
 }

 /**
  * Checks if the provided date matches the expected pattern.
  *
  * @param date The date to be validated.
  * @return True if the date matches the pattern, otherwise false.
  */
 public boolean checkDate(final String date) {
  String message = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19|20)\\d\\d$";
  String datePattern = message;
  return Pattern.matches(datePattern, date);
 }

 /**
  * Checks if the provided employee email matches the expected pattern.
  *
  * @param empEmail The employee email to be validated.
  * @return True if the employee email matches the pattern, otherwise false.
  */
 public boolean checkEmpEmail(final String empEmail) {
  String empEmailPattern = "^[a-zA-Z0-9._%+-]+@nucleusteq\\.com$";
  return Pattern.matches(empEmailPattern, empEmail);
 }

 /**
  * Check if the provided employee contact number matches the expected pattern.
  *
  * @param empContactNo The employee contact number to be validated.
  * @return True if the employee contact number matches the pattern, otherwise
  *         false.
  */
 public boolean checkEmpContactNo(final String empContactNo) {
  String empContactNoPattern = "^\\d{10}$";
  return Pattern.matches(empContactNoPattern, empContactNo);
 }

 /**
  * Checks if the provided employee password length is at least 8 characters.
  *
  * @param empPassword The employee password to be validated.
  * @return True if the employee password length is at least 8 characters,
  *         otherwise false.
  */
 public boolean checkEmpPassword(final String empPassword) {
  return empPassword.length() >= PASSWORDLENGTH;
 }

 /**
  * Checks if the provided name matches the expected pattern.
  *
  * @param name The name to be validated.
  * @return True if the name matches the pattern, otherwise false.
  */
 public boolean checkValidName(final String name) {
  String namePattern = "^[A-Za-z\\s]+$";
  return Pattern.matches(namePattern, name);
 }

 /**
  *
  * @param empEmail The employee's email address.
  * @param password The employee's password.
  * @throws WrongInputException WrongInputException.
  */
 public void loginValidation(final String empEmail, final String password)
  throws WrongInputException {
  Optional<RegisterEntity> userOptional = registerRepo
   .findByEmpEmail(empEmail);
  if (!userOptional.isPresent()) {
   String message = "Employee with email id " + empEmail + " does not exist";
   throw new DataNotFoundException(message);
  }
  RegisterEntity user = userOptional.get();
  byte[] decodedBytes = Base64.getDecoder().decode(password);
  String decodedPassword = new String(decodedBytes,
   java.nio.charset.StandardCharsets.UTF_8);

  if (!passwordEncoders.matches(decodedPassword, user.getEmpPassword())) {
   String message = "Wrong password";
   throw new WrongInputException(message);
  }

 }

}
