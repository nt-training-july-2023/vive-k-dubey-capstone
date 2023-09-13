package com.backend.employee.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class ResponseDtoTest {

    @Test
    public void testResponseDtoConstructor() {
        // Arrange
        String message = "Success";
        int statusCode = 200;
        List<String> data = new ArrayList<>();
        data.add("Data1");
        data.add("Data2");

        // Act
        ResponseDto<String> responseDto = new ResponseDto<>(message, statusCode, data);

        // Assert
        assertEquals(message, responseDto.getMessage());
        assertEquals(statusCode, responseDto.getStatusCode());
        assertEquals(data, responseDto.getData());
    }

    @Test
    public void testResponseDtoGettersAndSetters() {
        // Arrange
        ResponseDto<String> responseDto = new ResponseDto<>();
        String message = "Error";
        int statusCode = 404;
        List<String> data = new ArrayList<>();
        data.add("Data1");

        // Act
        responseDto.setMessage(message);
        responseDto.setStatusCode(statusCode);
        responseDto.setData(data);

        // Assert
        assertEquals(message, responseDto.getMessage());
        assertEquals(statusCode, responseDto.getStatusCode());
        assertEquals(data, responseDto.getData());
    }
}
