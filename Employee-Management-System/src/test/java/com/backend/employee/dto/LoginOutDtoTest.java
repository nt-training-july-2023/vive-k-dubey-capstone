package com.backend.employee.dto;

import com.backend.employee.dto.LoginOutDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginOutDtoTest {

    @Test
    public void testEmpRoleGetterSetter() {
        // Create a LoginOutDto object
        LoginOutDto loginOutDto = new LoginOutDto();

        // Set an employee role using the setter
        loginOutDto.setEmpRole("admin");

        // Get the employee role using the getter and assert its correctness
        assertEquals("admin", loginOutDto.getEmpRole());
    }

    @Test
    public void testMessageGetterSetter() {
        // Create a LoginOutDto object
        LoginOutDto loginOutDto = new LoginOutDto();

        // Set a message using the setter
        loginOutDto.setMessage("Test Message");

        // Get the message using the getter and assert its correctness
        assertEquals("Test Message", loginOutDto.getMessage());
    }

    @Test
    public void testNoArgsConstructor() {
        // Create a LoginOutDto object using the no-args constructor
        LoginOutDto loginOutDto = new LoginOutDto();

        // Assert that the empRole and message are initially null
        assertEquals(null, loginOutDto.getEmpRole());
        assertEquals(null, loginOutDto.getMessage());

        // Set values using the setters
        loginOutDto.setEmpRole("admin");
        loginOutDto.setMessage("Test Message");

        // Get the values using the getters and assert their correctness
        assertEquals("admin", loginOutDto.getEmpRole());
        assertEquals("Test Message", loginOutDto.getMessage());
    }

    @Test
    public void testAllArgsConstructor() {
        // Create a LoginOutDto object using the all-args constructor
        LoginOutDto loginOutDto = new LoginOutDto("admin", "Test Message");

        // Get the values using the getters and assert their correctness
        assertEquals("admin", loginOutDto.getEmpRole());
        assertEquals("Test Message", loginOutDto.getMessage());
    }
}
