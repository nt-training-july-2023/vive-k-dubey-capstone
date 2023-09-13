package com.backend.employee.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.LoginOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.validations.InputFieldChecks;
import com.backend.employee.validations.InputFieldsChecksUpdated;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

	@InjectMocks
    private AdminService adminService;
    
    @Mock
    private RegisterRepo registerRepo;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    
    @Mock
    private InputFieldsChecksUpdated inputFieldChecks;
    
    @Test
    void testAddEmployee()
        throws WrongInputException, DataAlreadyExistsException {
        RegisterDto input = new RegisterDto();
        input.setEmpId("N0001");
        input.setEmpName("Ashish");
        input.setEmpEmail("ashish@nucleusteq.com");
        input.setEmpDOB("25/04/2001");
        input.setEmpDOJ("25/04/2023");
        input.setEmpLocation("Raipur");
        input.setEmpDesignation("Engineer");
        input.setEmpContactNo("1111111111");
        input.setEmpPassword("12345678");
        input.setEmpRole("admin");
        input.setProjectId((long) 1);
        input.setManagerName("Ankita");
        List<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("python");
        input.setEmpSkills(skills);

        doNothing().when(inputFieldChecks).checkEmpName(anyString());
        doNothing().when(inputFieldChecks).checkEmpIdExistence(anyString());


        CommonResponseDto response = adminService.addEmployee(input);
        verify(registerRepo, times(1)).save(any(RegisterEntity.class));

        CommonResponseDto expectedResult = new CommonResponseDto("Employee added successfully");
        assertEquals(expectedResult.getMessage(), response.getMessage());
    }
    
    @Test
    void testGetAllEmployee() throws Exception{
    	RegisterEntity employee = new RegisterEntity();
        employee.setEmpId("N0001");
        employee.setEmpName("Vivek");
        employee.setEmpEmail("vivek@nucleusteq.com");
        employee.setEmpDOB("25/04/2002");
        employee.setEmpDOJ("25/04/2023");
        employee.setEmpLocation("Raipur");
        employee.setEmpDesignation("Engineer");
        employee.setEmpContactNo("1111111111");
        employee.setEmpPassword("12345678");
        employee.setEmpRole("employee");
        employee.setProjectId((long) 1);
        employee.setManagerId((long) 1);
        List<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("python");
        employee.setEmpSkills(skills);
        List<RegisterEntity> allEmployee = new ArrayList<>();
        allEmployee.add(employee);

        when(registerRepo.findAllByEmpRole("employee")).thenReturn(allEmployee);

        List<RegisterDto> responseList = adminService
                .getAllEmployees();

        verify(registerRepo, times(1)).findAllByEmpRole("employee");

        assertEquals(1, responseList.size());
        assertEquals("N0001", responseList.get(0).getEmpId());
        assertEquals("1111111111", responseList.get(0).getEmpContactNo());
        assertEquals("Vivek", responseList.get(0).getEmpName());
        assertEquals("vivek@nucleusteq.com", responseList.get(0).getEmpEmail());
        assertEquals("25/04/2002", responseList.get(0).getEmpDOB());
        assertEquals("25/04/2023", responseList.get(0).getEmpDOJ());
        assertEquals("Raipur", responseList.get(0).getEmpLocation());
        assertEquals("Engineer", responseList.get(0).getEmpDesignation()); 
   }
    
    
    
    
}
