package com.backend.employee.validations;

import com.backend.employee.dto.AssignProjectDto;
import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.ProjectDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import java.util.Arrays;
import java.util.Base64;
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
    
    @Mock
    private BCryptPasswordEncoder passwordEncoders;

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
    public void testValidateManagerExistence() {
        Long managerEmployeeId = 1L;
        String managerRole = "manager";
        String nonManagerRole = "employee";

        RegisterEntity managerEntity = new RegisterEntity();
        managerEntity.setId(managerEmployeeId);
        managerEntity.setEmpRole(managerRole);

        when(registerRepository.findById(managerEmployeeId)).thenReturn(Optional.of(managerEntity));

        assertDoesNotThrow(() -> validationService.validateManagerExistence(managerEmployeeId));

        RegisterEntity nonManagerEntity = new RegisterEntity();
        nonManagerEntity.setId(managerEmployeeId);
        nonManagerEntity.setEmpRole(nonManagerRole);

        when(registerRepository.findById(managerEmployeeId)).thenReturn(Optional.of(nonManagerEntity));

        assertThrows(WrongInputException.class, () -> validationService.validateManagerExistence(managerEmployeeId));

        when(registerRepository.findById(managerEmployeeId)).thenReturn(Optional.empty());
        assertThrows(WrongInputException.class, () -> validationService.validateManagerExistence(managerEmployeeId));
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
    public void testIsRequestedValidator() {
        RequestedDto requestedDto = new RequestedDto();
        requestedDto.setEmpId("N1234");
        requestedDto.setManagerEmail("manager@example.com");

        when(registerRepository.findByEmpId(requestedDto.getEmpId())).thenReturn(Optional.of(new RegisterEntity()));
        when(registerRepository.findByEmpEmail(requestedDto.getManagerEmail())).thenReturn(Optional.of(new RegisterEntity()));

        assertDoesNotThrow(() -> validationService.isRequestedValidator(requestedDto));

        when(registerRepository.findByEmpId(requestedDto.getEmpId())).thenReturn(Optional.of(new RegisterEntity()));
        when(registerRepository.findByEmpEmail(requestedDto.getManagerEmail())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> validationService.isRequestedValidator(requestedDto));

        when(registerRepository.findByEmpId(requestedDto.getEmpId())).thenReturn(Optional.empty());

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
    public void testLoginValidation() {
        String nonExistentEmpEmail = "nonexistent@example.com";
        String correctPassword = "correctPassword";

        when(registerRepository.findByEmpEmail(nonExistentEmpEmail)).thenReturn(Optional.empty());

        LoginDto loginDto1 = new LoginDto();
        loginDto1.setEmpEmail(nonExistentEmpEmail);
        loginDto1.setEmpPassword(correctPassword);

        assertThrows(DataNotFoundException.class, () -> validationService.loginValidation(loginDto1));

        String existingEmpEmail = "employee@example.com";
        String encodedPassword = "encodedPassword";

        RegisterEntity user = new RegisterEntity();
        user.setEmpPassword(encodedPassword);

        when(registerRepository.findByEmpEmail(existingEmpEmail)).thenReturn(Optional.of(user));
        when(passwordEncoders.matches(correctPassword, user.getEmpPassword())).thenReturn(true);

        LoginDto loginDto2 = new LoginDto();
        loginDto2.setEmpEmail(existingEmpEmail);
        loginDto2.setEmpPassword(correctPassword);

        assertDoesNotThrow(() -> validationService.loginValidation(loginDto2));

        String incorrectPassword = "incorrectPassword";

        when(passwordEncoders.matches(incorrectPassword, user.getEmpPassword())).thenReturn(false);

        LoginDto loginDto3 = new LoginDto();
        loginDto3.setEmpEmail(existingEmpEmail);
        loginDto3.setEmpPassword(incorrectPassword);

        assertThrows(UnauthorizedException.class, () -> validationService.loginValidation(loginDto3));

        String decodedPassword = "decodedPassword";

        byte[] encodedPasswordBytes = Base64.getEncoder().encode(decodedPassword.getBytes());
        
        when(passwordEncoders.matches(decodedPassword, user.getEmpPassword())).thenReturn(false);

        LoginDto loginDto4 = new LoginDto();
        loginDto4.setEmpEmail(existingEmpEmail);
        loginDto4.setEmpPassword(new String(encodedPasswordBytes));

        assertThrows(UnauthorizedException.class, () -> validationService.loginValidation(loginDto4));
        
        when(passwordEncoders.matches(decodedPassword, user.getEmpPassword())).thenReturn(true);
        assertDoesNotThrow(() -> validationService.loginValidation(loginDto4));
        
        
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
    public void testValidateManagerId() {
        Long existingManagerId = 1L;
        RegisterEntity existingManager = new RegisterEntity();
        existingManager.setId(existingManagerId);
        existingManager.setEmpRole("manager");

        when(registerRepository.findById(existingManagerId)).thenReturn(Optional.of(existingManager));

        assertDoesNotThrow(() -> validationService.validateManagerId(existingManagerId));

        Long nonManagerId = 2L;
        RegisterEntity nonManager = new RegisterEntity();
        nonManager.setId(nonManagerId);
        nonManager.setEmpRole("employee");

        when(registerRepository.findById(nonManagerId)).thenReturn(Optional.of(nonManager));

        assertThrows(WrongInputException.class, () -> validationService.validateManagerId(nonManagerId));

        Long nonExistentManagerId = 3L;

        when(registerRepository.findById(nonExistentManagerId)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> validationService.validateManagerId(nonExistentManagerId));
    }
    
    @Test
    public void testValidateSkills() {
        List<String> nullSkills = null;

        assertThrows(WrongInputException.class, () -> validationService.validateSkills(nullSkills));

        List<String> emptySkills = new ArrayList<>();

        assertThrows(WrongInputException.class, () -> validationService.validateSkills(emptySkills));

        List<String> skillsWithOneSkill = Arrays.asList("Java");
        List<String> skillsWithMultipleSkills = Arrays.asList("Java", "Python", "SQL");

        assertDoesNotThrow(() -> validationService.validateSkills(skillsWithOneSkill));
        assertDoesNotThrow(() -> validationService.validateSkills(skillsWithMultipleSkills));
    }
    
    @Test
    public void testValidateDuplicateProjectName() {
        String existingProjectName = "ExistingProject";
        String nonExistentProjectName = "NewProject";

        ProjectEntity existingProject = new ProjectEntity();
        existingProject.setName(existingProjectName);

        when(projectRepository.findByName(existingProjectName)).thenReturn(existingProject);

        assertThrows(DataAlreadyExistsException.class, () -> validationService.validateDuplicateProjectName(existingProjectName));

        when(projectRepository.findByName(nonExistentProjectName)).thenReturn(null);

        assertDoesNotThrow(() -> validationService.validateDuplicateProjectName(nonExistentProjectName));
    }

    @Test
    public void testValidateProjectDescription() {
        String validDescription = "A valid project description";

        assertDoesNotThrow(() -> validationService.validateProjectDescription(validDescription));

        String emptyDescription = "";

        assertThrows(WrongInputException.class, () -> validationService.validateProjectDescription(emptyDescription));

        String nullDescription = null;

        assertThrows(WrongInputException.class, () -> validationService.validateProjectDescription(nullDescription));
    }
    @Test
    public void testValidateAssignProjectDtoInvalidEmployee() {
        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("N1234");
        assignProjectDto.setProjectId(1L);

        when(registerRepository.findByEmpId("N1234")).thenReturn(Optional.empty());

        assertThrows(WrongInputException.class, () -> validationService.validateAssignProjectDto(assignProjectDto));
        
        AssignProjectDto existingProjectDto = new AssignProjectDto();
       existingProjectDto.setEmpId("N5678");
        existingProjectDto.setProjectId(2L); 
        
        RegisterEntity existingEmployee = new RegisterEntity();
        existingEmployee.setProjectId(2L);

        when(registerRepository.findByEmpId("N5678")).thenReturn(Optional.of(existingEmployee));
        
        assertThrows(WrongInputException.class, () -> validationService.validateAssignProjectDto(existingProjectDto));
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
        
        RegisterEntity employee = new RegisterEntity();
        employee.setEmpId("N5678");
        employee.setProjectId(null);
        when(registerRepository.findByEmpId("N5678")).thenReturn(Optional.of(employee));

        assertThrows(WrongInputException.class, () -> validationService.unassignProjectValidator("N5678"));
        
        RegisterEntity employeeTwo = new RegisterEntity();
        employeeTwo.setEmpId("N5678");
        employeeTwo.setProjectId(5L);
        when(registerRepository.findByEmpId("N5678")).thenReturn(Optional.of(employeeTwo));

        assertDoesNotThrow( () -> validationService.unassignProjectValidator("N5678"));
    }
    
    
    @Test
    public void testValidateManagerEmail() {
        String testEmail = "test@example.com";


        when(registerRepository.findByEmpEmail(testEmail)).thenReturn(Optional.empty());


        assertThrows(WrongInputException.class, () -> validationService.validateManagerEmail(testEmail));
        
        RegisterEntity nonManagerEmployee = new RegisterEntity();
        nonManagerEmployee.setEmpEmail(testEmail);
        nonManagerEmployee.setEmpRole("employee");
        when(registerRepository.findByEmpEmail(testEmail)).thenReturn(Optional.of(nonManagerEmployee));

        assertThrows(WrongInputException.class, () -> validationService.validateManagerEmail(testEmail));
        
        RegisterEntity managerEmployee = new RegisterEntity();
        managerEmployee.setEmpEmail(testEmail);
        managerEmployee.setEmpRole("manager");
        when(registerRepository.findByEmpEmail(testEmail)).thenReturn(Optional.of(managerEmployee));

        assertDoesNotThrow( () -> validationService.validateManagerEmail(testEmail));
    }

    @Test
    public void testValidateRejectResource() {

        Long testResourceId = 1L;


        when(requestResourceRepository.findById(testResourceId)).thenReturn(Optional.empty());


        assertThrows(DataNotFoundException.class, () -> validationService.validateRejectResource(testResourceId));
    }
}

