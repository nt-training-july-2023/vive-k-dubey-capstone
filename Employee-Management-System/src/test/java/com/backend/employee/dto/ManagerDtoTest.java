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
    public void testEquals() {
        // Create two ManagerDto objects with the same values
        ManagerDto managerDto1 = new ManagerDto(1L, "Vivek Dubey", "Manager", "1234567890", "vivek@example.com",
                "New Delhi", "EMP001", Arrays.asList("Skill1", "Skill2"), "Manager Name", Arrays.asList("Project1", "Project2"));
        ManagerDto managerDto2 = new ManagerDto(1L, "Vivek Dubey", "Manager", "1234567890", "vivek@example.com",
                "New Delhi", "EMP001", Arrays.asList("Skill1", "Skill2"), "Manager Name", Arrays.asList("Project1", "Project2"));

        // Create another ManagerDto object with different values
        ManagerDto managerDto3 = new ManagerDto(2L, "Jane Smith", "Manager", "9876543210", "jane@example.com",
                "Los Angeles", "EMP002", Arrays.asList("Skill1", "Skill2"), "Manager Name", Arrays.asList("Project1", "Project2"));

        // Test equals method
        assertTrue(managerDto1.equals(managerDto2)); // Equal objects
        assertFalse(managerDto1.equals(managerDto3)); // Different objects
    }

    @Test
    public void testHashCode() {
        // Create two ManagerDto objects with the same values
        ManagerDto managerDto1 = new ManagerDto(1L, "Vivek Dubey", "Manager", "1234567890", "vivek@example.com",
                "New Delhi", "EMP001", Arrays.asList("Skill1", "Skill2"), "Manager Name", Arrays.asList("Project1", "Project2"));
        ManagerDto managerDto2 = new ManagerDto(1L, "Vivek Dubey", "Manager", "1234567890", "vivek@example.com",
                "New Delhi", "EMP001", Arrays.asList("Skill1", "Skill2"), "Manager Name", Arrays.asList("Project1", "Project2"));

        // Test hashCode method
        assertEquals(managerDto1.hashCode(), managerDto2.hashCode()); // Equal objects
    }
}

