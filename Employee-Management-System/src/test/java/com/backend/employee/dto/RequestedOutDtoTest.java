package com.backend.employee.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestedOutDtoTest {

    @Test
    public void testIsRequested() {
        RequestedOutDto dto = new RequestedOutDto();
        boolean isRequested = true;
        dto.setIsRequested(isRequested);

        assertTrue(dto.isRequested());
    }

    @Test
    public void testIsNotRequested() {
        RequestedOutDto dto = new RequestedOutDto();
        boolean isRequested = false;
        dto.setIsRequested(isRequested);

        assertFalse(dto.isRequested());
    }
    
    @Test
    public void testHashCode() {
        RequestedOutDto dto1 = new RequestedOutDto();
        dto1.setIsRequested(true);

        RequestedOutDto dto2 = new RequestedOutDto();
        dto2.setIsRequested(true);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        RequestedOutDto dto1 = new RequestedOutDto();
        dto1.setIsRequested(true);

        RequestedOutDto dto2 = new RequestedOutDto();
        dto2.setIsRequested(true);

        RequestedOutDto dto3 = new RequestedOutDto();
        dto3.setIsRequested(false);

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
    }

    @Test
    public void testToString() {
        RequestedOutDto dto = new RequestedOutDto();
        dto.setIsRequested(true);

        String expectedString = "RequestedOutDto [isRequested=true]";
        assertEquals(expectedString, dto.toString());
    }
}

