package com.backend.employee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import com.backend.employee.exception.CustomErrorResponse;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.exception.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandleDataNotFoundException() {
        DataNotFoundException exception = new DataNotFoundException("Data not found");
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());
        CustomErrorResponse response = globalExceptionHandler.dataNotFoundExceptionHandler(exception);

        assertEquals("Data not found", response.getMessage());
    }

    @Test
    public void testHandleDataAlreadyExistsException() {
        DataAlreadyExistsException exception = new DataAlreadyExistsException("Data already exists");
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());
        CustomErrorResponse response = globalExceptionHandler.dataAlreadyExistExceptionHandler(exception);

        assertEquals("Data already exists", response.getMessage());
    }

    @Test
    public void testHandleWrongInputException() {
        WrongInputException exception = new WrongInputException("Invalid input");
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());
        CustomErrorResponse response = globalExceptionHandler.wrongInputExceptionHandler(exception);

        assertEquals("Invalid input", response.getMessage());
    }

    @Test
    public void testHandleUnauthorizedException() {
        UnauthorizedException exception = new UnauthorizedException("Unauthorized access");
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());
        CustomErrorResponse response = globalExceptionHandler.unauthorizedExceptionHandler(exception);


        assertEquals("Unauthorized access", response.getMessage());
    }
}
