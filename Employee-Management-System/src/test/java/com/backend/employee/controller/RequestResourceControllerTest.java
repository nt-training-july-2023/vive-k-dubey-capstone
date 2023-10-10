package com.backend.employee.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.LoginOutDto;
import com.backend.employee.dto.ManagerOutDto;
import com.backend.employee.dto.ManagerInfoDto;
import com.backend.employee.dto.ProjectDto;
import com.backend.employee.dto.ProjectOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.dto.RequestResourceDto;
import com.backend.employee.dto.RequestResourceManagerProjectDto;
import com.backend.employee.dto.RequestedDto;
import com.backend.employee.dto.RequestedOutDto;
import com.backend.employee.dto.ResourceRequestsAdminOutDto;
import com.backend.employee.service.AdminService;
import com.backend.employee.service.RegisterService;
import com.backend.employee.service.RequestResourceService;
import com.backend.employee.validations.RegisterValidationService;
import com.backend.employee.validations.ValidationService;

@WebMvcTest(RequestResourceController.class)
class RequestResourceControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private RegisterService registerService;

 @MockBean
 private ValidationService validationService;

 @MockBean
 private RegisterValidationService registerValidationService;

 @MockBean
 private RequestResourceService requestResourceService;

 @Test
 void testCreateRequestResource() throws Exception {
  String json = "{" + "\"empId\": \"N0001\","
   + "\"managerEmail\": \"manager@example.com\"," + "\"projectId\": 1,"
   + "\"comment\": \"Resource request comment\"" + "}";

  mockMvc
   .perform(post("/requestResource/create")
    .contentType(MediaType.APPLICATION_JSON).content(json))
   .andExpect(status().isOk())
   .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(
    jsonPath("$.message").value("Requested resource successfully"));

  MvcResult mvcResult = mockMvc.perform(post("/requestResource/create")
   .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

  String requestURI = mvcResult.getRequest().getRequestURI();
  assertEquals("/requestResource/create", requestURI);
 }
 
 @Test
 void testIsRequested() throws Exception {
     RequestedDto requestedDto = new RequestedDto();
     requestedDto.setEmpId("N0001");
     requestedDto.setManagerEmail("manager@example.com");

     RequestedOutDto requestedOutDto = new RequestedOutDto();
     requestedOutDto.setIsRequested(true);

     when(requestResourceService.isRequested(any(RequestedDto.class))).thenReturn(requestedOutDto);

     String json = "{"
             + "\"empId\": \"N0001\","
             + "\"managerEmail\": \"manager@example.com\""
             + "}";

     mockMvc.perform(post("/requestResource/isRequested")
             .contentType(MediaType.APPLICATION_JSON).content(json))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON));

     MvcResult mvcResult = mockMvc.perform(post("/requestResource/isRequested")
             .contentType(MediaType.APPLICATION_JSON).content(json))
             .andReturn();

     String requestURI = mvcResult.getRequest().getRequestURI();
     assertEquals("/requestResource/isRequested", requestURI);
 }
 
 @Test
 void testGetAllProjectsByManagerEmail() throws Exception {
     String managerEmail = "manager@example.com"; 

     List<RequestResourceManagerProjectDto> projectList = new ArrayList<>();
     RequestResourceManagerProjectDto projectDto = new RequestResourceManagerProjectDto();
     projectDto.setId(1L); 
     projectDto.setProjectName("Sample Project");
     projectList.add(projectDto);

     when(requestResourceService.getAllByManagerEmail(managerEmail)).thenReturn(projectList);

     mockMvc.perform(get("/getAll/project/byManager/{email}", managerEmail))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$").isArray())
             .andExpect(jsonPath("$.length()").value(projectList.size()))
             .andExpect(jsonPath("$[0].id").value(projectDto.getId()))
             .andExpect(jsonPath("$[0].projectName").value(projectDto.getProjectName()));

     MvcResult mvcResult = mockMvc.perform(get("/getAll/project/byManager/{email}", managerEmail))
             .andReturn();

     String requestURI = mvcResult.getRequest().getRequestURI();
     assertEquals("/getAll/project/byManager/" + managerEmail, requestURI);
 }
 
 @Test
 void testGetAllResourceRequests() throws Exception {
     List<ResourceRequestsAdminOutDto> resourceRequestsList = new ArrayList<>();
     ResourceRequestsAdminOutDto resourceRequestDto = new ResourceRequestsAdminOutDto();
     resourceRequestDto.setId(1L); 
     resourceRequestDto.setEmployeeId(2L); 
     resourceRequestDto.setEmployeeName("Vivek Dubey");
     resourceRequestDto.setManagerName("Manager Name");
     resourceRequestDto.setProjectName("Project Name");
     resourceRequestDto.setComment("Resource request comment");
     resourceRequestDto.setManagerId(3L); 
     resourceRequestDto.setProjectId(4L); 
     resourceRequestsList.add(resourceRequestDto);

     when(requestResourceService.getAllResourceRequests()).thenReturn(resourceRequestsList);

     mockMvc.perform(get("/requestResource/getAll/requests"))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$").isArray())
             .andExpect(jsonPath("$.length()").value(resourceRequestsList.size()))
             .andExpect(jsonPath("$[0].id").value(resourceRequestDto.getId()))
             .andExpect(jsonPath("$[0].employeeId").value(resourceRequestDto.getEmployeeId()))
             .andExpect(jsonPath("$[0].employeeName").value(resourceRequestDto.getEmployeeName()))
             .andExpect(jsonPath("$[0].managerName").value(resourceRequestDto.getManagerName()))
             .andExpect(jsonPath("$[0].projectName").value(resourceRequestDto.getProjectName()))
             .andExpect(jsonPath("$[0].comment").value(resourceRequestDto.getComment()))
             .andExpect(jsonPath("$[0].managerId").value(resourceRequestDto.getManagerId()))
             .andExpect(jsonPath("$[0].projectId").value(resourceRequestDto.getProjectId()));

     MvcResult mvcResult = mockMvc.perform(get("/requestResource/getAll/requests"))
             .andReturn();

     String requestURI = mvcResult.getRequest().getRequestURI();
     assertEquals("/requestResource/getAll/requests", requestURI);
 }
 
 @Test
 void testRejectResourceRequest() throws Exception {
     Long requestId = 1L; 
     CommonResponseDto commonResponseDto = new CommonResponseDto();
     commonResponseDto.setMessage("Resource request rejected successfully");

     mockMvc.perform(delete("/requestResource/reject/{id}", requestId))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.message").value("Resource request rejected successfully"));

     MvcResult mvcResult = mockMvc.perform(delete("/requestResource/reject/{id}", requestId))
             .andReturn();

     String requestURI = mvcResult.getRequest().getRequestURI();
     assertEquals("/requestResource/reject/1", requestURI); 
 }
 
 @Test
 void testAcceptRequest() throws Exception {
     Long requestId = 1L; 
     CommonResponseDto commonResponseDto = new CommonResponseDto();
     commonResponseDto.setMessage("Resource request accepted successfully");

     when(requestResourceService.acceptRequest(requestId)).thenReturn(commonResponseDto);

     mockMvc.perform(post("/request/accept/{id}", requestId))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.message").value("Resource request accepted successfully"));

     MvcResult mvcResult = mockMvc.perform(post("/request/accept/{id}", requestId))
             .andReturn();

     String requestURI = mvcResult.getRequest().getRequestURI();
     assertEquals("/request/accept/1", requestURI); 
 }






}
