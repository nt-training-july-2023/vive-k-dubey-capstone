package com.backend.employee.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.ManagerDto;
import com.backend.employee.dto.ManagerInfoDto;
import com.backend.employee.dto.ProjectDto;
import com.backend.employee.dto.ProjectOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.service.AdminService;
import com.backend.employee.service.RegisterService;

class AdminControllerTest {

		private MockMvc mockMvc;
	
	 	@Mock
	    private AdminService adminService;

	    @InjectMocks
	    private AdminController adminController;

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	    }
	    
	    
	    @Test
	    public void testAddEmployee() throws Exception {

	        CommonResponseDto rd = new CommonResponseDto("Success");
	        when(adminService.addEmployee(any(RegisterDto.class)))
	                .thenReturn(rd);
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
	        mockMvc.perform(post("/employee/add")
	                .contentType(MediaType.APPLICATION_JSON).content(json))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.message").value("Success"));
	    }
	    
	    @Test
	    public void testGetAllManagers() throws Exception {
	        List<ManagerDto> response = new ArrayList<>();
	        when(adminService.getAllManagers()).thenReturn(response);
	        mockMvc.perform(get("/employee/getAllManagers")).andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$").isArray())
	                .andExpect(jsonPath("$.length()").value(response.size()));
	    }
	    
	    @Test
	    public void testGetAllByManagerId() throws Exception {
	    	List<ProjectOutDto> response = new ArrayList<ProjectOutDto>();
	    	
	    	ProjectOutDto projectOutDto = new ProjectOutDto();
	    	projectOutDto.setId(5L);
	    	projectOutDto.setManagerId("N0001");
	    	
	    	response.add(projectOutDto);
	        when(adminService.getAllByManagerId(5L)).thenReturn(response);
	        mockMvc.perform(get("/employee/getAll/project/5")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$[0].id").value(5L)) // Access element by index
	                .andExpect(jsonPath("$[0].managerId").value("N0001")); 
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
	    }
	    
	    @Test
	    public void testGetAllEmployees() throws Exception {
	    	List<RegisterDto> employeeList = new ArrayList<>();
	        // Add mock employee data to the list

	        when(adminService.getAllEmployees()).thenReturn(employeeList);

	        mockMvc.perform(get("/employee//getAllEmployees")).andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$").isArray())
	                .andExpect(jsonPath("$.length()").value(employeeList.size()));
	    }
	    
	    @Test
	    void testAddProject() throws Exception {
	    	CommonResponseDto output = new CommonResponseDto("Success");
	        when(adminService.addProject(any(ProjectDto.class)))
	                .thenReturn(output);

	        String json = "{\r\n" + "    \"projectName\": \"EMS\",\r\n"
	                + "    \"managerId\":5,\r\n"
	                + "    \"startDate\":\"15/09/2023\",\r\n"
	                + "    \"skills\":[\"java\",\"python\"],\r\n"
	                + "    \"description\":\"Employee Management System\"\r\n"
	                + "}";
	        mockMvc.perform(post("/employee/addProject")
	                .contentType(MediaType.APPLICATION_JSON).content(json))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.message").value("Success"));
	    }
	    
	    
//	    @Test
//	    void testGetProject() throws Exception {
//	        // Create a list of ProjectDto objects with sample data
//	        List<ProjectDto> projectDtos = new ArrayList<>();
//	        ProjectDto project1 = new ProjectDto();
//	        project1.setProjectId(1L);
//	        project1.setName("Project A");
//	  
//	        Mockito.when(adminService.getAllProjects()).thenReturn(projectDtos);
//
//	        mockMvc.perform(MockMvcRequestBuilders.get("/employee/getAllProjects")
//	                .contentType(MediaType.APPLICATION_JSON))
//	                .andExpect(MockMvcResultMatchers.status().isOk())
//	                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//	                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
//	                .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(projectDtos.size()));
//
//	        
//	    }
//	    
//	    
//	    
	    


}
