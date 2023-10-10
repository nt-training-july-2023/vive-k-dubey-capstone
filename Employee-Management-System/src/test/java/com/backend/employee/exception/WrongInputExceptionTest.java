package com.backend.employee.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.backend.employee.exception.WrongInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WrongInputExceptionTest {

    @Test
    public void testWrongInputException() {
        assertThrows(WrongInputException.class, () -> {
            throw new WrongInputException("Invalid input");
        });
    }
}
