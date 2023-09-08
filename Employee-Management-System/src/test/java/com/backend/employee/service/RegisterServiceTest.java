package com.backend.employee.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

import com.backend.employee.dto.RegisterDto;
import com.backend.employee.dto.LoginDto;
import com.backend.employee.service.RegisterService;

public class RegisterServiceTest {

    private RegisterService registerService;

    @BeforeEach
    public void setup() {
        registerService = mock(RegisterService.class);
    }

    @Test
    public void testAddAdmin_Success() {
        // Create a sample RegisterDto
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmpId("E123");
        registerDto.setEmpName("Vivek Dubey");
        registerDto.setEmpEmail("vivek@example.com");
        registerDto.setEmpDOB("1990-01-01");
        registerDto.setEmpDOJ("2021-01-01");
        registerDto.setEmpLocation("Gorakhpur");
        registerDto.setEmpDesignation("Software Engineer");
        registerDto.setEmpContactNo("1234567890");
        registerDto.setEmpPassword("password");
        registerDto.setEmpRole("admin"); 
        

        // Configure mock behavior
        when(registerService.addAdmin(eq(registerDto)))
            .thenReturn(ResponseEntity.ok("Admin added successfully"));

       
        ResponseEntity<String> response = registerService.addAdmin(registerDto);

        // Verify the result
        assertNotNull(response);
        assertEquals("Admin added successfully", response.getBody());
    }

    @Test
    public void testAddAdmin_Failure() {
        // Create a sample RegisterDto with missing required fields
        RegisterDto registerDto = new RegisterDto();

        // Configure mock behavior
        when(registerService.addAdmin(eq(registerDto)))
            .thenReturn(ResponseEntity.badRequest().body("Invalid data"));

        // Call the service method
        ResponseEntity<String> response = registerService.addAdmin(registerDto);

        // Verify the result
        assertNotNull(response);
        assertEquals("Invalid data", response.getBody());
    }

    @Test
    public void testAuthenticate_Success() {
        // Create a sample LoginDto
        LoginDto loginDto = new LoginDto();
        loginDto.setEmpEmail("example@nucleusteq.com");
        loginDto.setEmpPassword("password");

        // Configure mock behavior
        when(registerService.authenticate(eq(loginDto)))
            .thenReturn(ResponseEntity.ok("Authentication successful"));

        // Call the service method
        ResponseEntity<String> response = registerService.authenticate(loginDto);

        // Verify the result
        assertNotNull(response);
        assertEquals("Authentication successful", response.getBody());
    }

    @Test
    public void testAuthenticate_Failure() {
        // Create a sample LoginDto with incorrect credentials
        LoginDto loginDto = new LoginDto();
        loginDto.setEmpEmail("example@nucleusteq.com");
        loginDto.setEmpPassword("wrongpassword");

        // Configure mock behavior
        when(registerService.authenticate(eq(loginDto)))
            .thenReturn(ResponseEntity.status(401).body("Authentication failed"));

        // Call the service method
        ResponseEntity<String> response = registerService.authenticate(loginDto);

        // Verify the result
        assertNotNull(response);
        assertEquals("Authentication failed", response.getBody());
    }
}
