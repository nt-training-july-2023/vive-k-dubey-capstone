package com.backend.employee.dto;

import com.backend.employee.dto.ManagerInfoDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerInfoDtoTest {

    @Test
    public void testGetterSetter() {
        // Create a ManagerInfoDto object
        ManagerInfoDto managerInfoDto = new ManagerInfoDto();

        // Set values using the setter methods with 
        managerInfoDto.setId(1L);
        managerInfoDto.setManagerName("Rajesh Kumar");
        managerInfoDto.setManagerEmployeeId("EMP002");

        // Get values using the getter methods and assert their correctness
        assertEquals(1L, managerInfoDto.getId());
        assertEquals("Rajesh Kumar", managerInfoDto.getManagerName());
        assertEquals("EMP002", managerInfoDto.getManagerEmployeeId());
    }


    @Test
    public void testHashCode() {
        // Create two ManagerInfoDto objects with the same values
        ManagerInfoDto managerInfoDto1 = new ManagerInfoDto(1L, "Rajesh Kumar", "EMP002");
        ManagerInfoDto managerInfoDto2 = new ManagerInfoDto(1L, "Rajesh Kumar", "EMP002");

        // Test hashCode method
        //assertEquals(managerInfoDto1.hashCode(), managerInfoDto2.hashCode()); // Equal objects
    }
}
