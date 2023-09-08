package com.backend.employee.serviceimpl;

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
import com.backend.employee.service.AdminService;
import com.backend.employee.validations.InputFieldsChecksUpdated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final RegisterRepo registerRepository;
    private final ProjectRepo projectRepository;
    
    @Autowired
    private InputFieldsChecksUpdated inputFieldChecksUpdated;

    @Autowired
    public AdminServiceImpl(RegisterRepo registerRepository, ProjectRepo projectRepository) {
        this.registerRepository = registerRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public CommonResponseDto addEmployee(RegisterDto registerDto)throws WrongInputException, DataAlreadyExistsException {
        
      inputFieldChecksUpdated.checkEmpId(registerDto.getEmpId());
      inputFieldChecksUpdated.checkEmpName(registerDto.getEmpName());
      inputFieldChecksUpdated.checkDob(registerDto.getEmpDOB());
      inputFieldChecksUpdated.checkDoj(registerDto.getEmpDOB());
      inputFieldChecksUpdated.checkEmpEmail(registerDto.getEmpEmail());
      //inputFieldChecksUpdated.checkEmpLocation(registerDto.getEmpLocation());
      //inputFieldChecksUpdated.checkEmpDesignation(employeeDto.getEmpDesignation());
      inputFieldChecksUpdated.checkEmpContactNo(registerDto.getEmpContactNo());
      inputFieldChecksUpdated.checkEmpPassword(registerDto.getEmpPassword());
      inputFieldChecksUpdated.checkEmailExistence(registerDto.getEmpEmail());
      inputFieldChecksUpdated.checkEmpIdExistence(registerDto.getEmpId());
      inputFieldChecksUpdated
              .checkEmpContactExistence(registerDto.getEmpContactNo());
//      inputFieldChecksUpdated.checkValidAdminEmail(registerDto.getEmpEmail());
//      String password = "";
//      if (inputFieldChecksUpdated.isPossiblyHashed(employeeDto.getEmpPassword())) {
//          password = employeeDto.getEmpPassword();
//      } else {
//          password = passwordEncoder.encode(employeeDto.getEmpPassword());
//      }
      
      
      
      // Convert DTO to Entity
        RegisterEntity registerEntity = new RegisterEntity(registerDto);
        
        //String skills = String.join(",", registerDto.getEmpSkills());
        registerEntity.setEmpSkills(registerDto.getEmpSkills());
        
        if ("employee".equals(registerDto.getEmpRole())) {
          
          RegisterEntity managerEntity = registerRepository.findByEmpRole("admin");
          if (managerEntity != null) {
              registerEntity.setManagerId(managerEntity.getId());
          } else {
              
              registerEntity.setManagerId(null);
          }
      } else {
          
          registerEntity.setManagerId(null);
      }

        registerRepository.save(registerEntity);

        return new CommonResponseDto("Employee added successfully");
    }
    
    
    
    
    @Override
    public List<RegisterDto> getAllEmployees() {
        List<RegisterEntity> managerEntities = registerRepository.findAllByEmpRole("employee");
        System.out.println(managerEntities);

        if ( !managerEntities.isEmpty()) {
            List<RegisterDto> managerDtos = convertToRegisterDtoList(managerEntities);
            return managerDtos;
        } else {
          throw new DataNotFoundException("No employees found");
        }
    }
    
 // Implement a method to convert a list of RegisterEntity objects to RegisterDto objects
    private List<RegisterDto> convertToRegisterDtoList(List<RegisterEntity> entities) {
        List<RegisterDto> dtos = new ArrayList<>();
        for (RegisterEntity entity : entities) {
            RegisterDto dto = new RegisterDto();
            // Map entity fields to dto fields
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
              Optional<RegisterEntity> managerEntityOptional = registerRepository.findById(entity.getManagerId());
              if (managerEntityOptional.isPresent()) {
                RegisterEntity managerEntity = managerEntityOptional.get();
                dto.setManagerName(managerEntity.getEmpName());
            }
          }
            dtos.add(dto);
        }
        return dtos;
    }
    
   
    
    @Override
    public List<ManagerDto> getAllManagers() {
        List<RegisterEntity> managerEntities = registerRepository.findAllByEmpRole("manager");
        if (!managerEntities.isEmpty()) {
            List<ManagerDto> managerDtos = convertToManagerDtoList(managerEntities);
            return managerDtos;
        } else {
            throw new DataNotFoundException("No managers found");
        }
    }

 // Implement a method to convert a list of RegisterEntity objects to ManagerDto objects
    private List<ManagerDto> convertToManagerDtoList(List<RegisterEntity> entities) {
        List<ManagerDto> dtos = new ArrayList<>();
        for (RegisterEntity entity : entities) {
            ManagerDto dto = new ManagerDto();
            // Map entity fields to dto fields
            dto.setEmpId(entity.getEmpId());
            dto.setEmpName(entity.getEmpName());
            dto.setEmpContactNo(entity.getEmpContactNo());
            dto.setEmpDesignation(entity.getEmpDesignation());
            dto.setEmpEmail(entity.getEmpEmail());
            dto.setEmpLocation(entity.getEmpLocation());
            //dto.setEmpSkills(entity.getEmpSkills());
            //dto.setManagerName(entity.getManagerName()); // Set manager name
            // Set project names for this manager (you can fetch this from your project table)
            // Example: dto.setProjectNames(projectService.getProjectNamesByManagerId(entity.getEmpId()));
            dtos.add(dto);
        }
        return dtos;
    }
    
    
    public List<ManagerInfoDto> getAllManagersInfo() {
      // Fetch manager information from the database based on some criteria (e.g., empRole)
      List<RegisterEntity> managerEntities = registerRepository.findAllByEmpRole("manager");

      // Convert managerEntities to ManagerInfoDto objects
      if(managerEntities != null) {
      List<ManagerInfoDto> managerInfoList = managerEntities.stream()
              .map(this::convertToManagerInfoDto)
              .collect(Collectors.toList());

      return managerInfoList;}
      else {
        throw new DataNotFoundException("YourEntity not found");
      }
  }

  private ManagerInfoDto convertToManagerInfoDto(RegisterEntity entity) {
      ManagerInfoDto managerInfoDto = new ManagerInfoDto();
      managerInfoDto.setManagerName(entity.getEmpName());
      managerInfoDto.setManagerEmployeeId(entity.getEmpId());
      managerInfoDto.setId(entity.getId());
      return managerInfoDto;
  }
    
    // testing code
    @Override
    public CommonResponseDto addProject(ProjectDto projectDto) throws WrongInputException {
      
      List<String> selectedSkills = projectDto.getSkills();
      if (selectedSkills == null || selectedSkills.isEmpty()) {
          throw new WrongInputException("At least one skill is required");
      }
      
      String projectName = projectDto.getName();
      if (projectName == null || projectName.isEmpty() || projectName.matches(".*\\d.*")) {
          throw new WrongInputException("Project name is invalid. It should not contain digits and should not be empty");
      }
      
      String description = projectDto.getDescription();
      if (description == null || description.isEmpty()) {
          throw new WrongInputException("Project description is required");
      }
      
      
      ProjectEntity existingProject = projectRepository.findByName(projectDto.getName());

      if (existingProject != null) {
          // If a project with the same name exists, throw an exception
          throw new DataAlreadyExistsException("Project with the same name already exists");
      }
        
      Optional<RegisterEntity> managerEntity = registerRepository.findById(projectDto.getManagerEmployeeId());
      System.out.println(managerEntity);
      
      if (managerEntity.isEmpty()) {
          
        throw new WrongInputException("Manager not found");
      }

        // Create a ProjectEntity from the ProjectDto
        ProjectEntity projectEntity = new ProjectEntity(projectDto);

        // Save the project to the database
        projectRepository.save(projectEntity);

        // Return a success response
        return new CommonResponseDto("Project added successfully");
    }
    
    @Override
    public ResponseDto<ProjectDto> getAllProjects() {
        List<ProjectEntity> projects = projectRepository.findAll();
        // Convert projects to a list of ProjectDto objects
        List<ProjectDto> projectDtos = convertToProjectDtoList(projects);
        return new ResponseDto<>("Projects retrieved successfully", 200, projectDtos);
    }
    
 
    private List<ProjectDto> convertToProjectDtoList(List<ProjectEntity> entities) {
        List<ProjectDto> dtos = new ArrayList<>();
        for (ProjectEntity entity : entities) {
            ProjectDto dto = new ProjectDto();
            // Map entity fields to dto fields
            dto.setProjectId(entity.getProjectId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setStartDate(entity.getStartDate());
            dto.setManagerEmployeeId(entity.getManagerEmployeeId());
            dto.setSkills(entity.getSkills());

            Optional<RegisterEntity> headEntity = registerRepository.findById(entity.getManagerEmployeeId());
            if (headEntity.isPresent()) {
                dto.setHead(headEntity.get().getEmpName());
            }
            
            dtos.add(dto);
        }
        return dtos;
    }
    
    @Override
    public List<ProjectOutDto> getAllByManagerId(Long managerId) {
        
        List<ProjectEntity> projectList = projectRepository
                .findAllByManagerEmployeeId(managerId);
        List<ProjectOutDto> projectOutList = new ArrayList<ProjectOutDto>();

        for (ProjectEntity project : projectList) {
            ProjectOutDto projectOutDto = new ProjectOutDto();

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

