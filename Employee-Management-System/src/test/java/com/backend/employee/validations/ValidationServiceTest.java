package com.backend.employee.validations;

import com.backend.employee.dto.AssignProjectDto;
import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.ProjectDto;
import com.backend.employee.dto.RequestResourceDto;
import com.backend.employee.dto.RequestedDto;
import com.backend.employee.dto.UpdateSkillsDto;
import com.backend.employee.entity.ProjectEntity;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.UnauthorizedException;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.repo.ProjectRepo;
import com.backend.employee.repo.RequestResourceRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationServiceTest {

    @InjectMocks
    private ValidationService validationService;

    @Mock
    private RegisterRepo registerRepository;

    @Mock
    private ProjectRepo projectRepository;

    @Mock
    private RequestResourceRepo requestResourceRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testRequestResourceValidatorInValid() {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("N1234");
        requestResourceDto.setManagerEmail("manager@example.com");
        requestResourceDto.setProjectId(1L);

        when(registerRepository.findByEmpId("N1234")).thenReturn(Optional.empty());
        when(registerRepository.findByEmpEmail("manager@example.com")).thenReturn(Optional.empty());
        assertThrows(DataNotFoundException.class, () -> validationService.requestResourceValidator(requestResourceDto));
    }

    @Test
    public void testRequestResourceValidatorEmptyEmpId() {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("");
        requestResourceDto.setManagerEmail("manager@example.com");
        requestResourceDto.setProjectId(1L);

        assertThrows(WrongInputException.class, () -> validationService.requestResourceValidator(requestResourceDto));
    }

    @Test
    public void testRequestResourceValidatorEmptyManagerEmail() {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("N1234");
        requestResourceDto.setManagerEmail("");
        requestResourceDto.setProjectId(1L);

        assertThrows(WrongInputException.class, () -> validationService.requestResourceValidator(requestResourceDto));
    }

    @Test
    public void testRequestResourceValidatorNullProjectId() {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("N1234");
        requestResourceDto.setManagerEmail("manager@example.com");
        requestResourceDto.setProjectId(null);

        assertThrows(WrongInputException.class, () -> validationService.requestResourceValidator(requestResourceDto));
    }

    @Test
    public void testRequestResourceValidatorNonexistentEmployee() {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("N5678");
        requestResourceDto.setManagerEmail("manager@example.com");
        requestResourceDto.setProjectId(1L);

        when(registerRepository.findByEmpId("N5678")).thenReturn(Optional.empty());
        when(registerRepository.findByEmpEmail("manager@example.com")).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> validationService.requestResourceValidator(requestResourceDto));
    }

    @Test
    public void testRequestResourceValidatorNonexistentManager() {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("N1234");
        requestResourceDto.setManagerEmail("nonexistent@example.com");
        requestResourceDto.setProjectId(1L);

        when(registerRepository.findByEmpId("N1234")).thenReturn(Optional.empty());
        when(registerRepository.findByEmpEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> validationService.requestResourceValidator(requestResourceDto));
    }

    @Test
    public void testRequestResourceValidatorNonexistentProject() {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("N1234");
        requestResourceDto.setManagerEmail("manager@example.com");
        requestResourceDto.setProjectId(2L);

        when(registerRepository.findByEmpId("N1234")).thenReturn(Optional.empty());
        when(registerRepository.findByEmpEmail("manager@example.com")).thenReturn(Optional.empty());
        when(projectRepository.findByProjectId(2L)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> validationService.requestResourceValidator(requestResourceDto));
    }

    @Test
    public void testRequestResourceValidatorMismatchedManager() {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("N1234");
        requestResourceDto.setManagerEmail("mismatched@example.com");
        requestResourceDto.setProjectId(1L);

        when(registerRepository.findByEmpId("N1234")).thenReturn(Optional.empty());
        when(registerRepository.findByEmpEmail("mismatched@example.com")).thenReturn(Optional.of(new RegisterEntity()));
        when(projectRepository.findByProjectId(1L)).thenReturn(new ProjectEntity());

        assertThrows(DataNotFoundException.class, () -> validationService.requestResourceValidator(requestResourceDto));
    }

    @Test
    public void testRequestResourceValidatorEmployeeAlreadyAssigned() {
        RequestResourceDto requestResourceDto = new RequestResourceDto();
        requestResourceDto.setEmpId("N1234");
        requestResourceDto.setManagerEmail("manager@example.com");
        requestResourceDto.setProjectId(1L);

        when(registerRepository.findByEmpId("N1234")).thenReturn(Optional.of(new RegisterEntity()));
        when(registerRepository.findByEmpEmail("manager@example.com")).thenReturn(Optional.empty());
        when(projectRepository.findByProjectId(1L)).thenReturn(new ProjectEntity());

        assertThrows(DataNotFoundException.class, () -> validationService.requestResourceValidator(requestResourceDto));
    }

    @Test
    public void testIsRequestedValidatorValid() {
        RequestedDto requestedDto = new RequestedDto();
        requestedDto.setEmpId("N1234");
        requestedDto.setManagerEmail("manager@example.com");

        assertThrows(DataNotFoundException.class, () -> validationService.isRequestedValidator(requestedDto));
    }

    @Test
    public void testIsRequestedValidatorEmptyEmpId() {
        RequestedDto requestedDto = new RequestedDto();
        requestedDto.setEmpId("");
        requestedDto.setManagerEmail("manager@example.com");

        assertThrows(WrongInputException.class, () -> validationService.isRequestedValidator(requestedDto));
    }
    
    @Test
    public void testIsRequestedValidatorEmptyManagerEmail() {
        RequestedDto requestedDto = new RequestedDto();
        requestedDto.setEmpId("N1234");
        requestedDto.setManagerEmail("");

        assertThrows(WrongInputException.class, () -> validationService.isRequestedValidator(requestedDto));
    }

    @Test
    public void testIsRequestedValidatorNonexistentEmpId() {
        RequestedDto requestedDto = new RequestedDto();
        requestedDto.setEmpId("N5678");
        requestedDto.setManagerEmail("manager@example.com");

        assertThrows(DataNotFoundException.class, () -> validationService.isRequestedValidator(requestedDto));
    }

    @Test
    public void testIsRequestedValidatorNonexistentManagerEmail() {
        RequestedDto requestedDto = new RequestedDto();
        requestedDto.setEmpId("N1234");
        requestedDto.setManagerEmail("nonexistent@example.com");

        assertThrows(DataNotFoundException.class, () -> validationService.isRequestedValidator(requestedDto));
    }
    


    @Test
    public void testValidateStartDateValid() {
        String startDate = "01/01/2024";

        assertDoesNotThrow(() -> validationService.validateStartDate(startDate));
    }

    @Test
    public void testValidateStartDateInvalidFormat() {
        String startDate = "2023-01-01";

        assertThrows(WrongInputException.class, () -> validationService.validateStartDate(startDate));
    }

    @Test
    public void testValidateStartDatePastDate() {
        // Use a past date
        String startDate = "01/01/2020";

        assertThrows(WrongInputException.class, () -> validationService.validateStartDate(startDate));
    }

    @Test
    public void testValidateStartDateInvalidDate() {
        // Use an invalid date format
        String startDate = "01/32/2023";

        assertThrows(WrongInputException.class, () -> validationService.validateStartDate(startDate));
    }



    @Test
    public void testLoginValidationValid() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmpEmail("user@example.com");
        loginDto.setEmpPassword("encodedPassword");

        RegisterEntity user = new RegisterEntity();
        user.setEmpEmail("user@example.com");
        user.setEmpPassword("encodedPassword");

        when(registerRepository.findByEmpEmail("user@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

        assertDoesNotThrow(() -> validationService.loginValidation(loginDto));
    }

    @Test
    public void testLoginValidationInvalidPassword() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmpEmail("user@example.com");
        loginDto.setEmpPassword("encodedPassword");

        RegisterEntity user = new RegisterEntity();
        user.setEmpEmail("user@example.com");
        user.setEmpPassword("wrongEncodedPassword");

        when(registerRepository.findByEmpEmail("user@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenReturn(false);

        assertThrows(UnauthorizedException.class, () -> validationService.loginValidation(loginDto));
    }

    @Test
    public void testValidateProjectDtoValid() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setStartDate("12/11/2024");
        projectDto.setName("Project Name");
        projectDto.setDescription("Project Description");
        projectDto.setSkills(Collections.singletonList("Java"));
        projectDto.setManagerEmployeeId(1L);
        
        ProjectDto projectDto2 = new ProjectDto();
        projectDto2.setStartDate(null);
        projectDto2.setName("Project Name");
        projectDto2.setDescription("Project Description");
        projectDto2.setSkills(Collections.singletonList("Java"));
        projectDto2.setManagerEmployeeId(1L);

        when(projectRepository.findByName("Project Name")).thenReturn(null);
        when(registerRepository.findById(1L)).thenReturn(Optional.of(new RegisterEntity()));

        assertThrows(NullPointerException.class,() -> validationService.validateProjectDto(projectDto2));
    }

    @Test
    public void testValidateProjectDtoInvalidName() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setStartDate("12/11/2024");
        projectDto.setName("123 Project");
        projectDto.setDescription("Project Description");
        projectDto.setSkills(Collections.singletonList("Java"));
        projectDto.setManagerEmployeeId(1L);

        assertThrows(WrongInputException.class, () -> validationService.validateProjectDto(projectDto));
    }

    @Test
    public void testValidateAssignProjectDtoValid() {
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("N1234");
        assignProjectDto.setProjectId(1L);

        when(registerRepository.findByEmpId("N1234")).thenReturn(Optional.of(new RegisterEntity()));
        when(projectRepository.findByProjectId(1L)).thenReturn(new ProjectEntity());

        assertDoesNotThrow(() -> validationService.validateAssignProjectDto(assignProjectDto));
    }

    @Test
    public void testValidateAssignProjectDtoInvalidEmployee() {
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("N1234");
        assignProjectDto.setProjectId(1L);

        when(registerRepository.findByEmpId("N1234")).thenReturn(Optional.empty());

        assertThrows(WrongInputException.class, () -> validationService.validateAssignProjectDto(assignProjectDto));
    }

    @Test
    public void testValidateAssignProjectDtoInvalidProject() {
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("N1234");
        assignProjectDto.setProjectId(1L);

        when(registerRepository.findByEmpId("N1234")).thenReturn(Optional.of(new RegisterEntity()));
        when(projectRepository.findByProjectId(1L)).thenReturn(null);

        assertThrows(WrongInputException.class, () -> validationService.validateAssignProjectDto(assignProjectDto));
    }


    @Test
    public void testValidateUserEmailInvalidUser() {
        when(registerRepository.findByEmpEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        assertThrows(WrongInputException.class, () -> validationService.validateUserEmail("nonexistent@example.com"));
    }

    @Test
    public void testValidateUserEmailManagerOrAdmin() {
        RegisterEntity admin = new RegisterEntity();
        admin.setEmpRole("admin");
        RegisterEntity manager = new RegisterEntity();
        manager.setEmpRole("manager");

        when(registerRepository.findByEmpEmail("admin@example.com")).thenReturn(Optional.of(admin));
        when(registerRepository.findByEmpEmail("manager@example.com")).thenReturn(Optional.of(manager));

        assertThrows(WrongInputException.class, () -> validationService.validateUserEmail("admin@example.com"));
        assertThrows(WrongInputException.class, () -> validationService.validateUserEmail("manager@example.com"));
    }

    @Test
    public void testValidateUpdateSkillsDtoValid() {
        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();
        updateSkillsDto.setEmpEmail("user@example.com");
        updateSkillsDto.setEmpSkills(Collections.singletonList("Java"));

        when(registerRepository.findByEmpEmail("user@example.com")).thenReturn(Optional.of(new RegisterEntity()));

        assertDoesNotThrow(() -> validationService.validateUpdateSkillsDto(updateSkillsDto));
    }

    @Test
    public void testValidateUpdateSkillsDtoInvalidUser() {
        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();
        updateSkillsDto.setEmpEmail("nonexistent@example.com");
        updateSkillsDto.setEmpSkills(Collections.singletonList("Java"));

        when(registerRepository.findByEmpEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> validationService.validateUpdateSkillsDto(updateSkillsDto));
    }

    @Test
    public void testValidateUpdateSkillsDtoEmptySkills() {
        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();
        updateSkillsDto.setEmpEmail("user@example.com");
        updateSkillsDto.setEmpSkills(new ArrayList<>());

        when(registerRepository.findByEmpEmail("user@example.com")).thenReturn(Optional.of(new RegisterEntity()));

        assertThrows(WrongInputException.class, () -> validationService.validateUpdateSkillsDto(updateSkillsDto));
    }


    @Test
    public void testUnassignProjectValidatorInvalid() {
        when(registerRepository.findByEmpId("N1234")).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> validationService.unassignProjectValidator("N1234"));
    }
    
    
    @Test
    public void testValidateManagerEmail() {
        String testEmail = "test@example.com";


        when(registerRepository.findByEmpEmail(testEmail)).thenReturn(Optional.empty());


        assertThrows(WrongInputException.class, () -> validationService.validateManagerEmail(testEmail));
    }

    @Test
    public void testValidateRejectResource() {

        Long testResourceId = 1L;


        when(requestResourceRepository.findById(testResourceId)).thenReturn(Optional.empty());


        assertThrows(DataNotFoundException.class, () -> validationService.validateRejectResource(testResourceId));
    }
}

