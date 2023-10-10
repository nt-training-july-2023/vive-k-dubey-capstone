package com.backend.employee.dto;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

public class AssignProjectDtoTest {

    @Test
    void testGetAndSetProjectId() {
     
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setProjectId(1L);
        assertEquals(1L, assignProjectDto.getProjectId());
        
    }

    @Test
    void testGetAndSetEmpId() {

        AssignProjectDto assignProjectDto = new AssignProjectDto();

        assignProjectDto.setEmpId("EMP001");

        assertEquals("EMP001", assignProjectDto.getEmpId());
    }

    @Test
    void testGetAndSetBothProjectIdAndEmpId() {

        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setProjectId(2L);
        assignProjectDto.setEmpId("EMP002");

        assertEquals(2L, assignProjectDto.getProjectId());
        assertEquals("EMP002", assignProjectDto.getEmpId());
    }
    
    @Test
    void testNotNullValidation() {
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("EMP001");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        var violations = validator.validate(assignProjectDto);


        assertEquals(1, violations.size());
        var violation = violations.iterator().next();
        assertEquals("ProjectId cannot be blank", violation.getMessage());
    }

    @Test
    void testNotBlankValidation() {
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setProjectId(1L);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        var violations = validator.validate(assignProjectDto);

        assertEquals(1, violations.size());
        var violation = violations.iterator().next();
        assertEquals("Employee Id cannot be blank", violation.getMessage());
    }

    @Test
    void testHashCodeAndEquals() {
        AssignProjectDto dto1 = new AssignProjectDto();
        dto1.setProjectId(1L);
        dto1.setEmpId("EMP001");

        AssignProjectDto dto2 = new AssignProjectDto();
        dto2.setProjectId(1L);
        dto2.setEmpId("EMP001");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());

        AssignProjectDto dto3 = new AssignProjectDto();
        dto3.setProjectId(2L);
        dto3.setEmpId("EMP002");

        assertNotEquals(dto1, dto3);
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    void testToString() {

        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setProjectId(1L);
        assignProjectDto.setEmpId("EMP001");


        String expectedToString = "AssignProjectDto [projectId=1, empId=EMP001]";
        assertEquals(expectedToString, assignProjectDto.toString());
    }
}
