package com.backend.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.employee.dto.RequestResourceDto;
import com.backend.employee.dto.RequestResourceManagerProjectDto;
import com.backend.employee.dto.RequestedDto;
import com.backend.employee.dto.RequestedOutDto;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.service.RequestResourceService;
import com.backend.employee.validations.ValidationService;

/**
 * Represents controller class for request resource.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RequestResourceController {

 /**
  * Instance of Logger.
  */
 private static Logger LOGGER = LoggerFactory
  .getLogger(EmployeeController.class);

 /**
  * Instance of RequestResourceService.
  */
 @Autowired
 private RequestResourceService requestResourceService;
 /**
  * Instance of ValidationService.
  */
 @Autowired
 private ValidationService validationService;

 /**
  * Controller method to create resource request.
  *
  * @param requestResourceDto The input data for request resource.
  * @return response message.
  * @throws WrongInputException
  */
 @PostMapping("/requestResource/create")
 public CommonResponseDto createRequestResource(
  @RequestBody final RequestResourceDto requestResourceDto)
  throws WrongInputException {
  LOGGER.info("Stared controller for creating resource request");
  validationService.requestResourceValidator(requestResourceDto);
  requestResourceService.createRequestResource(requestResourceDto);
  CommonResponseDto res = new CommonResponseDto();
  res.setMessage("Requested resource successfully");
  return res;

 }

 @PostMapping("/requestResource/isRequested")
 public RequestedOutDto isRequested(
  @RequestBody final RequestedDto requestedDto) throws WrongInputException {
  LOGGER.info("Stared controller isRequsted");
  validationService.isRequestedValidator(requestedDto);
  RequestedOutDto requestedOutDto = requestResourceService
   .isRequested(requestedDto);
  LOGGER.info("End of controller isRequsted");
  return requestedOutDto;
 }

 @GetMapping("/getAll/project/byManager/{email}")
 public final List<RequestResourceManagerProjectDto> getAllByManagerEmail(
  @PathVariable final String email) {
  LOGGER.info("Started getting all projects by manager email controller");
  List<RequestResourceManagerProjectDto> projectList = requestResourceService
   .getAllByManagerEmail(email);
  LOGGER.info("End of getting all projects by manager email controller");
  return projectList;

 }
}
