package com.backend.employee.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.backend.employee.dto.AssignProjectDto;
import com.backend.employee.dto.AssignProjectOutDto;
import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.ManagerOutDto;
import com.backend.employee.dto.ManagerInfoDto;
import com.backend.employee.dto.ProjectDto;
import com.backend.employee.dto.ProjectOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.service.AdminService;
import com.backend.employee.service.RegisterService;
import com.backend.employee.validations.RegisterValidationService;
import com.backend.employee.validations.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AdminController.class)
class AdminControllerTest {
 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private AdminService adminService;

 @MockBean
 private ValidationService validationService;

 @MockBean
 private RegisterValidationService registerValidationService;    

 @Test
 public void testAddEmployee() throws Exception {
  CommonResponseDto rd = new CommonResponseDto("Success");

  when(adminService.addEmployee(any(RegisterDto.class))).thenReturn(rd);
  doNothing().when(registerValidationService)
   .validateRegisterDto(any(RegisterDto.class));

  String json = "{\r\n" + "    \"empId\": \"N0007\",\r\n"
   + "    \"empName\": \"Sagar\",\r\n"
   + "    \"empEmail\": \"sagar@nucleusteq.com\",\r\n"
   + "    \"empDOB\": \"25/04/2000\",\r\n"
   + "    \"empDOJ\": \"25/04/2023\",\r\n"
   + "    \"empLocation\": \"Raipur\",\r\n"
   + "    \"empDesignation\": \"Engineer\",\r\n"
   + "    \"empContactNo\": \"7777777777\",\r\n"
   + "    \"empSkills\":[\"Java\",\"springboot\"],\r\n"
   + "    \"empRole\": \"Manager\"\r\n" + "\r\n" + "}";

  mockMvc
   .perform(post("/employee/add").contentType(MediaType.APPLICATION_JSON)
    .content(json))
   .andExpect(status().isOk())
   .andExpect(jsonPath("$.message").value("Success"));

  verify(registerValidationService, times(1))
   .validateRegisterDto(any(RegisterDto.class));

  MvcResult mvcResult = mockMvc.perform(post("/employee/add")
   .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

  MockHttpServletRequest request = mvcResult.getRequest();
  String requestURI = request.getRequestURI();

  assertEquals("/employee/add", requestURI);
 }

 @Test
 public void testGetAllManagers() throws Exception {
  List<ManagerOutDto> response = new ArrayList<>();
  when(adminService.getAllManagers()).thenReturn(response);

  mockMvc.perform(get("/employee/getAllManagers"))
   .andExpect(status().isOk())
   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
   .andExpect(jsonPath("$").isArray())
   .andExpect(jsonPath("$.length()").value(response.size()));

  MvcResult mvcResult = mockMvc.perform(get("/employee/getAllManagers"))
   .andReturn();

  MockHttpServletRequest request = mvcResult.getRequest();
  String requestURI = request.getRequestURI();

  assertEquals("/employee/getAllManagers", requestURI);
 }

 @Test
 public void testGetAllByManagerId() throws Exception {
  List<ProjectOutDto> response = new ArrayList<ProjectOutDto>();

  ProjectOutDto projectOutDto = new ProjectOutDto();
  projectOutDto.setId(5L);
  projectOutDto.setManagerId("N0001");

  response.add(projectOutDto);
  when(adminService.getAllByManagerId(5L)).thenReturn(response);

  mockMvc
   .perform(get("/employee/getAll/project/5")
    .contentType(MediaType.APPLICATION_JSON))
   .andExpect(status().isOk())
   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
   .andExpect(jsonPath("$[0].id").value(5L))
   .andExpect(jsonPath("$[0].managerId").value("N0001"));

  MvcResult mvcResult = mockMvc.perform(get("/employee/getAll/project/5")
   .contentType(MediaType.APPLICATION_JSON)).andReturn();

  MockHttpServletRequest request = mvcResult.getRequest();
  String requestURI = request.getRequestURI();

  assertEquals("/employee/getAll/project/5", requestURI);
 }

 @Test
 public void testGetAllManagersInfo() throws Exception {
  List<ManagerInfoDto> response = new ArrayList<>();
  when(adminService.getAllManagersInfo()).thenReturn(response);

  mockMvc.perform(get("/employee/getAllManagersInfo"))
   .andExpect(status().isOk())
   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
   .andExpect(jsonPath("$").isArray())
   .andExpect(jsonPath("$.length()").value(response.size()));
  MvcResult mvcResult = mockMvc.perform(get("/employee/getAllManagersInfo"))
   .andReturn();

  MockHttpServletRequest request = mvcResult.getRequest();
  String requestURI = request.getRequestURI();

  assertEquals("/employee/getAllManagersInfo", requestURI);
 }

 @Test
 public void testGetAllEmployees() throws Exception {
  List<RegisterDto> employeeList = new ArrayList<>();

  when(adminService.getAllEmployees()).thenReturn(employeeList);

  mockMvc.perform(get("/employee/getAllEmployees"))
   .andExpect(status().isOk())
   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
   .andExpect(jsonPath("$").isArray())
   .andExpect(jsonPath("$.length()").value(employeeList.size()));

  MvcResult mvcResult = mockMvc.perform(get("/employee/getAllEmployees"))
   .andReturn();

  MockHttpServletRequest request = mvcResult.getRequest();
  String requestURI = request.getRequestURI();

  assertEquals("/employee/getAllEmployees", requestURI);
 }

 @Test
 void testAddProject() throws Exception {
  CommonResponseDto output = new CommonResponseDto("Success");
  when(adminService.addProject(any(ProjectDto.class))).thenReturn(output);

  String json = "{\r\n" + "    \"name\": \"EMS\",\r\n"
   + "    \"managerEmployeeId\":5,\r\n"
   + "    \"startDate\":\"15/09/2023\",\r\n"
   + "    \"skills\":[\"java\",\"python\"],\r\n"
   + "    \"description\":\"Employee Management System\"\r\n" + "}";

  mockMvc
   .perform(post("/employee/addProject")
    .contentType(MediaType.APPLICATION_JSON).content(json))
   .andExpect(status().isOk())
   .andExpect(jsonPath("$.message").value("Success"));

  MvcResult mvcResult = mockMvc.perform(post("/employee/addProject")
   .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

  MockHttpServletRequest request = mvcResult.getRequest();
  String requestURI = request.getRequestURI();

  assertEquals("/employee/addProject", requestURI);
 }

 @Test
 void testGetAllEmployeesAndManagers() throws Exception {
  List<RegisterDto> employeesAndManagersList = new ArrayList<>();

  when(adminService.getAllEmployeesAndManagers())
   .thenReturn(employeesAndManagersList);

  mockMvc.perform(get("/employee/getAllEmployeesAndManagers"))
   .andExpect(status().isOk())
   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
   .andExpect(jsonPath("$").isArray()).andExpect(
    jsonPath("$.length()").value(employeesAndManagersList.size()));

  MvcResult mvcResult = mockMvc
   .perform(get("/employee/getAllEmployeesAndManagers")).andReturn();

  MockHttpServletRequest request = mvcResult.getRequest();
  String requestURI = request.getRequestURI();

  assertEquals("/employee/getAllEmployeesAndManagers", requestURI);
 }

 @Test
 void testGetAllProjects() throws Exception {
  List<ProjectDto> projectDtoList = new ArrayList<>();

  when(adminService.getAllProjects()).thenReturn(projectDtoList);

  mockMvc
   .perform(MockMvcRequestBuilders.get("/employee/getAllProjects")
    .contentType(MediaType.APPLICATION_JSON))
   .andExpect(MockMvcResultMatchers.status().isOk())
   .andExpect(
    MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
   .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
   .andExpect(MockMvcResultMatchers.jsonPath("$.length()")
    .value(projectDtoList.size()));

  MvcResult mvcResult = mockMvc
   .perform(MockMvcRequestBuilders.get("/employee/getAllProjects"))
   .andReturn();

  String requestURI = mvcResult.getRequest().getRequestURI();
  assertEquals("/employee/getAllProjects", requestURI);
 }

 @Test
 void testGetAllProjectsForAssign() throws Exception {
  List<AssignProjectOutDto> assignProjectOutDtoList = new ArrayList<>();

  when(adminService.getAllProjectsForAssign())
   .thenReturn(assignProjectOutDtoList);

  mockMvc
   .perform(get("/employee/getAllProjectsForAssign")
    .contentType(MediaType.APPLICATION_JSON))
   .andExpect(status().isOk())
   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
   .andExpect(jsonPath("$").isArray())
   .andExpect(jsonPath("$.length()").value(assignProjectOutDtoList.size()));

  MvcResult mvcResult = mockMvc
   .perform(get("/employee/getAllProjectsForAssign")).andReturn();

  String requestURI = mvcResult.getRequest().getRequestURI();
  assertEquals("/employee/getAllProjectsForAssign", requestURI);
 }

 @Test
 void testAssignProject() throws Exception {
  CommonResponseDto commonResponseDto = new CommonResponseDto("Success");
  when(adminService.assignProject(any(AssignProjectDto.class)))
   .thenReturn(commonResponseDto);

  String json = "{\r\n" + "    \"empId\": \"N4000\",\r\n"
   + "    \"projectId\": 5\r\n" + "}";

  mockMvc
   .perform(post("/employee/assignProject")
    .contentType(MediaType.APPLICATION_JSON).content(json))
   .andExpect(status().isOk())
   .andExpect(jsonPath("$.message").value("Success"));

  MvcResult mvcResult = mockMvc.perform(post("/employee/assignProject")
   .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

  String requestURI = mvcResult.getRequest().getRequestURI();
  assertEquals("/employee/assignProject", requestURI);
 }
 
 @Test
 void testGetFilteredEmployee() throws Exception {

     String jsonRequest = "{"
         + "\"skills\": [\"Java\", \"Spring\"],"
         + "\"checked\": true"
         + "}";


     MvcResult mvcResult = mockMvc.perform(post("/employee/filter")
             .contentType(MediaType.APPLICATION_JSON)
             .content(jsonRequest))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$").isArray())
             .andReturn();


     String requestURI = mvcResult.getRequest().getRequestURI();
     assertEquals("/employee/filter", requestURI);
 }
 
 @Test
 void testUnassignProject() throws Exception {
     String empId = "N0001"; // Replace with a valid empId

     // Perform a PUT request to the "/unassignProject/{empId}" endpoint
     mockMvc.perform(put("/employee/unassignProject/{empId}", empId))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.message").value("Project unassigned successfully"))
             .andReturn();
 }

}


