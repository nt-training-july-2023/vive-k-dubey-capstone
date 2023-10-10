package com.backend.employee.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.backend.employee.dto.AssignProjectDto;
import com.backend.employee.dto.AssignProjectOutDto;
import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.LoginOutDto;
import com.backend.employee.dto.ManagerOutDto;
import com.backend.employee.dto.ManagerInfoDto;
import com.backend.employee.dto.ProjectDto;
import com.backend.employee.dto.ProjectOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.entity.ProjectEntity;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.ProjectRepo;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.validations.InputFieldChecks;
import com.backend.employee.validations.RegisterValidationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

 @InjectMocks
 private AdminService adminService;

 @Mock
 private RegisterRepo registerRepo;

 @Mock
 private BCryptPasswordEncoder passwordEncoder;

 @Mock
 private ProjectRepo projectRepository;

 @Mock
 private RegisterValidationService inputFieldChecks;

 @Test
 void testAddEmployee()
  throws WrongInputException, DataAlreadyExistsException {
  RegisterDto input = new RegisterDto();
  input.setEmpId("N0001");
  input.setEmpName("Ashish");
  input.setEmpEmail("ashish@nucleusteq.com");
  input.setEmpDOB("25/04/2001");
  input.setEmpDOJ("25/04/2023");
  input.setEmpLocation("Raipur");
  input.setEmpDesignation("Engineer");
  input.setEmpContactNo("1111111111");
  input.setEmpPassword("12345678");
  input.setEmpRole("admin");
  input.setProjectId((long) 1);
  input.setManagerName("Ankita");
  List<String> skills = new ArrayList<>();
  skills.add("java");
  skills.add("python");
  input.setEmpSkills(skills);

  CommonResponseDto response = adminService.addEmployee(input);
  verify(registerRepo, times(1)).save(any(RegisterEntity.class));

  CommonResponseDto expectedResult = new CommonResponseDto(
   "Employee added successfully");
  assertEquals(expectedResult.getMessage(), response.getMessage());
 }

 @Test
 void testGetAllEmployee() throws Exception {
  RegisterEntity employee = new RegisterEntity();
  employee.setEmpId("N0001");
  employee.setEmpName("Vivek");
  employee.setEmpEmail("vivek@nucleusteq.com");
  employee.setEmpDOB("25/04/2002");
  employee.setEmpDOJ("25/04/2023");
  employee.setEmpLocation("Raipur");
  employee.setEmpDesignation("Engineer");
  employee.setEmpContactNo("1111111111");
  employee.setEmpPassword("12345678");
  employee.setEmpRole("employee");
  employee.setProjectId((long) 1);
  employee.setManagerId((long) 1);
  List<String> skills = new ArrayList<>();
  skills.add("java");
  skills.add("python");
  employee.setEmpSkills(skills);
  List<RegisterEntity> allEmployee = new ArrayList<>();
  allEmployee.add(employee);

  when(registerRepo.findAllByEmpRole("employee")).thenReturn(allEmployee);

  List<RegisterDto> responseList = adminService.getAllEmployees();

  verify(registerRepo, times(1)).findAllByEmpRole("employee");

  assertEquals(1, responseList.size());
  assertEquals("N0001", responseList.get(0).getEmpId());
  assertEquals("1111111111", responseList.get(0).getEmpContactNo());
  assertEquals("Vivek", responseList.get(0).getEmpName());
  assertEquals("vivek@nucleusteq.com", responseList.get(0).getEmpEmail());
  assertEquals("25/04/2002", responseList.get(0).getEmpDOB());
  assertEquals("25/04/2023", responseList.get(0).getEmpDOJ());
  assertEquals("Raipur", responseList.get(0).getEmpLocation());
  assertEquals("Engineer", responseList.get(0).getEmpDesignation());
 }

 @Test
 void testGetAllEmployeesAndManagers() {
  List<RegisterEntity> employeeAndManagerEntities = new ArrayList<>();

  RegisterEntity employee = new RegisterEntity();
  employee.setEmpId("E0001");
  employee.setEmpName("Vivek");
  employee.setEmpEmail("Vivek@example.com");
  employee.setEmpRole("employee");
  employeeAndManagerEntities.add(employee);

  RegisterEntity manager = new RegisterEntity();
  manager.setEmpId("M0001");
  manager.setEmpName("Prerna");
  manager.setEmpEmail("Prerna@example.com");
  manager.setEmpRole("manager");
  employeeAndManagerEntities.add(manager);

  when(registerRepo.findAllByEmpRoleNot("admin"))
   .thenReturn(employeeAndManagerEntities);

  List<RegisterDto> result = adminService.getAllEmployeesAndManagers();

  assertEquals(2, result.size());
  RegisterDto VivekDto = result.get(0);
  RegisterDto PrernaDto = result.get(1);

  assertEquals("E0001", VivekDto.getEmpId());
  assertEquals("Vivek", VivekDto.getEmpName());
  assertEquals("Vivek@example.com", VivekDto.getEmpEmail());
  assertEquals("employee", VivekDto.getEmpRole());

  assertEquals("M0001", PrernaDto.getEmpId());
  assertEquals("Prerna", PrernaDto.getEmpName());
  assertEquals("Prerna@example.com", PrernaDto.getEmpEmail());
  assertEquals("manager", PrernaDto.getEmpRole());
 }

 @Test
 public void testGetAllManagersWhenManagersExist() {
  
  List<RegisterEntity> mockManagerEntities = new ArrayList<>();
  RegisterEntity manager1 = new RegisterEntity();
  manager1.setEmpRole("manager");
  mockManagerEntities.add(manager1);

  Mockito.when(registerRepo.findAllByEmpRole("manager"))
   .thenReturn(mockManagerEntities);

  List<ManagerOutDto> managerDtos = adminService.getAllManagers();

  assertFalse(managerDtos.isEmpty());
 }

 @Test
 public void testAddProjectWithValidInput()
  throws WrongInputException, DataAlreadyExistsException {
  RegisterEntity managerEntity = new RegisterEntity();
  managerEntity.setEmpId("M001");
  managerEntity.setEmpName("Manager Name");

  ProjectDto validProjectDto = new ProjectDto();
  validProjectDto.setName("Valid Project Name");
  validProjectDto.setDescription("Valid Project Description");
  validProjectDto.setManagerEmployeeId(1L);
  List<String> skills = new ArrayList<>();
  skills.add("Java");
  validProjectDto.setSkills(skills);

  CommonResponseDto response = adminService.addProject(validProjectDto);

  assertEquals("Project added successfully", response.getMessage());
 }
 


 @Test
 public void testAddProject() throws WrongInputException {
  ProjectDto projectDto = new ProjectDto();
  projectDto.setName("Test Project");
  projectDto.setDescription("Project Description");
  projectDto.setManagerEmployeeId(1L);

  when(projectRepository.save(any(ProjectEntity.class)))
   .thenAnswer(invocation -> {
    ProjectEntity savedProject = invocation.getArgument(0);
    savedProject.setProjectId(1L);
    return savedProject;
   });

  CommonResponseDto response = adminService.addProject(projectDto);

  verify(projectRepository, times(1)).save(any(ProjectEntity.class));

  assertEquals("Project added successfully", response.getMessage());
 }

 @Test
 public void testGetAllProjects() throws DataNotFoundException {
  List<ProjectEntity> projectEntities = new ArrayList<>();
  ProjectEntity project1 = new ProjectEntity();
  project1.setProjectId(1L);
  project1.setName("Project 1");
  project1.setDescription("Description 1");
  project1.setManagerEmployeeId(101L);
  project1.setSkills(Arrays.asList("Java", "Spring Boot"));
  projectEntities.add(project1);

  ProjectEntity project2 = new ProjectEntity();
  project2.setProjectId(2L);
  project2.setName("Project 2");
  project2.setDescription("Description 2");
  project2.setManagerEmployeeId(102L);
  project2.setSkills(Arrays.asList("React", "JavaScript"));
  projectEntities.add(project2);

  Mockito.when(projectRepository.findAll()).thenReturn(projectEntities);

  List<ProjectDto> projectDtos = adminService.getAllProjects();

  assertEquals(2, projectDtos.size());
  assertEquals("Project 1", projectDtos.get(0).getName());
  assertEquals("Description 1", projectDtos.get(0).getDescription());
  assertEquals(101L, projectDtos.get(0).getManagerEmployeeId());
  assertEquals(Arrays.asList("Java", "Spring Boot"),
   projectDtos.get(0).getSkills());

  assertEquals("Project 2", projectDtos.get(1).getName());
  assertEquals("Description 2", projectDtos.get(1).getDescription());
  assertEquals(102L, projectDtos.get(1).getManagerEmployeeId());
  assertEquals(Arrays.asList("React", "JavaScript"),
   projectDtos.get(1).getSkills());
 }

 @Test
 void testGetAllManagersInfo() {
  List<RegisterEntity> managerEntities = new ArrayList<>();
  RegisterEntity manager1 = new RegisterEntity();
  manager1.setId(1L);
  manager1.setEmpName("Manager 1");
  manager1.setEmpId("M101");
  managerEntities.add(manager1);

  RegisterEntity manager2 = new RegisterEntity();
  manager2.setId(2L);
  manager2.setEmpName("Manager 2");
  manager2.setEmpId("M102");
  managerEntities.add(manager2);
  when(registerRepo.findAllByEmpRole("manager"))
   .thenReturn(managerEntities);

  List<ManagerInfoDto> managerInfoList = adminService.getAllManagersInfo();

  assertNotNull(managerInfoList);
  assertFalse(managerInfoList.isEmpty());
  assertEquals(2, managerInfoList.size());

  assertEquals("Manager 1", managerInfoList.get(0).getManagerName());
  assertEquals("M101", managerInfoList.get(0).getManagerEmployeeId());
  assertEquals(1L, managerInfoList.get(0).getId());

  assertEquals("Manager 2", managerInfoList.get(1).getManagerName());
  assertEquals("M102", managerInfoList.get(1).getManagerEmployeeId());
  assertEquals(2L, managerInfoList.get(1).getId());
 }

 @Test
 void testAssignProjectSuccess() throws WrongInputException {
  RegisterEntity employee = new RegisterEntity();
  employee.setEmpId("E001");
  when(registerRepo.findByEmpId("E001")).thenReturn(Optional.of(employee));

  ProjectEntity project = new ProjectEntity();
  project.setProjectId(1L);
  project.setManagerEmployeeId(2L);
  when(projectRepository.findByProjectId(1L)).thenReturn(project);

  AssignProjectDto assignProjectDto = new AssignProjectDto();
  assignProjectDto.setEmpId("E001");
  assignProjectDto.setProjectId(1L);
  
  CommonResponseDto response = adminService.assignProject(assignProjectDto);

  assertNotNull(response);
  assertEquals("Project assigned successfully", response.getMessage());
  assertEquals(2L, employee.getManagerId());
  assertEquals(1L, employee.getProjectId());
 }

 @Test
    void testAssignProjectEmployeeNotFound() {
        
        when(registerRepo.findByEmpId("E001")).thenReturn(Optional.empty());

        AssignProjectDto assignProjectDto = new AssignProjectDto();
        assignProjectDto.setEmpId("E001");
        assignProjectDto.setProjectId(1L);

        assertThrows(NullPointerException.class, () -> adminService.assignProject(assignProjectDto));
    }

 @Test
 void testAssignProjectProjectNotFound() {
  RegisterEntity employee = new RegisterEntity();
  employee.setEmpId("E001");
  when(registerRepo.findByEmpId("E001")).thenReturn(Optional.of(employee));

  when(projectRepository.findByProjectId(1L)).thenReturn(null);

  AssignProjectDto assignProjectDto = new AssignProjectDto();
  assignProjectDto.setEmpId("E001");
  assignProjectDto.setProjectId(1L);

  assertThrows(NullPointerException.class,
   () -> adminService.assignProject(assignProjectDto));
 }

 @Test
 void testGetAllByManagerId() {
  
  List<ProjectEntity> projectList = new ArrayList<>();
  ProjectEntity project1 = new ProjectEntity();
  project1.setProjectId(1L);
  project1.setName("Project 1");
  project1.setManagerEmployeeId(101L);
  projectList.add(project1);

  ProjectEntity project2 = new ProjectEntity();
  project2.setProjectId(2L);
  project2.setName("Project 2");
  project2.setManagerEmployeeId(101L);
  projectList.add(project2);

  when(projectRepository.findAllByManagerEmployeeId(101L))
   .thenReturn(projectList);

  List<RegisterEntity> teamMembers1 = new ArrayList<>();
  RegisterEntity teamMember1 = new RegisterEntity();
  teamMember1.setEmpName("Team Member 1");
  teamMembers1.add(teamMember1);

  List<RegisterEntity> teamMembers2 = new ArrayList<>();
  RegisterEntity teamMember2 = new RegisterEntity();
  teamMember2.setEmpName("Team Member 2");
  teamMembers2.add(teamMember2);

  when(registerRepo.findAllByProjectId(1L)).thenReturn(teamMembers1);
  when(registerRepo.findAllByProjectId(2L)).thenReturn(teamMembers2);

  List<ProjectOutDto> projectOutList = adminService.getAllByManagerId(101L);

  assertNotNull(projectOutList);
  assertEquals(2, projectOutList.size());

  ProjectOutDto projectOutDto1 = projectOutList.get(0);
  assertEquals(1L, projectOutDto1.getId());
  assertEquals("Project 1", projectOutDto1.getProjectName());
  assertEquals("101", projectOutDto1.getManagerId());
 }

 @Test
    void testGetAllByManagerIdNoProjectsFound() {
        when(projectRepository.findAllByManagerEmployeeId(101L)).thenReturn(new ArrayList<>());
        List<ProjectOutDto> projectOutList = adminService.getAllByManagerId(101L);

        assertNotNull(projectOutList);
        assertTrue(projectOutList.isEmpty());
    }
 
 @Test
 public void testGetAllProjectsForAssign() {
  
     List<ProjectEntity> projectsFound = new ArrayList<>();
     ProjectEntity projectA = new ProjectEntity();
     projectA.setProjectId(1L);
     projectA.setName("Project A");

     ProjectEntity projectB = new ProjectEntity();
     projectB.setProjectId(2L);
     projectB.setName("Project B");

     projectsFound.add(projectA);
     projectsFound.add(projectB);;

     when(projectRepository.findAll()).thenReturn(projectsFound);

     List<AssignProjectOutDto> result1 = adminService.getAllProjectsForAssign();

     assertEquals(2, result1.size());
     assertEquals(1L, result1.get(0).getProjectId());
     assertEquals("Project A", result1.get(0).getName());
     assertEquals(2L, result1.get(1).getProjectId());
     assertEquals("Project B", result1.get(1).getName());
     
     List<ProjectEntity> noProjectsFound = new ArrayList<>();

     when(projectRepository.findAll()).thenReturn(noProjectsFound);

     List<AssignProjectOutDto> result2 = adminService.getAllProjectsForAssign();

     assertNotNull(result2);
     assertTrue(result2.isEmpty());
 }
 
 @Test
 public void testUnassignProject() {

     RegisterEntity employee = new RegisterEntity();
     employee.setEmpId("N1234");
     employee.setProjectId(1L); 
     employee.setManagerId(null);

     RegisterEntity admin = new RegisterEntity();
     admin.setEmpId("N0001");
     admin.setId(2L);

     when(registerRepo.findByEmpId("N1234")).thenReturn(Optional.of(employee));
     when(registerRepo.findByEmpEmail("ankita.sharma@nucleusteq.com")).thenReturn(Optional.of(admin));

     adminService.unassignProject("N1234");

     assertNull(employee.getProjectId());
     assertEquals(2L, employee.getManagerId());

     verify(registerRepo).save(employee);
 }

}
