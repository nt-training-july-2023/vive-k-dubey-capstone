package com.backend.employee.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.dto.UpdateSkillsDto;
import com.backend.employee.entity.ProjectEntity;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.ProjectRepo;
import com.backend.employee.repo.RegisterRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private ProjectRepo projectRepo;

    @Mock
    private RegisterRepo registerRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEmployeeSuccess() throws WrongInputException {
        String userEmail = "employee@example.com";

        RegisterEntity employeeEntity = new RegisterEntity();
        employeeEntity.setEmpId("N0001");
        employeeEntity.setEmpName("Vivek");
        employeeEntity.setEmpEmail(userEmail);
        employeeEntity.setEmpContactNo("1234567890");
        employeeEntity.setEmpDOB("01/01/1990");
        employeeEntity.setEmpDOJ("01/01/2021");
        employeeEntity.setEmpLocation("New Delhi");
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Python");
        employeeEntity.setEmpSkills(skills);
        employeeEntity.setProjectId(1L);
        employeeEntity.setManagerId(2L);

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName("Project A");

        RegisterEntity managerEntity = new RegisterEntity();
        managerEntity.setEmpName("Manager A");

        when(registerRepo.findByEmpEmail(userEmail)).thenReturn(Optional.of(employeeEntity));
        when(projectRepo.findByProjectId(1L)).thenReturn(projectEntity);
        when(registerRepo.findById(2L)).thenReturn(Optional.of(managerEntity));

        RegisterDto resultDto = employeeService.getEmployee(userEmail);

        assertEquals("N0001", resultDto.getEmpId());
        assertEquals("Vivek", resultDto.getEmpName());
        assertEquals(userEmail, resultDto.getEmpEmail());
        assertEquals("1234567890", resultDto.getEmpContactNo());
        assertEquals("01/01/1990", resultDto.getEmpDOB());
        assertEquals("01/01/2021", resultDto.getEmpDOJ());
        assertEquals("New Delhi", resultDto.getEmpLocation());
        assertEquals(skills, resultDto.getEmpSkills());
        assertEquals("Project A", resultDto.getProjectName());
        assertEquals("Manager A", resultDto.getManagerName());
    }

    @Test
    void testGetEmployeeNotFound() {
        String userEmail = "nonexistent@example.com";

        when(registerRepo.findByEmpEmail(userEmail)).thenReturn(Optional.empty());

        assertThrows(WrongInputException.class, () -> employeeService.getEmployee(userEmail));
    }

    @Test
    void testUpdateSkillsSuccess() throws DataNotFoundException, WrongInputException {
        String userEmail = "employee@example.com";
        List<String> newSkills = List.of("Java", "Python", "JavaScript");

        RegisterEntity employeeEntity = new RegisterEntity();
        employeeEntity.setEmpEmail(userEmail);
        employeeEntity.setEmpSkills(new ArrayList<>());

        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();
        updateSkillsDto.setEmpEmail(userEmail);
        updateSkillsDto.setEmpSkills(newSkills);

        when(registerRepo.findByEmpEmail(userEmail)).thenReturn(Optional.of(employeeEntity));
        when(registerRepo.save(employeeEntity)).thenReturn(employeeEntity);

        CommonResponseDto responseDto = employeeService.updateSkills(updateSkillsDto);

        assertEquals("Updated Skills Successfully", responseDto.getMessage());
        assertEquals(newSkills, employeeEntity.getEmpSkills());
    }


    @Test
    void testUpdateSkillsEmployeeNotFound() {
        String userEmail = "nonexistent@example.com";
        List<String> newSkills = List.of("Java", "Python", "JavaScript");

        when(registerRepo.findByEmpEmail(userEmail)).thenReturn(Optional.empty());

        UpdateSkillsDto updateSkillsDto = new UpdateSkillsDto();
        updateSkillsDto.setEmpEmail(userEmail);
        updateSkillsDto.setEmpSkills(newSkills);

        assertThrows(DataNotFoundException.class, () -> employeeService.updateSkills(updateSkillsDto));
    }

}

