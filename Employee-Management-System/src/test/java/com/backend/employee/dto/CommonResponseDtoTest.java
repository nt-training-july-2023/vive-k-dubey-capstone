package com.backend.employee.dto;

import com.backend.employee.dto.CommonResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

