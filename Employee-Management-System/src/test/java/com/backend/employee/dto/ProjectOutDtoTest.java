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
    
    @Test
    public void testHashCode() {
        ProjectOutDto projectDto1 = new ProjectOutDto();
        projectDto1.setId(1L);
        projectDto1.setProjectName("Project A");
        projectDto1.setDescription("Description A");
        projectDto1.setStartDate("2023-01-01");
        projectDto1.setManagerId("101");
        projectDto1.setSkillsRequired(List.of("Java"));
        projectDto1.setTeamMembers(List.of("Vivek", "Abhay"));

        ProjectOutDto projectDto2 = new ProjectOutDto();
        projectDto2.setId(1L);
        projectDto2.setProjectName("Project A");
        projectDto2.setDescription("Description A");
        projectDto2.setStartDate("2023-01-01");
        projectDto2.setManagerId("101");
        projectDto2.setSkillsRequired(List.of("Java"));
        projectDto2.setTeamMembers(List.of("Vivek", "Abhay"));

        assertEquals(projectDto1.hashCode(), projectDto2.hashCode());
    }

    @Test
    public void testEquals() {
        ProjectOutDto projectDto1 = new ProjectOutDto();
        projectDto1.setId(1L);
        projectDto1.setProjectName("Project A");
        projectDto1.setDescription("Description A");
        projectDto1.setStartDate("2023-01-01");
        projectDto1.setManagerId("101");
        projectDto1.setSkillsRequired(List.of("Java"));
        projectDto1.setTeamMembers(List.of("Vivek", "Abhay"));

        ProjectOutDto projectDto2 = new ProjectOutDto();
        projectDto2.setId(1L);
        projectDto2.setProjectName("Project A");
        projectDto2.setDescription("Description A");
        projectDto2.setStartDate("2023-01-01");
        projectDto2.setManagerId("101");
        projectDto2.setSkillsRequired(List.of("Java"));
        projectDto2.setTeamMembers(List.of("Vivek", "Abhay"));

        ProjectOutDto projectDto3 = new ProjectOutDto();
        projectDto3.setId(2L);
        projectDto3.setProjectName("Project B");
        projectDto3.setDescription("Description B");
        projectDto3.setStartDate("2023-01-02");
        projectDto3.setManagerId("102");
        projectDto3.setSkillsRequired(List.of("Python"));
        projectDto3.setTeamMembers(List.of("Alice", "Bob"));

        assertTrue(projectDto1.equals(projectDto2));
        assertFalse(projectDto1.equals(projectDto3));
    }

    @Test
    public void testToString() {
        ProjectOutDto projectDto = new ProjectOutDto();
        projectDto.setId(1L);
        projectDto.setProjectName("Project A");
        projectDto.setDescription("Description A");
        projectDto.setStartDate("2023-01-01");
        projectDto.setManagerId("101");
        projectDto.setSkillsRequired(List.of("Java"));
        projectDto.setTeamMembers(List.of("Vivek", "Abhay"));

        String expectedString = "ProjectOutDto [id=1, projectName=Project A, managerId=101, startDate=2023-01-01, skillsRequired=[Java], description=Description A]";
        assertEquals(expectedString, projectDto.toString());
    }
}

