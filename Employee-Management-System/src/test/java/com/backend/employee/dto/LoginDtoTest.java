package com.backend.employee.dto;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

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

  private Validator validator;

  public LoginDtoTest() {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      validator = factory.getValidator();
  }

  @Test
  public void testEmpEmailNotBlank() {
      LoginDto loginDto = new LoginDto();
      loginDto.setEmpPassword("testPassword");

      Set<ConstraintViolation<LoginDto>> violations = validator.validate(loginDto);

      // Check if 'empEmail' has a violation message
      assertTrue(violations.stream()
              .anyMatch(violation -> "Email cannot be blank".equals(violation.getMessage())));
  }

  @Test
  public void testEmpPasswordNotBlank() {
      LoginDto loginDto = new LoginDto();
      loginDto.setEmpEmail("test@example.com");

      Set<ConstraintViolation<LoginDto>> violations = validator.validate(loginDto);

      // Check if 'empPassword' has a violation message
      assertTrue(violations.stream()
              .anyMatch(violation -> "Password cannot be blank".equals(violation.getMessage())));
  }

  @Test
  public void testHashCode() {
      LoginDto loginDto1 = new LoginDto("test@example.com", "testPassword");
      LoginDto loginDto2 = new LoginDto("test@example.com", "testPassword");
      LoginDto loginDto3 = new LoginDto("test1@example.com", "test1Password");

      assertEquals(loginDto1.hashCode(), loginDto2.hashCode());
      assertNotEquals(loginDto1.hashCode(), loginDto3.hashCode());
  }

  @Test
  public void testEquals() {
      LoginDto loginDto1 = new LoginDto("test@example.com", "testPassword");
      LoginDto loginDto2 = new LoginDto("test@example.com", "testPassword");
      LoginDto loginDto3 = new LoginDto("test1@example.com", "test1Password");

      assertEquals(loginDto1, loginDto2);
      assertNotEquals(loginDto1, loginDto3);
  }

  @Test
  public void testToString() {
      LoginDto loginDto = new LoginDto("test@example.com", "testPassword");

      assertEquals("LoginDto [empEmail=test@example.com, empPassword=testPassword]", loginDto.toString());
  }

  
}
