package com.backend.employee.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UpdateSkillsDtoTest {

    @Test
    void testGetAndSetEmpEmail() {
        // Create an instance of UpdateSkillsDto
        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();

        // Set the employee email
        updateSkillsDto.setEmpEmail("employee@example.com");

        // Get the employee email and assert that it matches the value set
        assertEquals("employee@example.com", updateSkillsDto.getEmpEmail());
    }

    @Test
    void testGetAndSetEmpSkills() {
        // Create an instance of UpdateSkillsDto
        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();

        // Create a list of skills
        List<String> skills = List.of("Java", "Python", "JavaScript");

        // Set the employee skills
        updateSkillsDto.setEmpSkills(skills);

        // Get the employee skills and assert that they match the list set
        assertEquals(skills, updateSkillsDto.getEmpSkills());
    }

    @Test
    void testGetAndSetEmpEmailAndEmpSkills() {
        // Create an instance of UpdateSkillsDto
        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();

        // Set both employee email and skills
        updateSkillsDto.setEmpEmail("employee@example.com");
        List<String> skills = List.of("Java", "Python", "JavaScript");
        updateSkillsDto.setEmpSkills(skills);

        // Get and assert both values
        assertEquals("employee@example.com", updateSkillsDto.getEmpEmail());
        assertEquals(skills, updateSkillsDto.getEmpSkills());
    }
}
