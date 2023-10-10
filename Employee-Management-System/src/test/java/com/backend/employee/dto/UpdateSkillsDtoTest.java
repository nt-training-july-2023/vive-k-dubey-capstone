package com.backend.employee.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UpdateSkillsDtoTest {

    @Test
    void testGetAndSetEmpEmail() {
        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();

        updateSkillsDto.setEmpEmail("employee@example.com");

        assertEquals("employee@example.com", updateSkillsDto.getEmpEmail());
    }

    @Test
    void testGetAndSetEmpSkills() {
        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();

        List<String> skills = List.of("Java", "Python", "JavaScript");

        updateSkillsDto.setEmpSkills(skills);

        assertEquals(skills, updateSkillsDto.getEmpSkills());
    }

    @Test
    void testGetAndSetEmpEmailAndEmpSkills() {
        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();

        updateSkillsDto.setEmpEmail("employee@example.com");
        List<String> skills = List.of("Java", "Python", "JavaScript");
        updateSkillsDto.setEmpSkills(skills);

        assertEquals("employee@example.com", updateSkillsDto.getEmpEmail());
        assertEquals(skills, updateSkillsDto.getEmpSkills());
    }
    
    private ResourceRequestsAdminOutDto dto1;
    private ResourceRequestsAdminOutDto dto2;

    @BeforeEach
    public void setUp() {
        dto1 = new ResourceRequestsAdminOutDto();
        dto1.setId(1L);
        dto1.setEmployeeId(100L);
        dto1.setEmployeeName("Vivek Dubey");
        dto1.setManagerName("Manager A");
        dto1.setProjectName("Project X");
        dto1.setComment("Request Comment");
        dto1.setManagerId(200L);
        dto1.setProjectId(300L);

        dto2 = new ResourceRequestsAdminOutDto();
        dto2.setId(1L);
        dto2.setEmployeeId(100L);
        dto2.setEmployeeName("Vivek Dubey");
        dto2.setManagerName("Manager A");
        dto2.setProjectName("Project X");
        dto2.setComment("Request Comment");
        dto2.setManagerId(200L);
        dto2.setProjectId(300L);
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
        String expectedString = "ResourceRequestsAdminOutDto [id=1, employeeId=100, employeeName=Vivek Dubey, managerName=Manager A, projectName=Project X, comment=Request Comment, managerId=200, projectId=300]";
        assertEquals(expectedString, dto1.toString());
    }
}
