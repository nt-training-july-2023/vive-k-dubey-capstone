package com.backend.employee.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.employee.dto.RegisterDto;

public class RegisterEntityTest {

  private RegisterDto registerDto;
  private RegisterEntity registerEntity;

  @BeforeEach
  public void setUp() {
      registerDto = new RegisterDto();
      registerDto.setEmpId("N001");
      registerDto.setEmpName("Vivek Dubey");
      registerDto.setEmpDesignation("Software Engineer");
      registerDto.setEmpContactNo("1234567890");
      registerDto.setEmpPassword("securePassword");
      registerDto.setEmpEmail("vivek@example.com");
      registerDto.setEmpDOB("1990-01-01");
      registerDto.setEmpDOJ("2021-08-01");
      registerDto.setEmpLocation("Gorakhpur");
      registerDto.setEmpRole("Employee");

      registerEntity = new RegisterEntity(registerDto);
  }

  @Test
  public void testId() {
      assertNull(registerEntity.getId());
  }

  @Test
  public void testEmpId() {
      assertEquals("N001", registerEntity.getEmpId());
  }

  @Test
  public void testEmpName() {
      assertEquals("Vivek Dubey", registerEntity.getEmpName());
  }

  @Test
  public void testEmpDesignation() {
      assertEquals("Software Engineer", registerEntity.getEmpDesignation());
  }

  @Test
  public void testEmpPassword() {
      assertEquals("securePassword", registerEntity.getEmpPassword());
  }

  @Test
  public void testEmpEmail() {
      assertEquals("vivek@example.com", registerEntity.getEmpEmail());
  }

  @Test
  public void testEmpDOB() {
      assertEquals("1990-01-01", registerEntity.getEmpDOB());
  }

  @Test
  public void testEmpDOJ() {
      assertEquals("2021-08-01", registerEntity.getEmpDOJ());
  }

  @Test
  public void testEmpLocation() {
      assertEquals("Gorakhpur", registerEntity.getEmpLocation());
  }

  @Test
  public void testEmpRole() {
      assertEquals("Employee", registerEntity.getEmpRole());
  }
}
