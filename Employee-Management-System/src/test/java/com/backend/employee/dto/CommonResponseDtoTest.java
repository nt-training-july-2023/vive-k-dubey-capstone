package com.backend.employee.dto;

import com.backend.employee.dto.CommonResponseDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonResponseDtoTest {

    @Test
    public void testMessageGetterSetter() {
        // Create a CommonResponseDto object
        CommonResponseDto responseDto = new CommonResponseDto();

        // Set a message using the setter
        responseDto.setMessage("Test Message");

        // Get the message using the getter and assert its correctness
        assertEquals("Test Message", responseDto.getMessage());
    }

    @Test
    public void testNoArgsConstructor() {
        // Create a CommonResponseDto object using the no-args constructor
        CommonResponseDto responseDto = new CommonResponseDto();

        // Assert that the message is initially null
        assertEquals(null, responseDto.getMessage());

        // Set a message using the setter
        responseDto.setMessage("Test Message");

        // Get the message using the getter and assert its correctness
        assertEquals("Test Message", responseDto.getMessage());
    }

    @Test
    public void testAllArgsConstructor() {
        // Create a CommonResponseDto object using the all-args constructor
        CommonResponseDto responseDto = new CommonResponseDto("Test Message");

        // Get the message using the getter and assert its correctness
        assertEquals("Test Message", responseDto.getMessage());
    }
    
    @Test
    public void testHashCode() {
        // Create two instances with the same message
        CommonResponseDto dto1 = new CommonResponseDto("Test Message");
        CommonResponseDto dto2 = new CommonResponseDto("Test Message");

        // Hash codes should be equal for objects with the same message
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
    
    @Test
    public void testHashCodeNot() {
        // Create two instances with the different message
        CommonResponseDto dto1 = new CommonResponseDto("Test Message");
        CommonResponseDto dto2 = new CommonResponseDto(" Message");

        // Hash codes should not be equal for objects with the different message
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        // Create two instances with the same message
        CommonResponseDto dto1 = new CommonResponseDto("Test Message");
        CommonResponseDto dto2 = new CommonResponseDto("Test Message");

        // Objects should be equal if they have the same message
        assertTrue(dto1.equals(dto2));
    }
    
    @Test
    public void testEqualsNot() {
        // Create two instances with the different message
        CommonResponseDto dto1 = new CommonResponseDto(" Message");
        CommonResponseDto dto2 = new CommonResponseDto("Test Message");

        // Objects should be equal if they have  different message
        assertFalse(dto1.equals(dto2));
    }

    @Test
    public void testToString() {
        // Create a CommonResponseDto object
        CommonResponseDto responseDto = new CommonResponseDto("Test Message");

        // Check the toString representation
        assertEquals("CommonResponseDto [message=Test Message]", responseDto.toString());
    }
}

