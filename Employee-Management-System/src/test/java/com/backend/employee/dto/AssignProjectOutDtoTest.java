package com.backend.employee.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssignProjectOutDtoTest {

    @Test
    void testGetAndSetProjectId() {
        // Create an instance of AssignProjectOutDto
        AssignProjectOutDto assignProjectOutDto = new AssignProjectOutDto();

        // Set the project ID
        assignProjectOutDto.setProjectId(1L);

        // Get the project ID and assert that it matches the value set
        assertEquals(1L, assignProjectOutDto.getProjectId());
    }

    @Test
    void testGetAndSetName() {
        // Create an instance of AssignProjectOutDto
        AssignProjectOutDto assignProjectOutDto = new AssignProjectOutDto();

        // Set the project name
        assignProjectOutDto.setName("Project 1");

        // Get the project name and assert that it matches the value set
        assertEquals("Project 1", assignProjectOutDto.getName());
    }

    @Test
    void testHashCode() {
        // Create two instances with the same values
        AssignProjectOutDto dto1 = new AssignProjectOutDto();
        dto1.setProjectId(1L);
        dto1.setName("Project 1");

        AssignProjectOutDto dto2 = new AssignProjectOutDto();
        dto2.setProjectId(1L);
        dto2.setName("Project 1");

        // Hash codes should be equal for objects with the same values
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testEquals() {
        // Create two instances with the same values
        AssignProjectOutDto dto1 = new AssignProjectOutDto();
        dto1.setProjectId(1L);
        dto1.setName("Project 1");

        AssignProjectOutDto dto2 = new AssignProjectOutDto();
        dto2.setProjectId(1L);
        dto2.setName("Project 1");

        // Objects should be equal if they have the same values
        assertTrue(dto1.equals(dto2));
    }

    @Test
    void testToString() {
        // Create an instance of AssignProjectOutDto
        AssignProjectOutDto assignProjectOutDto = new AssignProjectOutDto();
        assignProjectOutDto.setProjectId(1L);
        assignProjectOutDto.setName("Project 1");

        // Check the toString representation
        assertEquals("AssignProjectOutDto [projectId=1, name=Project 1]", assignProjectOutDto.toString());
    }
}
