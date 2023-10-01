package com.backend.employee.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
  
  @Test
  public void testHashCode() {
      RegisterDto dto1 = new RegisterDto();
      dto1.setId(1L);
      dto1.setEmpId("N001");
      dto1.setEmpName("Vivek Dubey");
      dto1.setEmpEmail("vivek@nucleusteq.com");
      dto1.setEmpDOB("1990-01-01");
      dto1.setEmpDOJ("2021-08-01");
      dto1.setEmpLocation("New Delhi");
      dto1.setEmpDesignation("Software Engineer");
      dto1.setEmpContactNo("1234567890");
      dto1.setEmpPassword("securePassword");
      dto1.setEmpRole("Employee");
      List<String> empSkills1 = new ArrayList<>();
      empSkills1.add("Java");
      empSkills1.add("Spring");
      dto1.setEmpSkills(empSkills1);
      dto1.setManagerName("Manager Name");
      dto1.setProjectId(101L);

      RegisterDto dto2 = new RegisterDto();
      dto2.setId(1L);
      dto2.setEmpId("N001");
      dto2.setEmpName("Vivek Dubey");
      dto2.setEmpEmail("vivek@nucleusteq.com");
      dto2.setEmpDOB("1990-01-01");
      dto2.setEmpDOJ("2021-08-01");
      dto2.setEmpLocation("New Delhi");
      dto2.setEmpDesignation("Software Engineer");
      dto2.setEmpContactNo("1234567890");
      dto2.setEmpPassword("securePassword");
      dto2.setEmpRole("Employee");
      List<String> empSkills2 = new ArrayList<>();
      empSkills2.add("Java");
      empSkills2.add("Spring");
      dto2.setEmpSkills(empSkills2);
      dto2.setManagerName("Manager Name");
      dto2.setProjectId(101L);

      assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  public void testEquals() {
      RegisterDto dto1 = new RegisterDto();
      dto1.setId(1L);
      dto1.setEmpId("N001");
      dto1.setEmpName("Vivek Dubey");
      dto1.setEmpEmail("vivek@nucleusteq.com");
      dto1.setEmpDOB("1990-01-01");
      dto1.setEmpDOJ("2021-08-01");
      dto1.setEmpLocation("New Delhi");
      dto1.setEmpDesignation("Software Engineer");
      dto1.setEmpContactNo("1234567890");
      dto1.setEmpPassword("securePassword");
      dto1.setEmpRole("Employee");
      List<String> empSkills1 = new ArrayList<>();
      empSkills1.add("Java");
      empSkills1.add("Spring");
      dto1.setEmpSkills(empSkills1);
      dto1.setManagerName("Manager Name");
      dto1.setProjectId(101L);

      RegisterDto dto2 = new RegisterDto();
      dto2.setId(1L);
      dto2.setEmpId("N001");
      dto2.setEmpName("Vivek Dubey");
      dto2.setEmpEmail("vivek@nucleusteq.com");
      dto2.setEmpDOB("1990-01-01");
      dto2.setEmpDOJ("2021-08-01");
      dto2.setEmpLocation("New Delhi");
      dto2.setEmpDesignation("Software Engineer");
      dto2.setEmpContactNo("1234567890");
      dto2.setEmpPassword("securePassword");
      dto2.setEmpRole("Employee");
      List<String> empSkills2 = new ArrayList<>();
      empSkills2.add("Java");
      empSkills2.add("Spring");
      dto2.setEmpSkills(empSkills2);
      dto2.setManagerName("Manager Name");
      dto2.setProjectId(101L);

      assertTrue(dto1.equals(dto2));
  }

  @Test
  public void testToString() {
      RegisterDto dto = new RegisterDto();
      dto.setId(1L);
      dto.setEmpId("N001");
      dto.setEmpName("Vivek Dubey");
      dto.setEmpEmail("vivek@nucleusteq.com");
      dto.setEmpDOB("1990-01-01");
      dto.setEmpDOJ("2021-08-01");
      dto.setEmpLocation("New Delhi");
      dto.setEmpDesignation("Software Engineer");
      dto.setEmpContactNo("1234567890");
      dto.setEmpPassword("securePassword");
      dto.setEmpRole("Employee");
      List<String> empSkills = new ArrayList<>();
      empSkills.add("Java");
      empSkills.add("Spring");
      dto.setEmpSkills(empSkills);
      dto.setManagerName("Manager Name");
      dto.setProjectId(101L);

      String expectedString = "RegisterDto [id=1, empId=N001, empName=Vivek Dubey, empEmail=vivek@nucleusteq.com, empDOB=1990-01-01, empDOJ=2021-08-01, empLocation=New Delhi, empDesignation=Software Engineer, empContactNo=1234567890, empPassword=securePassword, empRole=Employee, empSkills=[Java, Spring], managerName=Manager Name, projectId=101]";
      assertEquals(expectedString, dto.toString());
  }
}
