package com.backend.employee.dto;

import com.backend.employee.dto.ProjectDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectDtoTest {

    @Test
    public void testGetterSetter() {
        // Create a ProjectDto object
        ProjectDto projectDto = new ProjectDto();

        // Set values using the setter methods with 
        projectDto.setProjectId(1L);
        projectDto.setName("ProjectA");
        projectDto.setDescription("Description of ProjectA");
        projectDto.setStartDate("2023-08-31");
        projectDto.setManagerEmployeeId(2L);

        List<String> skills = Arrays.asList("Java", "Spring", "Hibernate");
        projectDto.setSkills(skills);
        projectDto.setHead("Rajesh Kumar");

        // Get values using the getter methods and assert their correctness
        assertEquals(1L, projectDto.getProjectId());
        assertEquals("ProjectA", projectDto.getName());
        assertEquals("Description of ProjectA", projectDto.getDescription());
        assertEquals("2023-08-31", projectDto.getStartDate());
        assertEquals(2L, projectDto.getManagerEmployeeId());
        assertEquals(skills, projectDto.getSkills());
        assertEquals("Rajesh Kumar", projectDto.getHead());
    }
}

