package com.backend.employee.dto;

import com.backend.employee.dto.LoginOutDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class LoginOutDtoTest {

    @Test
    public void testEmpRoleGetterSetter() {
        LoginOutDto loginOutDto = new LoginOutDto();

        loginOutDto.setEmpRole("admin");

        assertEquals("admin", loginOutDto.getEmpRole());
    }

    @Test
    public void testMessageGetterSetter() {
        LoginOutDto loginOutDto = new LoginOutDto();

        loginOutDto.setMessage("Test Message");

        assertEquals("Test Message", loginOutDto.getMessage());
    }

    @Test
    public void testNoArgsConstructor() {
        LoginOutDto loginOutDto = new LoginOutDto();

        assertEquals(null, loginOutDto.getEmpRole());
        assertEquals(null, loginOutDto.getMessage());

        loginOutDto.setEmpRole("admin");
        loginOutDto.setMessage("Test Message");

        assertEquals("admin", loginOutDto.getEmpRole());
        assertEquals("Test Message", loginOutDto.getMessage());
    }

    @Test
    public void testAllArgsConstructor() {
        LoginOutDto loginOutDto = new LoginOutDto("admin", "Test Message","Ankita Sharma");

        assertEquals("admin", loginOutDto.getEmpRole());
        assertEquals("Test Message", loginOutDto.getMessage());
        assertEquals("Ankita Sharma", loginOutDto.getEmpName());
    }
    
    @Test
    public void testEmpNameGetterSetter() {
        LoginOutDto loginOutDto = new LoginOutDto();

        loginOutDto.setEmpName("Ankita Sharma");

        assertEquals("Ankita Sharma", loginOutDto.getEmpName());
    }

    @Test
    public void testHashCode() {
        LoginOutDto loginOutDto1 = new LoginOutDto("admin", "Test Message", "Ankita Sharma");
        LoginOutDto loginOutDto2 = new LoginOutDto("admin", "Test Message", "Ankita Sharma");

        assertEquals(loginOutDto1.hashCode(), loginOutDto2.hashCode());
    }

    @Test
    public void testEquals() {
        LoginOutDto loginOutDto1 = new LoginOutDto("admin", "Test Message", "Ankita Sharma");
        LoginOutDto loginOutDto2 = new LoginOutDto("admin", "Test Message", "Ankita Sharma");
        LoginOutDto loginOutDto3 = new LoginOutDto("admin", "Test1 Message", "Ankita Sharma");

        assertEquals(loginOutDto1, loginOutDto2);
        assertNotEquals(loginOutDto1, loginOutDto3);
    }

    @Test
    public void testToString() {
        LoginOutDto loginOutDto = new LoginOutDto("admin", "Test Message", "Ankita Sharma");

        assertEquals("LoginOutDto [empRole=admin, message=Test Message, empName=Ankita Sharma]", loginOutDto.toString());
    }
}
