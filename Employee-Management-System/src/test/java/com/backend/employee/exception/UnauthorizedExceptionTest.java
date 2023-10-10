package com.backend.employee.exception;

import com.backend.employee.exception.UnauthorizedException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnauthorizedExceptionTest {

    @Test
    public void testExceptionMessage() {
        String errorMessage = "Unauthorized access detected!";
        UnauthorizedException exception = new UnauthorizedException(errorMessage);

        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testResponseStatusAnnotation() {
        ResponseStatus responseStatusAnnotation = UnauthorizedException.class.getAnnotation(ResponseStatus.class);

        assertNotNull(responseStatusAnnotation);
        assertEquals(HttpStatus.UNAUTHORIZED, responseStatusAnnotation.value());
    }
}

