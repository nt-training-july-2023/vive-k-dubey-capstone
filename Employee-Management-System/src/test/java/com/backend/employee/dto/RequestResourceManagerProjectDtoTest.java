package com.backend.employee.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class RequestResourceManagerProjectDtoTest {

    @Test
    public void testGetId() {
        RequestResourceManagerProjectDto dto = new RequestResourceManagerProjectDto();
        Long id = 123L;
        dto.setId(id);

        assertEquals(id, dto.getId());
    }

    @Test
    public void testGetProjectName() {
        RequestResourceManagerProjectDto dto = new RequestResourceManagerProjectDto();
        String projectName = "Sample Project";
        dto.setProjectName(projectName);

        assertEquals(projectName, dto.getProjectName());
    }
    
    private RequestResourceManagerProjectDto dto1;
    private RequestResourceManagerProjectDto dto2;

    @BeforeEach
    public void setUp() {
        dto1 = new RequestResourceManagerProjectDto();
        dto1.setId(1L);
        dto1.setProjectName("Project A");

        dto2 = new RequestResourceManagerProjectDto();
        dto2.setId(1L);
        dto2.setProjectName("Project A");
    }

    @Test
    public void testHashCode() {
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        assertEquals(dto1, dto2);
    }

    @Test
    public void testToString() {
        String expectedString = "RequestResourceManagerProjectDto [id=1, projectName=Project A]";
        assertEquals(expectedString, dto1.toString());
    }
}

