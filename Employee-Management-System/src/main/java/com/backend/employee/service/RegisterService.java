 
package com.backend.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.LoginOutDto;
import com.backend.employee.dto.RegisterDto;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.exception.DataNotFoundException;
import com.backend.employee.exception.WrongInputException;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.validations.InputFieldChecks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Service class that directly contains the implementation of service methods.
 */
@Service
@Getter
@Setter
public class RegisterService {
    @Autowired
    private RegisterRepo registerRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private PasswordEncoder passwordEncoder1;

    @Autowired
    private InputFieldChecks inputFieldChecks;

    private static final int REGISTRATIONS = 50;

    
        // Your implementation for adding an admin user here...
    	
    	public ResponseEntity<String> addAdmin(final RegisterDto registerDto) {

    	    

    	    /**
    	     * Check if the provided date is valid.
    	     */
    	    if (!inputFieldChecks.checkDate(registerDto.getEmpDOB())) {
    	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    	          .body("Invalid date of birth");
    	    }
    	    
    	    /**
    	     * Check if the provided employee ID is valid.
    	     */
    	    if (!inputFieldChecks.checkEmpId(registerDto.getEmpId())) {
    	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    	          .body("Invalid employee ID");
    	    }
    	    
    	    if (registerDto.getEmpEmail().equals("ankita.sharma@nucleusteq.com")) {
    	      registerDto.setEmpRole("admin"); // Set the role to "admin"
    	    }

    	    /**
    	     * Check if the provided email is valid.
    	     */
    	    if (!inputFieldChecks.checkEmpEmail(registerDto.getEmpEmail())) {
    	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    	          .body("Invalid email");
    	    }
    	    /**
    	     * setting the role as admin.
    	     */
    	    if (registerDto.getEmpEmail().equals("ankita.sharma@nucleusteq.com")) {
    	      registerDto.setEmpRole("admin");
    	    }

    	    /**
    	     * Check if the provided contact number is valid.
    	     */
    	    if (!inputFieldChecks.checkEmpContactNo(registerDto.getEmpContactNo())) {
    	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    	          .body("Invalid contact number");
    	    }

    	    /**
    	     * Check if the provided password is valid.
    	     */
    	    if (!inputFieldChecks.checkEmpPassword(registerDto.getEmpPassword())) {
    	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    	          .body("Invalid password");
    	    }

    	    // Check if the provided name is valid
    	    if (!inputFieldChecks.checkValidName(registerDto.getEmpName())) {
    	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid name");
    	    }
    	    /**
    	     * Check if the email is already registered.
    	     */
    	    Optional<RegisterEntity> existingUser = registerRepo
    	        .findByEmpEmail(registerDto.getEmpEmail());

    	    if (existingUser.isPresent()) {
    	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    	          .body("Email already registered");
    	    }

    	    /**
    	     * Check if the contact number is already registered.
    	     */
    	    Optional<RegisterEntity> existingContactNoUser = registerRepo
    	        .findByEmpContactNo(registerDto.getEmpContactNo());
    	    if (existingContactNoUser.isPresent()) {
    	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    	          .body("Contact number already registered");
    	    }

    	    /**
    	     * Check if the employee ID is already registered.
    	     */
    	    Optional<RegisterEntity> existingEmpIdUser = registerRepo
    	        .findByEmpId(registerDto.getEmpId());
    	    if (existingEmpIdUser.isPresent()) {
    	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    	          .body("Employee ID already registered");
    	    }
    	    
    	    String password = registerDto.getEmpPassword();
    	    
    	    /**
    	     * Create a new RegisterEntity instance.
    	     */
    	    RegisterEntity registerEntity = new RegisterEntity();

    	    /**
    	     * Fetch all registered entities.
    	     */
    	    List<RegisterEntity> allRegisterEntity = registerRepo.findAll();

    	    /**
    	     * Limit the number of registered entities to 5
    	     */
    	    if (allRegisterEntity.size() <= REGISTRATIONS) {
    	      /**
    	       * setting the empId
    	       */
    	      registerEntity.setEmpId(registerDto.getEmpId());
    	      /**
    	       * setting the empName
    	       */
    	      registerEntity.setEmpName(registerDto.getEmpName());
    	      /**
    	       * setting the empDob
    	       */
    	      registerEntity.setEmpDOB(registerDto.getEmpDOB());
    	      /**
    	       * setting the empDOJ
    	       */
    	      registerEntity.setEmpDOJ(registerDto.getEmpDOJ());
    	      /**
    	       * setting the empEmail
    	       */
    	      registerEntity.setEmpEmail(registerDto.getEmpEmail());
    	      /**
    	       * setting the empLocation
    	       */
    	      registerEntity.setEmpLocation(registerDto.getEmpLocation());
    	      /**
    	       * setting the empDesignation
    	       */
    	      registerEntity.setEmpDesignation(registerDto.getEmpDesignation());
    	      /**
    	       * setting the empContactNo
    	       */
    	      registerEntity.setEmpContactNo(registerDto.getEmpContactNo());

    	      /**
    	       * Encrypt and set the password
    	       */
//    	      String encryptedPassword = passwordEncoder
//    	          .encode(registerDto.getEmpPassword());
    	      /**
    	       * set password
    	       */
    	      registerEntity.setEmpPassword(password);
    	      /**
    	       * setting the role
    	       */
    	      registerEntity.setEmpRole(registerDto.getEmpRole());

    	      /**
    	       * Save the entity
    	       */
    	      registerRepo.save(registerEntity);
    	      /**
    	       * returning the response entity
    	       */
    	      return ResponseEntity.status(HttpStatus.OK)
    	          .body("Admin added successfully");
    	    } else {
    	      /**
    	       * returning the response entity
    	       */
    	      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    	          .body("Admin not added. ");
    	    }
    	  }
    

//    public LoginOutDto authenticate(final LoginDto loginDto) throws WrongInputException, DataNotFoundException {
//        String email = loginDto.getEmpEmail();
//        String password = loginDto.getEmpPassword();
//        Optional<RegisterEntity> optionalUser = registerRepo.findByEmpEmail(email);
//
//        if (optionalUser.isPresent()) {
//            RegisterEntity user = optionalUser.get();
//            String storedEncryptedPassword = user.getEmpPassword();
//
//            if (passwordEncoder.matches(password, storedEncryptedPassword)) {
//                LoginOutDto response = new LoginOutDto();
//                response.setEmpRole(user.getEmpRole()); 
//                response.setMessage("Login successful");
//                return response;
//            } else {
//                
//            	throw new WrongInputException("Invalid Password"); 
//            }
//        }
//
//        throw new DataNotFoundException("User Not Found");
//        
//    }
    	
    	public LoginOutDto authenticate(final LoginDto loginDto) throws WrongInputException, DataNotFoundException{
    		
    		
    		
    		inputFieldChecks.loginValidation(loginDto.getEmpEmail(),loginDto.getEmpPassword());
    		
    		Optional<RegisterEntity> employeeOptional = registerRepo.findByEmpEmail(loginDto.getEmpEmail());
    		
    		if (employeeOptional.isPresent()) {
    	        RegisterEntity employee = employeeOptional.get();

    	        LoginOutDto response = new LoginOutDto();
    	        response.setEmpRole(employee.getEmpRole());
    	        response.setMessage("Login successful");
    	        return response;
    	    } else {
    	        // User not found
    	        throw new DataNotFoundException("User Not Found");
    	    }
    			
    		
    		
    		
    		
    	}
    	
    	
    	
}
