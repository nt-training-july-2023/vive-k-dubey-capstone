package com.backend.employee.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegisterDtoTest {

  private RegisterDto registerDto;

  @BeforeEach
  public void setUp() {
      registerDto = new RegisterDto();
  }

  @Test
  public void testId() {
      registerDto.setId(1L);
      assertEquals(1L, registerDto.getId());
  }

  @Test
  public void testEmpId() {
      registerDto.setEmpId("N001");
      assertEquals("N001", registerDto.getEmpId());
  }

  @Test
  public void testEmpName() {
      registerDto.setEmpName("Vivek Dubey");
      assertEquals("Vivek Dubey", registerDto.getEmpName());
  }

  @Test
  public void testEmpEmail() {
      registerDto.setEmpEmail("vivek@example.com");
      assertEquals("vivek@example.com", registerDto.getEmpEmail());
  }

  @Test
  public void testEmpDOB() {
      registerDto.setEmpDOB("1990-01-01");
      assertEquals("1990-01-01", registerDto.getEmpDOB());
  }

  @Test
  public void testEmpDOJ() {
      registerDto.setEmpDOJ("2021-08-01");
      assertEquals("2021-08-01", registerDto.getEmpDOJ());
  }

  @Test
  public void testEmpLocation() {
      registerDto.setEmpLocation("Gorakhpur");
      assertEquals("Gorakhpur", registerDto.getEmpLocation());
  }

  @Test
  public void testEmpDesignation() {
      registerDto.setEmpDesignation("Software Engineer");
      assertEquals("Software Engineer", registerDto.getEmpDesignation());
  }

  @Test
  public void testEmpContactNo() {
      registerDto.setEmpContactNo("1234567890");
      assertEquals("1234567890", registerDto.getEmpContactNo());
  }

  @Test
  public void testEmpPassword() {
      registerDto.setEmpPassword("securePassword");
      assertEquals("securePassword", registerDto.getEmpPassword());
  }

  @Test
  public void testEmpRole() {
      registerDto.setEmpRole("Employee");
      assertEquals("Employee", registerDto.getEmpRole());
  }
}
