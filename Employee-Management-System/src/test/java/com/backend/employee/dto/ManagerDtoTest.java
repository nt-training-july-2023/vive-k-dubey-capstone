package com.backend.employee.dto;

import com.backend.employee.dto.ManagerDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerDtoTest {

    @Test
    public void testGetterSetter() {
        // Create a ManagerDto object
        ManagerDto managerDto = new ManagerDto();

        // Set values using the setter methods
        managerDto.setId(1L);
        managerDto.setEmpName("Vivek Dubey");
        managerDto.setEmpDesignation("Manager");
        managerDto.setEmpContactNo("1234567890");
        managerDto.setEmpEmail("vivek@example.com");
        managerDto.setEmpLocation("New Delhi");
        managerDto.setEmpId("EMP001");
        managerDto.setEmpSkills(Arrays.asList("Skill1", "Skill2"));
        managerDto.setManagerName("Manager Name");
        managerDto.setProjectNames(Arrays.asList("Project1", "Project2"));

        // Get values using the getter methods and assert their correctness
        assertEquals(1L, managerDto.getId());
        assertEquals("Vivek Dubey", managerDto.getEmpName());
        assertEquals("Manager", managerDto.getEmpDesignation());
        assertEquals("1234567890", managerDto.getEmpContactNo());
        assertEquals("vivek@example.com", managerDto.getEmpEmail());
        assertEquals("New Delhi", managerDto.getEmpLocation());
        assertEquals("EMP001", managerDto.getEmpId());
        assertEquals(Arrays.asList("Skill1", "Skill2"), managerDto.getEmpSkills());
        assertEquals("Manager Name", managerDto.getManagerName());
        assertEquals(Arrays.asList("Project1", "Project2"), managerDto.getProjectNames());
    }

    @Test
    public void testEqualsWithSettersAndGetters() {
        // Create two ManagerDto objects with the same values
        ManagerDto managerDto1 = new ManagerDto();
        managerDto1.setId(1L);
        managerDto1.setEmpName("Vivek Dubey");
        managerDto1.setEmpDesignation("Manager");
        managerDto1.setEmpContactNo("1234567890");
        managerDto1.setEmpEmail("vivek@example.com");
        managerDto1.setEmpLocation("New Delhi");
        managerDto1.setEmpId("EMP001");
        managerDto1.setEmpSkills(Arrays.asList("Skill1", "Skill2"));
        managerDto1.setManagerName("Manager Name");
        managerDto1.setProjectNames(Arrays.asList("Project1", "Project2"));

        ManagerDto managerDto2 = new ManagerDto();
        managerDto2.setId(1L);
        managerDto2.setEmpName("Vivek Dubey");
        managerDto2.setEmpDesignation("Manager");
        managerDto2.setEmpContactNo("1234567890");
        managerDto2.setEmpEmail("vivek@example.com");
        managerDto2.setEmpLocation("New Delhi");
        managerDto2.setEmpId("EMP001");
        managerDto2.setEmpSkills(Arrays.asList("Skill1", "Skill2"));
        managerDto2.setManagerName("Manager Name");
        managerDto2.setProjectNames(Arrays.asList("Project1", "Project2"));

        // Create another ManagerDto object with different values
        ManagerDto managerDto3 = new ManagerDto();
        managerDto3.setId(2L);
        managerDto3.setEmpName("Jane Smith");
        managerDto3.setEmpDesignation("Manager");
        managerDto3.setEmpContactNo("9876543210");
        managerDto3.setEmpEmail("jane@example.com");
        managerDto3.setEmpLocation("Los Angeles");
        managerDto3.setEmpId("EMP002");
        managerDto3.setEmpSkills(Arrays.asList("Skill1", "Skill2"));
        managerDto3.setManagerName("Manager Name");
        managerDto3.setProjectNames(Arrays.asList("Project1", "Project2"));

        // Test equals method
        assertTrue(managerDto1.equals(managerDto2)); // Equal objects
        assertFalse(managerDto1.equals(managerDto3)); // Different objects
    }
    @Test
    public void testHashCodeWithSettersAndGetters() {
        // Create two ManagerDto objects with the same values
        ManagerDto managerDto1 = new ManagerDto();
        managerDto1.setId(1L);
        managerDto1.setEmpName("Vivek Dubey");
        managerDto1.setEmpDesignation("Manager");
        managerDto1.setEmpContactNo("1234567890");
        managerDto1.setEmpEmail("vivek@example.com");
        managerDto1.setEmpLocation("New Delhi");
        managerDto1.setEmpId("EMP001");
        managerDto1.setEmpSkills(Arrays.asList("Skill1", "Skill2"));
        managerDto1.setManagerName("Manager Name");
        managerDto1.setProjectNames(Arrays.asList("Project1", "Project2"));

        ManagerDto managerDto2 = new ManagerDto();
        managerDto2.setId(1L);
        managerDto2.setEmpName("Vivek Dubey");
        managerDto2.setEmpDesignation("Manager");
        managerDto2.setEmpContactNo("1234567890");
        managerDto2.setEmpEmail("vivek@example.com");
        managerDto2.setEmpLocation("New Delhi");
        managerDto2.setEmpId("EMP001");
        managerDto2.setEmpSkills(Arrays.asList("Skill1", "Skill2"));
        managerDto2.setManagerName("Manager Name");
        managerDto2.setProjectNames(Arrays.asList("Project1", "Project2"));

        // Test hashCode method
        assertEquals(managerDto1.hashCode(), managerDto2.hashCode()); // Equal objects
    }
}

