package com.backend.employee.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataNotFoundExceptionTest {

    @Test
    public void testExceptionCreationWithMessage() {
        String errorMessage = "Requested data not found";

        DataNotFoundException exception = new DataNotFoundException(errorMessage);

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    } 
}


