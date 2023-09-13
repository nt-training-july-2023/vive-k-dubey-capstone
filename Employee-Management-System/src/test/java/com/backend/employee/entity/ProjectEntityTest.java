package com.backend.employee.entity;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.backend.employee.dto.ProjectDto;

public class ProjectEntityTest {

    @Test
    public void testProjectEntityConstructor() {
        // Arrange
        String projectName = "ProjectA";
        String projectDescription = "Description of ProjectA";
        String startDate = "2023-09-01";
        Long managerId = 1L;
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Spring");

        // Act
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName(projectName);
        projectDto.setDescription(projectDescription);
        projectDto.setStartDate(startDate);
        projectDto.setManagerEmployeeId(managerId);
        projectDto.setSkills(skills);

        ProjectEntity projectEntity = new ProjectEntity(projectDto);

        // Assert
        assertEquals(projectName, projectEntity.getName());
        assertEquals(projectDescription, projectEntity.getDescription());
        assertEquals(startDate, projectEntity.getStartDate());
        assertEquals(managerId, projectEntity.getManagerEmployeeId());
        assertEquals(skills, projectEntity.getSkills());
    }

    @Test
    public void testHashCodeAndEquals() {
        // Arrange
        ProjectEntity project1 = new ProjectEntity();
        project1.setProjectId(1L);
        project1.setName("ProjectA");
        project1.setDescription("Description of ProjectA");
        project1.setStartDate("2023-09-01");
        project1.setManagerEmployeeId(1L);
        List<String> skills1 = new ArrayList<>();
        skills1.add("Java");
        skills1.add("Spring");
        project1.setSkills(skills1);

        ProjectEntity project2 = new ProjectEntity();
        project2.setProjectId(2L);
        project2.setName("ProjectA");
        project2.setDescription("Description of ProjectA");
        project2.setStartDate("2023-09-01");
        project2.setManagerEmployeeId(1L);
        List<String> skills2 = new ArrayList<>();
        skills2.add("Java");
        skills2.add("Spring");
        project2.setSkills(skills2);

        ProjectEntity project3 = new ProjectEntity();
        project3.setProjectId(1L);
        project3.setName("ProjectB");
        project3.setDescription("Description of ProjectB");
        project3.setStartDate("2023-09-02");
        project3.setManagerEmployeeId(2L);
        List<String> skills3 = new ArrayList<>();
        skills3.add("Python");
        skills3.add("Django");
        project3.setSkills(skills3);

        // Act & Assert
        assertEquals(project1.hashCode(), project2.hashCode());
        assertNotEquals(project1.hashCode(), project3.hashCode());

        assertTrue(project1.equals(project2));
        assertFalse(project1.equals(project3));
    }
}
