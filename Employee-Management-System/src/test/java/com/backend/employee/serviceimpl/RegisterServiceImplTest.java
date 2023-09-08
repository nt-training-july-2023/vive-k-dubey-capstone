package com.backend.employee.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.backend.employee.validations.InputFieldChecks;

class RegisterServiceImplTest {

  private InputFieldChecks inputFieldChecks = new InputFieldChecks();

  @Test
  void testValidEmpId() {
      assertTrue(inputFieldChecks.checkEmpId("N1234"));
  }

  @Test
  void testInvalidEmpId() {
      assertFalse(inputFieldChecks.checkEmpId("A123"));
  }

  @Test
  void testValidDate() {
      assertTrue(inputFieldChecks.checkDate("15/08/1990")); 
  }

  @Test
  void testInvalidDate() {
      assertFalse(inputFieldChecks.checkDate("2022-05-25"));
  }

  @Test
  void testValidEmpEmail() {
      assertTrue(inputFieldChecks.checkEmpEmail("vivek.dubey@nucleusteq.com"));
  }

  @Test
  void testInvalidEmpEmail() {
      assertFalse(inputFieldChecks.checkEmpEmail("vivek.dubey@example.com"));
  }

  @Test
  void testValidEmpContactNo() {
      assertTrue(inputFieldChecks.checkEmpContactNo("1234567890"));
  }

  @Test
  void testInvalidEmpContactNo() {
      assertFalse(inputFieldChecks.checkEmpContactNo("12345"));
  }

  @Test
  void testValidEmpPassword() {
      assertTrue(inputFieldChecks.checkEmpPassword("secure123"));
  } 

  @Test
  void testInvalidEmpPassword() {
      assertFalse(inputFieldChecks.checkEmpPassword("weak"));
  }

  @Test
  void testValidName() {
      assertTrue(inputFieldChecks.checkValidName("Vivek Dubey"));
  }

  @Test
  void testInvalidName() {
      assertFalse(inputFieldChecks.checkValidName("Vivek123"));
  }

}
