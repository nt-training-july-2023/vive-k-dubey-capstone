package com.backend.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.LoginOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.UnauthorizedException;
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
 /**
  * Instance of validation service.
  */
 @Autowired
 private ValidationService validationService;
 /**
  * Instance of register validation service.
  */
 @Autowired
 private RegisterValidationService registerValidationService;
 /**
  * Logger for logging purposes.
  */
 private static Logger logger = LoggerFactory
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
  logger.info("Started addAdmin controller");
  registerValidationService.validateRegisterDtoAdmin(registerDto);
  CommonResponseDto commonResponseDto = registerService
   .addAdmin(registerDto);
  logger.info("Finished addAdmin controller");
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
  throws UnauthorizedException, DataNotFoundException {
  logger.info("Started login controller");
  validationService.loginValidation(loginDto);
  LoginOutDto loginOutDto = registerService.authenticate(loginDto);
  logger.info("Finished login controller");
  return loginOutDto;
 }
}
