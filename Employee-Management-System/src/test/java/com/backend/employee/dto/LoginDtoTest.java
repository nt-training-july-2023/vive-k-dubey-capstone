package com.backend.employee.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class LoginDtoTest {

  private LoginDto loginDto;

  @BeforeEach
  public void setUp() {
      loginDto = new LoginDto();
  }

  @Test
  public void testEmpEmail() {
      loginDto.setEmpEmail("test@example.com");
      assertEquals("test@example.com", loginDto.getEmpEmail());
  }

  @Test
  public void testEmpPassword() {
      loginDto.setEmpPassword("testPassword");
      assertEquals("testPassword", loginDto.getEmpPassword());
  }

  @Test
  public void testConstructorWithValues() {
      loginDto = new LoginDto("test@example.com", "testPassword");
      assertEquals("test@example.com", loginDto.getEmpEmail());
      assertEquals("testPassword", loginDto.getEmpPassword());
  }

  

  
}
