
package com.backend.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.LoginOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.UnauthorizedException;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.validations.InputFieldChecks;

/**
 * Service class that directly contains the implementation of service methods.
 */
@Service
public class RegisterService {
 /**
  * Object of the register repo.
  */
 @Autowired
 private RegisterRepo registerRepo;
 /**
  * Object of the below field.
  */
 @Autowired
 private BCryptPasswordEncoder passwordEncoder;
 /**
  * Object of the below field.
  */
 @Autowired
 private PasswordEncoder passwordEncoder1;
 /**
  * Object of the below class.
  */
 @Autowired
 private InputFieldChecks inputFieldChecks;

 /**
  * Add admin method.
  *
  * @param registerDto registerDto.
  * @return CommonResponseDto.
  */
 public CommonResponseDto addAdmin(final RegisterDto registerDto) {

  if (registerDto.getEmpEmail().equals("ankita.sharma@nucleusteq.com")) {
   registerDto.setEmpRole("admin");
  }

  String password = registerDto.getEmpPassword();
  RegisterEntity registerEntity = new RegisterEntity();
  registerEntity.setEmpId(registerDto.getEmpId());
  registerEntity.setEmpName(registerDto.getEmpName());
  registerEntity.setEmpDOB(registerDto.getEmpDOB());
  registerEntity.setEmpDOJ(registerDto.getEmpDOJ());
  registerEntity.setEmpEmail(registerDto.getEmpEmail());
  registerEntity.setEmpLocation(registerDto.getEmpLocation());
  registerEntity.setEmpDesignation(registerDto.getEmpDesignation());
  registerEntity.setEmpContactNo(registerDto.getEmpContactNo());
  registerEntity.setEmpPassword(password);
  registerEntity.setEmpRole(registerDto.getEmpRole());

  registerRepo.save(registerEntity);

  return new CommonResponseDto("Admin added successfully");
 }

 /**
  * Method for checking the authentication so that it can authenticate.
  *
  * @param loginDto loginDto.
  * @return Authentication status.
  * @throws WrongInputException   WrongInputException.
  * @throws DataNotFoundException DataNotFoundException.
  */

 public LoginOutDto authenticate(final LoginDto loginDto)
  throws UnauthorizedException, DataNotFoundException {

  Optional<RegisterEntity> employeeOptional = registerRepo
   .findByEmpEmail(loginDto.getEmpEmail());
   RegisterEntity employee = employeeOptional.get();

   LoginOutDto response = new LoginOutDto();
   response.setEmpRole(employee.getEmpRole());
   response.setMessage("Login successful");
   response.setEmpName(employee.getEmpName());
   return response;

 }

}
