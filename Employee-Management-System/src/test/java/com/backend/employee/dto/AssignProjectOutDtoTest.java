package com.backend.employee.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssignProjectOutDtoTest {

    @Test
    void testGetAndSetProjectId() {

        AssignProjectOutDto assignProjectOutDto = new AssignProjectOutDto();

        assignProjectOutDto.setProjectId(1L);

        assertEquals(1L, assignProjectOutDto.getProjectId());
    }

    @Test
    void testGetAndSetName() {

        AssignProjectOutDto assignProjectOutDto = new AssignProjectOutDto();

        assignProjectOutDto.setName("Project 1");

        assertEquals("Project 1", assignProjectOutDto.getName());
    }

    @Test
    void testHashCode() {
        AssignProjectOutDto dto1 = new AssignProjectOutDto();
        dto1.setProjectId(1L);
        dto1.setName("Project 1");

        AssignProjectOutDto dto2 = new AssignProjectOutDto();
        dto2.setProjectId(1L);
        dto2.setName("Project 1");
        
        AssignProjectOutDto dto3 = new AssignProjectOutDto();
        dto3.setProjectId(2L);
        dto3.setName("Project 1");
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    void testEquals() {
        AssignProjectOutDto dto1 = new AssignProjectOutDto();
        dto1.setProjectId(1L);
        dto1.setName("Project 1");

        AssignProjectOutDto dto2 = new AssignProjectOutDto();
        dto2.setProjectId(1L);
        dto2.setName("Project 1");
        
        AssignProjectOutDto dto3 = new AssignProjectOutDto();
        dto3.setProjectId(2L);
        dto3.setName("Project 1");

        assertTrue(dto1.equals(dto2));
        assertFalse(dto1.equals(dto3));
    }

    @Test
    void testToString() {
        AssignProjectOutDto assignProjectOutDto = new AssignProjectOutDto();
        assignProjectOutDto.setProjectId(1L);
        assignProjectOutDto.setName("Project 1");

        assertEquals("AssignProjectOutDto [projectId=1, name=Project 1]", assignProjectOutDto.toString());
    }
}
