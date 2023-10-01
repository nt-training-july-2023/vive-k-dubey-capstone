package com.backend.employee.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.LoginOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.service.RegisterService;
import com.backend.employee.validations.RegisterValidationService;
import com.backend.employee.validations.ValidationService;

import jakarta.validation.Valid;

/**
 * Controller class for handling registration and login operations.
 */
@CrossOrigin
@RestController
public class RegisterController {
 @Autowired
 private ValidationService validationService;
 
 @Autowired
 private RegisterValidationService registerValidationService;
 /**
  * Logger for logging purposes.
  */
 private static Logger LOGGER = LoggerFactory
  .getLogger(RegisterController.class);

 /**
  * registerService instance.
  */
 @Autowired
 private RegisterService registerService;

 /**
  * Handles the registration of a new admin.
  *
  * @param registerDto The DTO containing the registration information.
  * @return ResponseEntity indicating the status of the registration operation.
  * @throws WrongInputException 
  */
 @PostMapping("/admin")
 public CommonResponseDto addAdmin(
  @RequestBody final RegisterDto registerDto) throws WrongInputException {
  LOGGER.info("Started addAdmin controller");
  registerValidationService.validateRegisterDtoAdmin(registerDto);
  CommonResponseDto commonResponseDto = registerService.addAdmin(registerDto);
  LOGGER.info("Finished addAdmin controller");
  return commonResponseDto;
 }

 /**
  * Handles the authentication of a user during login.
  *
  * @param loginDto The DTO containing the login credentials.
  * @return ResponseEntity indicating status of the authentication operation.
  * @throws WrongInputException   WrongInputException.
  * @throws DataNotFoundException DataNotFoundException.
  */
 @PostMapping("/login")
 public LoginOutDto login(@Valid @RequestBody final LoginDto loginDto)
  throws WrongInputException, DataNotFoundException {
  LOGGER.info("Started login controller");
  validationService.loginValidation(loginDto);
  LoginOutDto loginOutDto = registerService.authenticate(loginDto);
  LOGGER.info("Finished login controller");
  return loginOutDto;
 }
}
