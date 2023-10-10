package com.backend.employee.validations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backend.employee.dto.RegisterDto;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.RegisterRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class RegisterValidationServiceTest {
 
    @InjectMocks
    private RegisterValidationService registerValidationService;
    
    @Mock
    private ValidationService validationService;
    
    @Mock
    private RegisterRepo registerRepository;

    @Test
    public void testCheckEmpId_ValidEmpId() {
        assertDoesNotThrow(() -> registerValidationService.checkEmpId("N1234"));
    }

    @Test
    public void testCheckEmpId_EmptyEmpId() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpId(""));
    }

    @Test
    public void testCheckEmpId_InvalidEmpId() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpId("1234"));
    }

    @Test
    public void testCheckDob_ValidDate() {
        assertDoesNotThrow(() -> registerValidationService.checkDob("01/01/1990"));
    }

    @Test
    public void testCheckDob_EmptyDate() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkDob(""));
    }

    @Test
    public void testCheckDob_InvalidDate() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkDob("01-01-1990"));
    }

    @Test
    public void testCheckDoj_ValidDate() {
        assertDoesNotThrow(() -> registerValidationService.checkDoj("01/01/2020"));
    }

    @Test
    public void testCheckDoj_EmptyDate() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkDoj(""));
    }

    @Test
    public void testCheckDoj_InvalidDate() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkDoj("01-01-2020"));
    }


    @Test
    public void testCheckEmpEmail_ValidEmail() {
        assertDoesNotThrow(() -> registerValidationService.checkEmpEmail("vivek.dubey@nucleusteq.com"));
    }

    @Test
    public void testCheckEmpEmail_EmptyEmail() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpEmail(""));
    }

    @Test
    public void testCheckEmpEmail_InvalidEmail() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpEmail("vivek.dubey@example.com"));
    }

    @Test
    public void testCheckEmpContactNo_ValidContactNo() {
        assertDoesNotThrow(() -> registerValidationService.checkEmpContactNo("1234567890"));
    }

    @Test
    public void testCheckEmpContactNo_EmptyContactNo() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpContactNo(""));
    }

    @Test
    public void testCheckEmpContactNo_InvalidContactNo() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpContactNo("12345"));
    }

    @Test
    public void testCheckEmpPassword_ShortPassword() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpPassword("Pwd"));
    }

    @Test
    public void testCheckEmpName_ValidName() {
        assertDoesNotThrow(() -> registerValidationService.checkEmpName("Vivek Dubey"));
    }

    @Test
    public void testCheckEmpName_EmptyName() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpName(""));
    }

    @Test
    public void testCheckEmpName_InvalidName() {
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpName("Vivek123"));
    }
    
    @Test
    public void testCheckDatesDifference() {
        String dob1 = "01/01/1990";
        String doj1 = "01/01/2022";

        assertDoesNotThrow(() -> registerValidationService.checkDatesDifference(dob1, doj1));

        String dob5 = "01/01/1990";
        String doj5 = "01/12/2007";

        assertThrows(WrongInputException.class, () -> registerValidationService.checkDatesDifference(dob5, doj5));
        
        
    }
    
    @Test
    public void testCheckValidAdminEmail() {
        String validEmail = "ankita.sharma@nucleusteq.com";
        String invalidEmail = "invalid.admin@nucleusteq.com";

        assertDoesNotThrow(() -> registerValidationService.checkValidAdminEmail(validEmail));

        assertThrows(DataAlreadyExistsException.class, () -> registerValidationService.checkValidAdminEmail(invalidEmail));
    }
    
    @Test
    public void testCheckEmailExistence() {
        String nonExistingEmail = "nonexisting@example.com";

        when(registerRepository.findByEmpEmail(nonExistingEmail)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> registerValidationService.checkEmailExistence(nonExistingEmail));

        String existingEmail = "existing@example.com";

        when(registerRepository.findByEmpEmail(existingEmail)).thenReturn(Optional.of(new RegisterEntity()));

        assertThrows(DataAlreadyExistsException.class, () -> registerValidationService.checkEmailExistence(existingEmail));
    }
    
    @Test
    public void testCheckEmpIdExistence() {
        String nonExistingEmpId = "N1234";

        when(registerRepository.findByEmpId(nonExistingEmpId)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> registerValidationService.checkEmpIdExistence(nonExistingEmpId));

        String existingEmpId = "N5678";

        when(registerRepository.findByEmpId(existingEmpId)).thenReturn(Optional.of(new RegisterEntity()));

        assertThrows(DataAlreadyExistsException.class, () -> registerValidationService.checkEmpIdExistence(existingEmpId));
    }
    
    @Test
    public void testCheckEmpContactExistence() {
        String nonExistingContact = "1234567890";

        when(registerRepository.findByEmpContactNo(nonExistingContact)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> registerValidationService.checkEmpContactExistence(nonExistingContact));

        String existingContact = "9876543210";

        when(registerRepository.findByEmpContactNo(existingContact)).thenReturn(Optional.of(new RegisterEntity()));

        assertThrows(DataAlreadyExistsException.class, () -> registerValidationService.checkEmpContactExistence(existingContact));
    }
    
    @Test
    public void testCheckEmpLocation() {
        String validLocation = "Indore";

        assertDoesNotThrow(() -> registerValidationService.checkEmpLocation(validLocation));

        String emptyLocation = "";
        String nullLocation = null;
        String emptyDifferentLocation= "   ";

        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpLocation(emptyLocation));
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpLocation(nullLocation));
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpLocation(emptyDifferentLocation));
        

        String invalidLocation = "InvalidCity";
        
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpLocation(invalidLocation));
    }
    
    @Test
    public void testCheckEmpDesignation() {
        String validDesignation = "Software Engineer";

        assertDoesNotThrow(() -> registerValidationService.checkEmpDesignation(validDesignation));

        String emptyDesignation = "";
        String nullDesignation = null;
        String emptyDifferent = "   ";

        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpDesignation(emptyDesignation));
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpDesignation(nullDesignation));
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpDesignation(emptyDifferent));

        String invalidDesignation = "Invalid Designation";
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpDesignation(invalidDesignation));
    }
    
    @Test
    public void testCheckEmpSkills() {
        List<String> validSkills = Arrays.asList("JavaScript", "React", "Node.js");

        assertDoesNotThrow(() -> registerValidationService.checkEmpSkills(validSkills));

        List<String> emptySkills = new ArrayList<>();

        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpSkills(emptySkills));
        
        List<String> nullSkills = null;

        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpSkills(nullSkills));

        List<String> invalidSkills = Arrays.asList("Invalid Skill", "Java", "SQL");

        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpSkills(invalidSkills));
    }
    
    @Test
    public void testCheckEmpRole() {
     
        String validEmployeeRole = "employee";
        assertDoesNotThrow(() -> registerValidationService.checkEmpRole(validEmployeeRole));

        String validManagerRole = "manager";
        assertDoesNotThrow(() -> registerValidationService.checkEmpRole(validManagerRole));

        String emptyRole = "";
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpRole(emptyRole));
        
        String nullRole = null;
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpRole(nullRole));

        String invalidRole = "Invalid Role";
        assertThrows(WrongInputException.class, () -> registerValidationService.checkEmpRole(invalidRole));
    }
    
    @Test
    public void testValidateRegisterDto() {
        RegisterDto validRegisterDto = new RegisterDto();
        validRegisterDto.setEmpId("N1234");
        validRegisterDto.setEmpDOB("01/01/1990");
        validRegisterDto.setEmpDOJ("01/01/2022");
        validRegisterDto.setEmpEmail("test@nucleusteq.com");
        validRegisterDto.setEmpContactNo("1234567890");
        validRegisterDto.setEmpPassword("password");
        validRegisterDto.setEmpSkills(Arrays.asList("Java", "SQL"));
        validRegisterDto.setEmpName("Vivek Dubey");
        validRegisterDto.setEmpRole("employee");
        validRegisterDto.setEmpDesignation("Software Engineer");
        validRegisterDto.setEmpLocation("Indore");

        when(registerRepository.findByEmpEmail(validRegisterDto.getEmpEmail())).thenReturn(Optional.empty());
        when(registerRepository.findByEmpId(validRegisterDto.getEmpId())).thenReturn(Optional.empty());
        when(registerRepository.findByEmpContactNo(validRegisterDto.getEmpContactNo())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> registerValidationService.validateRegisterDto(validRegisterDto));

        RegisterDto invalidRegisterDto = new RegisterDto();
        invalidRegisterDto.setEmpId("N54321");
        invalidRegisterDto.setEmpDOB("01/01/2000");
        invalidRegisterDto.setEmpDOJ("01/01/2022");
        invalidRegisterDto.setEmpEmail("test@example.com");
        invalidRegisterDto.setEmpContactNo("1234567890");
        invalidRegisterDto.setEmpPassword("password");
        invalidRegisterDto.setEmpSkills(Arrays.asList("Java", "SQL"));
        invalidRegisterDto.setEmpName("Vivek Dubey");
        invalidRegisterDto.setEmpRole("employee");
        invalidRegisterDto.setEmpDesignation("Software Engineer");
        invalidRegisterDto.setEmpLocation("Indore");

        assertThrows(WrongInputException.class, () -> registerValidationService.validateRegisterDto(invalidRegisterDto));
    }
    
    @Test
    public void testValidateRegisterDtoAdmin() {
        RegisterDto validRegisterDto = new RegisterDto();
        validRegisterDto.setEmpId("N1234");
        validRegisterDto.setEmpDOB("01/01/1990");
        validRegisterDto.setEmpDOJ("01/01/2022");
        validRegisterDto.setEmpEmail("ankita.sharma@nucleusteq.com"); 
        validRegisterDto.setEmpContactNo("1234567890");
        validRegisterDto.setEmpPassword("password");
        validRegisterDto.setEmpName("Ankita Sharma");
        validRegisterDto.setEmpDesignation("Software Engineer");
        validRegisterDto.setEmpLocation("Indore");

        when(registerRepository.findByEmpEmail(validRegisterDto.getEmpEmail())).thenReturn(Optional.empty());
        when(registerRepository.findByEmpId(validRegisterDto.getEmpId())).thenReturn(Optional.empty());
        when(registerRepository.findByEmpContactNo(validRegisterDto.getEmpContactNo())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> registerValidationService.validateRegisterDtoAdmin(validRegisterDto));

        RegisterDto invalidAdminEmailDto = new RegisterDto();
        invalidAdminEmailDto.setEmpId("N5432");
        invalidAdminEmailDto.setEmpDOB("01/01/1990");
        invalidAdminEmailDto.setEmpDOJ("01/01/2022");
        invalidAdminEmailDto.setEmpEmail("test@example.com"); 
        invalidAdminEmailDto.setEmpContactNo("1234567890");
        invalidAdminEmailDto.setEmpPassword("password");
        invalidAdminEmailDto.setEmpName("Ankita Sharma");
        invalidAdminEmailDto.setEmpDesignation("Software Engineer");
        invalidAdminEmailDto.setEmpLocation("Indore");

        assertThrows(WrongInputException.class, () -> registerValidationService.validateRegisterDtoAdmin(invalidAdminEmailDto));
    }

    
}
