package com.backend.employee.dto;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RequestedDtoTest {

    @Test
    public void testGetEmpId() {
        RequestedDto dto = new RequestedDto();
        String empId = "EMP123";
        dto.setEmpId(empId);

        assertEquals(empId, dto.getEmpId());
    }

    @Test
    public void testGetManagerEmail() {
        RequestedDto dto = new RequestedDto();
        String managerEmail = "manager@example.com";
        dto.setManagerEmail(managerEmail);

        assertEquals(managerEmail, dto.getManagerEmail());
    }
    
    @Test
    public void testAnnotations() {
        RequestedDto dto = new RequestedDto();
        dto.setEmpId("");
        dto.setManagerEmail("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RequestedDto>> violations = validator.validate(dto);

        assertEquals(2, violations.size());

        for (ConstraintViolation<RequestedDto> violation : violations) {
            assertTrue(
                violation.getPropertyPath().toString().equals("empId") ||
                violation.getPropertyPath().toString().equals("managerEmail")
            );
        }
    }

    @Test
    public void testHashCode() {
        RequestedDto dto1 = new RequestedDto();
        dto1.setEmpId("N001");
        dto1.setManagerEmail("manager@example.com");

        RequestedDto dto2 = new RequestedDto();
        dto2.setEmpId("N001");
        dto2.setManagerEmail("manager@example.com");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        RequestedDto dto1 = new RequestedDto();
        dto1.setEmpId("N001");
        dto1.setManagerEmail("manager@example.com");

        RequestedDto dto2 = new RequestedDto();
        dto2.setEmpId("N001");
        dto2.setManagerEmail("manager@example.com");

        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void testToString() {
        RequestedDto dto = new RequestedDto();
        dto.setEmpId("N001");
        dto.setManagerEmail("manager@example.com");

        String expectedString = "RequestedDto [empId=N001, managerEmail=manager@example.com]";
        assertEquals(expectedString, dto.toString());
    }
}

