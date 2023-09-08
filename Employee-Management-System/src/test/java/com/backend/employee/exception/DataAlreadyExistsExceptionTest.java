package com.backend.employee.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataAlreadyExistsExceptionTest {

    @Test
    public void testExceptionCreationWithMessage() {
        // Arrange
        String errorMessage = "Duplicate email detected";

        // Act
        DataAlreadyExistsException exception = new DataAlreadyExistsException(errorMessage);

        // Assert
        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }
}
