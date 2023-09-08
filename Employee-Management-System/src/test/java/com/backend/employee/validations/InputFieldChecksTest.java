package com.backend.employee.validations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputFieldChecksTest {

  private InputFieldChecks inputFieldChecks;

  @BeforeEach
  public void setUp() {
      inputFieldChecks = new InputFieldChecks();
  }

  @Test
  public void testCheckEmpId_Valid() {
      assertTrue(inputFieldChecks.checkEmpId("N1234")); 
  }

  @Test
  public void testCheckEmpId_Invalid() {
      assertFalse(inputFieldChecks.checkEmpId("12345"));
  }

  @Test
  public void testCheckDate_Valid() {
      assertTrue(inputFieldChecks.checkDate("31/12/2022"));
  }

  @Test
  public void testCheckDate_Invalid() {
      assertFalse(inputFieldChecks.checkDate("2022-12-31"));
  }

  @Test
  public void testCheckEmpEmail_Valid() {
      assertTrue(inputFieldChecks.checkEmpEmail("john@nucleusteq.com"));
  }

  @Test
  public void testCheckEmpEmail_Invalid() {
      assertFalse(inputFieldChecks.checkEmpEmail("john@example.com"));
  }

  @Test
  public void testCheckEmpContactNo_Valid() {
      assertTrue(inputFieldChecks.checkEmpContactNo("1234567890"));
  }

  @Test
  public void testCheckEmpContactNo_Invalid() {
      assertFalse(inputFieldChecks.checkEmpContactNo("12345"));
  }

  @Test
  public void testCheckEmpPassword_Valid() {
      assertTrue(inputFieldChecks.checkEmpPassword("secure123"));
  }

  @Test
  public void testCheckEmpPassword_Invalid() {
      assertFalse(inputFieldChecks.checkEmpPassword("short"));
  }

  @Test
  public void testCheckValidName_Valid() {
      assertTrue(inputFieldChecks.checkValidName("John Doe"));
  }

  @Test
  public void testCheckValidName_Invalid() {
      assertFalse(inputFieldChecks.checkValidName("John123"));
  }
}
