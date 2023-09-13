 

package com.backend.employee.service;


import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.ManagerDto;
import com.backend.employee.dto.ManagerInfoDto;
import com.backend.employee.dto.ProjectDto;
import com.backend.employee.dto.ProjectOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.dto.ResponseDto;
import com.backend.employee.entity.ProjectEntity;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataAlreadyExistsException;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.ProjectRepo;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.validations.InputFieldsChecksUpdated;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AdminService {

    @Autowired
    private RegisterRepo registerRepository;

    @Autowired
    private ProjectRepo projectRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private InputFieldsChecksUpdated inputFieldChecksUpdated;

    private static final int REGISTRATIONS = 0;
    
    /**
	 * Adds a new employee based on the provided RegisterDto.
	 *
	 * @param registerDto The RegisterDto containing employee information.
	 * @return A CommonResponseDto indicating the success or failure of the
	 *         operation.
	 * @throws WrongInputException        If the input data is incorrect.
	 * @throws DataAlreadyExistsException If the employee data already exists.
	 */
    public CommonResponseDto addEmployee(RegisterDto registerDto)
			throws WrongInputException, DataAlreadyExistsException {

		/*
		 * Check input fields for correctness
		 */
		inputFieldChecksUpdated.checkEmpId(registerDto.getEmpId());
		inputFieldChecksUpdated.checkEmpName(registerDto.getEmpName());
		inputFieldChecksUpdated.checkDob(registerDto.getEmpDOB());
		inputFieldChecksUpdated.checkDoj(registerDto.getEmpDOB());
		inputFieldChecksUpdated.checkEmpEmail(registerDto.getEmpEmail());
		inputFieldChecksUpdated
				.checkEmpContactNo(registerDto.getEmpContactNo());
		inputFieldChecksUpdated.checkEmpPassword(registerDto.getEmpPassword());
		inputFieldChecksUpdated.checkEmailExistence(registerDto.getEmpEmail());
		inputFieldChecksUpdated.checkEmpIdExistence(registerDto.getEmpId());
		inputFieldChecksUpdated
				.checkEmpContactExistence(registerDto.getEmpContactNo());

		/*
		 * Create a new RegisterEntity from the RegisterDto
		 */
		RegisterEntity registerEntity = new RegisterEntity(registerDto);

		/*
		 * Set employee skills
		 */
		registerEntity.setEmpSkills(registerDto.getEmpSkills());

		/*
		 * Encrypt the employee password
		 */
		String encryptedPassword = passwordEncoder
				.encode(registerDto.getEmpPassword());
		registerEntity.setEmpPassword(encryptedPassword);

		/*
		 * Determine and set the manager for the employee based on their role
		 */
		if ("employee".equals(registerDto.getEmpRole())) {
			RegisterEntity managerEntity = registerRepository
					.findByEmpRole("admin");
			if (managerEntity != null) {
				registerEntity.setManagerId(managerEntity.getId());
			} else {
				registerEntity.setManagerId(null);
			}
		} else {
			registerEntity.setManagerId(null);
		}

		/*
		 * Save the new employee entity to the repository
		 */
		registerRepository.save(registerEntity);

		return new CommonResponseDto("Employee added successfully");
	}

    /**
	 * Retrieves a list of all employees.
	 *
	 * @return A list of RegisterDto objects representing employees.
	 * @throws DataNotFoundException If no employee data is found.
	 */
    public List<RegisterDto> getAllEmployees() {
		/*
		 * Retrieve all employee entities
		 */
		List<RegisterEntity> managerEntities = registerRepository
				.findAllByEmpRole("employee");

		if (!managerEntities.isEmpty()) {
			/*
			 * Convert employee entities to RegisterDto objects
			 */
			List<RegisterDto> managerDtos = convertToRegisterDtoList(
					managerEntities);
			return managerDtos;
		} else {
			throw new DataNotFoundException("No employees found");
		}
	}

	/**
	 * Converts a list of RegisterEntity objects to a list of RegisterDto
	 * objects.
	 *
	 * @param entities The list of RegisterEntity objects to convert.
	 * @return A list of RegisterDto objects.
	 */
	private List<RegisterDto> convertToRegisterDtoList(
			List<RegisterEntity> entities) {
		List<RegisterDto> dtos = new ArrayList<>();
		for (RegisterEntity entity : entities) {
			RegisterDto dto = new RegisterDto();
			dto.setEmpId(entity.getEmpId());
			dto.setEmpName(entity.getEmpName());
			dto.setEmpContactNo(entity.getEmpContactNo());
			dto.setEmpDesignation(entity.getEmpDesignation());
			dto.setEmpDOB(entity.getEmpDOB());
			dto.setEmpDOJ(entity.getEmpDOJ());
			dto.setEmpLocation(entity.getEmpLocation());
			dto.setEmpRole(entity.getEmpRole());
			dto.setEmpEmail(entity.getEmpEmail());
			dto.setEmpSkills(entity.getEmpSkills());
			dto.setProjectId(entity.getProjectId());

			if (entity.getManagerId() != null) {
				Optional<RegisterEntity> managerEntityOptional = registerRepository
						.findById(entity.getManagerId());
				if (managerEntityOptional.isPresent()) {
					RegisterEntity managerEntity = managerEntityOptional.get();
					dto.setManagerName(managerEntity.getEmpName());
				}
			}
			dtos.add(dto);
		}
		return dtos;
	}
	/**
	 * Retrieves a list of all managers.
	 *
	 * @return A list of ManagerDto objects representing managers.
	 * @throws DataNotFoundException If no manager data is found.
	 */
	public List<ManagerDto> getAllManagers() {
		/*
		 * Retrieve all manager entities
		 */
		List<RegisterEntity> managerEntities = registerRepository
				.findAllByEmpRole("manager");

		if (!managerEntities.isEmpty()) {
			/*
			 * Convert manager entities to ManagerDto objects
			 */
			List<ManagerDto> managerDtos = convertToManagerDtoList(
					managerEntities);
			return managerDtos;
		} else {
			throw new DataNotFoundException("No managers found");
		}
	}

	/**
	 * Converts a list of RegisterEntity objects to a list of ManagerDto
	 * objects.
	 *
	 * @param entities The list of RegisterEntity objects to convert.
	 * @return A list of ManagerDto objects.
	 */
	private List<ManagerDto> convertToManagerDtoList(
			List<RegisterEntity> entities) {
		List<ManagerDto> dtos = new ArrayList<>();
		for (RegisterEntity entity : entities) {
			ManagerDto dto = new ManagerDto();
			// Map entity fields to dto fields
			dto.setId(entity.getId());
			dto.setEmpId(entity.getEmpId());
			dto.setEmpName(entity.getEmpName());
			dto.setEmpContactNo(entity.getEmpContactNo());
			dto.setEmpDesignation(entity.getEmpDesignation());
			dto.setEmpEmail(entity.getEmpEmail());
			dto.setEmpLocation(entity.getEmpLocation());
			dtos.add(dto);
		}
		return dtos;
	}
	/**
	 * Retrieves a list of manager information.
	 *
	 * @return A list of ManagerInfoDto objects representing manager
	 *         information.
	 * @throws DataNotFoundException If no manager information data is found.
	 */
	public List<ManagerInfoDto> getAllManagersInfo() {
		/*
		 * Retrieve all manager entities
		 */
		List<RegisterEntity> managerEntities = registerRepository
				.findAllByEmpRole("manager");

		if (managerEntities != null) {
			/*
			 * Convert manager entities to ManagerInfoDto objects
			 */
			List<ManagerInfoDto> managerInfoList = managerEntities.stream()
					.map(this::convertToManagerInfoDto)
					.collect(Collectors.toList());

			return managerInfoList;
		} else {
			throw new DataNotFoundException("Manager information not found");
		}
	}

	/**
	 * Converts a RegisterEntity object to a ManagerInfoDto object.
	 *
	 * @param entity The RegisterEntity object to convert.
	 * @return A ManagerInfoDto object representing manager information.
	 */
	private ManagerInfoDto convertToManagerInfoDto(RegisterEntity entity) {
		ManagerInfoDto managerInfoDto = new ManagerInfoDto();
		managerInfoDto.setManagerName(entity.getEmpName());
		managerInfoDto.setManagerEmployeeId(entity.getEmpId());
		managerInfoDto.setId(entity.getId());
		return managerInfoDto;
	}
	
	/**
	 * Adds a new project based on the provided ProjectDto.
	 *
	 * @param projectDto The ProjectDto containing project information.
	 * @return A CommonResponseDto indicating the success or failure of the
	 *         operation.
	 * @throws WrongInputException        If the input data is incorrect.
	 * @throws DataAlreadyExistsException If a project with the same name
	 *                                    already exists.
	 */
	public CommonResponseDto addProject(ProjectDto projectDto)
			throws WrongInputException {

		/*
		 * Check if at least one skill is selected
		 */
		List<String> selectedSkills = projectDto.getSkills();
		if (selectedSkills == null || selectedSkills.isEmpty()) {
			throw new WrongInputException("At least one skill is required");
		}

		/*
		 * Check the project name for validity
		 */
		String projectName = projectDto.getName();
		if (projectName == null || projectName.isEmpty()
				|| projectName.matches(".*\\d.*")) {
			throw new WrongInputException(
					"Project name is invalid. It should not contain digits and should not be empty");
		}

		/*
		 * Check if project description is provided
		 */
		String description = projectDto.getDescription();
		if (description == null || description.isEmpty()) {
			throw new WrongInputException("Project description is required");
		}

		/*
		 * Check if a project with the same name already exists
		 */
		ProjectEntity existingProject = projectRepository
				.findByName(projectDto.getName());
		if (existingProject != null) {
			throw new DataAlreadyExistsException(
					"Project with the same name already exists");
		}

		/*
		 * Retrieve the manager entity by ID
		 */
		Optional<RegisterEntity> managerEntity = registerRepository
				.findById(projectDto.getManagerEmployeeId());
		if (managerEntity.isEmpty()) {
			throw new WrongInputException("Manager not found");
		}

		/*
		 * Create a ProjectEntity from the ProjectDto
		 */
		ProjectEntity projectEntity = new ProjectEntity(projectDto);

		/*
		 * Save the project to the database
		 */
		projectRepository.save(projectEntity);

		/*
		 * Return a success response
		 */
		return new CommonResponseDto("Project added successfully");
	}

	/**
	 * Retrieves a list of all projects.
	 *
	 * @return A ResponseDto containing a list of ProjectDto objects
	 *         representing projects.
	 */
//	public ResponseDto<ProjectDto> getAllProjects() {
//		/*
//		 * Retrieve all project entities
//		 */
//		List<ProjectEntity> projects = projectRepository.findAll();
//
//		/*
//		 * Convert project entities to a list of ProjectDto objects
//		 */
//		List<ProjectDto> projectDtos = convertToProjectDtoList(projects);
//
//		/*
//		 * Create and return a ResponseDto with the list of projects
//		 */
//		return new ResponseDto<>("Projects retrieved successfully", 200,
//				projectDtos);
//	}
	
	public List<ProjectDto> getAllProjects() throws DataNotFoundException {
	    /*
	     * Retrieve all project entities
	     */
	    List<ProjectEntity> projects = projectRepository.findAll();

	    /*
	     * Check if projects list is empty and throw DataNotFoundException if it is.
	     */
	    if (projects.isEmpty()) {
	        throw new DataNotFoundException("No projects found");
	    }

	    /*
	     * Convert project entities to a list of ProjectDto objects
	     */
	    List<ProjectDto> projectDtos = convertToProjectDtoList(projects);

	    return projectDtos;
	}


	/**
	 * Converts a list of ProjectEntity objects to a list of ProjectDto objects.
	 *
	 * @param entities The list of ProjectEntity objects to convert.
	 * @return A list of ProjectDto objects.
	 */
	private List<ProjectDto> convertToProjectDtoList(
			List<ProjectEntity> entities) {
		List<ProjectDto> dtos = new ArrayList<>();
		for (ProjectEntity entity : entities) {
			ProjectDto dto = new ProjectDto();

			/*
			 * Map entity fields to dto fields
			 */
			dto.setProjectId(entity.getProjectId());
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());
			dto.setStartDate(entity.getStartDate());
			dto.setManagerEmployeeId(entity.getManagerEmployeeId());
			dto.setSkills(entity.getSkills());

			/*
			 * optional
			 */
			Optional<RegisterEntity> headEntity = registerRepository
					.findById(entity.getManagerEmployeeId());
			if (headEntity.isPresent()) {
				dto.setHead(headEntity.get().getEmpName());
			}

			dtos.add(dto);
		}
		return dtos;
	}

	/**
	 * Retrieves a list of projects managed by a manager with the given manager
	 * ID.
	 *
	 * @param managerId The ID of the manager whose projects are to be
	 *                  retrieved.
	 * @return A list of ProjectOutDto objects representing the projects managed
	 *         by the manager.
	 */
	public List<ProjectOutDto> getAllByManagerId(Long managerId) {
		/*
		 * Retrieve a list of project entities managed by the manager
		 */
		List<ProjectEntity> projectList = projectRepository
				.findAllByManagerEmployeeId(managerId);

		/*
		 * Create a list to store the output DTOs
		 */
		List<ProjectOutDto> projectOutList = new ArrayList<ProjectOutDto>();

		for (ProjectEntity project : projectList) {
			ProjectOutDto projectOutDto = new ProjectOutDto();

			/*
			 * Map entity fields to DTO fields
			 */
			projectOutDto.setId(project.getProjectId());
			projectOutDto.setProjectName(project.getName());
			projectOutDto.setManagerId(project.getManagerEmployeeId() + "");
			projectOutDto.setSkillsRequired(project.getSkills());
			projectOutDto.setDescription(project.getDescription());
			projectOutDto.setStartDate(project.getStartDate());

			projectOutList.add(projectOutDto);
		}

		return projectOutList;
	}
}

