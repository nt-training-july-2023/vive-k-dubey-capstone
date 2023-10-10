package com.backend.employee.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataAlreadyExistsExceptionTest {

    @Test
    public void testExceptionCreationWithMessage() {
        String errorMessage = "Duplicate email detected";

        DataAlreadyExistsException exception = new DataAlreadyExistsException(errorMessage);

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }
}

