package com.backend.employee.service;

import java.util.List;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.ManagerDto;
import com.backend.employee.dto.ManagerInfoDto;
import com.backend.employee.dto.ProjectDto;
import com.backend.employee.dto.ProjectOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.dto.ResponseDto;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;

public interface AdminService {
    CommonResponseDto addEmployee(RegisterDto registerDto) throws WrongInputException, DataAlreadyExistsException;
    
    
    List<RegisterDto> getAllEmployees() throws DataNotFoundException;
    
    List<ManagerDto> getAllManagers() throws DataNotFoundException;
    List<ManagerInfoDto> getAllManagersInfo() throws DataNotFoundException;
    
    // testing code 
    // Add a new method to add a project
    CommonResponseDto addProject(ProjectDto projectDto) throws WrongInputException;
    
    // Add a new method to get all projects
    ResponseDto<ProjectDto> getAllProjects();
    
    List<ProjectOutDto> getAllByManagerId(Long managerId);
}

