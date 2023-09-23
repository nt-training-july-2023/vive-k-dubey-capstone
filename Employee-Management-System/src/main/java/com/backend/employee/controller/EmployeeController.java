package com.backend.employee.controller;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.dto.UpdateSkillsDto;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.service.EmployeeService;
import com.backend.employee.validations.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
 /**
  * Object.
  */
 private EmployeeService employeeService;

 /**
  *
  * @param employeeServiceLocal employeeServiceLocal.
  */
 @Autowired
 public EmployeeController(final EmployeeService employeeServiceLocal) {
  this.employeeService = employeeServiceLocal;
 }

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
  validationService.validateUserEmail(email);
  return employeeService.getEmployee(email);
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
  @RequestBody final UpdateSkillsDto updateSkillsDto)
  throws DataNotFoundException, WrongInputException {
  validationService.validateUpdateSkillsDto(updateSkillsDto);
  return employeeService.updateSkills(updateSkillsDto);
 }
}
