package com.backend.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.employee.dto.RequestResourceDto;
import com.backend.employee.dto.RequestResourceManagerProjectDto;
import com.backend.employee.dto.RequestedDto;
import com.backend.employee.dto.RequestedOutDto;
import com.backend.employee.dto.ResourceRequestsAdminOutDto;
import com.backend.employee.entity.RequestResource;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.service.RequestResourceService;
import com.backend.employee.validations.ValidationService;

import jakarta.validation.Valid;

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
  .getLogger(RequestResourceController.class);

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
  @Valid @RequestBody final RequestResourceDto requestResourceDto)
  throws WrongInputException {
  LOGGER.info("Stared controller for creating resource request");
  validationService.requestResourceValidator(requestResourceDto);
  requestResourceService.createRequestResource(requestResourceDto);
  CommonResponseDto commonResponseDto = new CommonResponseDto();
  commonResponseDto.setMessage("Requested resource successfully");
  LOGGER.info("End of controller creating resource.");
  return commonResponseDto;

 }

 @PostMapping("/requestResource/isRequested")
 public RequestedOutDto isRequested(
  @Valid @RequestBody final RequestedDto requestedDto) throws WrongInputException {
  LOGGER.info("Stared controller isRequsted");
  validationService.isRequestedValidator(requestedDto);
  RequestedOutDto requestedOutDto = requestResourceService
   .isRequested(requestedDto);
  LOGGER.info("End of controller isRequsted");
  return requestedOutDto;
 }

 @GetMapping("/getAll/project/byManager/{email}")
 public final List<RequestResourceManagerProjectDto> getAllByManagerEmail(
  @PathVariable final String email) throws WrongInputException {
  LOGGER.info("Started getting all projects by manager email controller");
  validationService.validateManagerEmail(email);
  List<RequestResourceManagerProjectDto> projectList = requestResourceService
   .getAllByManagerEmail(email);
  LOGGER.info("End of getting all projects by manager email controller");
  return projectList;

 }
 
 @GetMapping("/requestResource/getAll/requests")
 public List<ResourceRequestsAdminOutDto> getAllResourceRequests() throws DataNotFoundException {
     LOGGER.info("Stared controller for get all resource requests");
     List<ResourceRequestsAdminOutDto> outDtoList = requestResourceService
             .getAllResourceRequests();
     LOGGER.info("End of controller for get all resource requests");
     return outDtoList;

 }
 
 @DeleteMapping("/requestResource/reject/{id}")
 public CommonResponseDto rejectResourceRequest(@PathVariable Long id) throws WrongInputException {
     LOGGER.info("Stared controller to reject a resource request");
     validationService.validateRejectResource(id);
     requestResourceService.rejectResourceRequest(id);
     CommonResponseDto commonResponseDto = new CommonResponseDto();
     commonResponseDto.setMessage("Resource request rejected successfully");
     LOGGER.info("End of controller to reject a resource request");
     return commonResponseDto;
     
 }
 
 @PostMapping("/request/accept/{id}")
 public final CommonResponseDto acceptRequest(@PathVariable Long id) throws WrongInputException {
     LOGGER.info("Stared controller to accept a resource request");
     validationService.validateRejectResource(id);
     CommonResponseDto commonResponseDto = requestResourceService.acceptRequest(id);
     LOGGER.info("End of controller to accept a resource request");
     return commonResponseDto;
 }
 
 


}
