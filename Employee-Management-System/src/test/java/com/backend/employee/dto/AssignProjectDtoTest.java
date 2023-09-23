package com.backend.employee.dto;

import org.junit.jupiter.api.Test;
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
}
