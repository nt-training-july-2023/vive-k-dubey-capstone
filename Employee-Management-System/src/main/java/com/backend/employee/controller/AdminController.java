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

/**
 * The controller class for managing employee-related operations.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employee")
public class AdminController {

	private final AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * Endpoint to add an employee.
	 *
	 * @param registerDto The employee information to add.
	 * @return A response indicating the success or failure of the operation.
	 * @throws DataAlreadyExistsException If the employee data already exists.
	 * @throws WrongInputException        If the input data is incorrect.
	 */
	@PostMapping("/add")
	public CommonResponseDto addEmployee(@RequestBody RegisterDto registerDto)
			throws DataAlreadyExistsException, WrongInputException {
		return adminService.addEmployee(registerDto);
	}

	/**
	 * Endpoint to retrieve all employees.
	 *
	 * @return A list of employee data.
	 * @throws DataNotFoundException If no employee data is found.
	 */
	@GetMapping("/getAllEmployees")
	public List<RegisterDto> getAllEmployees() throws DataNotFoundException {
		return adminService.getAllEmployees();
	}

	/**
	 * Endpoint to retrieve all managers.
	 *
	 * @return A list of manager data.
	 * @throws DataNotFoundException If no manager data is found.
	 */
	@GetMapping("/getAllManagers")
	public List<ManagerDto> getAllManagers() throws DataNotFoundException {
		return adminService.getAllManagers();
	}

	/**
	 * Endpoint to retrieve all manager information.
	 *
	 * @return A list of manager information data.
	 * @throws DataNotFoundException If no manager information data is found.
	 */
	@GetMapping("/getAllManagersInfo")
	public List<ManagerInfoDto> getAllManagersInfo() throws DataNotFoundException {
		// Retrieve manager information from your database
		List<ManagerInfoDto> managerInfoList = adminService.getAllManagersInfo();
		return managerInfoList;
	}

	/**
	 * Endpoint to add a project.
	 *
	 * @param projectDto The project information to add.
	 * @return A response indicating the success or failure of the operation.
	 * @throws WrongInputException If the input data is incorrect.
	 */
	@PostMapping("/addProject")
	public CommonResponseDto addProject(@RequestBody ProjectDto projectDto) throws WrongInputException {
		return adminService.addProject(projectDto);
	}

	/**
	 * Endpoint to retrieve all projects.
	 *
	 * @return A response containing a list of project data.
	 */
	@GetMapping("/getAllProjects")
	public List<ProjectDto> getAllProjects() throws DataNotFoundException {
		return adminService.getAllProjects();
	}

	/**
	 * Endpoint to retrieve all projects by manager ID.
	 *
	 * @param managerId The ID of the manager.
	 * @return A list of project data associated with the manager.
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/getAll/project/{managerId}")
	public List<ProjectOutDto> getAllByManagerId(@PathVariable Long managerId) {
		return adminService.getAllByManagerId(managerId);
	}
}
