package com.backend.employee.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
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
import com.backend.employee.dto.ManagerDto;
import com.backend.employee.dto.ManagerInfoDto;
import com.backend.employee.dto.ProjectDto;
import com.backend.employee.dto.ProjectOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.dto.ResponseDto;
import com.backend.employee.service.AdminService;
import com.backend.employee.service.RegisterService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegisterControllerTest {

	private MockMvc mockMvc;
	
 	@Mock
    private RegisterService registerService;

    @InjectMocks
    private RegisterController registerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }
    
    
    @Test
    public void testAddAdmin() throws Exception {
        when(registerService.addAdmin(any(RegisterDto.class)))
                .thenReturn(ResponseEntity.ok("Success"));

        String json = "{\r\n" + "    \"empId\": \"N0000\",\r\n"
                + "    \"empName\": \"Vivek\",\r\n"
                + "    \"empEmail\": \"vivek@nucleusteq.com\",\r\n"
                + "    \"empDOB\": \"25/04/2000\",\r\n"
                + "    \"empDOJ\": \"25/04/2020\",\r\n"
                + "    \"empLocation\": \"Raipur\",\r\n"
                + "    \"empDesignation\": \"Engineer\",\r\n"
                + "    \"empContactNo\": \"1111111111\",\r\n"
                + "    \"empPassword\": \"12345678\",\r\n"
                + "    \"empRole\": \"admin\"\r\n" + "}";

        mockMvc.perform(post("/admin")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));
    }
    
    @Test
    public void testLogin() throws Exception {
        LoginOutDto loginOutDto = new LoginOutDto();
        loginOutDto.setMessage("Success");
        loginOutDto.setEmpRole("Admin");
        when(registerService.authenticate(any(LoginDto.class)))
                .thenReturn(loginOutDto);
        String json = "{\r\n"
                + "    \"empEmail\": \"ankita.sharma@nucleusteq.com\",\r\n"
                + "    \"empPassword\": \"asd@12345#\"\r\n" + "}";
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"));
    }

    
    
    


}
