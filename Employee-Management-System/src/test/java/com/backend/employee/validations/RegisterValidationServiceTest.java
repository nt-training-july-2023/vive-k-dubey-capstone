package com.backend.employee.validations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.WrongInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterValidationServiceTest {

    private RegisterValidationService inputFieldsChecksUpdated;

    @BeforeEach
    public void setUp() {
        inputFieldsChecksUpdated = new RegisterValidationService();
    }

    @Test
    public void testCheckEmpId_ValidEmpId() {
        assertDoesNotThrow(() -> inputFieldsChecksUpdated.checkEmpId("N1234"));
    }

    @Test
    public void testCheckEmpId_EmptyEmpId() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkEmpId(""));
    }

    @Test
    public void testCheckEmpId_InvalidEmpId() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkEmpId("1234"));
    }

    @Test
    public void testCheckDob_ValidDate() {
        assertDoesNotThrow(() -> inputFieldsChecksUpdated.checkDob("01/01/1990"));
    }

    @Test
    public void testCheckDob_EmptyDate() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkDob(""));
    }

    @Test
    public void testCheckDob_InvalidDate() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkDob("01-01-1990"));
    }

    @Test
    public void testCheckDoj_ValidDate() {
        assertDoesNotThrow(() -> inputFieldsChecksUpdated.checkDoj("01/01/2020"));
    }

    @Test
    public void testCheckDoj_EmptyDate() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkDoj(""));
    }

    @Test
    public void testCheckDoj_InvalidDate() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkDoj("01-01-2020"));
    }


    @Test
    public void testCheckEmpEmail_ValidEmail() {
        assertDoesNotThrow(() -> inputFieldsChecksUpdated.checkEmpEmail("john.doe@nucleusteq.com"));
    }

    @Test
    public void testCheckEmpEmail_EmptyEmail() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkEmpEmail(""));
    }

    @Test
    public void testCheckEmpEmail_InvalidEmail() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkEmpEmail("john.doe@example.com"));
    }

    @Test
    public void testCheckEmpContactNo_ValidContactNo() {
        assertDoesNotThrow(() -> inputFieldsChecksUpdated.checkEmpContactNo("1234567890"));
    }

    @Test
    public void testCheckEmpContactNo_EmptyContactNo() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkEmpContactNo(""));
    }

    @Test
    public void testCheckEmpContactNo_InvalidContactNo() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkEmpContactNo("12345"));
    }

    @Test
    public void testCheckEmpPassword_ShortPassword() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkEmpPassword("Pwd"));
    }

    @Test
    public void testCheckEmpName_ValidName() {
        assertDoesNotThrow(() -> inputFieldsChecksUpdated.checkEmpName("John Doe"));
    }

    @Test
    public void testCheckEmpName_EmptyName() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkEmpName(""));
    }

    @Test
    public void testCheckEmpName_InvalidName() {
        assertThrows(WrongInputException.class, () -> inputFieldsChecksUpdated.checkEmpName("John123"));
    }

    
    @Test
    public void testIsPossiblyHashed_PossiblyHashed() {
 //       assertTrue(inputFieldsChecksUpdated.isPossiblyHashed("$2a$12$123456789012345678901u4j4nsxLdS7ksBmmMhPV22V4.9BjRblY"));
    }

    @Test
    public void testIsPossiblyHashed_NotHashed() {
 //       assertFalse(inputFieldsChecksUpdated.isPossiblyHashed("Password123"));
    }
}
