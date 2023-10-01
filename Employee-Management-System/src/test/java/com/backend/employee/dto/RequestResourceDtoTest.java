package com.backend.employee.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestResourceDtoTest {

    @Test
    public void testGetEmpId() {
        RequestResourceDto dto = new RequestResourceDto();
        String empId = "123";
        dto.setEmpId(empId);

        assertEquals(empId, dto.getEmpId());
    }

    @Test
    public void testGetManagerEmail() {
        RequestResourceDto dto = new RequestResourceDto();
        String managerEmail = "manager@example.com";
        dto.setManagerEmail(managerEmail);

        assertEquals(managerEmail, dto.getManagerEmail());
    }

    @Test
    public void testGetProjectId() {
        RequestResourceDto dto = new RequestResourceDto();
        Long projectId = 456L;
        dto.setProjectId(projectId);

        assertEquals(projectId, dto.getProjectId());
    }

    @Test
    public void testGetComment() {
        RequestResourceDto dto = new RequestResourceDto();
        String comment = "Requesting resource for a project.";
        dto.setComment(comment);

        assertEquals(comment, dto.getComment());
    }
    
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testAnnotations() {
        RequestResourceDto dto = new RequestResourceDto();
        dto.setEmpId("");
        dto.setManagerEmail("");
        dto.setProjectId(null);
        dto.setComment("");

        Set<ConstraintViolation<RequestResourceDto>> violations = validator.validate(dto);

        // Ensure that all annotated fields are marked as invalid
        assertEquals(4, violations.size());

        for (ConstraintViolation<RequestResourceDto> violation : violations) {
            assertTrue(
                violation.getPropertyPath().toString().equals("empId") ||
                violation.getPropertyPath().toString().equals("managerEmail") ||
                violation.getPropertyPath().toString().equals("projectId") ||
                violation.getPropertyPath().toString().equals("comment")
            );
        }
    }

    @Test
    public void testHashCode() {
        RequestResourceDto dto1 = new RequestResourceDto();
        dto1.setEmpId("EMP001");
        dto1.setManagerEmail("manager@example.com");
        dto1.setProjectId(1L);
        dto1.setComment("Request Comment");

        RequestResourceDto dto2 = new RequestResourceDto();
        dto2.setEmpId("EMP001");
        dto2.setManagerEmail("manager@example.com");
        dto2.setProjectId(1L);
        dto2.setComment("Request Comment");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        RequestResourceDto dto1 = new RequestResourceDto();
        dto1.setEmpId("EMP001");
        dto1.setManagerEmail("manager@example.com");
        dto1.setProjectId(1L);
        dto1.setComment("Request Comment");

        RequestResourceDto dto2 = new RequestResourceDto();
        dto2.setEmpId("EMP001");
        dto2.setManagerEmail("manager@example.com");
        dto2.setProjectId(1L);
        dto2.setComment("Request Comment");

        RequestResourceDto dto3 = new RequestResourceDto();
        dto3.setEmpId("EMP002");
        dto3.setManagerEmail("manager@example.com");
        dto3.setProjectId(2L);
        dto3.setComment("Different Comment");

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
    }

    @Test
    public void testToString() {
        RequestResourceDto dto = new RequestResourceDto();
        dto.setEmpId("EMP001");
        dto.setManagerEmail("manager@example.com");
        dto.setProjectId(1L);
        dto.setComment("Request Comment");

        String expectedString = "RequestResourceDto [empId=EMP001, managerEmail=manager@example.com, projectId=1, comment=Request Comment]";
        assertEquals(expectedString, dto.toString());
    }
}

