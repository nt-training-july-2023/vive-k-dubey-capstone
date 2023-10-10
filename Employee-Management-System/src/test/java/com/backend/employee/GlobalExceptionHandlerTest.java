package com.backend.employee;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.backend.employee.exception.CustomErrorResponse;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.exception.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        CustomErrorResponse response = globalExceptionHandler.dataNotFoundExceptionHandler(exception);

        assertEquals("Data not found", response.getMessage());
    }

    @Test
    public void testHandleDataAlreadyExistsException() {
        DataAlreadyExistsException exception = new DataAlreadyExistsException("Data already exists");
        CustomErrorResponse response = globalExceptionHandler.dataAlreadyExistExceptionHandler(exception);

        assertEquals("Data already exists", response.getMessage());
    }

    @Test
    public void testHandleWrongInputException() {
        WrongInputException exception = new WrongInputException("Invalid input");
        CustomErrorResponse response = globalExceptionHandler.wrongInputExceptionHandler(exception);

        assertEquals("Invalid input", response.getMessage());
    }

    @Test
    public void testHandleUnauthorizedException() {
        UnauthorizedException exception = new UnauthorizedException("Unauthorized access");
        CustomErrorResponse response = globalExceptionHandler.unauthorizedExceptionHandler(exception);


        assertEquals("Unauthorized access", response.getMessage());
    }
    
    @Test
    public void testHandleException() {
        Exception ex = new Exception("Simulated exception message");

        CustomErrorResponse response = globalExceptionHandler.exceptionHandler(ex);

        assertEquals("Simulated exception message", response.getMessage());
    }

}
