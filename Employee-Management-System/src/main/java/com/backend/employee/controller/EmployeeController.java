package com.backend.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.dto.UpdateSkillsDto;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.service.EmployeeService;
import com.backend.employee.validations.ValidationService;

import jakarta.validation.Valid;

/**
 * Controller class.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
 /**
  * Object of employee service.
  */
 @Autowired
 private EmployeeService employeeService;

 /**
  * Logger for logging the status.
  */
 private static Logger logger = LoggerFactory
  .getLogger(EmployeeController.class);


 /**
  * Instance of the validation service.
  */
 @Autowired
 private ValidationService validationService;

 /**
  *
  * @param email email.
  * @return RegisterDto.
  * @throws WrongInputException WrongInputException.
  */
 @GetMapping("/{email}")
 public RegisterDto getEmployeeByEmail(@PathVariable final String email)
  throws WrongInputException {
  logger.info("Started getEmployeeByEmail controller");
  validationService.validateUserEmail(email);
  RegisterDto registerDto = employeeService.getEmployee(email);
  logger.info("Finished getEmployeeByEmail controller");
  return registerDto;
 }

 /**
  *
  * @param updateSkillsDto updateSkillsDto.
  * @return CommonResponseDto.
  * @throws DataNotFoundException DataNotFoundException.
  * @throws WrongInputException
  */
 @PostMapping("/updateskills")
 public CommonResponseDto updateSkillsOfEmployee(
  @Valid @RequestBody final UpdateSkillsDto updateSkillsDto)
  throws DataNotFoundException, WrongInputException {
  logger.info("Started updateSkillsOfEmployee controller");
  validationService.validateUpdateSkillsDto(updateSkillsDto);
  CommonResponseDto commonResponseDto = employeeService
   .updateSkills(updateSkillsDto);
  logger.info("Finished updateSkillsOfEmployee controller");
  return commonResponseDto;
 }
}
