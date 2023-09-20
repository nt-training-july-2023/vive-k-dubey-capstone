package com.backend.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.LoginOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.service.RegisterService;

/**
 * Controller class for handling registration and login operations.
 */
@CrossOrigin
@RestController
public class RegisterController {

 /**
  * Logger for logging purposes.
  */
 static final Logger LOGGER = LoggerFactory
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
  */
 @PostMapping("/admin")
 public final ResponseEntity<String> addAdmin(
  @RequestBody final RegisterDto registerDto) {
  return registerService.addAdmin(registerDto);
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
 public final LoginOutDto login(@RequestBody final LoginDto loginDto)
  throws WrongInputException, DataNotFoundException {
  return registerService.authenticate(loginDto);
 }
}
