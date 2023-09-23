package com.backend.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.dto.UpdateSkillsDto;
import com.backend.employee.entity.ProjectEntity;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.ProjectRepo;
import com.backend.employee.repo.RegisterRepo;

/**
 * Service class.
 */
@Service
public class EmployeeService {
 /**
  * Project repo.
  */
 @Autowired
 private ProjectRepo projectRepo;
 /**
  * Register repo.
  */
 @Autowired
 private RegisterRepo registerRepo;

 /**
  * Method for getting user details.
  *
  * @param email email.
  * @return RegisterDto.
  * @throws WrongInputException WrongInputException.
  */
 public RegisterDto getEmployee(final String email)
  throws WrongInputException {

  RegisterEntity employee = registerRepo.findByEmpEmail(email).orElse(null);

  RegisterDto resultDto = new RegisterDto();

  resultDto.setEmpId(employee.getEmpId());
  resultDto.setEmpName(employee.getEmpName());
  resultDto.setEmpEmail(employee.getEmpEmail());
  resultDto.setEmpContactNo(employee.getEmpContactNo());
  resultDto.setEmpDOB(employee.getEmpDOB());
  resultDto.setEmpDOJ(employee.getEmpDOJ());
  resultDto.setEmpLocation(employee.getEmpLocation());
  resultDto.setEmpSkills(employee.getEmpSkills());

  if (employee.getProjectId() == null) {
   resultDto.setProjectName("N/A");
  } else {

   ProjectEntity project = projectRepo
    .findByProjectId(employee.getProjectId());
   if (project == null) {
    throw new WrongInputException("Project does not exist");
   }
   resultDto.setProjectName(project.getName());
  }
  RegisterEntity manager = registerRepo.findById(employee.getManagerId())
   .orElse(null);
  if (manager == null) {
   throw new WrongInputException("Manager does not exists");
  }
  resultDto.setManagerName(manager.getEmpName());

  return resultDto;
 }

 /**
  * Method for updating the user skills.
  *
  * @param updateSkillsDto updateSkillsDto.
  * @return CommonResponseDto.
  * @throws DataNotFoundException DataNotFoundException.
  */
 public CommonResponseDto updateSkills(
  final UpdateSkillsDto updateSkillsDto)
  throws DataNotFoundException, WrongInputException {

  String userEmail = updateSkillsDto.getEmpEmail();
  RegisterEntity employee = registerRepo.findByEmpEmail(userEmail)
   .orElse(null);

  employee.setEmpSkills(updateSkillsDto.getEmpSkills());
  registerRepo.save(employee);
  return new CommonResponseDto("Updated Skills Successfully");

 }

}
