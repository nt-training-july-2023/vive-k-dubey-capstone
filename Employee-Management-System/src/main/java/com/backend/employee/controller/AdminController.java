package com.backend.employee.controller;

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
import com.backend.employee.service.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employee")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/add")
    public CommonResponseDto addEmployee(@RequestBody RegisterDto registerDto) throws DataAlreadyExistsException, WrongInputException {
        return adminService.addEmployee(registerDto);
    }
    
     
    @GetMapping("/getAllEmployees")
    public List<RegisterDto> getAllEmployees() throws DataNotFoundException {
        return adminService.getAllEmployees();
    }
    
    
    @GetMapping("/getAllManagers")
    public List<ManagerDto> getAllManagers() throws DataNotFoundException {
        return adminService.getAllManagers();
    }
    
    
    // testing code 
    @GetMapping("/getAllManagersInfo")
    public List<ManagerInfoDto> getAllManagersInfo() throws DataNotFoundException {
        // Retrieve manager information from your database
        List<ManagerInfoDto> managerInfoList = adminService.getAllManagersInfo();
        return managerInfoList;
    }
    
    
 // Add a new endpoint to add a project
    @PostMapping("/addProject")
    public CommonResponseDto addProject(@RequestBody ProjectDto projectDto) throws WrongInputException  {
        return adminService.addProject(projectDto);
    }
    
    // Add a new endpoint to get all projects
    @GetMapping("/getAllProjects")
    public ResponseDto<ProjectDto> getAllProjects() {
        return adminService.getAllProjects();
    }
    
    @GetMapping("/getAll/project/{managerId}")
    public List<ProjectOutDto> getAllByManagerId(@PathVariable Long managerId){
        return adminService.getAllByManagerId(managerId);
        
    }
    
}
