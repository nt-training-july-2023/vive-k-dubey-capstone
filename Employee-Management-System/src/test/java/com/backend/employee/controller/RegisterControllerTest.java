package com.backend.employee.controller;

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
import com.backend.employee.service.AdminService;
import com.backend.employee.service.RegisterService;
import com.backend.employee.validations.RegisterValidationService;
import com.backend.employee.validations.ValidationService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@WebMvcTest(RegisterController.class)
class RegisterControllerTest {
   @Autowired
	  private MockMvc mockMvc;
	
   @MockBean
    private RegisterService registerService;
    
   @MockBean
   private ValidationService validationService;
   
   @MockBean
   private RegisterValidationService registerValidationService;
    
    @Test
    void testAddAdmin() throws Exception {
        when(registerService.addAdmin(any(RegisterDto.class)))
                .thenReturn(new CommonResponseDto("Success"));

        String json = "{"
                + "\"empId\": \"N0000\","
                + "\"empName\": \"Vivek\","
                + "\"empEmail\": \"vivek@nucleusteq.com\","
                + "\"empDOB\": \"25/04/2000\","
                + "\"empDOJ\": \"25/04/2020\","
                + "\"empLocation\": \"Raipur\","
                + "\"empDesignation\": \"Engineer\","
                + "\"empContactNo\": \"1111111111\","
                + "\"empPassword\": \"12345678\","
                + "\"empRole\": \"admin\""
                + "}";

        mockMvc.perform(post("/admin")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Success"));

        MvcResult mvcResult = mockMvc.perform(post("/admin")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andReturn();

        String requestURI = mvcResult.getRequest().getRequestURI();
        assertEquals("/admin", requestURI);
    }

    @Test
    void testLogin() throws Exception {
        LoginOutDto loginOutDto = new LoginOutDto();
        loginOutDto.setMessage("Success");
        loginOutDto.setEmpRole("Admin");
        when(registerService.authenticate(any(LoginDto.class)))
                .thenReturn(loginOutDto);

        String json = "{"
                + "\"empEmail\": \"ankita.sharma@nucleusteq.com\","
                + "\"empPassword\": \"asd@12345#\""
                + "}";

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.empRole").value("Admin"));

        MvcResult mvcResult = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andReturn();

        String requestURI = mvcResult.getRequest().getRequestURI();
        assertEquals("/login", requestURI);
    }


    
    
    


}
