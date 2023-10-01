package com.backend.employee.dto;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

public class AssignProjectDtoTest {

    @Test
    void testGetAndSetProjectId() {
        // Create an instance of AssignProjectDto
        AssignProjectDto assignProjectDto = new AssignProjectDto();

        // Set the project ID
        assignProjectDto.setProjectId(1L);

        // Get the project ID and assert that it matches the value set
        assertEquals(1L, assignProjectDto.getProjectId());
    }

    @Test
    void testGetAndSetEmpId() {
        // Create an instance of AssignProjectDto
        AssignProjectDto assignProjectDto = new AssignProjectDto();

        // Set the employee ID
        assignProjectDto.setEmpId("EMP001");

        // Get the employee ID and assert that it matches the value set
        assertEquals("EMP001", assignProjectDto.getEmpId());
    }

    @Test
    void testGetAndSetBothProjectIdAndEmpId() {
        // Create an instance of AssignProjectDto
        AssignProjectDto assignProjectDto = new AssignProjectDto();

        // Set both project ID and employee ID
        assignProjectDto.setProjectId(2L);
        assignProjectDto.setEmpId("EMP002");

        // Get and assert both values
        assertEquals(2L, assignProjectDto.getProjectId());
        assertEquals("EMP002", assignProjectDto.getEmpId());
    }
    
    @Test
    void testNotNullValidation() {
        // Create an instance of AssignProjectDto with null project ID
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("EMP001");

        // Create a validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Validate the DTO
        var violations = validator.validate(assignProjectDto);

        // There should be one violation for project ID
        assertEquals(1, violations.size());
        var violation = violations.iterator().next();
        assertEquals("ProjectId cannot be blank", violation.getMessage());
    }

    @Test
    void testNotBlankValidation() {
        // Create an instance of AssignProjectDto with null employee ID
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setProjectId(1L);

        // Create a validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Validate the DTO
        var violations = validator.validate(assignProjectDto);

        // There should be one violation for employee ID
        assertEquals(1, violations.size());
        var violation = violations.iterator().next();
        assertEquals("Employee Id cannot be blank", violation.getMessage());
    }

    @Test
    void testHashCodeAndEquals() {
        // Create two instances of AssignProjectDto with the same values
        AssignProjectDto dto1 = new AssignProjectDto();
        dto1.setProjectId(1L);
        dto1.setEmpId("EMP001");

        AssignProjectDto dto2 = new AssignProjectDto();
        dto2.setProjectId(1L);
        dto2.setEmpId("EMP001");

        // They should be equal
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());

        // Create an instance with different values
        AssignProjectDto dto3 = new AssignProjectDto();
        dto3.setProjectId(2L);
        dto3.setEmpId("EMP002");

        // They should not be equal
        assertNotEquals(dto1, dto3);
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    void testToString() {
        // Create an instance of AssignProjectDto
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setProjectId(1L);
        assignProjectDto.setEmpId("EMP001");

        // Check the toString method
        String expectedToString = "AssignProjectDto [projectId=1, empId=EMP001]";
        assertEquals(expectedToString, assignProjectDto.toString());
    }
}
