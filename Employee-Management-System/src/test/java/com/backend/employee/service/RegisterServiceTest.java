package com.backend.employee.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
    	registerService = new RegisterService();
    	registerService.setRegisterRepo(registerRepo);
    	registerService.setInputFieldChecks(inputFieldChecks);
    	registerService.setPasswordEncoder(passwordEncoder);
    	 	
    }
    
    @Test
    void testAddAdmin()  {
        RegisterDto input = new RegisterDto();
        
        input.setEmpName("Vivek");
        input.setEmpId("N0001");
        input.setEmpEmail("Vivek@nucleusteq.com");
        input.setEmpDOB("01-06-2000");
        input.setEmpDOJ("18/07/2022");
        input.setEmpLocation("Raipur");
        input.setEmpDesignation("Engineer");
        input.setEmpContactNo("1111111111");
        input.setEmpPassword("12345678");
        input.setEmpRole("admin");

        
        when(passwordEncoder.encode(input.getEmpPassword()))
                .thenReturn("encryptedPassword");
        

        ResponseEntity<String> output = registerService.addAdmin(input);
//        verify(registerRepo, times(1)).save(any(RegisterEntity.class));

        assertEquals("Invalid date of birth", output.getBody()); 
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
        
        //doNothing().when(inputFieldChecks).loginValidation(input.getEmpEmail(), input.getEmpPassword());

        when(registerRepo.findByEmpEmail(input.getEmpEmail()))
                .thenReturn(employee);
        
        LoginOutDto output = registerService.authenticate(input);
        
        //verify(inputFieldChecks, times(1)).loginValidation(input.getEmpEmail(), input.getEmpPassword());
        verify(registerRepo, times(1)).findByEmpEmail(input.getEmpEmail());

        LoginOutDto expectedOutput = new LoginOutDto();
        expectedOutput.setMessage("Login success!!");
        expectedOutput.setEmpRole(employeeEntity.getEmpRole());

        Objects.equals(output, expectedOutput);
    }


    
}

