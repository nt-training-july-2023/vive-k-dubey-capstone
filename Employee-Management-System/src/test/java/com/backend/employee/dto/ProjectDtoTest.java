package com.backend.employee.dto;

import com.backend.employee.dto.ProjectDto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectDtoTest {

    @Test
    public void testGetterSetter() {
        ProjectDto projectDto = new ProjectDto();

        projectDto.setProjectId(1L);
        projectDto.setName("ProjectA");
        projectDto.setDescription("Description of ProjectA");
        projectDto.setStartDate("2023-08-31");
        projectDto.setManagerEmployeeId(2L);

        List<String> skills = Arrays.asList("Java", "Spring", "Hibernate");
        projectDto.setSkills(skills);
        projectDto.setHead("Rajesh Kumar");

        assertEquals(1L, projectDto.getProjectId());
        assertEquals("ProjectA", projectDto.getName());
        assertEquals("Description of ProjectA", projectDto.getDescription());
        assertEquals("2023-08-31", projectDto.getStartDate());
        assertEquals(2L, projectDto.getManagerEmployeeId());
        assertEquals(skills, projectDto.getSkills());
        assertEquals("Rajesh Kumar", projectDto.getHead());
    }
    
    @Test
    public void testHashCode() {
        ProjectDto projectDto1 = new ProjectDto();
        projectDto1.setProjectId(1L);
        projectDto1.setName("Project A");
        projectDto1.setDescription("Description A");
        projectDto1.setStartDate("2023-01-01");
        projectDto1.setManagerEmployeeId(101L);
        projectDto1.setSkills(List.of("Java"));
        projectDto1.setHead("Vivek Dubey");

        ProjectDto projectDto2 = new ProjectDto();
        projectDto2.setProjectId(1L);
        projectDto2.setName("Project A");
        projectDto2.setDescription("Description A");
        projectDto2.setStartDate("2023-01-01");
        projectDto2.setManagerEmployeeId(101L);
        projectDto2.setSkills(List.of("Java"));
        projectDto2.setHead("Vivek Dubey");

        assertEquals(projectDto1.hashCode(), projectDto2.hashCode());
    }

    @Test
    public void testEquals() {
        ProjectDto projectDto1 = new ProjectDto();
        projectDto1.setProjectId(1L);
        projectDto1.setName("Project A");
        projectDto1.setDescription("Description A");
        projectDto1.setStartDate("2023-01-01");
        projectDto1.setManagerEmployeeId(101L);
        projectDto1.setSkills(List.of("Java"));
        projectDto1.setHead("Vivek Dubey");

        ProjectDto projectDto2 = new ProjectDto();
        projectDto2.setProjectId(1L);
        projectDto2.setName("Project A");
        projectDto2.setDescription("Description A");
        projectDto2.setStartDate("2023-01-01");
        projectDto2.setManagerEmployeeId(101L);
        projectDto2.setSkills(List.of("Java"));
        projectDto2.setHead("Vivek Dubey");

        ProjectDto projectDto3 = new ProjectDto();
        projectDto3.setProjectId(2L);
        projectDto3.setName("Project B");
        projectDto3.setDescription("Description B");
        projectDto3.setStartDate("2023-01-02");
        projectDto3.setManagerEmployeeId(102L);
        projectDto3.setSkills(List.of("Python"));
        projectDto3.setHead("Ashish");

        assertTrue(projectDto1.equals(projectDto2));
        assertFalse(projectDto1.equals(projectDto3));
    }

    @Test
    public void testToString() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(1L);
        projectDto.setName("Project A");
        projectDto.setDescription("Description A");
        projectDto.setStartDate("2023-01-01");
        projectDto.setManagerEmployeeId(101L);
        projectDto.setSkills(List.of("Java"));
        projectDto.setHead("Vivek Dubey");

        String expectedString = "ProjectDto [projectId=1, name=Project A, description=Description A, startDate=2023-01-01, managerEmployeeId=101, skills=[Java], head=Vivek Dubey]";
        assertEquals(expectedString, projectDto.toString());
    }
    
    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testNameNotBlank() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("");
        projectDto.setStartDate("15/12/2023");
        projectDto.setDescription("Description");
        List<String> skills = new ArrayList<>();
        skills.add("Skill1");
        skills.add("Skill2");
        projectDto.setSkills(skills); 
        projectDto.setManagerEmployeeId(2L);

        Set<ConstraintViolation<ProjectDto>> violations = validator.validate(projectDto);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());

        
    }

    @Test
    public void testDescriptionNotBlank() {
     ProjectDto projectDto = new ProjectDto();
     projectDto.setName("Fyndr");
     projectDto.setStartDate("15/12/2023");
     projectDto.setDescription("");
     List<String> skills = new ArrayList<>();
     skills.add("Skill1");
     skills.add("Skill2");
     projectDto.setSkills(skills); 
     projectDto.setManagerEmployeeId(2L);

     Set<ConstraintViolation<ProjectDto>> violations = validator.validate(projectDto);

     assertFalse(violations.isEmpty());
     assertEquals(1, violations.size());

        
    }

    @Test
    public void testStartDateNotBlank() {
     ProjectDto projectDto = new ProjectDto();
     projectDto.setName("Fyndr");
     projectDto.setStartDate("");
     projectDto.setDescription("Description");
     List<String> skills = new ArrayList<>();
     skills.add("Skill1");
     skills.add("Skill2");
     projectDto.setSkills(skills); 
     projectDto.setManagerEmployeeId(2L);

     Set<ConstraintViolation<ProjectDto>> violations = validator.validate(projectDto);

     assertFalse(violations.isEmpty());
     assertEquals(1, violations.size());
        
    }

    
    @Test
    public void testManagerEmployeeIdNotNull() {
     ProjectDto projectDto = new ProjectDto();
     projectDto.setName("Fyndr");
     projectDto.setStartDate("15/12/2023");
     projectDto.setDescription("Description");
     List<String> skills = new ArrayList<>();
     skills.add("Skill1");
     skills.add("Skill2");
     projectDto.setSkills(skills); 
     projectDto.setManagerEmployeeId(null);

     Set<ConstraintViolation<ProjectDto>> violations = validator.validate(projectDto);

     assertFalse(violations.isEmpty());
     assertEquals(1, violations.size());
        
    }
    
    @Test
    public void testAnnotationValidation() {
        ProjectDto projectDto = new ProjectDto();
       
        projectDto.setName(""); 
        projectDto.setDescription(""); 
        projectDto.setStartDate(""); 
        projectDto.setManagerEmployeeId(null);
        List<String> skills = new ArrayList<>();
        projectDto.setSkills(skills);
        Set<ConstraintViolation<ProjectDto>> violations = validator.validate(projectDto);

        assertEquals(5, violations.size());
        assertFalse(violations.isEmpty());

        
        for (ConstraintViolation<ProjectDto> violation : violations) {
            System.out.println("Property: " + violation.getPropertyPath() + ", Message: " + violation.getMessage());
        }
    }
    
    
}

