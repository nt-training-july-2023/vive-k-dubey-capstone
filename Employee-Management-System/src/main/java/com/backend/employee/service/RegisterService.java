
package com.backend.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.LoginOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.validations.InputFieldChecks;

/**
 * Service class that directly contains the implementation of service methods.
 */
@Service
public class RegisterService {
 /**
  * Object of the register repo.
  */
 @Autowired
 private RegisterRepo registerRepo;
 /**
  * Object of the below field.
  */
 @Autowired
 private BCryptPasswordEncoder passwordEncoder;
 /**
  * Object of the below field.
  */
 @Autowired
 private PasswordEncoder passwordEncoder1;
 /**
  * Object of the below class.
  */
 @Autowired
 private InputFieldChecks inputFieldChecks;
 /**
  * Number of allowed registrations.
  */
 private static final int REGISTRATIONS = 500;

 /**
  * Add admin method.
  *
  * @param registerDto registerDto.
  * @return CommonResponseDto.
  */
 public CommonResponseDto addAdmin(final RegisterDto registerDto)
  throws WrongInputException {

  if (registerDto.getEmpEmail().equals("ankita.sharma@nucleusteq.com")) {
   registerDto.setEmpRole("admin");
  }

  String password = registerDto.getEmpPassword();

  /**
   * Create a new RegisterEntity instance.
   */
  RegisterEntity registerEntity = new RegisterEntity();

  /**
   * Fetch all registered entities.
   */
  List<RegisterEntity> allRegisterEntity = registerRepo.findAll();

  /**
   * Limit the number of registered entities to 5
   */
  if (allRegisterEntity.size() <= REGISTRATIONS) {
   /**
    * setting the empId
    */
   registerEntity.setEmpId(registerDto.getEmpId());
   /**
    * setting the empName
    */
   registerEntity.setEmpName(registerDto.getEmpName());
   /**
    * setting the empDob
    */
   registerEntity.setEmpDOB(registerDto.getEmpDOB());
   /**
    * setting the empDOJ
    */
   registerEntity.setEmpDOJ(registerDto.getEmpDOJ());
   /**
    * setting the empEmail
    */
   registerEntity.setEmpEmail(registerDto.getEmpEmail());
   /**
    * setting the empLocation
    */
   registerEntity.setEmpLocation(registerDto.getEmpLocation());
   /**
    * setting the empDesignation
    */
   registerEntity.setEmpDesignation(registerDto.getEmpDesignation());
   /**
    * setting the empContactNo
    */
   registerEntity.setEmpContactNo(registerDto.getEmpContactNo());

   /**
    * set password
    */
   registerEntity.setEmpPassword(password);
   /**
    * setting the role
    */
   registerEntity.setEmpRole(registerDto.getEmpRole());

   /**
    * Save the entity
    */
   registerRepo.save(registerEntity);
   /**
    * returning the response entity
    */
   return new CommonResponseDto("Admin added successfully");
  } else {
   /**
    * returning the response entity
    */
   throw new WrongInputException("Admin not added.");
  }
 }

 /**
  * Method for checking the authentication so that it can authenticate.
  *
  * @param loginDto loginDto.
  * @return Authentication status.
  * @throws WrongInputException   WrongInputException.
  * @throws DataNotFoundException DataNotFoundException.
  */

 public LoginOutDto authenticate(final LoginDto loginDto)
  throws WrongInputException, DataNotFoundException {

  Optional<RegisterEntity> employeeOptional = registerRepo
   .findByEmpEmail(loginDto.getEmpEmail());

  if (employeeOptional.isPresent()) {
   RegisterEntity employee = employeeOptional.get();

   LoginOutDto response = new LoginOutDto();
   response.setEmpRole(employee.getEmpRole());
   response.setMessage("Login successful");
   response.setEmpName(employee.getEmpName());
   return response;
  } else {
   throw new DataNotFoundException("User Not Found");
  }

 }

}
