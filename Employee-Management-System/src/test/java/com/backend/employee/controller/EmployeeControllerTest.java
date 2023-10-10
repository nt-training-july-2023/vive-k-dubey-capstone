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
import com.backend.employee.dto.UpdateSkillsDto;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.service.AdminService;
import com.backend.employee.service.EmployeeService;
import com.backend.employee.service.RegisterService;
import com.backend.employee.validations.RegisterValidationService;
import com.backend.employee.validations.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private EmployeeService employeeService;


 @MockBean
 private ValidationService validationService;

 @MockBean
 private RegisterValidationService registerValidationService;
 
 @Test
 void testGetEmployeeByEmail() throws Exception {
     String email = "test@example.com"; 

     RegisterDto expectedDto = new RegisterDto();
     expectedDto.setEmpEmail(email);
     expectedDto.setEmpName("Vivek");

     when(employeeService.getEmployee(email)).thenReturn(expectedDto);

     mockMvc.perform(get("/api/employee/{email}", email))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.empEmail").value(email)) 
             .andExpect(jsonPath("$.empName").value("Vivek")) 
             .andReturn();
 }
 
 @Test
 void testUpdateSkills() throws Exception {
     String json = "{"
             + "\"empEmail\": \"employee@example.com\","
             + "\"empSkills\": [\"Java\", \"Spring\"]"
             + "}";
     
     CommonResponseDto commonResponseDto = new CommonResponseDto("Updated Skills Successfully");

     when(employeeService.updateSkills(any(UpdateSkillsDto.class)))
             .thenReturn(commonResponseDto);

     mockMvc.perform(post("/api/employee/updateskills")
             .contentType(MediaType.APPLICATION_JSON)
             .content(json))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.message").value("Updated Skills Successfully"));

     MvcResult mvcResult = mockMvc.perform(post("/api/employee/updateskills")
             .contentType(MediaType.APPLICATION_JSON)
             .content(json))
             .andReturn();

     String requestURI = mvcResult.getRequest().getRequestURI();
     assertEquals("/api/employee/updateskills", requestURI);
 }





}
