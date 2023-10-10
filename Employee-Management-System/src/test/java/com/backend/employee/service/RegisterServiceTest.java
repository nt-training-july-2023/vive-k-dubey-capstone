package com.backend.employee.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.LoginOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.validations.InputFieldChecks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {

	@InjectMocks
    private RegisterService registerService;
    
    @Mock
    private RegisterRepo registerRepo;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    
    @Mock
    private InputFieldChecks inputFieldChecks;

    @BeforeEach
    public void setUp() {
    	
    	
    	MockitoAnnotations.openMocks(this);
    	 	
    }
    @Test
    public void testAddAdmin() {
        RegisterDto validAdminDto = new RegisterDto();
        validAdminDto.setEmpId("N1234");
        validAdminDto.setEmpDOB("01/01/1990");
        validAdminDto.setEmpDOJ("01/01/2022");
        validAdminDto.setEmpEmail("ankita.sharma@nucleusteq.com"); // Valid admin email
        validAdminDto.setEmpContactNo("1234567890");
        validAdminDto.setEmpPassword("password");
        validAdminDto.setEmpName("Ankita Sharma");
        validAdminDto.setEmpDesignation("Recruiter");
        validAdminDto.setEmpLocation("Indore");

        when(registerRepo.save(any(RegisterEntity.class))).thenReturn(new RegisterEntity());

        CommonResponseDto responseDto = registerService.addAdmin(validAdminDto);
        assertEquals("Admin added successfully", responseDto.getMessage());

        RegisterDto invalidAdminDto = new RegisterDto();
        invalidAdminDto.setEmpId("N54321");
        invalidAdminDto.setEmpDOB("01/01/1990");
        invalidAdminDto.setEmpDOJ("01/01/2022");
        invalidAdminDto.setEmpEmail("different@admin.com");
        invalidAdminDto.setEmpContactNo("1234567890");
        invalidAdminDto.setEmpPassword("password");
        invalidAdminDto.setEmpName("Jane Doe");
        invalidAdminDto.setEmpDesignation("Software Engineer");
        invalidAdminDto.setEmpLocation("Indore");

        registerService.addAdmin(invalidAdminDto);
        assertNotEquals("admin", invalidAdminDto.getEmpRole());
    }
    
    @Test
    void testLogin() throws WrongInputException {
    	LoginDto input = new LoginDto();
        input.setEmpEmail("vivek@nucleusteq.com");
        input.setEmpPassword("12345678");
        RegisterEntity employeeEntity = new RegisterEntity(); 
     
        
        employeeEntity.setEmpId("N0001");
        employeeEntity.setEmpName("Vivek");
        employeeEntity.setEmpEmail("vivek@nucleusteq.com");
        employeeEntity.setEmpDOB("25/04/2001");
        employeeEntity.setEmpDOJ("25/04/2023");
        employeeEntity.setEmpLocation("Raipur");
        employeeEntity.setEmpDesignation("Engineer");
        employeeEntity.setEmpContactNo("1111111111");
        employeeEntity.setEmpPassword("12345678");
        employeeEntity.setEmpRole("admin");
        
        Optional<RegisterEntity> employee = Optional.of(employeeEntity);
        

        when(registerRepo.findByEmpEmail(input.getEmpEmail()))
                .thenReturn(employee);
        
        LoginOutDto output = registerService.authenticate(input);
        verify(registerRepo, times(1)).findByEmpEmail(input.getEmpEmail());

        LoginOutDto expectedOutput = new LoginOutDto();
        expectedOutput.setMessage("Login success!!");
        expectedOutput.setEmpRole(employeeEntity.getEmpRole());

        Objects.equals(output, expectedOutput);
    }


    
}

