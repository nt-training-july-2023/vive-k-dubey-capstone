package com.backend.employee.dto;

import com.backend.employee.dto.ProjectOutDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectOutDtoTest {

    @Test
    public void testGetterSetter() {
        // Create a ProjectOutDto object
        ProjectOutDto projectOutDto = new ProjectOutDto();

        // Set values using the setter methods
        projectOutDto.setId(1L);
        projectOutDto.setProjectName("ProjectA");
        projectOutDto.setManagerId("Manager1");
        projectOutDto.setStartDate("2023-08-31");

        List<String> skillsRequired = Arrays.asList("Java", "Spring", "Hibernate");
        projectOutDto.setSkillsRequired(skillsRequired);
        projectOutDto.setDescription("Description of ProjectA");

        // Get values using the getter methods and assert their correctness
        assertEquals(1L, projectOutDto.getId());
        assertEquals("ProjectA", projectOutDto.getProjectName());
        assertEquals("Manager1", projectOutDto.getManagerId());
        assertEquals("2023-08-31", projectOutDto.getStartDate());
        assertEquals(skillsRequired, projectOutDto.getSkillsRequired());
        assertEquals("Description of ProjectA", projectOutDto.getDescription());
    }
}

